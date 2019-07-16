package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class Users extends BaseEntity {
	private String loginId;
	private String loginPwd;
	private String realName;
	private BigDecimal score;
	
}
