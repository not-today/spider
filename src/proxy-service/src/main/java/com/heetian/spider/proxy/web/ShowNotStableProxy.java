package com.heetian.spider.proxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.proxy.service.ProxyServiceIml;
import com.heetian.spider.proxy.service.ProxyServiceInter;

/**
 * Servlet implementation class ShowNotStableProxy
 */
public class ShowNotStableProxy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProxyServiceInter service = new ProxyServiceIml();
		List<Proxy> proxys = service.showAllNotStableProxys();
		request.setAttribute("title", "基准代理");
		request.setAttribute("notstableProxys", proxys);
		request.setAttribute("pvs", PvnObj.init32());
		request.getRequestDispatcher("/showNotStableProxys.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
