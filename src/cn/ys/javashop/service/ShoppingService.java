package cn.ys.javashop.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import cn.ys.javashop.common.Common;
import cn.ys.javashop.contant.Contant;
import cn.ys.javashop.dao.GoodsDao;
import cn.ys.javashop.dao.OrdersDao;
import cn.ys.javashop.dao.UsersDao;
import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.entity.Users;
import cn.ys.javashop.main.JavaShopAppliction;
import cn.ys.javashop.ui.UI;
import cn.ys.javashop.util.DBUtils;

public class ShoppingService {
	private Scanner scanner = JavaShopAppliction.scanner;
	private boolean topFlag = true;
	private Common common = new Common();
	private GoodsDao goodsDao = new GoodsDao();
	private OrdersDao ordersDao = new OrdersDao();
	public void enterShopping() {
		// �����������
		int  i = 0;
		String option = null;
		do {
			if(i==0 || "1".equals(option) ||
			   "2".equals(option) 
			){
				UI.shopingUI();
				System.out.print("������:");
			}else {
				System.out.print("�����������������:");	
			}
			option = scanner.next();
			
			switch (option) {
			case "1":
				selectShops();
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
	
	
	
	public void selectShops() {
		Users user = common.login();
		if(user !=null){
			List<Goods> goodsLists = goodsDao.selectGoods();
			UI.goodInfoUI(goodsLists);		
			//
			HashMap<Integer, Integer> gooIdToNumMap= new HashMap<>();
			// �����ܶ������
			BigDecimal total = new BigDecimal(0);
			boolean selectShopsFlag = true;
			int count = 0;
			do {
				boolean flag = true;
				do {
					System.out.println("�����룺");
					try {
						int goodsId = scanner.nextInt();
						for(int i=0 ;i<goodsLists.size(); i++ ){
							if(goodsId == goodsLists.get(i).getId()){
								count++;
								total = total.add(goodsLists.get(i).getPrice());
								if(gooIdToNumMap.containsKey(goodsId)){
									Integer value = gooIdToNumMap.get(goodsId)+1;
									gooIdToNumMap.put(goodsId, value);
								}else{
									gooIdToNumMap.put(goodsId, 1);
								}
								flag =false;
								break;
							}
							if(i==(goodsLists.size()-1)){
								System.out.println("�������Ʒ��Ų��Ϸ�������������");
							}
						}
						
					} catch (Exception e) {
						System.out.println("�������Ʒ��Ų��Ϸ�������������");
					}	
				} while (flag);
				
				System.out.println("��Ʒ�Ѽ��뵽���ﳵ������ѡ���밴1�������밴2�������밴#");
				String option = scanner.next();
				if("2".equals(option)){
					int orderId = ordersDao.updateOrders(user.getId(), gooIdToNumMap,total);
					if(orderId != -1){
						do {
							System.out.println("���Ĺ��ﳵ���и�"+count+"��Ʒ��ȷ�������𣿰�y�����#�˳�");
							String isPay = scanner.next();
							if("y".equals(isPay)){
								boolean hasPay = ordersDao.updatePay(user.getId(), orderId,"1");
								if(hasPay){
									System.out.println("����ɹ�,��"+count+"����Ʒ������");
									commonStep();
									selectShopsFlag =false;
									break;
								}else{
									System.out.println("����ʧ��");
								}
							}else if ("#".equals(isPay)){
								System.out.println("�˳�����");
								selectShopsFlag =false;
								break;
							}else {
								System.out.println("��������������");
							}
						} while (true);
						
					}else{
						System.out.println("��������ʧ��");
					}
				}else if("#".equals(option)){
					break;
				}else if("1".equals(option)){
					
				}else {
					System.out.println("�������ָ��Ϸ�������������");
				}
				
			} while (selectShopsFlag);
		
		}
	}
	
	
	private void commonStep() {
		System.out.println("���³ɹ�����#������һ��,��0���ض��㡣�����룺");
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
					System.out.println("��������������"+Contant.MAX_TRY+"�Σ��Զ�������һ��");
					break ;
				}else{
					System.out.println("���벻�Ϸ����������������룺");
				}			
			}
		} while (flag);
	}
}
