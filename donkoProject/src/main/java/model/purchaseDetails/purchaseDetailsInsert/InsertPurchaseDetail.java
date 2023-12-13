package model.purchaseDetails.purchaseDetailsInsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import bean.PurchaseBean;
import classes.Cart;
import classes.user.CustomerUser;
import dao.DatabaseConnection;
import dao.GeneralDao;

public class InsertPurchaseDetail {

	//購入詳細を追加する
	public static void insertPurchaseDetail(PurchaseBean purchaseBean){
		
		//購入詳細を追加するSQL
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO ");
		insertSql.append(		"purchase_details ");
		insertSql.append("(");
		insertSql.append(		"purchase_id, ");
		insertSql.append(		"item_id, ");
		insertSql.append(		"purchase_amount, ");
		insertSql.append(		"quantity");
		insertSql.append(")");
		insertSql.append("VALUES ");
		insertSql.append("(");
		insertSql.append(	"(");
		insertSql.append(		"SELECT ");
		insertSql.append(			"purchase_id ");
		insertSql.append(		"FROM ");
		insertSql.append( 			"purchases ");
		insertSql.append( 		"WHERE ");
		//パラメータをここで使う(1/5)
		insertSql.append( 			"user_id = ? ");
		insertSql.append( 		"AND ");
		insertSql.append(			"purchase_date = (");
		insertSql.append(								"SELECT	");
		insertSql.append(									"MAX(purchase_date)	");
		insertSql.append(								"FROM ");
		insertSql.append(									"purchases	");
		insertSql.append(								"WHERE ");
		//パラメータをここで使う(2/5)
		insertSql.append(								"user_id = ? ");
		insertSql.append(								"GROUP BY ");
		insertSql.append(								"user_id ");
		insertSql.append(							")");
		insertSql.append(	"), ");
		//パラメータをここで使う(3/5)
		insertSql.append(	"?, ");
		//パラメータをここで使う(4/5)
		insertSql.append(	"?, ");
		//パラメータをここで使う(5/5)
		insertSql.append(	"?");
		insertSql.append(")");
		//sqlを文字列化
		final String INSERT_PURCHASEDETAIL_SQL = insertSql.toString();
		
		//データベースに接続
		try(Connection conn = DatabaseConnection.getConnection();){
				
			//購入したユーザーのIDを保持するcustomerUserBeanをnew
			CustomerUser customerUser = new CustomerUser();
			//購入したユーザーのIDをcustomerUserにセット
			customerUser.setUserId(purchaseBean.getUserId());
			//カート内にある購入した商品の情報を全て取得
			ArrayList<CartBean> cartList = Cart.getItemListFromCart(customerUser);
		
			//カート内の商品情報をパラメータリストに追加
			cartList.forEach(cb -> {
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(purchaseBean.getUserId());
				params.add(purchaseBean.getUserId());
				params.add(cb.getItemId());
				params.add(cb.getItemPrice() * cb.getQuantity());
				params.add(cb.getQuantity());

				try {
					//購入した商品を商品数のだけインサート
					GeneralDao.executeUpdate(conn, INSERT_PURCHASEDETAIL_SQL, params);
					conn.commit();
				}catch(Exception e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
				}
				
			});
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	};
}
