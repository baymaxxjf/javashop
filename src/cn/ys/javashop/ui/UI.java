package cn.ys.javashop.ui;

import java.util.List;

import cn.ys.javashop.entity.Gift;
import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.entity.OrderDetail;
import cn.ys.javashop.entity.Orders;

public class UI {
	
	public static void showMainUI() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���***********");
		System.out.println("*          1.��Ա����                              *");
		System.out.println("*          2.���빺��                              *");
		System.out.println("*          3.��������                              *");
		System.out.println("********************************");
	}
	
	public static void memberCenterUI() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���***********");
		System.out.println("*          1.�޸�����                              *");
		System.out.println("*          2.�ҵĶ���                               *");
		System.out.println("*          3.�ҵ�����                             *");
		System.out.println("*          4.�����ϲ�                             *");
		System.out.println("********************************");
	}
	
	public static void shopingUI() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���***********");
		System.out.println("*          1.ѡ����Ʒ                             *");
		System.out.println("*          2.�����ϲ�                              *");
		System.out.println("********************************");
	}
	
	public static void tryLuckyUI() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���***********");
		System.out.println("*          1.��ʼ����֮��                            *");
		System.out.println("*          2.�����ϲ�                              *");
		System.out.println("********************************");
	}
	
	public static void orderInfoUI(List<Orders> orderLists) {
		System.out.println("���Ķ�����Ϣ���£�");
//		System.out.println("������Ϣ���£�");
		System.out.println("����������"+ orderLists.size());
		for(Orders item : orderLists){
			System.out.println("------------------------------------------------------------------");
			System.out.println("�����ţ�      �����û�����     ������         �������ڣ�            �Ƿ�֧����");
			String isPay="1".equals(item.getIsPay())?"��":"��";
			System.out.println(item.getId()+"\t"+ 
							   item.getUsers().getRealName()+"\t"+ 
							   item.getTotal()+"\t"+ 
							   item.getAddDate()+"\t"+ isPay
							   
			);
			System.out.println("��Ʒ��ϸ���£�");
			System.out.println("��Ʒ����            ��Ʒ����          ��Ʒ����           С�ƽ�� ");
			for(OrderDetail i : item.getOrderDetailLists()){
				System.out.println(i.getGoods().getGoodsName()+"\t"+
								   i.getGoods().getPrice()+"\t"+
								   i.getNumber()+"\t"+
								   i.getNumber().multiply(i.getGoods().getPrice())
				);
			}
			System.out.println("------------------------------------------------------------------");
		}
	}
	
	public static void giftInfoUI(List<Gift> giftLists){
		System.out.println("������������£�");
		System.out.println("����������"+giftLists.size());
		System.out.println("------------------------------------------------------------------");
		System.out.println("��������"+"\t"+"�����ֵ");
		for(Gift item : giftLists){
			System.out.println(item.getGiftName()+"\t"+item.getPrice());
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	public static void goodInfoUI(List<Goods> goodLists){
		System.out.println("��Ʒ�б����£���������Ҫ�������Ʒ��ţ�");
		System.out.println("��Ʒ���            ��Ʒ����          ��Ʒ�۸� ");
		for(Goods item : goodLists){
			System.out.println(item.getId()+"\t"+item.getGoodsName()+"\t\t\t"+item.getPrice());
		}
	}
	

}
