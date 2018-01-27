package com.glf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/owner")
public class OwnerPageController {
	@RequestMapping("/test")
    public ModelAndView   inputPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("owner/test");      
		mav.addObject("name", "jason");
		return mav;
	}
	
	
	@RequestMapping("/test2")
    public ModelAndView   test2(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("owner/test2");      
		request.setAttribute("name","tom");
		return mav;
	}
	
	@RequestMapping("/test3")
    public ModelAndView   test3(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("owner/test3");  
		String name = request.getParameter("name");
		request.setAttribute("name",name);
		return mav;
	}
}
