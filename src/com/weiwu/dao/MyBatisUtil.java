package com.weiwu.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static  SqlSessionFactory sqlSessionFactory;  
	  
    private static synchronized void init() {  
        String resource="db/config/mybatis-config.xml";  
        
        try {  
        	 InputStream inputStream = Resources.getResourceAsStream(resource);
             sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
    }  
  
    public static  SqlSessionFactory getSqlSessionFactory(){  
    	if(sqlSessionFactory == null){
    		init();
    	}
        return sqlSessionFactory;  
    }  
}
