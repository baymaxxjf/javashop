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
		// �����������
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) 
			){
				UI.tryLuckyUI();;
				System.out.print("������:");
			}else {
				System.out.print("�����������������:");	
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
					System.out.println("�������������࣬�˻���һ��");
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
			// 3�γ齱����
			int chance = 3;
			Random random =new Random();
			do {
				chance--;
				System.out.println("����0-9֮����������ѡ��һ��������Ϊ����������֡�");
				int luckNumber;
				
				 while (true) {
					System.out.println("�����룺");
					try {
						
						luckNumber = scanner.nextInt();
						break;
					} catch (Exception e) {
						System.out.println("�������������");
						scanner.next();
					}
				};
				int randomLuckNumber = random.nextInt(10);
				if(luckNumber == randomLuckNumber){
					boolean result = usersDao.addUserGift(user.getId(),luckNumber+1);
					if(result){
						System.out.println("��ϲ��������������");
						Gift gift = usersDao.selectGiftById(luckNumber+1);
						System.out.println("���ƣ�"+gift.getGiftName()+"����ֵ��"+gift.getPrice()+",����'�ҵ������в鿴'" );
						if(chance>-1){ 
							do {
								System.out.println("�����밴1�������밴#");
								System.out.println("�����룺");
								String option = scanner.next();
								if("1".equals(option)){
									break;
								}else if("#".equals(option)){
									selectLuckyNumberFlag =false;
									break;
								}else {
									System.out.println("�������������");
								}
							} while (true);
							
						}else{
							commonStep();
							break;
						}
						
						
					}else{
						System.out.println("����ʧ��");
					}
				}else {
					
					System.out.println("���ź�����δ�ܻ�ȡ��������������ǣ�"+randomLuckNumber+"�㻹��"+chance+"����");
					if(chance == 0) {
						break;
					}
					do {
						System.out.println("�����밴1�������밴#");
						System.out.println("�����룺");
						String option = scanner.next();
						if("1".equals(option)){
							break;
						}else if("#".equals(option)){
							selectLuckyNumberFlag =false;
							break;
						}else {
							System.out.println("�������������");
						}
					} while (true);
				}
			} while (selectLuckyNumberFlag);
		}
	}
	
	private void commonStep() {
		System.out.println("��#������һ��,��0���ض��㡣");
		int count = 0;
		boolean flag =true;
		do {	
			System.out.println("�����룺");
			String option = scanner.next();
			if("#".equals(option) ){
				break ;
			}else if("0".equals(option)){
				topFlag = false;
				break ;
			}else{
				count++;
				if(count>Contant.MAX_TRY){
					System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
					break ;
				}else{
					System.out.println("���벻�Ϸ����������������룺");
				}			
			}
		} while (flag);
	}
	
}
