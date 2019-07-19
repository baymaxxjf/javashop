package cn.ys.javashop.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.entity.OrderDetail;
import cn.ys.javashop.entity.Orders;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.util.DBUtils;

public class OrdersDao {
	public List<Orders> selectOrders(int id){
		String sql = "select u.realName,o.id,o.total,o.addDate,o.isPay from users as u, orders as o where userId = ?";
		ResultSet orders = DBUtils.selectOrders(sql, id);
		Users users = null;
		Orders order =null;
		List<Orders> orderLists = null;
		if(orders != null){
			orderLists = new ArrayList<>();
			try {
				while (orders.next()) {
					List<OrderDetail> orderdetailLists = null ;
					users = new Users();
					order = new Orders();
					users.setRealName(orders.getString("realName"));
					order.setId(orders.getInt("id"));
					orderdetailLists = selectOrderDetails(orders.getInt("id"));
					order.setOrderDetailLists(orderdetailLists);
					order.setUsers(users);
					order.setAddDate(orders.getDate("addDate"));
					order.setIsPay(orders.getString("isPay"));
					order.setTotal(orders.getBigDecimal("total"));
					orderLists.add(order);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
		return orderLists;
		
	}
	
	public List<OrderDetail> selectOrderDetails(int id){
		String sql = "select g.goodsName,od.number,g.price from orderdetail od left join goods g"
				+ " on od.goodsId=g.id where od.orderId =? ";
		ResultSet rs = DBUtils.getResultSet(sql,id);
		Goods good =null;
		OrderDetail orderDetail = null;
		List<OrderDetail> orderdetailLists =null;
		if(rs !=null){
			orderdetailLists = new ArrayList<>();
			try {
				while (rs.next()) {
					orderDetail = new OrderDetail();
					good = new Goods();
					good.setGoodsName(rs.getString("goodsName"));
					good.setPrice(rs.getBigDecimal("price"));
					orderDetail.setNumber(rs.getBigDecimal("number"));
					orderDetail.setGoods(good);
					orderdetailLists.add(orderDetail);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderdetailLists;
	}

	public int updateOrders(int id, HashMap<Integer, Integer> gooIdToNumMap, BigDecimal count) {
		int total = countOrders(id);
		if(total != -1){
			Date date =new Date();
			String dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			Timestamp timestamp = Timestamp.valueOf(dateFormat);
			String sql1 = "insert into orders (id, userId, total, addDate, isPay) VALUES (?, ?, ?, ?, '0');";
			boolean rs = DBUtils.update(sql1, total+1, id , count, timestamp );
			if(rs){
				for(Entry<Integer, Integer> entry :gooIdToNumMap.entrySet()){
					String sql2 = "insert into orderdetail ( orderId, goodsId, number) VALUES ( ?, ?, ?);";
					boolean rs1 = DBUtils.update(sql2, total+1,entry.getKey(), entry.getValue() );
					if(!rs1){
						total = -1;
					}
				}
				
			}else{
				total = -1;
			}
		}else{
//			total = -1;
		}
		return total+1;
	}
	
	private int countOrders(int id){
		String sql = "select count(*) total from orders where userId = ?";
		ResultSet rs = DBUtils.selectOrders(sql, id);
		int total =-1;
		if(rs != null){
			try{
				if(rs.next()){
					total = rs.getInt("total");
				}
			}catch (Exception e) {
			}
		}
		return total;
	}

	public boolean updatePay(int id, int orderId,String isPay) {
		String sql = "update orders set isPay = ? where userId = ? and id = ?";
		Boolean resultSet = DBUtils.update(sql, isPay, id, orderId);
		if(resultSet){
			return true;
		}
		return false;
	}
}
