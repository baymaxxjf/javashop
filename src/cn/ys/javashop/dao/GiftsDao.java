package cn.ys.javashop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.ys.javashop.entity.Gift;
import cn.ys.javashop.entity.Goods;
import cn.ys.javashop.entity.OrderDetail;
import cn.ys.javashop.entity.Usergift;
import cn.ys.javashop.util.DBUtils;

public class GiftsDao {

	public List<Gift> selectGifts(int id) {
		String sql = "select g.giftName,g.price from usergift u left join gift g on u.giftId=g.id where u.userId =? ";
		ResultSet rs = DBUtils.getResultSet(sql,id);
		Gift gift =null;
	
		List<Gift> giftLists =null;
		if(rs !=null){
			giftLists = new ArrayList<>();
			try {
				while (rs.next()) {
					gift =new Gift();
					gift.setGiftName(rs.getString("giftName"));
					gift.setPrice(rs.getBigDecimal("price"));
					giftLists.add(gift);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return giftLists;

	}

}
