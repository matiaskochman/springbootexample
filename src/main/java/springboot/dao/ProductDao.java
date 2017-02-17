package springboot.dao;

import springboot.entity.Product;

public interface ProductDao {
	public Product getProductById(String productId);
}
