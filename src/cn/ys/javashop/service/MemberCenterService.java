package cn.ys.javashop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.dao.GiftsDao;
import cn.ys.javashop.dao.OrdersDao;
import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Orders;
import cn.ys.javashop.entity.Usergift;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;
import cn.ys.javashop.util.DBUtils;

public class MemberCenterService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	private UsersDao usersDao = new UsersDao();
	private OrdersDao odersDao = new OrdersDao();
	private GiftsDao giftsDao = new GiftsDao();
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
			UI.orderInfoUI(orderLists);
			commonStep();
		}else{
			
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
	
	public void myGift(){
		Users user = common.login();
		if(user !=null){
			List<Usergift> giftLists = giftsDao.selectGifts(user.getId());
		}
	}


	
}
