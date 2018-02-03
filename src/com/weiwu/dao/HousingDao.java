package com.weiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.weiwu.entry.Building;
import com.weiwu.entry.HousingEstate;

public interface HousingDao {
	
	public List<HousingEstate> getAllHousingEstate();
	
	public List<Building> getBuildingsByEstate(@Param("id") String estateId);
	
}
