package com.iu.home.product;

public class ProductService_1 {
	
	//사용빈도 낮음 겹합도가 강하다 
	private ProductDAO productDAO = new ProductDAO();
	
	{
		this.productDAO = new ProductDAO();
	}
	
	public void ProductService() {
	
		this.productDAO = new ProductDAO();
	}
	//결합도가 약하다
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public int setAddProduct(ProductDTO productDTO) throws Exception {
		Long productNum = productDAO.getProductnum();
		productDTO.getProductNum(productNum);
		int result = productDAO.set
	}


}
