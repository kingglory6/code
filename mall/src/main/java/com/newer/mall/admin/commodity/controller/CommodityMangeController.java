package com.newer.mall.admin.commodity.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.admin.commodity.service.impl.CommodityServiceImpl;
import com.newer.mall.admin.commodity.service.impl.ImageService;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Commodity;

@RestController
@RequestMapping("api/v1/admin/commodity")
public class CommodityMangeController {

	@Autowired
	private CommodityServiceImpl service;

	@Autowired
	private ImageService imageService;

//	@GetMapping("/loadcommodity/{page}")
//	public Map<String, Object> loadCommodity(@PathVariable int page,@RequestParam("size") int size) {
//		Map<String, Object> map = new HashMap<>();
//		String key = (String) session.getAttribute("key");
//		if (key == null) {
//			map.put("code", "未登录");
//		} else if (!reqkey.equals(key)) {
//			map.put("code", "Illegal Request");
//			return map;
//		}
//		PageHelper.startPage(page, size);
//		map.put("data", new PageInfo<Commodity>(service.findCommodity()));
//		return map;
//	}

	@PostMapping("/add")
	public Map<String, Object> uploadCommodity(@RequestBody Commodity com) {
		Map<String, Object> map = new HashMap<>();
//		String key = (String) session.getAttribute("key");
		try {
//			if (key == null) {
//				map.put("code", "未登录");
//				return map;
//				
//			}
			service.createCommodity(com);
			map.put("code", "ok");
		} catch (SQLException e) {
			map.put("code", "error");
		}
		return map;
	}

	@PostMapping("/state")
	public Map<String, Object> commodityState(int id, int option) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.upDown(id, option);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "error");
		} catch (StateException e) {
			map.put("code", "state error");
		}
		return map;
	}

	@PutMapping("/stock")
	public Map<String, Object> stockMange(int id, int num) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.stockMange(id, num);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "error");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		return map;
	}

	@PostMapping("/recommend")
	public Map<String, Object> recommendMange(int id, int type) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.recommend(id, type);
			map.put("code", "ok");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		return map;
	}

	@PutMapping("/modifycommodity")
	public Map<String, Object> putCommodity(@RequestBody Commodity com) {
		Map<String, Object> map = new HashMap<>();
		service.saveCommodity(com);
		map.put("code", "ok");
		return map;
	}

	@DeleteMapping("/removecommodity/{id}")
	public Map<String, Object> dropCommodity(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		service.dropCommodity(id);
		map.put("code", "ok");
		return map;
	}

	@DeleteMapping("/removecategory/{id}")
	public Map<String, Object> dropCategory(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.dropCategory(id);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "id不存在");
		}
		return map;
	}

	@DeleteMapping("/removebrand/{id}")
	public Map<String, Object> dropBrand(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.dropBrand(id);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "id不存在");
		}
		return map;
	}

	@PostMapping("/createbrand")
	public Map<String, Object> createBrand(@RequestBody Brand brand) {
		Map<String, Object> map = new HashMap<>();
		service.createBrand(brand);
		map.put("code", "ok");
		return map;
	}

	@PostMapping("/createcategory")
	public Map<String, Object> createCategory(@RequestBody Category category) {
		Map<String, Object> map = new HashMap<>();
		service.createCategory(category);
		map.put("code", "ok");
		return map;
	}

	@GetMapping("/wuliu")
	public Object getWuliu(String on) {
		return service.wuliu(on);
	}

	@GetMapping("/category")
	public Map<String, Object> loadCategory() {
		Map<String, Object> map = new HashMap<>();
		map.put("data", service.findCategory());
		return map;
	}

	@GetMapping("/brand")
	public Map<String, Object> loadBrand() {
		Map<String, Object> map = new HashMap<>();
		map.put("data", service.findBrand());
		return map;
	}

//	@GetMapping("/upcommodity/{page}")
//	public Map<String,Object> loadUpCommodity(@PathVariable int page,@RequestParam("size") int size,int num){
//		Map<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page, size);
//		map.put("data", new PageInfo<Commodity>(service.findUpCommodity(num)));
//		return map;
//	}

	@GetMapping("/querycommodity/{page}")
	public Map<String, Object> conditionalQuery(@PathVariable int page, int size, int shelf, int cid, int bid,
			String text) {
		Map<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, size);
		if ("".equals(text))
			text = null;
		map.put("data", new PageInfo<Commodity>(service.conditionalQuery(shelf, cid, bid, text)));
		return map;
	}

	// 上传图片到本地
	@PostMapping("/upimage")
	public String upload(@RequestParam("file") MultipartFile image) {

		return imageService.upload(image);
	}

	@GetMapping("/loadcomm")
	public Map<String, Object> loadComm(int id) {
		Map<String, Object> map = new HashMap<>();
		map.put("data", service.findComm(id));
		return map;
	}

}
