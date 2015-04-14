package com.plate.frame.security;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-24
 * @描述：
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 设置验证码
	 */
	private String captcha;

	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
