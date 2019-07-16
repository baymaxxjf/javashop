package cn.ys.javashop.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Gift extends BaseEntity {
	private String giftName;
	private BigDecimal price;
}
