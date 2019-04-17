package com.newer.mall.admin.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.common.exception.IllegalRequestException;

@RestController
@RequestMapping("api/v1/admin")
public class CommodityMangeController {
	
	@GetMapping("/loadcommodity")
	public Map<String,Object> loadCommodity(@RequestParam("key") String key,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		String skey= (String)session.getAttribute("key");
		if(!key.equals(skey)) {
			try {
				throw new IllegalRequestException();
			} catch (IllegalRequestException e) {
				map.put("code", "Illegal Request");
			}
		}
		
		return map;
	}

}
