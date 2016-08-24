package com.heetian.spider.proxy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heetian.spider.proxy.service.ProxyServiceIml;
import com.heetian.spider.proxy.service.ProxyServiceInter;

/**
 * Servlet implementation class ShowCharset
 */
public class ShowCharset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProxyServiceInter service = new ProxyServiceIml();
		request.setAttribute("types", service.showAllType());
		request.getRequestDispatcher("/showCharsets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
