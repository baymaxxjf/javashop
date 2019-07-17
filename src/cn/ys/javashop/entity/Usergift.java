package cn.ys.javashop.entity;

import lombok.Data;

@Data
public class Usergift extends BaseEntity {
	private int userId;
	private int giftId;
	private Gift gift;
	
}
