package cn.ys.javashop.service;

import java.util.Random;
import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;

import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Gift;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;

public class TryLuckyService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	private Common common = new Common();
	private UsersDao usersDao = new UsersDao();
	public void enterTryLucky() {
		// 错误输入计数
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) 
			){
				UI.tryLuckyUI();;
				System.out.print("请输入:");
			}else {
				System.out.print("输入错误，请重新输入:");	
			}
			option = scanner.next();
			
			switch (option) {
			case "1":
				selectLuckyNumber();
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
	
	public void selectLuckyNumber() {
		Users user = common.login();
		boolean selectLuckyNumberFlag = true;
		if(user !=null){
			// 3次抽奖机会
			int chance = 3;
			Random random =new Random();
			do {
				chance--;
				System.out.println("请在0-9之间输入任意选择一个数，作为你的幸运数字。");
				int luckNumber;
				
				 while (true) {
					System.out.println("请输入：");
					try {
						
						luckNumber = scanner.nextInt();
						break;
					} catch (Exception e) {
						System.out.println("输入错误，请重试");
						scanner.next();
					}
				};
				int randomLuckNumber = random.nextInt(10);
				if(luckNumber == randomLuckNumber){
					boolean result = usersDao.addUserGift(user.getId(),luckNumber+1);
					if(result){
						System.out.println("恭喜，您获得如下礼物：");
						Gift gift = usersDao.selectGiftById(luckNumber+1);
						System.out.println("名称："+gift.getGiftName()+"，价值："+gift.getPrice()+",可在'我的礼物中查看'" );
						if(chance>-1){ 
							do {
								System.out.println("继续请按1，返回请按#");
								System.out.println("请输入：");
								String option = scanner.next();
								if("1".equals(option)){
									break;
								}else if("#".equals(option)){
									selectLuckyNumberFlag =false;
									break;
								}else {
									System.out.println("输入错误，请重试");
								}
							} while (true);
							
						}else{
							commonStep();
							break;
						}
						
						
					}else{
						System.out.println("更新失败");
					}
				}else {
					
					System.out.println("很遗憾，你未能获取礼物，本次幸运数是："+randomLuckNumber+"你还有"+chance+"机会");
					if(chance == 0) {
						break;
					}
					do {
						System.out.println("继续请按1，返回请按#");
						System.out.println("请输入：");
						String option = scanner.next();
						if("1".equals(option)){
							break;
						}else if("#".equals(option)){
							selectLuckyNumberFlag =false;
							break;
						}else {
							System.out.println("输入错误，请重试");
						}
					} while (true);
				}
			} while (selectLuckyNumberFlag);
		}
	}
	
	private void commonStep() {
		System.out.println("按#返回上一级,按0返回顶层。");
		int count = 0;
		boolean flag =true;
		do {	
			System.out.println("请输入：");
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
