package com.hs.gmall.service;


import javax.annotation.Resource;

public interface OrderService {
	
	/**
	 * 初始化订单
	 * @param userId
	 */
	public void initOrder(String userId);

}
