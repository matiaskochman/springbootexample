package springboot.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import springboot.entity.Product;

@Repository
@Qualifier("productRestData")
public class ProductDaoImpl implements ProductDao{

	public static final String URL_PRODUCT_BY_PRODUCT_ID = "http://74.50.59.155:6000/api/products/";
    private ObjectReader productReader = null;
	private static final Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	@Cacheable("product")
	public Product getProductById(String productId) {
		
        ResponseEntity<String> resultStr = restTemplate.getForEntity(URL_PRODUCT_BY_PRODUCT_ID+productId, String.class);
        LOG.debug("GetProduct http-status: {}", resultStr.getStatusCode());
        LOG.debug("GetProduct body: {}", resultStr.getBody());
        
        ProductJson productJson = response2Product(resultStr);
        
		return productJson.getProduct();
	}
	
    private ProductJson response2Product(ResponseEntity<String> response) {
        try {
            return getProductReader().readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private ObjectReader getProductReader() {

        if (productReader != null) return productReader;

        ObjectMapper mapper = new ObjectMapper();
        return productReader = mapper.reader(ProductJson.class);
    }    
    
}

class ProductJson{
	private Product product;

	public ProductJson(Product product) {
		super();
		this.product = product;
	}

	public ProductJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
