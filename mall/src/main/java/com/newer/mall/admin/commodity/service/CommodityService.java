package com.newer.mall.admin.commodity.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.binding.BindingException;

import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Spec;

public interface CommodityService {

	public List<Commodity> findCommodity();

	public void createCommodity(Commodity com) throws SQLException;

	public void saveCommodity(Commodity com);

	public void upDown(int id, int type) throws BindingException, StateException;

	public void stockMange(int id, int num) throws BindingException, DataException;

	public void stockMangeAppen(int id, int num) throws BindingException;

	public void recommend(int id, int type) throws DataException;

	public void dropCommodity(int id);

	public void createSpec(Spec spec);

	public void createSpecList(List<Spec> spec);

	public Object wuliu(String on);

	public List<Category> findCategory();

	public List<Brand> findBrand();

	// public List<Commodity> findUpCommodity(int num);

	public List<Commodity> conditionalQuery(int shelf, int cid, int bid,String text);
	
	public Commodity findComm(int id);
}
