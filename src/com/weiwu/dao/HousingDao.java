package com.weiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.weiwu.entry.*;

public interface HousingDao {
	
	public List<HousingEstate> getAllHousingEstate();
	
	public List<Building> getBuildingsByEstate(@Param("id") String estateId);
	
	public List<Ladder> getLadderByBuilding(@Param("id") String buidlingId);
	
	public List<Room> getRoomByBuilding(@Param("id") String buidlingId);
	
	public List<Room> getRoomByLadder(@Param("id") String ladderId);
	
	public List<HouseFullInfo> getHouseFullInfoByUser(@Param("userId") String userId);
	
	public List<RenterFull> getRenterFullInfoByUser(@Param("userId") String userId);
	
	public HouseFullInfo getHouseFullInfoByHouseId(@Param("houseId") String houseId);
	
	public List<HouseFullInfo> getHouseBillingByUser(@Param("userId") String userId);
	
	
}
