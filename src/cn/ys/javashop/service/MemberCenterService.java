package cn.ys.javashop.service;

import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;

public class MemberCenterService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	public void enterMemberCenter() {
		
		// �����������
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) || "3".equals(option) ||
			   "4".equals(option)
			){
				UI.memberCenterUI();
				System.out.print("������:");
			}else {
				System.out.print("�����������������:");	
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
					System.out.println("�������������࣬�˻���һ��");
					topFlag = false;
				}
				break;
			}
			
			
		} while (topFlag);
	}
	 
	
	public void updatePassword(){
		boolean result = new Common().login();
		if(result){
			int count = 0;
			boolean flag1 =true;
			outerLoop:
			do {
				
				System.out.println("�ṩ�����¼�Ĺ��ܣ������������룺");
				String newPassword =scanner.next();
				System.out.println("����һ������������ȷ�ϣ�");
				String confirmPassword = scanner.next();
				if(newPassword!="" && newPassword == confirmPassword){
					boolean updateResult = updateUser();
					if(updateResult){
						System.out.println("���³ɹ�����#������һ��,��0���ض��㡣�����룺");
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
									System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
									// flag2 = false;
									break outerLoop;
								}else{
									System.out.println("���벻�Ϸ����������������룺");
								}			
							}
							
						} while (flag2);	
						
					}else{
						System.out.println("��������ʧ��");
					}
					
				}else {
					count++;
					System.out.println("�����벻��Ϊ�ջ��������벻һ�£������ԡ�");
				}
				if(count>Contant.MAX_TRY){
					System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
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
		boolean result = new Common().login();
		if(result){
			
		}else{
			
		}
		System.out.println("�����ҵĶ���");
		
	}
	
	public void myGift(){
		
	}


	public void login1() {

		boolean flag = true;
		int outerCount = 0;
		outerLoop:
		do {
			System.out.println("************���²�����Ҫ���ȵ�¼���������û���************");
			outerCount++;
			String userName = scanner.next();
			System.out.println("�������¼����:");
			String password = scanner.next();
			boolean result = updateUser();
			if(result){
				System.out.println("************��¼�ɹ��������������¼֮ǰ�Ĳ���************");
				int count = 0;
				boolean flag1 =true;
				do {
					count++;
					System.out.println("�ṩ�����¼�Ĺ��ܣ������������룺");
					String newPassword =scanner.next();
					System.out.println("����һ������������ȷ�ϣ�");
					String confirmPassword = scanner.next();
					if(newPassword!="" && newPassword == confirmPassword){
						boolean updateResult = updateUser();
						if(updateResult){
							System.out.println("���³ɹ�����#������һ��,��0���ض��㡣�����룺");
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
										System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
										// flag2 = false;
										break outerLoop;
									}else{
										System.out.println("���벻�Ϸ����������������룺");
									}			
								}
								
							} while (flag2);	
							
						}
						
					}else {
						System.out.println("�����벻��Ϊ�ջ��������벻һ�£������ԡ�");
					}
					if(count>Contant.MAX_TRY){
						System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
						// flag = false;
						break outerLoop;
					}
				} while (flag1);
			}else {			
				System.out.println("�û��������������ѡ����һ�������������밴1��������һ���밴#");
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
							System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
							// flag2 = false;
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
		
	}
}
