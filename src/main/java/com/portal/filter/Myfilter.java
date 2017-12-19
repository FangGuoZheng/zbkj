package com.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class Myfilter implements Filter{
	public FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.config=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest hrequest = (HttpServletRequest)request;  
	     HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);   
	     String excludeStrings =config.getInitParameter("excludeStrings");  
	     String includeStrings = config.getInitParameter("includeStrings");    
	     String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");  
	     String disabletestfilter = config.getInitParameter("disableFilter");
         if (disabletestfilter.toUpperCase().equals("Y")) {   
	         chain.doFilter(request, response);    //放行
	         return;  
	     }  
         String[] excludeList=excludeStrings.split(";");   //对于默写页面或请求不过滤
         if(isContains(hrequest.getRequestURI(),excludeList))
         {
        	 chain.doFilter(request, response);    //放行
	         return;  
         }
	     String[] includeList = includeStrings.split(";");  //对url过滤
	     if (!this.isContains(hrequest.getRequestURI(), includeList)) { 
	         chain.doFilter(request, response);  
	         return;  
	     }  
	     String user = ( String ) hrequest.getSession().getAttribute("sessionInfo");  
	     if (user == null) 
	     {  
	    	 System.out.println("filter");
	    	 wrapper.sendRedirect(redirectPath);  
	    	 return;  
	     }
	     else 
	     {  
	    	chain.doFilter(request, response);  
	    	return;  
	     }  
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config=config;
	}
	public boolean isContains(String container, String[] regx) {  
        for (int i = 0; i < regx.length; i++) {  
            if(container.equals("zbkj"+regx[i]))
            {
            	return true;
            }
        }  
        return false;  
    }  

}
