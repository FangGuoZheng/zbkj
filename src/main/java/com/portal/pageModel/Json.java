package com.portal.pageModel;

public class Json implements java.io.Serializable {

	private static final long serialVersionUID = 1511000325055608702L;

	// 返回前台的标识，不管对不对
	private boolean success = false;
	// 返回信息
	private String msg = "";
	// 返回前台的对象
	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
