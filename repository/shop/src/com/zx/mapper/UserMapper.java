package com.zx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zx.vo.User;

/**
 * UserMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface UserMapper {

	@Update("update ec_user set disabled = 1 where active = #{activeCode}")
	void active(String activeCode);

	@Select("select * from ec_user where (login_name = #{loginName} or email = #{loginName}) and password = #{password}")
	User findUser(@Param("loginName")String loginName, @Param("password")String password);

	@Select("select * from ec_user where (login_name = #{loginName} or email = #{loginName}) and password = #{password} and disabled = 1")
	User findUserByActive(@Param("loginName")String loginName, @Param("password")String password);

	@Select("select * from ec_user where  email = #{email}")
	User findUserByEmail(String email);

	@Insert("INSERT INTO ec_user(LOGIN_NAME,PASSWORD,NAME,SEX,EMAIL,PHONE,ADDRESS,CREATE_DATE,ACTIVE) VALUES(#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{createDate},#{active})")
	void save(User user);

	@Select("select * from ec_user where (login_name = #{loginName} or email = #{loginName})")
	User findUserByLoginName(String loginName);



}