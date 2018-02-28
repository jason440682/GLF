package com.weiwu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weiwu.dao.HousingDao;
import com.weiwu.dao.MyBatisUtil;
import com.weiwu.dao.SystemPropertyDao;
import com.weiwu.service.WeiXinService;
import com.weiwu.util.ApplicationContants;



@Controller
@RequestMapping("/home")
public class HomePageController {
	@RequestMapping("/index")
	public ModelAndView inputPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		WeiXinService weiXinService	= new WeiXinService();
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SystemPropertyDao systemPropertyDao = session.getMapper(SystemPropertyDao.class);
			String testFlag =systemPropertyDao.getSystemPropertyByCode(ApplicationContants.LOCAL_TEST_FLAG);
			if("Y".equals(testFlag)){			
				mav.addObject("nickName","Testing Profile");
			}else{
				String openId =weiXinService.jsAuth(request); 
				String nickName = weiXinService.getNickname(request); 
				mav.addObject("nickName",nickName);
			}		
		}finally{
			session.close();
		}
		return mav;
	}
}
