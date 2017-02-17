package springboot.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.PurchaseDao;
import springboot.entity.Purchase;
import springboot.entity.User;

@Service
public class PurchaseService {

	@Autowired
	@Qualifier("purchaseRestData")
	private PurchaseDao purchaseDao;
	
	@Autowired
	private UserService userService;
	
	public Collection<Purchase> getLast5PurchaseByUser(String username){
		return purchaseDao.getLast5PurchaseByUser(username);
	}
	
	public Collection<Purchase> getPurchasesByProductId(String productId){
		return purchaseDao.getPurchasesByProductId(productId);
	}
	
	
	public Collection<Purchase> getPopularPurchases() {
		
		Collection<User> userList = userService.getAllUsers();
		
		Map<String,Integer>[] mapArray = new HashMap[userList.size()];
		int index = 0;
		Collection<Purchase> purchaseList = null;
		for (User user : userList) {
			Map<String,Integer> lastBuyersOfProduct = new HashMap<String,Integer>();
			purchaseList = this.getLast5PurchaseByUser(user.getUsername());
			
			//Collection<Purchase> 
			for (Purchase purchase : purchaseList) {
				
			}
			mapArray[index] = lastBuyersOfProduct;
			
		}
		return null;
	}	
	
}
