package com.hs.service;


import com.hs.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
//服务降级
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory {


    @Override
    public Object create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                Dept dept = new Dept();
                dept.setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭");
                dept.setDeptno(id);
                dept.setDbSource("无数据...");
                return dept;
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
