package jmx.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import jmx.server.entity.User;

@Mapper
public interface UserMapper {
	
	@Insert("insert into users(id,name,age) values (#{id},#{name},#{age})")
	void insert(@Param("id")int id,@Param("name")String name,@Param("age")int age);
	
	@Select("select id,name,age from users")
	List<User> findAll();
}
