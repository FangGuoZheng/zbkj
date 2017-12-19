package com.portal.interceptor;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.portal.pageModel.SessionInfo;


public class DefaultInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2484100342544349272L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		SessionInfo sessionInfo = (SessionInfo) session.get("sessionInfo");
	/*	if(sessionInfo==null)
		{
			System.out.println("interceptor");
			return "index";   //返回index页面
		}
		else
		{
			invocation.invoke();
		}
		*/
		invocation.invoke();
		return null;
	}

	

}
