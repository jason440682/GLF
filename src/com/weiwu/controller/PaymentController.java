package com.weiwu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.XMLParser;
import com.weiwu.dao.BillingDao;
import com.weiwu.dao.HousingDao;
import com.weiwu.dao.MyBatisUtil;
import com.weiwu.dao.PaymentDao;
import com.weiwu.entry.Billing;
import com.weiwu.entry.HouseFullInfo;
import com.weiwu.entry.Orders;
import com.weiwu.entry.PaySign;
import com.weiwu.service.WeiXinPayService;
import com.weiwu.service.WeiXinService;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.DateUtil;
import com.weiwu.util.Sha1Sign;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private static String SUCCESS_XML = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
	private static String FAIL_XML = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
	private static Logger logger = Logger.getLogger(PaymentController.class);

	@RequestMapping("/index")
	public ModelAndView inputPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("payment/index");

		List<HouseFullInfo> houseFullList;
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		String userId = ("userA");
		try {
			HousingDao housingDao = session.getMapper(HousingDao.class);
			houseFullList = housingDao.getHouseBillingByUser(userId);
		} finally {
			session.commit();
			session.close();
		}
		mav.addObject("houseFullList", houseFullList);
		return mav;

	}

	@RequestMapping("/detail/{houseId}")
	public ModelAndView renterManage(@PathVariable("houseId") String houseId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("payment/detail");
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		WeiXinService weiXinService = new WeiXinService();
		String ticket = weiXinService.getTicket(request);
		String noncestr = RandomStringGenerator.getRandomStringByLength(16);
		long timestamp = System.currentTimeMillis() / 1000;
		mav.addObject("noncestr", noncestr);
		mav.addObject("timestamp", timestamp);

		// for safe
		HttpSession session = request.getSession();
		session.setAttribute("noncestr", noncestr);

		String url = ApplicationContants.DOMAIN + "payment/detail/" + houseId;
		String signature = Sha1Sign.getSign(noncestr, url, ticket, timestamp);
		mav.addObject("signature", signature);

		try {
			HousingDao housingDao = sqlSession.getMapper(HousingDao.class);
			HouseFullInfo houseFull = housingDao.getHouseFullInfoByHouseId(houseId);

			BillingDao billingDao = sqlSession.getMapper(BillingDao.class);
			List<Billing> billingList = billingDao.getBillingByHouseId(houseId);

			// calculte total amount
			double totalAmount = 0;
			for (int i = 0; i < billingList.size(); i++) {
				totalAmount = totalAmount + billingList.get(i).getAmount();
			}
			mav.addObject("billingDate", DateUtil.getCurYearMonth());
			mav.addObject("totalAmount", totalAmount);
			mav.addObject("billingList", billingList);
			mav.addObject("houseFull", houseFull);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

		return mav;
	}

	@RequestMapping("/submit")
	public void orderSubmit(HttpServletRequest request,HttpServletResponse response) {
		String jsonString = null;
		try{
			String houseId = request.getParameter("houseId");
			
			SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
			SqlSession sqlSession = sqlSessionFactory.openSession();
			BillingDao billingDao = sqlSession.getMapper(BillingDao.class);
			PaymentDao payMentDao = sqlSession.getMapper(PaymentDao.class);
			List<Billing> billingList = billingDao.getBillingByHouseId(houseId);
			
			String order_id = RandomStringGenerator.getRandomStringByLength(16);
			String prepay_id = new WeiXinPayService().getPrepayId(request, order_id,100);
			
			List<Orders> orders= new LinkedList<Orders>();
			Orders order;
			for(int i=0;i<billingList.size();i++){
				order = new Orders();
				order.setBillingId(billingList.get(i).getBillingId());
				order.setOrderId(order_id);
				order.setPrepayId(prepay_id);
				order.setStatus("N");
				orders.add(order);
			}
			payMentDao.addOrder(orders);
			
			sqlSession.commit();			
			
			jsonString = new Gson().toJson(new PaySign("prepay_id=" + prepay_id));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("***payment",e);
		}
		

		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			printWriter = response.getWriter();
			printWriter.print(jsonString);
		} catch (IOException io) {
			logger.error(io.getMessage());

		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}    
	}

	@RequestMapping("/payNotify")
	public void payNotify(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter printWriter = null;
		response.setContentType("text/html;charset=UTF-8");

		StringBuffer xmlStr = new StringBuffer();
		Map payInfoMap;
		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				xmlStr.append(line);
			}
			payInfoMap = XMLParser.getMapFromXML(xmlStr.toString());
			String outTradeNo = (String) payInfoMap.get("out_trade_no");
			String resultCode = (String) payInfoMap.get("result_code");

			printWriter = response.getWriter();

			if (resultCode.equals("SUCCESS")) {
				logger.info(("支付完成 尝试更新支付状态：" + outTradeNo));

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

}
