package com.portal.pageModel;

public class StaffPage {
	private Integer staffId;
	private String account;
	private String name;
	private String phone;
	private String email;
	private Integer roleId;
	private String roleName;
	private String password;
	private String question;
	private String answer;
	private int pageSize;
	private int page;

	public StaffPage() {
		this.pageSize=50;
		this.page=1;
	}

	public StaffPage(Integer staffId, String account, String name, String phone, String email, Integer roleId, String roleName, String password, String question, String answer, int pageSize, int page) {
		this.staffId = staffId;
		this.account = account;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.pageSize = pageSize;
		this.page = page;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
