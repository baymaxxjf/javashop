package cn.ys.javashop.service;

import java.util.List;
import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.dao.OrdersDao;
import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Orders;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;
import cn.ys.javashop.util.DBUtils;

public class MemberCenterService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	private UsersDao usersDao = new UsersDao();
	private OrdersDao odersDao = new OrdersDao();
	private Common common = new Common();
	public void enterMemberCenter() {
		
		// 错误输入计数
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) || "3".equals(option) ||
			   "4".equals(option)
			){
				UI.memberCenterUI();
				System.out.print("请输入:");
			}else {
				System.out.print("输入错误，请重新输入:");	
			}
			option = scanner.next();
			
			switch (option) {
			case "1":
				updatePassword();
				break;
			case "2":
				myOrder();
				break;
			case "3":
				myGift();
				break;
			case "4":
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
	 
	
	public void updatePassword(){
		Users result = common.login();
		if(result !=null ){
			int count = 0;
			boolean flag1 =true;
			outerLoop:
			do {
				
				System.out.println("提供密码登录的功能，请输入新密码：");
				String newPassword =scanner.next();
				System.out.println("请再一次输入新密码确认：");
				String confirmPassword = scanner.next();
				if(newPassword!="" && newPassword.equals(confirmPassword)){
					boolean updateResult = usersDao.updatePassword(result.getId(),newPassword);
					if(updateResult){
						System.out.println("更新成功，按#返回上一级,按0返回顶层。请输入：");
						int innerCount = 0;
						boolean flag2 =true;
						do {
							
							String option = scanner.next();
							if("#".equals(option) ){
								break outerLoop;
							}else if("0".equals(option)){
								topFlag = false;
								break outerLoop;
							}else{
								innerCount++;
								if(innerCount>Contant.MAX_TRY){
									System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
									// flag2 = false;
									break outerLoop;
								}else{
									System.out.println("输入不合法，请输入重新输入：");
								}			
							}
							
						} while (flag2);	
						
					}else{
						System.out.println("更新密码失败");
					}
					
				}else {
					count++;
					System.out.println("新密码不能为空或两次密码不一致，请重试。");
				}
				if(count>Contant.MAX_TRY){
					System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
					// flag = false;
					break outerLoop;
				}
			} while (flag1);
		}
	}
	
	
	public boolean updateUser() {
		return true;
	}
	
	public void myOrder() {
		Users user = common.login();
		if(user !=null){
			List<Orders> orderLists = odersDao.selectOrders(user.getId());
			System.out.println(orderLists);
			UI.orderInfoUI(orderLists);
		}else{
			
		}
		System.out.println("这是我的订单");
		
	}
	
	public void myGift(){
		
	}


	public void login1() {

		boolean flag = true;
		int outerCount = 0;
		outerLoop:
		do {
			System.out.println("************以下操作需要您先登录，请输入用户名************");
			outerCount++;
			String userName = scanner.next();
			System.out.println("请输入登录密码:");
			String password = scanner.next();
			boolean result = updateUser();
			if(result){
				System.out.println("************登录成功，接下来进入登录之前的操作************");
				int count = 0;
				boolean flag1 =true;
				do {
					count++;
					System.out.println("提供密码登录的功能，请输入新密码：");
					String newPassword =scanner.next();
					System.out.println("请再一次输入新密码确认：");
					String confirmPassword = scanner.next();
					if(newPassword!="" && newPassword == confirmPassword){
						boolean updateResult = updateUser();
						if(updateResult){
							System.out.println("更新成功，按#返回上一级,按0返回顶层。请输入：");
							int innerCount = 0;
							boolean flag2 =true;
							do {
								count++;
								String option = scanner.next();
								if("#".equals(option) ){
									break outerLoop;
								}else if("0".equals(option)){
									topFlag = false;
									break outerLoop;
								}else{
									if(innerCount>Contant.MAX_TRY){
										System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
										// flag2 = false;
										break outerLoop;
									}else{
										System.out.println("输入不合法，请输入重新输入：");
									}			
								}
								
							} while (flag2);	
							
						}
						
					}else {
						System.out.println("新密码不能为空或两次密码不一致，请重试。");
					}
					if(count>Contant.MAX_TRY){
						System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
						// flag = false;
						break outerLoop;
					}
				} while (flag1);
			}else {			
				System.out.println("用户名或密码错误。请选择下一步操作：继续请按1，返回上一层请按#");
				int count = 0;
				boolean flag2 =true;
				do {
					count++;
					String option = scanner.next();
					if("#".equals(option) ){
						// flag2= false;
						// flag = false;
						break outerLoop;
					}else if("1".equals(option)){
						flag2= false;
					}else{
						if(count>Contant.MAX_TRY){
							System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
							// flag2 = false;
							break outerLoop;
						}else{
							System.out.println("输入不合法，请输入重新输入：");
						}			
					}
					
				} while (flag2);	
			}
			if(outerCount>Contant.MAX_TRY){
				System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
				// flag = false;
				break outerLoop;
			}
		} while (flag);
		
	}
}
