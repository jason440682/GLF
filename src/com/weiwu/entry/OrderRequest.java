package com.weiwu.entry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tencent.common.RandomStringGenerator;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.Signature;


public class OrderRequest {
	 //每个字段具体的意思请查看API文档
    private String appid;
    private String body;
    private String mch_id;
    private String nonce_str;
    private String notify_url;
    private String openid;
    private String out_trade_no;
    private String spbill_create_ip;
    private int total_fee;
    private String trade_type;  
    private String sign; 
    
    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public OrderRequest(String openid ,String user_ip ,String order_id,int price){
    	setAppid(ApplicationContants.APP_ID);
    	setMch_id(ApplicationContants.MCH_ID);
    	setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
    	setOut_trade_no(order_id);
    	setTotal_fee(price);
    	setNotify_url(ApplicationContants.NOTIFY_URL);
    	setBody(ApplicationContants.BODY);
    	setSpbill_create_ip(user_ip);
    	setOpenid(openid);
    	setTrade_type(ApplicationContants.TRADE_TYPE);
    	
    	String sign = Signature.getSign(toMap());
    	setSign(sign);
    }
    
    public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getMch_id() {
		return mch_id;
	}


	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}


	public String getNonce_str() {
		return nonce_str;
	}


	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getNotify_url() {
		return notify_url;
	}


	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}


	public int getTotal_fee() {
		return total_fee;
	}


	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}


	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}


	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}


	public String getTrade_type() {
		return trade_type;
	}


	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}


	public String getOut_trade_no() {
		return out_trade_no;
	}


	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}


	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
