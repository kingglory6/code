package com.newer.mall.admin.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.mall.common.mapper.CommodityMangeMapper;
import com.newer.mall.common.pojo.Commodity;

@Service
public class CommodityService {

	@Autowired
	CommodityMangeMapper mapper;
	
	public List<Commodity> findCommodity(){
		return mapper.getCommodityAll();
	}
	
	public void createCommodity(Commodity com) throws SQLException{
		mapper.addCommodity(com);
	}
}
