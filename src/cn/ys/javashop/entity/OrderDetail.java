package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrderDetail extends BaseEntity {
	private int orderId;
	private int goodsId;
	private Orders orders;
	private Goods goods;
	private BigDecimal number;
	
}
