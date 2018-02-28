package com.weiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weiwu.entry.Billing;

public interface SystemPropertyDao {
	public String getSystemPropertyByCode(@Param("code") String code);
}
