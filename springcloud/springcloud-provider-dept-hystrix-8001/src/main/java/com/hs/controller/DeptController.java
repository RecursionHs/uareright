package com.hs.controller;

import com.hs.pojo.Dept;
import com.hs.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hsir
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGetDept")
    public Dept getDept(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if(dept == null){
            throw new RuntimeException("getDept -> error!");
        }
        return dept;
    }

    /**
     * {"deptno":44,"dname":"Hystrix->8001","dbSource":"error hystrix db no data"}
     * @param id
     * @return
     */
    //备选调用
    public Dept hystrixGetDept(@PathVariable("id") Long id){
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("Hystrix->8001");
        dept.setDbSource("error hystrix db no data");
        return dept;
    }



}
