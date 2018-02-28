package com.weiwu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weiwu.service.WeiXinService;


@Controller
@RequestMapping("/HouseDataUploadController")
public class HouseDataUploadController {
	@RequestMapping("/")
	public ModelAndView inputPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		WeiXinService weiXinService	= new WeiXinService();
		String openId =weiXinService.jsAuth(request); 
		String nickName = weiXinService.getNickname(request); 
		mav.addObject("nickName",nickName);
		
		return mav;
	}
}
