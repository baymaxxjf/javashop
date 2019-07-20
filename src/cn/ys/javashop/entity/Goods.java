package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;

public class Goods extends BaseEntity {
	private String goodsName;
	private String proArea;
	private String spec;
	private BigDecimal price;
	private String picture;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getProArea() {
		return proArea;
	}
	public void setProArea(String proArea) {
		this.proArea = proArea;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Goods [goodsName=" + goodsName + ", proArea=" + proArea + ", spec=" + spec + ", price=" + price
				+ ", picture=" + picture + "]";
	}
	
	
}
