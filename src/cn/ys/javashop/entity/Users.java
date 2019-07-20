package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;


public class Users extends BaseEntity {
	private String loginId;
	private String loginPwd;
	private String realName;
	private BigDecimal score;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Users [loginId=" + loginId + ", loginPwd=" + loginPwd + ", realName=" + realName + ", score=" + score
				+ "]";
	}
	
}
