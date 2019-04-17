package com.newer.mall.admin.account.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.mall.admin.account.service.CommodityService;
import com.newer.mall.admin.account.thread.EmailRunnable;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.mapper.CommodityMangeMapper;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Notice;
import com.newer.mall.common.utils.EmailSenderService;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	CommodityMangeMapper mapper;

	@Autowired
	EmailSenderService mail;

	@Override
	public List<Commodity> findCommodity() {
		return mapper.getCommodityAll();
	}

	@Override
	public void createCommodity(Commodity com) throws SQLException {
		mapper.addCommodity(com);
	}

	@Override
	public void upDown(int id, int type) throws BindingException, StateException {
		if (type != 0 && type != 1) {
			throw new StateException();
		}
		mapper.updateShelf(id, type);
	}

	@Override
	public void stockMange(int id, int num) throws BindingException, DataException {
		if (num < 0) {
			throw new DataException();
		}
		int stock = mapper.getStock(id);
		mapper.updateStock(id, num);
		if (stock == 0) {
			List<Notice> email = mapper.getEmail(id);

			new Thread(new EmailRunnable(email,mail)).start();
		}

	}

	@Override
	public void stockMangeAppen(int id, int num) throws BindingException {
		
	}

	@Override
	public void recommend(int id, int type) throws DataException {
		if(type != 0 && type != 1) {
			throw new DataException();
		}
		mapper.updateRecommend(id, type);
		
	}

}
