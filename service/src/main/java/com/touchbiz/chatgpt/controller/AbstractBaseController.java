package com.touchbiz.chatgpt.controller;


import com.touchbiz.common.entity.model.SysUserCacheInfo;
import com.touchbiz.webflux.starter.filter.ReactiveRequestContextHolder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: Controller基类
 * @Date: 2019-4-21 8:13
 * @Version: 1.0
 */
@Slf4j
public abstract class AbstractBaseController<T, S extends List<T>> {

    @Getter
    @Autowired
    protected S service;

    protected SysUserCacheInfo getCurrentUser(){
       return (SysUserCacheInfo) ReactiveRequestContextHolder.getUser();
    }

}
