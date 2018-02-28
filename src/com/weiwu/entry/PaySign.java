package com.weiwu.entry;

import java.util.HashMap;
import java.util.Map;

import com.tencent.common.RandomStringGenerator;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.Signature;

public class PaySign {
	String appId;
	String nonceStr;
	String pack;
	String signType;
	long timeStamp;
	String paySign;
	
	public PaySign(String pack){
		setAppId(ApplicationContants.APP_ID);
		setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
		setSignType("MD5");
		setTimeStamp(System.currentTimeMillis());
		setPack(pack);
		
		
		setPaySign(Signature.getSign(toMap()));
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getpack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
	public Map<String,Object> toMap(){
        Map<String,Object> map= new HashMap<String,Object>();
        
        map.put("appId", this.getAppId());
        map.put("nonceStr", this.getNonceStr());
        map.put("package", this.getpack());
        map.put("signType", this.getSignType());
        map.put("timeStamp", this.getTimeStamp());
        return map;
    }
}
