package com.qlz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.qlz.constant.ExceptionCode;
import com.qlz.model.JsonResult;
import com.qlz.service.WebSocketService;
import com.qlz.socket.SystemWebSocketHandler;

@RequestMapping("/socket")
@Controller
public class AdminController {

	static Logger logger = LoggerFactory.getLogger(AdminController.class);

	// @Autowired
	// private WebSocketService adminService;
	// @Autowired
	// private SystemWebSocketHandler systemWebSocketHandler;

	/*
	 * @Bean public SystemWebSocketHandler systemWebSocketHandler() { return new
	 * SystemWebSocketHandler(); }
	 */
	@RequestMapping("/auditing")
	@ResponseBody
	public JsonResult auditing(HttpServletRequest request, @RequestParam(defaultValue = "qilizhi") String username,
			String msg) {
		// 无关代码都省略了
		/*
		 * int unReadNewsCount = adminService.getUnReadNews(username); try {
		 * systemWebSocketHandler.sendMessageToUser(username, new
		 * TextMessage("msg:"+msg+"N:"+unReadNewsCount)); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		return new JsonResult(ExceptionCode.SUCCESSFUL, "send OK");
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, Object> jsonTest() {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("testControll", "OK");
		System.out.println("testController ok");
		return maps;
	}
}