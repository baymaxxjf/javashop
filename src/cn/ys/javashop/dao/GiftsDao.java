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

	public List<Usergift> selectGifts(int id) {
		String sql = "select g.giftName,g.price from usergift u left join gift g on u.giftId=g.id where u.userId =? ";
		ResultSet rs = DBUtils.getResultSet(sql,id);
		Gift gift =null;
		Usergift usergift = null;
		List<Usergift> usergiftLists =null;
		if(rs !=null){
			usergiftLists = new ArrayList<>();
			try {
				while (rs.next()) {
					gift =new Gift();
					usergift = new Usergift();
					gift.setGiftName(rs.getString("giftName"));
					gift.setPrice(rs.getBigDecimal("price"));
					usergift.setGift(gift);
					usergiftLists.add(usergift);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usergiftLists;

	}

}
