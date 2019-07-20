package cn.ys.javashop.entity;

import lombok.Data;

public class Usergift extends BaseEntity {
	private int userId;
	private int giftId;
	private Gift gift;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	@Override
	public String toString() {
		return "Usergift [userId=" + userId + ", giftId=" + giftId + ", gift=" + gift + "]";
	}
	
}
