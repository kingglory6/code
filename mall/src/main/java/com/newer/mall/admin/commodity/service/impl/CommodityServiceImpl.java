package com.newer.mall.admin.commodity.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.newer.mall.admin.account.thread.EmailRunnable;
import com.newer.mall.admin.commodity.service.CommodityService;
import com.newer.mall.admin.commodity.thread.CopyFile;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.exception.DeleteException;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.mapper.CommodityMangeMapper;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Notice;
import com.newer.mall.common.pojo.Spec;
import com.newer.mall.common.utils.EmailSenderService;
import com.newer.mall.common.utils.HttpUtils;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	CommodityMangeMapper mapper;

	@Autowired
	EmailSenderService mail;

	@Override
	public Page<Commodity> findCommodity() {
		return (Page<Commodity>) mapper.getCommodityAll();
	}

	@Override
	@Transactional
	public void createCommodity(Commodity com) throws SQLException {
		mapper.addCommodity(com);
		mapper.addSpecList(mapper.getId(),com.getSpecList());
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for(Spec spec:com.getSpecList()) {
			cachedThreadPool.execute(new CopyFile(spec.getImg()));
		}
		
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

			new Thread(new EmailRunnable(email, mail)).start();
		}

	}

	@Override
	public void stockMangeAppen(int id, int num) throws BindingException {

	}

	@Override
	public void recommend(int id, int type) throws DataException {
		if (type != 0 && type != 1) {
			throw new DataException();
		}
		mapper.updateRecommend(id, type);

	}

	@Override
	public void saveCommodity(Commodity com) throws DataException {
		if(com.getStock()<0) {
			throw new DataException();
		}
		int stock = mapper.getStock(com.getId());
		mapper.updateCommodity(com);
		if(stock==0&&com.getStock()>stock) {
			List<Notice> email = mapper.getEmail(com.getId());
			new Thread(new EmailRunnable(email, mail)).start();
		}
	}

	@Override
	public void dropCommodity(int id) {
		mapper.deleteCommodity(id);
	}

	@Override
	public void createSpec(Spec spec) {
		mapper.addSpec(spec);
	}

	@Override
	public void createSpecList(List<Spec> spec) {
		// mapper.addSpecList(spec);
	}

	@Override
	public Object wuliu(String no) {
		String host = "https://wuliu.market.alicloudapi.com";
		String path = "/kdi";
		String method = "GET";
		String appcode = "ea88d67e70754747bc4686681205a293"; // !!!替换填写自己的AppCode 在买家中心查看
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode); // 格式为:Authorization:APPCODE
															// 83359fd73fe11248385f570e3c139xxx
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("no", no);// !!! 请求参数
		// querys.put("type", "zto");// !!! 请求参数
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			// System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
			// 状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
			// 获取response的body
			String a = EntityUtils.toString(response.getEntity()); // 输出json
			return a;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Category> findCategory() {
		return mapper.getCategorys();
	}

	@Override
	public List<Brand> findBrand() {
		return mapper.getBrands();
	}

	@Override
	public Page<Commodity> conditionalQuery(int shelf, int cid, int bid, String text) {

		return mapper.conditionalQuery(shelf, cid, bid, text);
	}

	@Override
	public Commodity findComm(int id) {
		return mapper.getCommodity(id);
	}

	@Override
	public void dropCategory(int id) throws BindingException, DeleteException {
		if(mapper.getCategoryNumber(id)>0) {
			throw new DeleteException();
		}
		mapper.deleteCategory(id);
	}

	@Override
	public void createCategory(Category category) {
		mapper.addCategory(category);
	}

	@Override
	public void createBrand(Brand brand) {
		mapper.addBrand(brand);
	}

	@Override
	public void dropBrand(int id) throws BindingException, DeleteException{
		if(mapper.getCategoryNumber(id)>0) {
			throw new DeleteException();
		}
		mapper.deleteBrand(id);
	}
	

//	@Override
//	public Page<Commodity> findUpCommodity(int num) {
//		return (Page<Commodity>)mapper.getUpCommodity(num);
//	}

}
