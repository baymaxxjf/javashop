package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class Goods extends BaseEntity {
	private String goodsName;
	private String proArea;
	private String spec;
	private BigDecimal price;
	private String picture;
	
}
