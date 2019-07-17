package cn.ys.javashop.main;

import java.util.Scanner;

import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.service.LoginService;
import cn.ys.javashop.service.MemberCenterService;
import cn.ys.javashop.service.ShoppingService;
import cn.ys.javashop.ui.UI;

public class JavaShopAppliction {
	public final static Scanner scanner = new Scanner(System.in);
	public  static LoginService loginService = new LoginService();
	private static MemberCenterService memberCenterService = new MemberCenterService();
	private static ShoppingService shoppingService = new ShoppingService();
	public static void main(String[] args) {
		
		System.out.println("欢迎来到命令行商城！");
		// 设置整个应用是否退出的标记
		boolean flag = true;
		// 错误输入计数
		int  i= 0;
		String option = null;
		do {
			
			if(i==0 ||"1".equals(option) ||
			   "2".equals(option) || "3".equals(option)
			){
				UI.showMainUI();
				System.out.println("请输入:");
				
			}else {
				System.out.println("输入不合法，请重新输入:");
			}
			
			option = scanner.next();
			i++;
			
			switch (option) {
			case "1":
				memberCenterService.enterMemberCenter();
				break;
			case "2":
				shoppingService.enterShopping();
				break;
			case "3":
				break;
				
			default:
				if(i>Contant.MAX_TRY){
					System.out.println("输入错误次数过多，退出系统");
					scanner.close();
					flag = false;
				}
				break;
			}
		} while (flag);
		System.out.println("系统关闭");

	}

}
