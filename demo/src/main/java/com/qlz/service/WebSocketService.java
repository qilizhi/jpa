/**
 * 
 */
package com.qlz.service;

import org.springframework.stereotype.Service;

/**
 * @author qilizhi
 * @date 2016��7��6�� ����12:15:31
 */
@Service
public class WebSocketService {
	
	public int getUnReadNews(String socketUserName){
		
		System.out.println("��ȡ���û�����Ϣδ����Ϣ����"+socketUserName);
		return 2;
	}

}
