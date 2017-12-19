package com.portal.model;

import java.util.Date;

import javax.persistence.*;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "m4_staff")
public class Staff implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1908545072948146720L;
	// Fields
	private Integer id;
	private String account;
	private String password;
	private String name;
	private String phone;
	private String email;
	private Role role;
	private String question;
	private String answer;
	private Date createTime;
	private Boolean deleteflag;  // true表示该条记录被删除，false表示未被删除

	// Constructors

	/** default constructor */
	public Staff() {
		this.deleteflag = false;
	}

	public Staff(Integer id, String account, String password, String name, String phone, String email, Role role, String question, String answer, Date createTime, Boolean deleteflag) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.question = question;
		this.answer = answer;
		this.createTime = createTime;
		this.deleteflag = deleteflag;
	}

	// Property accessors

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	@Column(name = "createtime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
}