package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;

public class OrderDetail extends BaseEntity {
	private int orderId;
	private int goodsId;
	private Orders orders;
	private Goods goods;
	private BigDecimal number;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public BigDecimal getNumber() {
		return number;
	}
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", goodsId=" + goodsId + ", orders=" + orders + ", goods=" + goods
				+ ", number=" + number + "]";
	}
	
}
