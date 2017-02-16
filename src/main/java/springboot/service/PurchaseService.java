package springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.PurchaseDao;
import springboot.entity.Purchase;

@Service
public class PurchaseService {

	@Autowired
	@Qualifier("purchaseData")
	private PurchaseDao purchaseDao;
	
	public Collection<Purchase> getLast5PurchaseByUser(String username){
		return purchaseDao.getLast5PurchaseByUser(username);
	}	
	
}
