package cn.ys.javashop.main;

import java.util.Scanner;

import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.service.LoginService;
import cn.ys.javashop.service.MemberCenterService;
import cn.ys.javashop.ui.UI;

public class JavaShopAppliction {
	public final static Scanner scanner = new Scanner(System.in);
	public  static LoginService loginService = new LoginService();
	public static void main(String[] args) {
		
		System.out.println("��ӭ�����������̳ǣ�");
		// ��������Ӧ���Ƿ��˳��ı��
		boolean flag = true;
		// �����������
		int  i= 0;
		String option = null;
		do {
			
			if(i==0 ||"1".equals(option) ||
			   "2".equals(option) || "3".equals(option)
			){
				UI.showMainUI();
				System.out.println("������:");
				
			}else {
				System.out.println("���벻�Ϸ�������������:");
			}
			
			option = scanner.next();
			i++;
			
			switch (option) {
			case "1":
				new MemberCenterService().enterMemberCenter();
				break;
			case "2":
				
				break;
			case "3":
				break;
				
			default:
				if(i>Contant.MAX_TRY){
					System.out.println("�������������࣬�˳�ϵͳ");
					scanner.close();
					flag = false;
				}
				break;
			}
		} while (flag);
		System.out.println("ϵͳ�ر�");

	}

}
