package com.weiwu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tencent.common.RandomStringGenerator;
import com.weiwu.dao.BillingDao;
import com.weiwu.dao.HousingDao;
import com.weiwu.dao.MyBatisUtil;
import com.weiwu.entry.Billing;
import com.weiwu.entry.Building;
import com.weiwu.entry.HouseFullInfo;
import com.weiwu.entry.HousingEstate;
import com.weiwu.entry.Ladder;
import com.weiwu.entry.Room;
import com.weiwu.service.WeiXinService;
import com.weiwu.util.ApplicationContants;
import com.weiwu.util.DateUtil;
import com.weiwu.util.Sha1Sign;

@Controller
@RequestMapping("/house")
public class HouseController {
	@RequestMapping("/addHousing")
	public ModelAndView addHousingPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("house/add_house");

		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		List<HousingEstate>  estateList;
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			estateList =housingEstateDao.getAllHousingEstate();
		} finally {
			session.commit();
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
			session.commit();
			session.close();
		}	
		return new ResponseEntity<List>(buildingList, HttpStatus.OK);
	}
	
	@RequestMapping("/getLadders")
	@ResponseBody
	public ResponseEntity<List> getLadders(HttpServletRequest request) {
		String buidlingId= request.getParameter("id");
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		List<Ladder>  ladderList;	
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			ladderList =housingEstateDao.getLadderByBuilding(buidlingId);
		} finally {
			session.commit();
			session.close();
		}	
		return new ResponseEntity<List>(ladderList, HttpStatus.OK);
	}
	
	@RequestMapping("/getRooms")
	@ResponseBody
	public ResponseEntity<List> getRooms(HttpServletRequest request) {
		String id= request.getParameter("id");
		String getBy= request.getParameter("getBy");
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		List<Room>  roomList = null;	
		try {
			HousingDao housingEstateDao = session.getMapper(HousingDao.class);
			if(getBy.equals("BU")){
				roomList =housingEstateDao.getRoomByBuilding(id);
			}else{
				roomList =housingEstateDao.getRoomByLadder(id);
			}
			
		} finally {
			session.commit();
			session.close();
		}	
		return new ResponseEntity<List>(roomList, HttpStatus.OK);
	}
}
