package com.heetian.spider.proxy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heetian.spider.proxy.service.ProxyServiceIml;
import com.heetian.spider.proxy.service.ProxyServiceInter;

/**
 * Servlet implementation class EditProxyType
 */
public class EditCharset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCharset() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pvn = request.getParameter("pvn");
		String charset = request.getParameter("charset");
		ProxyServiceInter service = new ProxyServiceIml();
		service.editChaset(pvn,charset);
		response.sendRedirect(request.getContextPath()+"/ShowCharset");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
