package model.purchases.purchasesInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import bean.PurchaseBean;
import classes.Cart;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertPurchaseToPurchases {

	//商品を購入する
	public static void insertPurchaseToPurchases(PurchaseBean purchaseBean) {

		//購入情報を追加するSQL
		StringBuilder insertPurchaseSb = new StringBuilder();
		insertPurchaseSb.append("INSERT INTO ");
		insertPurchaseSb.append(		"purchases");
		insertPurchaseSb.append("(");
		insertPurchaseSb.append(		"user_id, ");
		insertPurchaseSb.append(		"total_amount, ");
		insertPurchaseSb.append(		"shipping_address_id, ");
		insertPurchaseSb.append(		"shipping_id");
		insertPurchaseSb.append(")");
		insertPurchaseSb.append("VALUES ");
		insertPurchaseSb.append("(");
		//パラメータをここで使う(1/3)
		insertPurchaseSb.append(		"?, ");
		//パラメータをここで使う(2/3)
		insertPurchaseSb.append(		"?, ");
		//パラメータをここで使う(3/3)
		insertPurchaseSb.append(		"?, ");
		insertPurchaseSb.append(		"1");
		insertPurchaseSb.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASE_SQL = insertPurchaseSb.toString();
		
		//購入したユーザーのユーザーIDと合計金額、配送先IDをリストに追加
		ArrayList<Object> insertPurchasParams = new ArrayList<Object>();
		insertPurchasParams.add(purchaseBean.getUserId());
		insertPurchasParams.add(purchaseBean.getTotalAmount());
		insertPurchasParams.add(purchaseBean.getShippingAddressId());
		
		//購入詳細情報を追加するSQL
		StringBuilder insertPurchaseDetailSb = new StringBuilder();
		insertPurchaseDetailSb.append("INSERT INTO ");
		insertPurchaseDetailSb.append(		"purchase_details ");
		insertPurchaseDetailSb.append("(");
		insertPurchaseDetailSb.append(		"purchase_id, ");
		insertPurchaseDetailSb.append(		"item_id, ");
		insertPurchaseDetailSb.append(		"purchase_amount, ");
		insertPurchaseDetailSb.append(		"quantity");
		insertPurchaseDetailSb.append(")");
		insertPurchaseDetailSb.append("VALUES ");
		insertPurchaseDetailSb.append("(");
		insertPurchaseDetailSb.append(	"(");
		insertPurchaseDetailSb.append(		"SELECT ");
		insertPurchaseDetailSb.append(			"purchase_id ");
		insertPurchaseDetailSb.append(		"FROM ");
		insertPurchaseDetailSb.append( 			"purchases ");
		insertPurchaseDetailSb.append( 		"WHERE ");
		//パラメータをここで使う(1/5)
		insertPurchaseDetailSb.append( 			"user_id = ? ");
		insertPurchaseDetailSb.append( 		"AND ");
		insertPurchaseDetailSb.append(			"purchase_date = (");
		insertPurchaseDetailSb.append(								"SELECT	");
		insertPurchaseDetailSb.append(									"MAX(purchase_date)	");
		insertPurchaseDetailSb.append(								"FROM ");
		insertPurchaseDetailSb.append(									"purchases	");
		insertPurchaseDetailSb.append(								"WHERE ");
		//パラメータをここで使う(2/5)
		insertPurchaseDetailSb.append(								"user_id = ? ");
		insertPurchaseDetailSb.append(								"GROUP BY ");
		insertPurchaseDetailSb.append(								"user_id ");
		insertPurchaseDetailSb.append(							")");
		insertPurchaseDetailSb.append(	"), ");
		//パラメータをここで使う(3/5)
		insertPurchaseDetailSb.append(	"?, ");
		//パラメータをここで使う(4/5)
		insertPurchaseDetailSb.append(	"?, ");
		//パラメータをここで使う(5/5)
		insertPurchaseDetailSb.append(	"?");
		insertPurchaseDetailSb.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASE_DETAIL_SQL = insertPurchaseDetailSb.toString();
		
		//購入したユーザーのIDを保持するcustomerUserBeanをnew
		CustomerUser customerUser = new CustomerUser();
		//購入したユーザーのIDをcustomerUserにセット
		customerUser.setUserId(purchaseBean.getUserId());
		//カート内にある購入した商品の情報を全て取得
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(customerUser);
		
		//購入された商品の在庫数を減らすSQL
		 StringBuilder updateItemStockSb  = new StringBuilder();
		 updateItemStockSb.append("UPDATE ");
		 updateItemStockSb.append(	"items ");
		 updateItemStockSb.append("SET ");
		 updateItemStockSb.append(	"stock = stock - ? ");
		 updateItemStockSb.append("WHERE ");
		 //ここでパラメータを使う
		 updateItemStockSb.append(	"item_id = ?");
		 //sqlを文字列化
		 final String UPDATE_ITEM_STOCK_SQL = updateItemStockSb.toString();
				
		//カート内の対象のユーザーの商品を全て削除するSQL
		StringBuilder deleteCartItemAllSb = new StringBuilder();
		deleteCartItemAllSb.append("DELETE ");
		deleteCartItemAllSb.append("FROM ");
		deleteCartItemAllSb.append(		"carts ");
		deleteCartItemAllSb.append("WHERE ");
		//パラメータをここで使う
		deleteCartItemAllSb.append(		"user_id = ? ");
		//sqlを文字列化
		final String DELETE_ITEM_ALL_SQL = deleteCartItemAllSb.toString();

		//ログインしているユーザーのユーザーIDをリストに追加
		ArrayList<Object> deleteCartItemAllparams = new ArrayList<Object>();
		deleteCartItemAllparams.add(purchaseBean.getUserId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				//購入情報を追加する
				GeneralDao.executeUpdate(conn, INSERT_PURCHASE_SQL, insertPurchasParams);
			
				//購入詳細情報を購入の商品数の分だけ追加する
				cartList.forEach(cb -> {
					ArrayList<Object> insertPurchaseDetailparams = new ArrayList<Object>();
					insertPurchaseDetailparams.add(purchaseBean.getUserId());
					insertPurchaseDetailparams.add(purchaseBean.getUserId());
					insertPurchaseDetailparams.add(cb.getItemId());
					insertPurchaseDetailparams.add(cb.getItemPrice() * cb.getQuantity());
					insertPurchaseDetailparams.add(cb.getQuantity());
						
					try {
						//購入詳細を全て追加する
						GeneralDao.executeUpdate(conn, INSERT_PURCHASE_DETAIL_SQL, insertPurchaseDetailparams);
					} catch (SQLException e) {
						//トランザクション処理によるロールバックをさせるための外側のcatch区にエラーを投げる
						throw new RuntimeException();
					}
				});
				
				//商品の在庫数を購入された分だけ減らす
				cartList.forEach(cb -> {
					 ArrayList<Object> updateItemStockParams = new ArrayList<Object>();
					 updateItemStockParams.add(cb.getQuantity());
					 updateItemStockParams.add(cb.getItemId());
					 
					try {
						//商品の在庫数を減らす
						GeneralDao.executeUpdate(conn, UPDATE_ITEM_STOCK_SQL, updateItemStockParams);
					} catch (SQLException e) {
						//トランザクション処理によるロールバックをさせるための外側のcatch区にエラーを投げる
						throw new RuntimeException();
					}
					 
				});
				
				//カートからログインしているユーザーが購入した商品全て削除
				GeneralDao.executeUpdate(conn, DELETE_ITEM_ALL_SQL, deleteCartItemAllparams);
				
				//sqlをコミット
				conn.commit();
			} catch (SQLException | RuntimeException e) {
				if (!conn.isClosed()) {
					//一つでも処理が失敗したらロールバック
					conn.rollback();
				}
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	};

}
