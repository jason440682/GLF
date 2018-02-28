package com.weiwu.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.tencent.common.XMLParser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.weiwu.entry.OrderRequest;
import com.weiwu.util.ApplicationContants;

public class WeiXinPayService {
	private static Logger logger = Logger.getLogger(WeiXinPayService.class);  

	//向微信下单接口发出请求，获取prepay_id
	public String getPrepayId(HttpServletRequest request,String order_id,int price){
		String prepay_id = "";
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {	
			e.printStackTrace();
		}
    	String ip = request.getRemoteAddr();
        String openid = (String)request.getSession().getAttribute("openid");
   
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		OrderRequest orderRequest = new  OrderRequest(openid,ip,order_id,price);
		CloseableHttpClient httpClient = HttpClients.custom().build();
		String orderReques = xStreamForRequestPostData.toXML(orderRequest);
		logger.info("orderReques:"+orderReques);
		HttpPost httpPost = new HttpPost(ApplicationContants.ORDER_API);
	    StringEntity postEntity = new StringEntity(orderReques, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

		try {
            HttpResponse payResponse = httpClient.execute(httpPost);

            HttpEntity entity = payResponse .getEntity();
            String  result = EntityUtils.toString(entity, "UTF-8");
            logger.info("prepay_id result:"+result);
            Map responseMap = XMLParser.getMapFromXML(result);
            prepay_id = (String )responseMap.get("prepay_id");
        }catch(Exception e){
        	logger.error(e.getMessage());
        }
		return prepay_id;
	}
}
