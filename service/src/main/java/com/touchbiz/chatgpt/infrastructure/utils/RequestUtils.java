package com.touchbiz.chatgpt.infrastructure.utils;


import com.touchbiz.common.entity.exception.BizException;
import com.touchbiz.common.utils.text.CommonConstant;
import com.touchbiz.common.utils.text.oConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * IP地址
 * 
 * @Author scott
 *
 * update: 【类名改了大小写】 date: 2022-04-18
 * @email jeecgos@163.com
 * @Date 2019年01月14日
 */
@Slf4j
public class RequestUtils {
	private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 ||CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
        	logger.error("IPUtils ERROR ", e);
        }
        
//        //使用代理，则获取第一个IP地址
//        if(StringUtils.isEmpty(ip) && ip.length() > 15) {
//			if(ip.indexOf(",") > 0) {
//				ip = ip.substring(0, ip.indexOf(","));
//			}
//		}
        
        return ip;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(com.touchbiz.chatgpt.infrastructure.constants.CommonConstant.X_ACCESS_TOKEN);
        if(oConvertUtils.isEmpty(token)) {
            throw new BizException("用户token失效，请联系管理员");
        }
        return token;
    }

    public static Map<String, Object> getHeads(HttpServletRequest request){
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();
        log.info("请求头信息");
        while(headers.hasMoreElements()){
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            log.info(headName+"："+headValue);
            stringObjectHashMap.put(headName,headValue);
        }
        return stringObjectHashMap;
    }
	
}
