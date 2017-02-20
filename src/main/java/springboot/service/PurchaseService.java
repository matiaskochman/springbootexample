package springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import springboot.composite.ProductAggregated;
import springboot.dao.PurchaseDao;
import springboot.entity.Product;
import springboot.entity.Purchase;
import springboot.entity.User;

@Service
public class PurchaseService {

	@Autowired
	@Qualifier("purchaseRestData")
	private PurchaseDao purchaseDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	public Collection<Purchase> getLast5PurchaseByUser(String username){
		return purchaseDao.getLast5PurchaseByUser(username);
	}
	
	public Collection<Purchase> getPurchasesByProductId(String productId){
		return purchaseDao.getPurchasesByProductId(productId);
	}
	
	@Cacheable("popularPurchasesByUser")
	public List<ProductAggregated> getPopularPurchasesByUser(String username){
		
		User user = userService.getUser(username);
		if(user==null){
			return null;
		}
		
		Map<String,ProductAggregated> productAggregatedMap = new HashMap<String,ProductAggregated>();
		
		processUser(user, productAggregatedMap);
		
		Set<String> keys = productAggregatedMap.keySet();
		
		List<ProductAggregated> productAggregatedList = new ArrayList<ProductAggregated>();		
		
		sortProductAggregated(productAggregatedMap, keys, productAggregatedList);		

		return productAggregatedList;
		
	}

	/**
	 * sort the ProductAggregated by sells
	 * @param productAggregatedMap
	 * @param keys
	 * @param productAggregatedList
	 */
	private void sortProductAggregated(Map<String, ProductAggregated> productAggregatedMap, Set<String> keys,
			List<ProductAggregated> productAggregatedList) {
		ProductAggregated pa = null;
		
		for (String productId : keys) {
			pa = productAggregatedMap.get(productId);
			productAggregatedList.add(pa);
		}

	    Collections.sort(productAggregatedList, new Comparator<ProductAggregated>() {
	        @Override
	        public int compare(ProductAggregated pa1, ProductAggregated pa2) {
	        	if(pa1.getSold() < pa2.getSold()){
	        		return 1;
	        	}else{
	        		return -1;
	        	}

	        }
	    });
	}
	/**
	 * get the data from a user.
	 * @param user
	 * @param productAggregatedMap
	 */
	private void processUser(User user, Map<String, ProductAggregated> productAggregatedMap) {
		Collection<Purchase> purchaseList;
		ProductAggregated prodAg;
		purchaseList = this.getLast5PurchaseByUser(user.getUsername());

		for (Purchase purchase : purchaseList) {
			
			Collection<String> lastBuyersForProduct = getLastBuyersForProduct(purchase.getProductId());
			
			prodAg = new ProductAggregated();
			
			Product product = productService.getProductById(purchase.getProductId().toString());
			prodAg.setId(product.getId());
			prodAg.setFace(product.getFace());
			prodAg.setPrice(product.getPrice());
			prodAg.setSize(product.getSize());
			prodAg.getUsernameSet().add(purchase.getusername());
			prodAg.getUsernameSet().addAll(lastBuyersForProduct);
			prodAg.setSold(lastBuyersForProduct.size());
			productAggregatedMap.put(purchase.getProductId().toString(), prodAg);
			
		}
	}
	/**
	 * collect oll the recent buyers list from a product
	 * @param productId
	 * @return
	 */
	private Collection<String> getLastBuyersForProduct(Integer productId) {
		Collection<Purchase> purchases = getPurchasesByProductId(productId.toString());
		
		Collection<String> userNameList = new ArrayList<String>();
		for (Purchase purchase : purchases) {
			userNameList.add(purchase.getusername());
		}
		return userNameList;
	}	
	
}
