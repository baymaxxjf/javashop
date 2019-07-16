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
			System.out.println("************���²�����Ҫ���ȵ�¼���������û���************");
		
			String userName = scanner.next();
			System.out.println("�������¼����:");
			String password = scanner.next();
			Users result = usersDao.login(userName, password); //updateUser();
			if(result!=null){
				System.out.println("************��¼�ɹ��������������¼֮ǰ�Ĳ���************");
				return result;
			}else{
				outerCount++;
				System.out.println("�û��������������ѡ����һ�������������밴1��������һ���밴#");
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
							System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
							break outerLoop;
						}else{
							System.out.println("���벻�Ϸ����������������룺");
						}			
					}
					
				} while (flag2);	
			}
			if(outerCount>Contant.MAX_TRY){
				System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
				// flag = false;
				break outerLoop;
			}
		} while (flag);
		return null;
	}
}

