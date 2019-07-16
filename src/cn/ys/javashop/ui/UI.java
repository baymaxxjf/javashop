package cn.ys.javashop.ui;

import java.util.List;

import cn.ys.javashop.entity.OrderDetail;
import cn.ys.javashop.entity.Orders;

public class UI {
	
	public static void showMainUI() {
		System.out.println("*********请选择您要的操作***********");
		System.out.println("*          1.会员中心                              *");
		System.out.println("*          2.进入购物                              *");
		System.out.println("*          3.试试手气                              *");
		System.out.println("********************************");
	}
	
	public static void memberCenterUI() {
		System.out.println("*********请选择您要的操作***********");
		System.out.println("*          1.修改资料                              *");
		System.out.println("*          2.我的订单                               *");
		System.out.println("*          3.我的礼物                             *");
		System.out.println("*          4.返回上层                             *");
		System.out.println("********************************");
	}
	
	public static void shopingUI() {
		System.out.println("*********请选择您要的操作***********");
		System.out.println("*          1.选择商品                             *");
		System.out.println("*          2.返回上层                              *");
		System.out.println("********************************");
	}
	
	public static void tryLuckyUI() {
		System.out.println("*********请选择您要的操作***********");
		System.out.println("*          1.开始手气之旅                            *");
		System.out.println("*          2.返回上层                              *");
		System.out.println("********************************");
	}
	
	public static void orderInfoUI(List<Orders> orderLists) {
		System.out.println("您的订单信息如下：");
		System.out.println("订单信息如下：");
		System.out.println("订单数量："+ orderLists.size());
		for(Orders item : orderLists){
			System.out.println("------------------------------------------------------------------");
			System.out.println("订单号：      订购用户名：     订购金额：         订单日期：            是否支付：");
			String isPay="1".equals(item.getIsPay())?"是":"否";
			System.out.println(item.getId()+"\t"+ 
							   item.getUsers().getRealName()+"\t"+ 
							   item.getTotal()+"\t"+ 
							   item.getAddDate()+"\t"+ isPay
							   
			);
			System.out.println("商品明细如下：");
			System.out.println("商品名称            商品单价          商品数量           小计金额 ");
			for(OrderDetail item : item.get){
				
			}
			System.out.println("------------------------------------------------------------------");
		}
	}
	
	

}
