package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.newer.mall.common.pojo.Commodity;

@Mapper
public interface CommodityMangeMapper {
	
	@Select("select * from Commodity")
	public List<Commodity> getCommodityAll();

}
