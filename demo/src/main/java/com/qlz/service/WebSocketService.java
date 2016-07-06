/**
 * 
 */
package com.qlz.service;

import org.springframework.stereotype.Service;

/**
 * @author qilizhi
 * @date 2016年7月6日 下午12:15:31
 */
@Service
public class WebSocketService {
	
	public int getUnReadNews(String socketUserName){
		
		System.out.println("获取该用户的信息未读信息条数"+socketUserName);
		return 2;
	}

}
