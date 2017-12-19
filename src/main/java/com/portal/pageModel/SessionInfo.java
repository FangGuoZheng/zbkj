package com.portal.pageModel;

/**
 * sessionInfo模型
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -4396043396019134319L;

	private Integer staffId;// 用户ID
	private String loginName;// 用户登录名称
	private String loginPassword;// 登录密码
	private String name;
	private String phone;
	private String email;
	private Integer roleId;
	private String roleName;
	private Long createTime;
	private String ip;// IP地址

	private Integer userId ;
	private String uuid;
	private String nickname;
	private String areacode;
	private String mobile;
	private String avatar;
	private Integer gender;
	private String type;
	private Long expireDate;
	private Integer saleId;

	public SessionInfo() {
	}

	public SessionInfo(Integer staffId, String loginName, String loginPassword, String name, String phone, String email, Integer roleId, String roleName, Long createTime, String ip, Integer userId, String uuid, String nickname, String areacode, String mobile, String avatar, Integer gender, String type, Long expireDate, Integer saleId) {
		this.staffId = staffId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
		this.createTime = createTime;
		this.ip = ip;
		this.userId = userId;
		this.uuid = uuid;
		this.nickname = nickname;
		this.areacode = areacode;
		this.mobile = mobile;
		this.avatar = avatar;
		this.gender = gender;
		this.type = type;
		this.expireDate = expireDate;
		this.saleId = saleId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Long expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
}
