package cn.ys.javashop.common;
import cn.ys.javashop.main.JavaShopAppliction;

import java.util.Scanner;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Users;
public class Common {
    private Scanner scanner = JavaShopAppliction.scanner;
    private UsersDao usersDao = new UsersDao();
    public Users login() {
		boolean flag = true;
		int outerCount = 0;
		outerLoop:
		do {
			System.out.println("************以下操作需要您先登录，请输入用户名************");
		
			String userName = scanner.next();
			System.out.println("请输入登录密码:");
			String password = scanner.next();
			Users result = usersDao.login(userName, password); //updateUser();
			if(result!=null){
				System.out.println("************登录成功，接下来进入登录之前的操作************");
				return result;
			}else{
				outerCount++;
				System.out.println("用户名或密码错误。请选择下一步操作：继续请按1，返回上一层请按#");
				int count = 0;
				boolean flag2 =true;
				do {
					
					String option = scanner.next();
					if("#".equals(option) ){
						break outerLoop;
					}else if("1".equals(option)){
						flag2= false;
					}else{
						count++;
						if(count>Contant.MAX_TRY){
							System.out.println("输入错误次数超过"+Contant.MAX_TRY+"次，自动返回上一层");
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
		return null;
	}
}

