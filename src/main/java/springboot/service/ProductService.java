package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.ProductDao;
import springboot.entity.Product;

@Service
public class ProductService {
	@Autowired
	@Qualifier("productRestData")
	private ProductDao productDao;
	
	public Product getProductById(String productId){
		return productDao.getProductById(productId);
	}	
}
