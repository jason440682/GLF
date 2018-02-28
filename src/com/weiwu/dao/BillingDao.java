package com.weiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weiwu.entry.Billing;

public interface BillingDao {
	public List<Billing> getBillingByHouseId(@Param("houseId") String houseId);
}
