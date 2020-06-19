package jp.co.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class LoginForm {

	@NotNull(message="IDは必須です。aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
	private String loginId;
	@NotBlank(message="IDは必須です。")
	private String password;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}




}
