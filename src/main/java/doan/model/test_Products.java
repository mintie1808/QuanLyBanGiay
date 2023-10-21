package doan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class test_Products {
	public List<test_Product> showProduct(String tensp) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://LAPTOP-MGRO6IPK\\SQLEXPRESS:1433;databaseName=Sql_web_ban__quan_ao;encrypt=true;trustServerCertificate=true;";
			Connection con = DriverManager.getConnection(url, "trongtruong", "truong123456");
			String sql = "select * from Product__tb";
			if (tensp.length() > 0) {
				sql += "where ProductName like '%" + tensp + "%'";
			}
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			List<test_Product> list = new ArrayList<test_Product>();
			while (rs.next()) {
				int id = rs.getInt("ProductID");
				String name = rs.getString("ProductName");
				int price = rs.getInt("Price");
				test_Product sp = new test_Product(id, name, price);
				list.add(sp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
