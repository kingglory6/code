package com.newer.mall.admin.account.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.binding.BindingException;

import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Commodity;

public interface CommodityService {

	public List<Commodity> findCommodity();

	public void createCommodity(Commodity com) throws SQLException;

	public void upDown(int id, int type) throws BindingException, StateException;

	public void stockMange(int id, int num) throws BindingException, DataException;

	public void stockMangeAppen(int id, int num) throws BindingException;

	public void recommend(int id, int type) throws DataException;

	public void activity(Activity activity) throws DataException;

}
