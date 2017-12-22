package com.portal.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.JSONObject;
import com.portal.pageModel.UserPage;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/")

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 3265819587502402507L;

	// 用于写回前台的数据，转换成json对象
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 用于对密码进行加密
	public String toSHA256Code(String password){
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] passBytes = password.getBytes();
			byte[] passHash = sha256.digest(passBytes);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< passHash.length ;i++) {
				sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
			}
			String generatedPassword = sb.toString();
			return generatedPassword;
		}catch  (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 访问第三方URL来获得用户信息
	 */
	public UserPage getUserByURL(String areacode, String mobile, Integer saleId){
		StringBuffer bs = new StringBuffer();
		try {
			URL url = new URL("http://60.205.141.183/third/contacts/profile/mobile");
			HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
			urlcon.setRequestMethod("POST");
			urlcon.setRequestProperty("contentType", "utf-8");
			urlcon.setDoOutput(true);  // 是否输入参数
			StringBuffer params = new StringBuffer();
			params.append("appid").append("=").append("03d98797a2be45fca26792f559949fa3").append("&")
					.append("appsecret").append("=").append("e40bb81d42994b2eb8ac3224304d2b9b").append("&")
					.append("areacode").append("=").append(areacode).append("&")
					.append("mobile").append("=").append(mobile);
			byte[] bypes = params.toString().getBytes();
			urlcon.getOutputStream().write(bypes);
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String l = null;
			while((l=buffer.readLine())!=null){
				bs.append(l);
			}
		}catch (Exception e){
			System.out.println("----------"+ e.getMessage());
			// 下面的返回值，是为了在调用第三方接口出错时，给用户提示
			UserPage up = new UserPage();
			up.setId(0);
			return up;
		}

		Object o = JSON.parse(bs.toString());
		Object status = ((JSONObject) o).get("status");
		int code = Integer.parseInt(((JSONObject) status).get("code").toString());
		if (code == 0){
			Object data = ((JSONObject) o).get("data");
			Object user = ((JSONObject) data).get("user");

			UserPage subscriberPage = new UserPage();
			String areacode1 = ((JSONObject) user).get("areacode").toString();
			subscriberPage.setAreacode("".equals(areacode1)?null:areacode1);

			String avatar = ((JSONObject) user).get("avatar").toString();
			subscriberPage.setAvatar("".equals(avatar)?null:avatar);

			String birthdate = ((JSONObject) user).get("birthdate").toString();
			subscriberPage.setBirthdate("".equals(birthdate)?null:birthdate);

			Integer gender = Integer.parseInt(((JSONObject) user).get("gender").toString());
			subscriberPage.setGender(gender);

			String location = ((JSONObject) user).get("location").toString();
			subscriberPage.setLocation("".equals(location)?null:location);

			String mobile1 = ((JSONObject) user).get("mobile").toString();
			subscriberPage.setMobile("".equals(mobile1)?null:mobile1);

			String name = ((JSONObject) user).get("name").toString();
			subscriberPage.setName("".equals(name)?null:name);

			String nickname = ((JSONObject) user).get("nickname").toString();
			subscriberPage.setNickname("".equals(nickname)?null:nickname);

			String id = ((JSONObject) user).get("userid").toString();
			subscriberPage.setUuid(id);

			subscriberPage.setSaleId(saleId);
			return subscriberPage;
		} else{
			return null;
		}
	}

}
