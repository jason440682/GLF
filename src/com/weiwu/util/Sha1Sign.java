package com.weiwu.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.weiwu.controller.PaymentController;

public class Sha1Sign {
	
	private static Logger logger = Logger.getLogger(Sha1Sign.class);
	
	public static String getSign(String str){
		String sign = DigestUtils.shaHex(str);	
		return sign;
	}
	
	
	public static String getSign(String noncestr,String url, String ticket,long timestamp){
		String str="jsapi_ticket="+ticket+"&noncestr="+noncestr
				   +"&timestamp="+timestamp
				   +"&url="+url;
		
		logger.info("****"+str);
		
		String sign = DigestUtils.shaHex(str);
		return sign;
	}
}
