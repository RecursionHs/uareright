package com.hs.gmall.service.impl;

import com.hs.gmall.bean.UserAddress;
import com.hs.gmall.service.OrderService;
import com.hs.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;

    @Override
    public void initOrder(String userId) {
        System.out.println("用户ID： " + userId);
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress: addressList) {
            System.out.println(userAddress.getUserAddress());
        }

    }
}
