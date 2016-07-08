package com.qlz.aop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.qlz.dao.LogDao;
import com.qlz.entities.Log;

/**
 * 
 * @ClassName: LogAspect
 * @Description: ��־��¼AOPʵ��
 * @author shaojian.yu
 * @date 2014��11��3�� ����1:51:59
 *
 */
@Aspect
@Component
public class LogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String requestPath = null; // �����ַ
	private String userName = null; // �û���
	private Map<?, ?> inputParamMap = null; // �������
	private Map<String, Object> outputParamMap = null; // ���������
	private long startTimeMillis = 0; // ��ʼʱ��
	private long endTimeMillis = 0; // ����ʱ��

	@Autowired
	private LogDao logDao;

	/**
	 * 
	 * @Title��doBeforeInServiceLayer
	 * @Description: ��������ǰ���� ��¼��ʼʱ��
	 * @author shaojian.yu
	 * @date 2014��11��2�� ����4:45:53
	 * @param joinPoint
	 */
	@Before("execution(* com.qlz.controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		startTimeMillis = System.currentTimeMillis(); // ��¼������ʼִ�е�ʱ��
	}

	/**
	 * 
	 * @Title��doAfterInServiceLayer
	 * @Description: �������ú󴥷� ��¼����ʱ��
	 * @author shaojian.yu
	 * @date 2014��11��2�� ����4:46:21
	 * @param joinPoint
	 */
	@After("execution(* com.qlz.controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		endTimeMillis = System.currentTimeMillis(); // ��¼����ִ����ɵ�ʱ��
		this.printOptLog();
		saveDb();
	}

	/**
	 * 
	 * @Title��doAround
	 * @Description: ���ƴ���
	 * @author shaojian.yu
	 * @date 2014��11��3�� ����1:58:45
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.qlz.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

		/**
		 * 1.��ȡrequest��Ϣ 2.���request��ȡsession 3.��session��ȡ����¼�û���Ϣ
		 */
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		// ��session�л�ȡ�û���Ϣ
		// String loginInfo = (String) session.getAttribute("username");
		/*
		 * if(loginInfo != null && !"".equals(loginInfo)){ userName =
		 * operLoginModel.getLogin_Name(); }else{ userName = "�û�δ��¼" ; }
		 */
		userName = "qilizhi";
		// ��ȡ�������
		inputParamMap = request.getParameterMap();
		// ��ȡ�����ַ
		requestPath = request.getRequestURI();

		// ִ���귽���ķ���ֵ������proceed()�������ͻᴥ������㷽��ִ��
		outputParamMap = new HashMap<String, Object>();
		Object result = pjp.proceed();// result��ֵ���Ǳ����ط����ķ���ֵ
		outputParamMap.put("result", result);
		outputParamMap.put("controller", pjp.getSignature().getDeclaringTypeName());
		outputParamMap.put("method", pjp.getSignature().getName());
		outputParamMap.put("params", pjp.getArgs());
		// outputParamMap.put("pjp", pjp);

		return result;
	}

	/**
	 * 
	 * @Title��printOptLog
	 * @Description: �����־
	 * @author shaojian.yu
	 * @date 2014��11��2�� ����4:47:09
	 */
	private void printOptLog() {
		// Gson gson = new Gson(); // ��Ҫ�õ�google��gson������
		String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
		logger.info("用户user" + userName + "  url:" + requestPath + "; op_time:" + optTime + " pro_time:"
				+ (endTimeMillis - startTimeMillis) + "ms ;" + " param:" + JSON.toJSONString(inputParamMap.toString()) + ";"
				+ " result:" + JSON.toJSONString(outputParamMap.toString()));
	}

	private void saveDb() {
		//userId Ӧ�Ǵ�session ��ȥ��ȡ
		Log log = new Log(null, "qilizhi", new Date(),
				outputParamMap.get("controller").toString() + "." + outputParamMap.get("method").toString(),
				JSON.toJSONString(outputParamMap.get("params").toString()));
		logDao.save(log);
	}

}