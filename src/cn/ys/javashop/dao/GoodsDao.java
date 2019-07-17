package cn.ys.javashop.dao;import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.util.DBUtils;



public class GoodsDao {
	public  List<Goods> selectGoods() {
		String sql = "select id, goodsName, price  from goods";
		ResultSet rs = DBUtils.getResultSet(sql);
		Goods goods =null;
	
		List<Goods> goodsLists =null;
		if(rs !=null){
			goodsLists = new ArrayList<>();
			try {
				while (rs.next()) {
					goods =new Goods();
					goods.setId(rs.getShort("id"));
					goods.setGoodsName(rs.getString("goodsName"));
					goods.setPrice(rs.getBigDecimal("price"));
					goodsLists.add(goods);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goodsLists;
	}
}
