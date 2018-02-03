package com.weiwu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.weiwu.dao.HousingDao;
import com.weiwu.dao.MyBatisUtil;
import com.weiwu.dao.UserDao;
import com.weiwu.entry.Building;
import com.weiwu.entry.HousingEstate;
import com.weiwu.entry.User;

@Controller
@RequestMapping("/owner")
public class OwnerPageController {

	@RequestMapping("/home")
	public ModelAndView inputPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/index");

		return mav;
	}

	@RequestMapping("/addHousing")
	public ModelAndView addHousingPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/add_house");

		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		List<HousingEstate>  estateList;
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			estateList =housingEstateDao.getAllHousingEstate();
		} finally {
			session.close();
		}

		mav.addObject("estateList", estateList);
		return mav;
	}
	
	
	@RequestMapping("/getBuilding")
	@ResponseBody
	public ResponseEntity<List> getBuilding(HttpServletRequest request) {
		String estateId= request.getParameter("id");
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		List<Building>  buildingList;
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			buildingList =housingEstateDao.getBuildingsByEstate(estateId);
		} finally {
			session.close();
		}	
		return new ResponseEntity<List>(buildingList, HttpStatus.OK);
	}
	

	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/test");
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		User user;
		try {
			UserDao UserMaper = session.getMapper(UserDao.class);
			user = UserMaper.selectUserById("11223");
		} finally {
			session.close();
		}

		mav.addObject("user", user);

		return mav;
	}
}
