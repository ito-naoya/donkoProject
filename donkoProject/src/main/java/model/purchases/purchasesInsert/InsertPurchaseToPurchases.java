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
		StringBuilder insertPurchaseSql = new StringBuilder();
		insertPurchaseSql.append("INSERT INTO ");
		insertPurchaseSql.append(		"purchases");
		insertPurchaseSql.append("(");
		insertPurchaseSql.append(		"user_id, ");
		insertPurchaseSql.append(		"total_amount, ");
		insertPurchaseSql.append(		"shipping_address_id, ");
		insertPurchaseSql.append(		"shipping_id");
		insertPurchaseSql.append(")");
		insertPurchaseSql.append("VALUES ");
		insertPurchaseSql.append("(");
		//パラメータをここで使う(1/3)
		insertPurchaseSql.append(		"?, ");
		//パラメータをここで使う(2/3)
		insertPurchaseSql.append(		"?, ");
		//パラメータをここで使う(3/3)
		insertPurchaseSql.append(		"?, ");
		insertPurchaseSql.append(		"1");
		insertPurchaseSql.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASES_SQL = insertPurchaseSql.toString();
		
		//購入したユーザーのIDと合計金額、配送先IDをリストに追加
		ArrayList<Object> insertPurchasParams = new ArrayList<Object>();
		insertPurchasParams.add(purchaseBean.getUserId());
		insertPurchasParams.add(purchaseBean.getTotalAmount());
		insertPurchasParams.add(purchaseBean.getShippingAddressId());
		
		//購入詳細を追加するSQL
		StringBuilder insertPurchaseDetailSql = new StringBuilder();
		insertPurchaseDetailSql.append("INSERT INTO ");
		insertPurchaseDetailSql.append(		"purchase_details ");
		insertPurchaseDetailSql.append("(");
		insertPurchaseDetailSql.append(		"purchase_id, ");
		insertPurchaseDetailSql.append(		"item_id, ");
		insertPurchaseDetailSql.append(		"purchase_amount, ");
		insertPurchaseDetailSql.append(		"quantity");
		insertPurchaseDetailSql.append(")");
		insertPurchaseDetailSql.append("VALUES ");
		insertPurchaseDetailSql.append("(");
		insertPurchaseDetailSql.append(	"(");
		insertPurchaseDetailSql.append(		"SELECT ");
		insertPurchaseDetailSql.append(			"purchase_id ");
		insertPurchaseDetailSql.append(		"FROM ");
		insertPurchaseDetailSql.append( 			"purchases ");
		insertPurchaseDetailSql.append( 		"WHERE ");
		//パラメータをここで使う(1/5)
		insertPurchaseDetailSql.append( 			"user_id = ? ");
		insertPurchaseDetailSql.append( 		"AND ");
		insertPurchaseDetailSql.append(			"purchase_date = (");
		insertPurchaseDetailSql.append(								"SELECT	");
		insertPurchaseDetailSql.append(									"MAX(purchase_date)	");
		insertPurchaseDetailSql.append(								"FROM ");
		insertPurchaseDetailSql.append(									"purchases	");
		insertPurchaseDetailSql.append(								"WHERE ");
		//パラメータをここで使う(2/5)
		insertPurchaseDetailSql.append(								"user_id = ? ");
		insertPurchaseDetailSql.append(								"GROUP BY ");
		insertPurchaseDetailSql.append(								"user_id ");
		insertPurchaseDetailSql.append(							")");
		insertPurchaseDetailSql.append(	"), ");
		//パラメータをここで使う(3/5)
		insertPurchaseDetailSql.append(	"?, ");
		//パラメータをここで使う(4/5)
		insertPurchaseDetailSql.append(	"?, ");
		//パラメータをここで使う(5/5)
		insertPurchaseDetailSql.append(	"?");
		insertPurchaseDetailSql.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASEDETAIL_SQL = insertPurchaseDetailSql.toString();
		
		//購入したユーザーのIDを保持するcustomerUserBeanをnew
		CustomerUser customerUser = new CustomerUser();
		//購入したユーザーのIDをcustomerUserにセット
		customerUser.setUserId(purchaseBean.getUserId());
		//カート内にある購入した商品の情報を全て取得
		ArrayList<CartBean> cartList = Cart.getItemListFromCart(customerUser);
		
		//購入された商品の在庫数を減らすSQL
		 StringBuilder updateItemStockSql  = new StringBuilder();
		 updateItemStockSql.append("UPDATE ");
		 updateItemStockSql.append(	"items ");
		 updateItemStockSql.append("SET ");
		 updateItemStockSql.append(	"stock = stock - ? ");
		 updateItemStockSql.append("WHERE ");
		 //ここでパラメータを使う
		 updateItemStockSql.append(	"item_id = ?");
		 //sqlを文字列化
		 final String UPDATE_ITEMSTOCK_SQL = updateItemStockSql.toString();
				
		//カート内の対象のユーザーの商品を全て削除するSQL
		StringBuilder deleteCartItemAllSql = new StringBuilder();
		deleteCartItemAllSql.append("DELETE ");
		deleteCartItemAllSql.append("FROM ");
		deleteCartItemAllSql.append(		"carts ");
		deleteCartItemAllSql.append("WHERE ");
		//パラメータをここで使う
		deleteCartItemAllSql.append(		"user_id = ? ");
		//sqlを文字列化
		final String DELETE_ITEMALL_SQL = deleteCartItemAllSql.toString();

		//対象のユーザーのuser_idをリストに追加
		ArrayList<Object> deleteCartItemAllparams = new ArrayList<Object>();
		deleteCartItemAllparams.add(purchaseBean.getUserId());

		//データベース接続
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				//購入情報を追加する
				GeneralDao.executeUpdate(conn, INSERT_PURCHASES_SQL, insertPurchasParams);
			
				//購入の詳細情報を購入の商品数の分だけ追加する
				cartList.forEach(cb -> {
					ArrayList<Object> insertPurchaseDetailparams = new ArrayList<Object>();
					insertPurchaseDetailparams.add(purchaseBean.getUserId());
					insertPurchaseDetailparams.add(purchaseBean.getUserId());
					insertPurchaseDetailparams.add(cb.getItemId());
					insertPurchaseDetailparams.add(cb.getItemPrice() * cb.getQuantity());
					insertPurchaseDetailparams.add(cb.getQuantity());
						
					try {
						//購入詳細を全て追加する
						GeneralDao.executeUpdate(conn, INSERT_PURCHASEDETAIL_SQL, insertPurchaseDetailparams);
					} catch (SQLException e) {
						try {
							if(!conn.isClosed()) {
								conn.rollback();
							}
						} catch (SQLException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				});
				
				//商品の在庫数を購入された分だけ減らす
				cartList.forEach(cb -> {
					 ArrayList<Object> updateItemStockParams = new ArrayList<Object>();
					 updateItemStockParams.add(cb.getQuantity());
					 updateItemStockParams.add(cb.getItemId());
					 
					try {
						//商品の在庫数を減らす
						GeneralDao.executeUpdate(conn, UPDATE_ITEMSTOCK_SQL, updateItemStockParams);
					} catch (SQLException e) {
						try {
							if(!conn.isClosed()) {
								conn.rollback();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					 
				});
				
				//カートからユーザーが購入した商品全て削除
				GeneralDao.executeUpdate(conn, DELETE_ITEMALL_SQL, deleteCartItemAllparams);
				
				conn.commit();
			} catch (SQLException e) {
				if (!conn.isClosed()) {
					conn.rollback();
				}
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	};

}
