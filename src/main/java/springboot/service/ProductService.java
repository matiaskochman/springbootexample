package springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.ProductDao;
import springboot.entity.Product;

@Service
public class ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	@Qualifier("productRestData")
	private ProductDao productDao;
	
	public Product getProductById(String productId){
		return productDao.getProductById(productId);
	}	
}
