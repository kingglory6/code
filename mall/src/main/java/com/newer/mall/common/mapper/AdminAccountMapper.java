package com.newer.mall.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.mall.common.pojo.Admin;

@Mapper
public interface AdminAccountMapper {
	
	@Select("select * from admin where account = #{account}")
	public Admin login(@Param("account") String account);

}
