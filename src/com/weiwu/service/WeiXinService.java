package com.weiwu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.NetUtil;

public class WeiXinService {
	private static Logger logger = Logger.getLogger(WeiXinService.class);
	JsonParser parser = new JsonParser();

	//微信入口授权, 获取 access_token, 
	//返回open id
	public String jsAuth(HttpServletRequest request) {
		String code = request.getParameter("code");
		JsonParser parser = new JsonParser();
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		
		if (openid == null) {
			if (code != null) {
				String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
						+ "appid="+ ApplicationContants.APP_ID
						+ "&secret="+ ApplicationContants.SECRET
						+ "&code="+ code+ "&grant_type=authorization_code";

				String result = NetUtil.sendGet(authUrl);

				JsonObject authResponse = (JsonObject) parser.parse(result);
				openid = authResponse.get("openid").getAsString();
				session.setAttribute("openid",openid);
				session.setAttribute("code",code);
				session.setAttribute("access_token", authResponse.get("access_token").getAsString());
			}
		}
		return openid;
	}
	
	public String getNickname(HttpServletRequest request){
		HttpSession session = request.getSession();
		String access_token = (String) session.getAttribute("access_token");
		String openid = (String) session.getAttribute("openid");
		if(openid == null)  {jsAuth(request);
		}
		String nickname = (String) session.getAttribute("nickname");
		if(nickname == null){
			String userInfoUrl = ApplicationContants.USER_INFO_API+"?access_token="+access_token
					+"&openid="+openid+"&lang=eh";
			String result = NetUtil.sendGet(userInfoUrl);
			JsonObject userInfoResponse = (JsonObject) parser.parse(result);
			nickname =userInfoResponse.get("nickname").getAsString();
			session.setAttribute("nickname", nickname);
		}
		
		return nickname;
	}

	public String getTicket(HttpServletRequest request) {
		// access token for js api
		HttpSession session = request.getSession();
		String ticket = (String) session.getAttribute("ticket");
		String token = (String) session.getAttribute("token");
		logger.info("tick:"+ticket);
		logger.info("token:"+token);
		if (token == null) {
			String tokenResult = NetUtil.sendGet(ApplicationContants.TOKEN_API);
			JsonObject tokenResponse = (JsonObject) parser.parse(tokenResult);
			token = tokenResponse.get("access_token").getAsString();
			if (token != null) {
				session.setAttribute("token", token);
			} else {
				logger.error("fail to get access_token");
			}
		}

		if (ticket == null) {
			String ticketUrl = ApplicationContants.JS_API + "?access_token="
					+ token + "&type=jsapi";
			String ticketResult = NetUtil.sendGet(ticketUrl);
			JsonObject ticketResponse = (JsonObject) parser.parse(ticketResult);
			String errmsg = ticketResponse.get("errmsg").getAsString();
			if (errmsg.equals("ok")) {
				ticket = ticketResponse.get("ticket").getAsString();
				session.setAttribute("ticket", ticket);
			} else {
				logger.error("*** get ticket fail:"+errmsg);
			}
		}

		return ticket;
	}
}
