package springboot.dao;

import java.util.List;

import springboot.entity.Purchase;
import springboot.entity.User;

public interface PurchaseDao {
	public List<Purchase> getLast5PurchaseByUser(String username);
}
