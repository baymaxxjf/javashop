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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getIsPay() {
		return isPay;
	}
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}
	public List<OrderDetail> getOrderDetailLists() {
		return orderDetailLists;
	}
	public void setOrderDetailLists(List<OrderDetail> orderDetailLists) {
		this.orderDetailLists = orderDetailLists;
	}
	@Override
	public String toString() {
		return "Orders [userId=" + userId + ", users=" + users + ", total=" + total + ", addDate=" + addDate
				+ ", isPay=" + isPay + ", orderDetailLists=" + orderDetailLists + "]";
	}
	
}
