package cn.ys.javashop.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.dao.GoodsDao;
import cn.ys.javashop.dao.OrdersDao;
import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;
import cn.ys.javashop.util.DBUtils;

public class ShoppingService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	private Common common = new Common();
	private GoodsDao goodsDao = new GoodsDao();
	private OrdersDao ordersDao = new OrdersDao();
	public void enterShopping() {
		// 错误输入计数
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) 
			){
				UI.shopingUI();
				System.out.print("请输入:");
			}else {
				System.out.print("输入错误，请重新输入:");	
			}
			option = scanner.next();
			
			switch (option) {
			case "1":
				selectShops();
				break;
			case "2":
				topFlag = false;
				break;
			default:
				i++;
				if(i>Contant.MAX_TRY){
					System.out.println("输入错误次数过多，退回上一级");
					topFlag = false;
				}
				break;
			}
		} while (topFlag);
	}
	
	
	
	public void selectShops() {
		Users user = common.login();
		if(user !=null){
			List<Goods> goodsLists = goodsDao.selectGoods();
			UI.goodInfoUI(goodsLists);		
			//
			HashMap<Integer, Integer> gooIdToNumMap= new HashMap<>();
			// 计算总订单金额
			BigDecimal total = new BigDecimal(0);
			boolean selectShopsFlag = true;
			int count = 0;
			do {
				boolean flag = true;
				do {
					System.out.println("请输入：");
					try {
						int goodsId = scanner.nextInt();
						for(int i=0 ;i<goodsLists.size(); i++ ){
							if(goodsId == goodsLists.get(i).getId()){
								count++;
								total = total.add(goodsLists.get(i).getPrice());
								if(gooIdToNumMap.containsKey(goodsId)){
									Integer value = gooIdToNumMap.get(goodsId)+1;
									gooIdToNumMap.put(goodsId, value);
								}else{
									gooIdToNumMap.put(goodsId, 1);
								}
								flag =false;
								break;
							}
							if(i==(goodsLists.size()-1)){
								System.out.println("输入的商品编号不合法，请重新输入");
							}
						}
						
					} catch (Exception e) {
						System.out.println("输入的商品编号不合法，请重新输入");
					}	
				} while (flag);
				
				System.out.println("商品已加入到购物车，继续选购请按1，结算请按2，返回请按#");
				String option = scanner.next();
				if("2".equals(option)){
					int orderId = ordersDao.updateOrders(user.getId(), gooIdToNumMap,total);
					if(orderId != -1){
						do {
							System.out.println("您的购物车中有个"+count+"产品，确定购买吗？按y付款，按#退出");
							String isPay = scanner.next();
							if("y".equals(isPay)){
								boolean hasPay = ordersDao.updatePay(user.getId(), orderId,"1");
								if(hasPay){
									System.out.println("购买成功,有"+count+"条产品被购买");
									commonStep();
									selectShopsFlag =false;
									break;
								}else{
									System.out.println("更新失败");
								}
							}else if ("#".equals(isPay)){
								System.out.println("退出购买");
								selectShopsFlag =false;
								break;
							}else {
								System.out.println("输入有误，请重试");
							}
						} while (true);
						
					}else{
						System.out.println("订单插入失败");
					}
				}else if("#".equals(option)){
					break;
				}else if("1".equals(option)){
					
				}else {
					System.out.println("你输入的指令不合法，请重新输入");
				}
				
			} while (selectShopsFlag);
		
		}
	}
	
	
	private void commonStep() {
		System.out.println("更新成功，按#返回上一级,按0返回顶层。请输入：");
		int count = 0;
		boolean flag =true;
		do {	
			String option = scanner.next();
			if("#".equals(option) ){
				break ;
			}else if("0".equals(option)){
				topFlag = false;
				break ;
			}else{
				count++;
				if(count>Contant.MAX_TRY){
					System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
					break ;
				}else{
					System.out.println("输入不合法，请输入重新输入：");
				}			
			}
		} while (flag);
	}
}
