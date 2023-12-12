package model.shippingAddresses.shippingAddressesInsert;

import java.util.ArrayList;

import bean.ShippingAddressBean;

public class InsertNewShippingAddress {
	
	//新規配送先を登録する
	public static void insertNewShippingAddress(ShippingAddressBean shippingAddressBean){
		
		// SQLコマンド生成
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " 								);
		sb.append(	"shipping_addresses ( "						);
		sb.append(		"user_id, "								);
		sb.append(		"postal_code, "							);
		sb.append(		"address, "								);
		sb.append(		"main_shipping_address, "				);
		sb.append(		"addressee"								);
		sb.append(	") "										);
		sb.append("VALUES ( "									);
		// ユーザーID
		sb.append(	"?, "										);
		// 郵便番号
		sb.append(	"?, "										);
		// 住所
		sb.append(	"?, "										);
		// 配送先住所の設定
		sb.append(	"?, "										);
		// 宛名
		sb.append(	"?, "										);
		sb.append(	"purchase_details.item_id = items.item_id "	);
		sb.append(	") "										);
		String sql = sb.toString();
		
		// ？の引数に渡す値
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(shippingAddressBean.getUserId());
	}
}
