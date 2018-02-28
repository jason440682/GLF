package com.weiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weiwu.entry.Orders;

public interface PaymentDao {
	public void addOrder(@Param("orders") List<Orders> orders);
}
