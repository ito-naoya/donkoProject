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
		final String INSERT_PURCHASEDETAIL_SQL = insertSql.toString();
		
		try(Connection conn = DatabaseConnection.getConnection();){
				
				CustomerUser customerUser = new CustomerUser();
				customerUser.setUserId(purchaseBean.getUserId());
				ArrayList<CartBean> cartList = Cart.getItemListFromCart(customerUser);
			
				cartList.forEach(cb -> {
					ArrayList<Object> params = new ArrayList<Object>();
					params.add(purchaseBean.getUserId());
					params.add(purchaseBean.getUserId());
					params.add(cb.getItemId());
					params.add(cb.getItemPrice() * cb.getQuantity());
					params.add(cb.getQuantity());
	
					try {
						GeneralDao.executeUpdate(conn, INSERT_PURCHASEDETAIL_SQL, params);
						conn.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				});
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	};
}
