package com.hs.gmall.service;


import com.hs.gmall.bean.UserAddress;

import javax.annotation.Resource;
import java.util.List;

public interface OrderService {
	
	/**
	 * 初始化订单
	 * @param userId
	 */
	public List<UserAddress> initOrder(String userId);

}
