package cn.ys.javashop.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class Orders extends BaseEntity {
	private int userId;
	private Users users;
	private BigDecimal total;
	private Date addDate;
	private String isPay; 
	private List<OrderDetail> orderDetailLists;
}
