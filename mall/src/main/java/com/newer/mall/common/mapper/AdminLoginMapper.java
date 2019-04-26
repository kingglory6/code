package com.newer.mall.common.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.mall.common.pojo.Log;

@Mapper
public interface AdminLoginMapper {

	@Insert("insert into log(account,action,result,ip,description) values(#{account},#{action},#{result},#{ip},#{description})")
	public void insertLog(@Param("account") String account, @Param("ip") String ip, @Param("action") String action,
			@Param("result") boolean result, @Param("description") String description);
	
	@Select({ "<script>",
		"select * from log <if test='startTime!=null or endTime!=null'> where </if> <if test='startTime!=null'> time >= #{startTime} </if> <if test='startTime!=null and endTime!=null'>and</if> <if test='endTime!=null'>time &lt;= #{endTime}</if>",
		"</script>" })
	public List<Log> getLogs(@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	
	@Delete("delete from log where id =#{id}")
	public void deleteLog(@Param("id") int id);

}
