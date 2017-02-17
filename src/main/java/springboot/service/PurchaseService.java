package springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	
	public List<ProductAggregated> getPopularPurchasesByUser(String username){
		
		User user = userService.getUser(username);
		if(user==null){
			return null;
		}
		
		Collection<Purchase> purchaseList = null;				
		Map<String,ProductAggregated> productAggregatedMap = new HashMap<String,ProductAggregated>();
		ProductAggregated prodAg = null;		
		
		processUser(user, productAggregatedMap);
		
		Set<String> keys = productAggregatedMap.keySet();
		
		List<ProductAggregated> productAggregatedList = new ArrayList<ProductAggregated>();		
		ProductAggregated pa = null;
		
		for (String productId : keys) {
			pa = productAggregatedMap.get(productId);
			boolean result = productAggregatedList.add(pa);
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

		return productAggregatedList;
		
	}

	private void processUser(User user, Map<String, ProductAggregated> productAggregatedMap) {
		Collection<Purchase> purchaseList;
		ProductAggregated prodAg;
		purchaseList = this.getLast5PurchaseByUser(user.getUsername());

		for (Purchase purchase : purchaseList) {
			
			if(productAggregatedMap.containsKey(purchase.getProductId().toString())){
				ProductAggregated pa = productAggregatedMap.get(purchase.getProductId().toString());
				pa.getUsernameSet().add(purchase.getusername());
				pa.setSold(pa.getSold()+1);
			}else{
				prodAg = new ProductAggregated();
				
				Product product = productService.getProductById(purchase.getProductId().toString());
				prodAg.setId(product.getId());
				prodAg.setFace(product.getFace());
				prodAg.setPrice(product.getPrice());
				prodAg.setSize(product.getSize());
				prodAg.getUsernameSet().add(purchase.getusername());
				prodAg.setSold(1);
				productAggregatedMap.put(purchase.getProductId().toString(), prodAg);

			}
		}
	}
	
	public List<ProductAggregated> getAllPopularPurchases() {
		
		Collection<User> userList = userService.getAllUsers();
		
		Collection<Purchase> purchaseList = null;
		
		Map<String,ProductAggregated> productAggregatedMap = new HashMap<String,ProductAggregated>();
		ProductAggregated prodAg = null;
		
		for (User user : userList) {
			
			processUser(user, productAggregatedMap);
			
		}
		
		List<ProductAggregated> productAggregatedList = new ArrayList<ProductAggregated>();
		
		Set<String> keys = productAggregatedMap.keySet();
		ProductAggregated pa = null;
		
		for (String productId : keys) {
			pa = productAggregatedMap.get(productId);
			boolean result = productAggregatedList.add(pa);
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

		return productAggregatedList;
	}	
	
}
