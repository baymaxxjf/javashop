package cn.ys.javashop.entity;

import java.math.BigDecimal;




public class Gift extends BaseEntity {
	private String giftName;
	private BigDecimal price;
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Gift [giftName=" + giftName + ", price=" + price + "]";
	}
	
}
