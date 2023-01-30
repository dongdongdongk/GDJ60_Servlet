package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.util.DBConnection;

public class ProductDAO {
	
	
	public Long getMax() throws Exception { 
		Connection connection = DBConnection.getConnnection();
		String sql = "SELECT MAX(PRODUCTNUM) FROM PRODUCT";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		Long num = rs.getLong(1);
		
		DBConnection.disConnect(rs, st, connection);
		
		return num;
		
	}
	
	
	
	
	public List<ProductDTO> getProductList()throws Exception{
		Connection connection  = DBConnection.getConnnection();
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTJUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductJumsu(rs.getDouble("PRODUCTJUMSU"));
			ar.add(productDTO);
		}
		DBConnection.disConnect(rs, st, connection);
		return ar;
	}
	
	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		try {
			List<ProductDTO> ar = productDAO.getProductList();
		
			System.out.println(ar.size()!=0);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	public int setProduct(ProductDTO productDTO) throws Exception {
		Connection connection = DBConnection.getConnnection();
		String sql = "INSERT INTO PRODUCT (PRODUCTNUM, PRODUCTNAME, PRODUCTDETAIL,PRODUCTJUMSU "
				+ "VALUES(PRODUCT_SEQ.NEXTVAL, ?, ?, 0.0)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setLong(1, productDTO.getProductNum());
		st.setString(2, productDTO.getProductName());
		st.setString(3, productDTO.getProductDetail());
		st.setDouble(4, productDTO.getProductJumsu());
		int result = st.executeUpdate();
		DBConnection.disConnect(null, st, connection);
		return result;
	}
}
