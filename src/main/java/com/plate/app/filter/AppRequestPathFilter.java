package com.plate.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-4
 * @描述:
 */
public class AppRequestPathFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(ServletRequest request,ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//客户端请求的IP
		String ip = req.getHeader("x-forwarded-for");
		if(ip == null){
			ip = req.getRemoteAddr();
		}
		//客户端请求地址
		String uri = req.getRequestURI();
		if(uri.contains("/mobile/app/start")){
			chain.doFilter(req, resp);
		}else if(uri.contains("/mobile/know/correct")){
			chain.doFilter(req, resp);
		}else{
			System.out.println("---"+uri);
			
			chain.doFilter(req, resp);
		}
	}
	
}
