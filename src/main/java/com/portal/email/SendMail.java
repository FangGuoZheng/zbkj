package com.portal.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 发送邮件类
 * 
 * @author Administrator
 * 
 */
public class SendMail {

	private String smtpserver = "smtp.163.com";// PropertiesTools.getNovaPropertiesByKey(PropertiesKeyEnum.mailSmtpServer.key);
	// smtp认证用户名 dhuoj_noreply@163.com
	private String username = "dhuoj_noreply@163.com";// PropertiesTools.getNovaPropertiesByKey(PropertiesKeyEnum.mailUser.key);
	// smtp认证用密码 dhuoj124
	private String password = "dhuoj124";// PropertiesTools.getNovaPropertiesByKey(PropertiesKeyEnum.mailPassword.key);
	// 邮件内容
	protected List<BodyPart> bodypartArrayList = null;
	// 发信人
	private InternetAddress mailFromAddress = null;
	// 收信人
	private InternetAddress mailToAddress = null;
	// Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	private Multipart multipart = null;
	// MIME邮件对象
	private MimeMessage mailMessage = null;
	// 邮件会话对象
	private Session mailSession = null;
	// 系统属性
	private Properties mailProperties = System.getProperties();

	// 邮件服务器

	/**
	 * 发送邮件类构造函数，默认邮件服务器及用户名密码
	 */
	public SendMail() {
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		mailProperties.put("mail.smtp.host", smtpserver);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证
		// mailProperties.put("mail.smtp.port", "25");
		mailProperties.put("mail.smtp.auth", "true");
		mailSession = Session.getDefaultInstance(mailProperties);
		// mailSession=Session.getInstance(mailProperties, popupAuthenticator);
		mailSession.setDebug(false);
		mailMessage = new MimeMessage(mailSession);
		multipart = new MimeMultipart();
		bodypartArrayList = new ArrayList<BodyPart>();
	}

	/**
	 * 发送邮件类构造函数，需要设定邮件服务器及用户名密码
	 * 
	 * @param smtpHost
	 *            邮件服务器
	 * @param username
	 *            邮件用户名
	 * @param password
	 *            密码
	 */
	public SendMail(String smtpHost, String username, String password) {
		this.smtpserver = smtpHost;
		this.username = username;
		this.password = password;
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		mailProperties.put("mail.smtp.host", smtpserver);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证
		mailProperties.put("mail.smtp.auth", "true");
		mailSession = Session.getDefaultInstance(mailProperties);
		mailSession.setDebug(false);
		mailMessage = new MimeMessage(mailSession);
		multipart = new MimeMultipart();
		bodypartArrayList = new ArrayList<BodyPart>();
	}

	/**
	 * 设置发信人
	 * 
	 * @param mailFrom
	 *            发信人
	 * @throws MessagingException
	 */
	public void setMailFrom(String mailFrom) throws MessagingException {
		try {
			mailFromAddress = new InternetAddress(mailFrom, "KGO-M4系统管理员");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailMessage.setFrom(mailFromAddress);

	}

	/**
	 * 设置收信人
	 * 
	 * @param mailTo
	 *            收信人列表
	 * @param mailType
	 *            收信人类别
	 * @throws Exception
	 */
	public void setMailTo(String[] mailTo, String mailType) throws Exception {
		for (int i = 0; i < mailTo.length; i++) {
			mailToAddress = new InternetAddress(mailTo[i]);
			if (mailType.equalsIgnoreCase("to")) {
				// 收件人
				mailMessage.addRecipient(Message.RecipientType.TO,
						mailToAddress);
			} else if (mailType.equalsIgnoreCase("cc")) {
				// 抄送
				mailMessage.addRecipient(Message.RecipientType.CC,
						mailToAddress);
			} else if (mailType.equalsIgnoreCase("bcc")) {
				// 暗送
				mailMessage.addRecipient(Message.RecipientType.BCC,
						mailToAddress);
			} else {
				throw new Exception("Unknown mailSendType: " + mailType + "!");
			}
		}
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param mailSubject
	 *            邮件主题
	 * @throws MessagingException
	 */
	public void setSubject(String mailSubject) throws MessagingException {
		mailMessage.setSubject(mailSubject);
	}

	/**
	 * 设置发送日期
	 * 
	 * @param sendDate
	 *            发送日期
	 * @throws MessagingException
	 */
	public void setSendDate(Date sendDate) throws MessagingException {
		mailMessage.setSentDate(sendDate);
	}

	/**
	 * 文本格式
	 * 
	 * @param textcontent
	 *            邮件内容
	 * @throws MessagingException
	 */
	public void addTextContext(String textcontent) throws MessagingException {
		BodyPart bodypart = new MimeBodyPart();
		bodypart.setContent(textcontent, "text/plain;charset=GB2312");
		bodypartArrayList.add(bodypart);
	}

	/**
	 * html格式
	 * 
	 * @param htmlContent
	 *            邮件内容
	 * @throws MessagingException
	 */
	public void addHtmlContext(String htmlContent) throws MessagingException {
		BodyPart bodypart = new MimeBodyPart();
		bodypart.setContent(htmlContent, "text/html;charset=GB2312");
		bodypartArrayList.add(bodypart);
	}

	/**
	 * 发送邮件
	 * 
	 * @throws MessagingException
	 * @throws SendFailedException
	 */
	public synchronized void send() throws MessagingException,
			SendFailedException {
		// 邮件内容
		for (int i = 0; i < bodypartArrayList.size(); i++) {
			multipart.addBodyPart(bodypartArrayList.get(i));
		}
		mailMessage.setContent(multipart);
		mailMessage.saveChanges();
		// mailMessage.setReplyTo(new InternetAddress[]{new
		// InternetAddress("OJ系统管理员@dhu.edu.cn")});
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(smtpserver, username, password);
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		transport.close();
	}

	/**
	 * 为邮件添加附件
	 * 
	 * @param fileName
	 *            文件名
	 * @param displayFileName
	 *            邮件显示文件名
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void addAttachment(String fileName, String displayFileName)
			throws MessagingException, UnsupportedEncodingException {
		BodyPart bodypart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(fileName);
		DataHandler dh = new DataHandler(fds);
		String displayfilename = "";
		// 设置邮件编码规则
		displayfilename = MimeUtility.encodeWord(displayFileName, "gb2312",
				null);
		// 设置邮件显示文件名
		bodypart.setFileName(displayfilename);
		bodypart.setDataHandler(dh);
		bodypartArrayList.add(bodypart);
	}

//	public static void main(String arg[]) throws Exception {
//		try {
//
//			SendMail mail = new SendMail();
//			// 收信人
//			String[] list = { "1987356692@qq.com" };
//			// mail.setMailTo(list, "cc");
//			mail.setMailTo(list, "to");
//			// 发信人
//			mail.setMailFrom("dhuoj_noreply@163.com");
//			// mail.setMailFrom("duzhen");
//			// 邮件主题
//			mail.setSubject("测试邮件");
//
//			// 邮件发送时间
//			mail.setSendDate(new Date());
//			// 附件
//			// mail.addAttachment("C://Users//Administrator//Desktop//parents_logo.png",
//			// "aaa.png");
//			// // html格式邮件
//			// // 邮件内容
//			String context = "发送测试邮件@@@";
//			mail.addHtmlContext(context);
//			// txt格式邮件
//			// // mail.addTextContext("");
//			mail.send();
//			System.out.println("send success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}