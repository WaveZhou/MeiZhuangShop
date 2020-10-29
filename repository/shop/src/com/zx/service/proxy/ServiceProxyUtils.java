package com.zx.service.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

import com.zx.common.annotation.AutoWired;
import com.zx.common.util.ConnectionUtils;

public class ServiceProxyUtils {

	
	@SuppressWarnings("unchecked")
	public <T> T bindService(T obj) {
		   
		   return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				SqlSession sqlSession = null;
				try {
					
					//获取连接
					sqlSession = ConnectionUtils.getSqlSession();
					
					//获取被代理对象的class类型
					Class<?> clazz = obj.getClass();
					//获取被代理对象中所有的成员变量
					Field[] fields =  clazz.getDeclaredFields();
					
					if(fields != null) {
						for(Field field : fields){
						
							if(!field.isAccessible()) {
								field.setAccessible(true);
							} 
							
							//获取字段上的注解
							AutoWired autoWired = field.getAnnotation(AutoWired.class);
	                        if(autoWired != null && autoWired.value()) {
	                        	field.set(obj, sqlSession.getMapper(field.getType()));
	                        }
							
							
						}
					}
					
					//回调当前执行的方法
					 Object result =  method.invoke(obj, args);
					
					
					return result; 
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					//回滚
					sqlSession.rollback();
				}finally {
					//关闭连接
					sqlSession.close();
					
				}

				return null;
			}
		});
	   }
}
