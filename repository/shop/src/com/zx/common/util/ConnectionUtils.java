package com.zx.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//数据库连接工具类，提供获取连接以及关闭连接的方法
public class ConnectionUtils {
	
	  private static SqlSessionFactory sqlSessionFactory;
	  static {
		
			try {
				InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
	  }
	  
	  
	  //获取连接
	  public static SqlSession getSqlSession() {
		  
		 return  sqlSessionFactory.openSession(true);
	  }
	
	
	
	
	
}
