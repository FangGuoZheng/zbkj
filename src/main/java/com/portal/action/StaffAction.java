package com.portal.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.portal.email.SendMail;
import com.portal.model.Staff;
import com.portal.pageModel.SessionInfo;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.portal.pageModel.Json;
import com.portal.pageModel.StaffPage;
import com.portal.service.StaffServiceI;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "StaffAction")
public class StaffAction extends BaseAction implements ModelDriven<StaffPage>{

	private static final long serialVersionUID = 4426765055782995839L;

	// 记录日志
	private static final Logger logger = Logger.getLogger(StaffAction.class);

	private StaffServiceI staffService;
	@Autowired
	public void setStaffService(StaffServiceI staffService) {
		this.staffService = staffService;
	}

	StaffPage staffPage=new StaffPage();
	@Override
	public StaffPage getModel() {
		return staffPage;
	}

	/**
	 * 验证密码输入是否正确
	 */
	public void verifyPassword(){
		if (staffPage.getPassword() != null){
			staffPage.setPassword(toSHA256Code(staffPage.getPassword()));
		}
		Json j = new Json();
		boolean flag = staffService.verifyPassword(staffPage);
		if (flag) {
			j.setSuccess(true);
			j.setMsg("旧密码输入正确");
		} else {
			j.setSuccess(false);
			j.setMsg("旧密码输入错误");
		}
		j.setObj(flag);
		super.writeJson(j);
	}

	/**
	 * 前台登陆Action
	 */
	public void login(){
		if (staffPage.getPassword() != null){
			staffPage.setPassword(toSHA256Code(staffPage.getPassword()));
		}
		Json j = staffLogin(staffPage);
		super.writeJson(j);
	}
	public Json staffLogin(StaffPage staffPage) {
		Json j = new Json();
		SessionInfo sessionInfo = staffService.login(staffPage);
		if (sessionInfo != null) {
			// 将登录信息放到session中
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("sessionInfo", sessionInfo);

			j.setObj(sessionInfo);
			j.setSuccess(true);
			j.setMsg("登录成功");
		} else {
			j.setSuccess(false);
			j.setMsg("登录失败");
		}
		return j;
	}

	/**
	 * 退出登录
	 */
	public void logout() {
		// 删除session中员工登录信息
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("sessionInfo");
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("退出登录成功");
		super.writeJson(j);
	}

	/*
	 * 根据条件获取员工列表，分页
	 */
	public void getStaff()
	{
		List<StaffPage> list=staffService.getAll(staffPage.getRoleName(), staffPage.getAccount(), staffPage.getName(),
				staffPage.getPhone(), staffPage.getEmail(),staffPage.getPageSize(),staffPage.getPage());
		Json j = new Json();
		logger.info("查询员工成功");
	    j.setSuccess(true);
	    j.setMsg("查询员工成功");
	    j.setObj(list);
	    super.writeJson(j);
	}

	/*
	 * 根据查询条件获取员工的数目
	 */
	public void getStaffCount()
	{
		int count=staffService.getAllCount(staffPage.getRoleName(), staffPage.getAccount(), staffPage.getName(),
				staffPage.getPhone(), staffPage.getEmail());
		Json j = new Json();
		logger.info("查询员工数目成功");
	    j.setSuccess(true);
	    j.setMsg("查询员工数目成功");
	    j.setObj(count);
	    super.writeJson(j);
	}
	/*
	 * 添加员工
	 */
	public void add()
	{
		if(checkStaffInformessage(staffPage)) {
			if (staffPage.getPassword() != null){
				staffPage.setPassword(toSHA256Code(staffPage.getPassword()));
			}
			boolean result=staffService.add(staffPage);
			if(result) {
				Json j = new Json();
				logger.info("添加员工成功");
				j.setSuccess(true);
				j.setMsg("添加员工成功");
				j.setObj(true);
				super.writeJson(j);
			}
			else {
				Json j = new Json();
				logger.info("添加员工失败");
				j.setSuccess(true);
				j.setMsg("添加员工失败");
				j.setObj(false);
				super.writeJson(j);
			}
		}
		else {
			Json j = new Json();
			logger.info("添加员工失败");
		    j.setSuccess(true);
		    j.setMsg("添加员工失败");
		    j.setObj(false);
		    super.writeJson(j);
		}
	}
	/*
	 * 根据账号获取员工信息
	 */
	public void getByAccount()
	{
		StaffPage page=staffService.getByAccount(staffPage.getAccount());
		if(page!=null)
		{
			Json j = new Json();
			logger.info("查询员工成功,account:"+staffPage.getAccount());
			j.setSuccess(true);
			j.setMsg("查询员工成功");
			j.setObj(page);
			super.writeJson(j);
		}
		else
		{
			Json j = new Json();
			logger.info("查询员工失败");
		    j.setSuccess(false);
		    j.setMsg("查询员工失败");
		    super.writeJson(j);
		}
	}

	/**
	 * 根据id号获取员工信息
	 */
	public void getById(){
		StaffPage page = staffService.getById(staffPage.getStaffId());
		Json j = new Json();
		if (page != null){
			j.setSuccess(true);
			j.setMsg("查询员工成功");
			j.setObj(page);
			super.writeJson(j);
		} else{
			j.setSuccess(false);
			j.setMsg("查询员工失败");
			super.writeJson(j);
		}
	}

	/**
	 * 根据role获取员工列表
	 */
	public void getByRole(){
		List<Staff> staffs = staffService.getByRoleId(staffPage.getRoleId());
		Json j = new Json();
		if (staffs != null){
			j.setSuccess(true);
			j.setMsg("根据role获取员工列表成功");
			j.setObj(staffs);
		} else{
			j.setSuccess(false);
			j.setMsg("根据role获取员工列表失败");
			j.setObj(null);
		}
		super.writeJson(j);
	}

	/**
	 * 根据id删除员工
	 */
	public void deleteById(){
		Integer staffId = staffPage.getStaffId();
		Json j = new Json();
		if (staffId == null){
			Logger.getLogger(StaffAction.class).info("staffId is null, please checkout.");
			j.setSuccess(false);
			j.setMsg("删除员工失败");
			super.writeJson(j);
		} else {
			boolean result = staffService.deleteById(staffId);
			if(result) {
				j.setSuccess(true);
				j.setMsg("删除员工成功");
				j.setObj(true);
				super.writeJson(j);
			} else {
				j.setSuccess(false);
				j.setMsg("删除员工失败");
				super.writeJson(j);
			}
		}
	}

	public void delete()
	{
		String[] accounts=staffPage.getAccount().split(";");
		boolean result=true;
		for(int i=0;i<accounts.length;i++)
		{
			if(accounts[i].length()==0)
				continue;
			result=staffService.deleteByAccount(accounts[i]);
			if(!result)
				break;
		}
		if(result)
		{
			Json j = new Json();
			logger.info("删除员工成功,account:"+staffPage.getAccount());
			j.setSuccess(true);
			j.setMsg("删除员工成功");
			j.setObj(true);
			super.writeJson(j);
		}
		else
		{
			Json j = new Json();
			logger.info("删除员工失败");
		    j.setSuccess(false);
		    j.setMsg("删除员工失败");
		    super.writeJson(j);
		}
	}
	/*
	 * 编辑员工信息
	 */
	public void edit()
	{
		boolean result=staffService.edit(staffPage);
		if(result)
		{
			Json j = new Json();
			logger.info("修改员工信息成功,account:"+staffPage.getAccount());
			j.setSuccess(true);
			j.setMsg("修改员工信息成功");
			j.setObj(true);
			super.writeJson(j);
		}
		else
		{
			Json j = new Json();
			logger.info("修改员工信息失败");
		    j.setSuccess(false);
		    j.setMsg("修改员工信息失败");
		    super.writeJson(j);
		}
	}
	/*
	 * 修改密码
	 */
	public void editPassword()
	{
		if (staffPage.getPassword() != null){
			staffPage.setPassword(toSHA256Code(staffPage.getPassword()));
		}
		boolean result=staffService.editPassword(staffPage);
		if(result)
		{
			Json j = new Json();
			logger.info("修改员工密码成功,account:"+staffPage.getAccount());
			j.setSuccess(true);
			j.setMsg("修改员工密码成功");
			j.setObj(true);
			super.writeJson(j);
		}
		else
		{
			Json j = new Json();
			logger.info("修改员工密码失败");
		    j.setSuccess(false);
		    j.setMsg("修改员工密码失败");
		    super.writeJson(j);
		}
	}

	/**
	 * 重置密码
	 */
	public void resetTPW() {
		// 返回前台的json数据
		Json j = new Json();
		StaffPage result = staffService.getByAccount(staffPage.getAccount());
		if (result != null) {
			try {

				SendMail mail = new SendMail();
				// 收信人
				String[] list = { result.getEmail() };
				// mail.setMailTo(list, "cc");
				mail.setMailTo(list, "to");
				// 发信人
				mail.setMailFrom("dhuoj_noreply@163.com");
				// mail.setMailFrom("duzhen");
				// 邮件主题
				mail.setSubject("KGO-M4系统重置密码激活邮件");
				// 邮件发送时间
				mail.setSendDate(new Date());
				// html格式邮件
				// 邮件内容
				String context = "<html>"
						+ "<body>"
						+ "您将重置KGO-M4系统的登录密码，如果这是您在进行的操作，请点击链接(如果点击无效，请复制以下链接并在浏览器中打开):<br>"
						+ "<a href='http://localhost:8080/admin/ResetPassword.jsp?account="
						+ result.getAccount()
						+ "&staffId="+result.getStaffId()
						+ "'>http://localhost:8080/admin/ResetPassword.jsp?account="
						+ result.getAccount()
						+ "&staffId="+result.getStaffId()
						+ "</a>" + "</body>" + "</html>";
				mail.addHtmlContext(context);
				// txt格式邮件
				// mail.addTextContext("");
				mail.send();
				System.out.println("send success");
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("重置密码邮件发送成功");
			j.setSuccess(true);
			j.setMsg("重置密码邮件发送成功");
			j.setObj(result);
		} else {
			logger.info("重置密码邮件发送失败");
			j.setSuccess(false);
			j.setMsg("重置密码邮件发送失败");
			j.setObj(null);
		}
		super.writeJson(j);
	}

	/*
	 * 检查员工的信息是否合法
	 */
	public boolean checkStaffInformessage(StaffPage cstaff){  //检查员工的信息是否合法

		if(cstaff.getAccount() == null || cstaff.getAccount().length() == 0) {
			logger.info("员工账号为空,创建失败！");
			return false;
		}
		if(cstaff.getName() == null || cstaff.getName().length() == 0) {
			logger.info("员工姓名为空,创建失败！");
			return false;
		}
		if(cstaff.getEmail() == null || cstaff.getEmail().length()==0) {
			logger.info("员工邮箱为空,创建失败！");
			return false;
		}
		if(cstaff.getPhone() == null || cstaff.getPhone().length()==0) {
			logger.info("员工电话为空,创建失败！");
			return false;
		}
		if(cstaff.getPassword() == null || cstaff.getPassword().length()==0) {
			logger.info("员工密码为空,创建失败！");
			return false;
		}
		if(cstaff.getRoleId() == null) {
			logger.info("员工类型为空,创建失败！");
			return false;
		}
		return true;
	}

}
