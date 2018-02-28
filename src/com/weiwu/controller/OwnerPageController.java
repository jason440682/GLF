package com.weiwu.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weiwu.dao.HousingDao;
import com.weiwu.dao.MyBatisUtil;
import com.weiwu.dao.RenterDao;
import com.weiwu.dao.SystemPropertyDao;
import com.weiwu.dao.UserDao;
import com.weiwu.entry.Building;
import com.weiwu.entry.HouseFullInfo;
import com.weiwu.entry.HousingEstate;
import com.weiwu.entry.Renter;
import com.weiwu.entry.RenterFull;
import com.weiwu.entry.RenterPayItem;
import com.weiwu.entry.User;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.MD5;
import com.weiwu.util.NetUtil;

@Controller
@RequestMapping("/owner")
public class OwnerPageController {

	@RequestMapping("/home")
	public ModelAndView inputPage(HttpServletRequest request) {
		ModelAndView mav ;
		List<HouseFullInfo> houseFullList;
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String userId="";
		try {
			
			SystemPropertyDao systemPropertyDao = sqlSession.getMapper(SystemPropertyDao.class);
			String testFlag =systemPropertyDao.getSystemPropertyByCode(ApplicationContants.LOCAL_TEST_FLAG);
			if("Y".equals(testFlag)){
				userId="userC";
			}else{
				HttpSession session = request.getSession();
				String openId = (String) session.getAttribute("openid");
				UserDao userDao = sqlSession.getMapper(UserDao.class);
				User user = userDao.getUserByWeiXin(openId);
				if(user!=null){
					userId = user.getUserId();
				}
			}					
			HousingDao housingEstateDao = sqlSession.getMapper(HousingDao.class);
			houseFullList =housingEstateDao.getHouseFullInfoByUser(userId);
			
			if(houseFullList==null || houseFullList.size()==0){
				mav = new ModelAndView("house/add_step1");
			}else{
				mav = new ModelAndView("owner/index");
			}
			
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		mav.addObject("houseFullList", houseFullList);
		return mav;
	}
	
	@RequestMapping("/renter")
	public ModelAndView renterPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/renter");
		List<RenterFull> renterFull;
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		String userId=("userA");
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			renterFull =housingEstateDao.getRenterFullInfoByUser(userId);
		} finally {
			session.commit();
			session.close();
		}
		mav.addObject("renterFullList", renterFull);
		return mav;
	}


	
	@RequestMapping("/renterManage/{houseId}")
	public ModelAndView renterManage(@PathVariable("houseId") String houseId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/renter_manage");

		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			HousingDao housingDao = session.getMapper(HousingDao.class);
			HouseFullInfo houseFullInfo =housingDao.getHouseFullInfoByHouseId(houseId);
			
			RenterDao  renterDao = session.getMapper(RenterDao.class);
			Renter renter = renterDao.getRenterByHouseId(houseId);
			List<RenterPayItem> renterPayItems = renterDao.getPayItemByRenterId(renter.getRenterId());
			
			mav.addObject("houseFullInfo", houseFullInfo);
			mav.addObject("renter", renter);
			mav.addObject("renterPayItems", renterPayItems);
			
			//租客是否已经代缴
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");      
	        //获取前月的第一天
	        Calendar   cal_1=Calendar.getInstance();
	        cal_1.set(Calendar.DAY_OF_MONTH,1);
	        String startDate = format.format(cal_1.getTime());
	        String isPay=renterDao.isPayByRenter(startDate, renter.getRenterId());			
			
			mav.addObject("isPay", isPay);
			
		} finally {
			session.commit();
			session.close();
		}

		
		return mav;
	}
	
	

	
/*
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/test");
		
		NetUtil netUtil = new NetUtil();
		HashMap<String,String> paramsHashMap = new HashMap<String,String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
		String timestamp = sdf.format(new Date());
		paramsHashMap.put("accountSid", ApplicationContants.ACCOUNT_SID);
		paramsHashMap.put("templateid", ApplicationContants.SMS_TMEPLATE);
		paramsHashMap.put("timestamp",timestamp);
		paramsHashMap.put("param","203721,5");
		paramsHashMap.put("to","18819423308");
		
		String sig = MD5.MD5Encode(ApplicationContants.ACCOUNT_SID+ApplicationContants.AUTH_TOKEN+timestamp);
		paramsHashMap.put("sig",sig);
		JsonObject jsonResult= netUtil.getJSONObjectByPost(paramsHashMap, ApplicationContants.SMS_API, "UTF-8");	
		
		mav.addObject("user", jsonResult.toString());

		return mav;
	}
*/
	
	@RequestMapping("/test2")
	@ResponseBody
	public ResponseEntity<String> test2(HttpServletRequest request) {
		String jsonStr = "{\"respCode\":\"00000\",\"respDesc\":\"?????\",\"failCount\":\"0\",\"failList\":[],\"smsId\":\"8bc6c54222c64e878ca6803fdeb2a296\"}";
		//JsonParser parser = new JsonParser();
		//JsonObject result = (JsonObject) parser.parse(jsonStr);
				
		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}
}
