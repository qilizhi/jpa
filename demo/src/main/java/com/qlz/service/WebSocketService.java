/**
 * 
 */
package com.qlz.service;

import org.springframework.stereotype.Service;

/**
 * 
 * @author qilizhi
 * @date 2016年7月8日 下午4:11:05
 */
@Service
public class WebSocketService {
	
	public int getUnReadNews(String socketUserName){
		
		System.out.println("qilizhi"+socketUserName);
		return 2;
	}

}
