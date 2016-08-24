package com.heetian.spider.proxy.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hazelcast.core.IMap;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.utils.ConstantDBCP;
import com.heetian.spider.proxy.dao.Manager;
import com.heetian.spider.proxy.utils.ClientExecuteProxy;

/**
 * Servlet implementation class LoadResource
 */
public class LoadResource extends HttpServlet {
	private static final Log logger = LogFactory.getLog(LoadResource.class);
	private static final long serialVersionUID = 1L;
	public static final Map<String, String> allType = new HashMap<String, String>();
	static{
		allType.put(ConstantDBCP.proxyType_stable, "稳定代理");
		allType.put(ConstantDBCP.proxyType_nostable, "不稳定代理");
		allType.put(ConstantDBCP.proxyType_noprxy, "不使用代理");
	}
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        IMap<Object, Object> types = Manager.dao.showAllType();
        types.clear();
        List<PvnObj> pvns = PvnObj.init32();
        for(PvnObj pvn:pvns){
        	types.put(pvn.getCode(), pvn);
        }
        logger.info("初始化各个省份的代理类型完成........");
        new Thread(new ClientExecuteProxy()).start();// 启动代理线程
		logger.info("开启代理解析线程完成........");
        String ips =  config.getServletContext().getInitParameter("stableProxy");
		if(ips!=null&&!"".equals(ips)){
			Matcher m = Proxy.pattern.matcher(ips);
			while(m.find()){
				Proxy tmp = new Proxy(m.group(1), m.group(2),null);
				tmp.setStable(ProxyStatus.YSE);
				Manager.dao.addProxy(tmp);
			}
		}
		logger.info("加载稳定代理线程完成........");
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();  
		/*** 定制每日2:00执行方法 ***/ 
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date=calendar.getTime(); //第一次执行定时任务的时间
		//如果第一次执行定时任务的时间 小于 当前的时间
		//此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
		if (date.before(new Date())) {
		    date = this.addDay(date, 1);
		}
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				logger.info("时间过了24小时，对各个省份代理进行初始化一次");
				Manager.dao.clearProxy();
			}
		};
		//安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task,date,PERIOD_DAY);
		logger.info("启动定时初始化各省份代理状态线程完成........");
	}
 // 增加或减少天数
    public Date addDay(Date date, int num) {
	   Calendar startDT = Calendar.getInstance();
	   startDT.setTime(date);
	   startDT.add(Calendar.DAY_OF_MONTH, num);
	   return startDT.getTime();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
