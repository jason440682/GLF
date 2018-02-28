package com.weiwu.dao;

import java.util.List;

import com.weiwu.entry.Renter;
import com.weiwu.entry.RenterPayItem;
import com.weiwu.entry.User;

public interface  RenterDao {

	public Renter getRenterByHouseId(String houseId);
	
	public List<RenterPayItem> getPayItemByRenterId(String renterId);
	
	public String isPayByRenter(String startDate,String renterId);
}
