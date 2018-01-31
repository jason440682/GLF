package com.weiwu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weiwu.dao.MyBatisUtil;
import com.weiwu.entry.User;
import com.weiwu.mapper.UserMapper;


@Controller
@RequestMapping("/owner")
public class OwnerPageController {
	
	
	@RequestMapping("/home")
	public ModelAndView   inputPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("owner/index");      
	
		return mav;
	}
	
	@RequestMapping("/test")
	public ModelAndView   test(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("owner/test");      
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		User user;
		try {
		  UserMapper UserMaper = session.getMapper( UserMapper.class);
		  user = UserMaper.selectUserById("11223");
		} finally {
		  session.close();
		}
		
		mav.addObject("user",user);
	
		return mav;
	}
}
