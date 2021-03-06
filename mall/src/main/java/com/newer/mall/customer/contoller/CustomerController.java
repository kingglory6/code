package com.newer.mall.customer.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.exception.RegisterException;
import com.newer.mall.common.pojo.Address;
import com.newer.mall.common.pojo.Collection;
import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.History;
import com.newer.mall.customer.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService custservice;
	/**
	 * 注册新用户
	 * @return
	 */
	@PostMapping("/register")
	public boolean addCustomer(@RequestBody Customer customer) {
		try {
			custservice.register(customer);
			return true;
		} catch (RegisterException e) {
			e.getMessage();
			return false;
		}
	}
	
	/**
	  * 用户登录
	 * @param session 获得会话
	 * @param email	登录邮箱
	 * @param password	登录密码
	 * @return
	 */
	@PostMapping("/login")
	public Customer login(HttpSession session,@RequestParam String email,@RequestParam String password) {
		 try {
			Customer cust=custservice.login(session,email, password);
			return cust;
		} catch (PasswordErrorException e) {
			e.getMessage();
			return null;
		}
		 
		
	}
	
	/**
	 * 更新用户信息
	 * @param session
	 * @param customer
	 * @return
	 */
	@PostMapping("/update")
	public boolean update(@RequestBody Customer customer){
		boolean info = custservice.info(customer);
		return info;
	}
	
	/**
	 * 添加用户收货地址
	 * @param uid
	 * @param address
	 * @return
	 */
	@PostMapping("/addAddress")
	public List<Address> addAddress(@RequestParam int uid,@RequestBody Address address){

		return custservice.addAddress(uid, address);
	}
	
	/**
	 * 显示用户收获地址
	 * @param uid
	 * @return
	 */
	@GetMapping("/showAddress")
	public List<Address> showAddress(@RequestParam int uid){
		return custservice.showAddresses(uid);
	}
	
	/**
	 * 添加用户足迹
	 * @param uid
	 * @param history
	 */
	@PostMapping("/foot")
	public void foot(@RequestBody Customer customer){
		custservice.foot(customer.getId(), customer.getHistoryList().get(0));
	}
	
	
	/**
	 * 查看用户足迹
	 * @param uid
	 * @return
	 */
	@GetMapping("/showfoot")
	public List<History> showFoot(@RequestParam int uid){
		return custservice.showFoot(uid);
	}
	
	/**
	 * 用户添加收藏
	 * @param uid
	 * @param collection
	 */
	@PostMapping("/collection")
	public void collection(@RequestBody Customer customer) {
		custservice.collection(customer.getId(),customer.getCollectionList().get(0));
	}
	
	/**
	 * 查询用户收藏商品
	 * @param uid
	 * @return
	 */
	@GetMapping("/showcol/{pageNum}")
	public PageInfo<Collection> showCollection(@RequestParam int uid ,@PathVariable int pageNum){
		return custservice.showCollection(uid,pageNum);
	}
	
  
 }
