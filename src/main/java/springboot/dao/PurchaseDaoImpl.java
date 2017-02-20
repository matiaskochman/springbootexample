package springboot.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import springboot.entity.Purchase;

@Repository
@Qualifier("purchaseRestData")
public class PurchaseDaoImpl implements PurchaseDao{

	public static final String URL_PURCHASES_BY_USER_ID = "http://74.50.59.155:6000/api/purchases/by_user/";
	public static final String URL_PURCHASES_BY_PRODUCT_ID = "http://74.50.59.155:6000/api/purchases/by_product/";
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	private RestTemplate restTemplate = new RestTemplate();
	

	
	@Override
	public List<Purchase> getLast5PurchaseByUser(String username) {
		
        ResponseEntity<String> resultStr = restTemplate.getForEntity(URL_PURCHASES_BY_USER_ID+username+"?limit=5", String.class);
        LOG.debug("GetUserList http-status: {}", resultStr.getStatusCode());
        LOG.debug("GetUserList body: {}", resultStr.getBody());		
		
        
        List<Purchase> userList = response2PurchaseList(resultStr);

        return userList;
	}
	
    private List<Purchase> response2PurchaseList(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PurchasesJson purchases = mapper.readValue(response.getBody(), new TypeReference<PurchasesJson>() {});

            List<Purchase> purchaseList = purchases.getPurchases();
            return purchaseList;

        } catch (IOException e) {
            LOG.warn("IO-err. Failed to read JSON", e);
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            LOG.warn("RTE-err. Failed to read JSON", re);
            throw re;
        }
    }

	@Override
	@Cacheable("purchasesByProduct")
	public Collection<Purchase> getPurchasesByProductId(String productId) {
		
        ResponseEntity<String> resultStr = restTemplate.getForEntity(URL_PURCHASES_BY_PRODUCT_ID+productId, String.class);
        LOG.debug("GetUserList http-status: {}", resultStr.getStatusCode());
        LOG.debug("GetUserList body: {}", resultStr.getBody());		
		
        
        List<Purchase> userList = response2PurchaseList(resultStr);

        return userList;
	}


}
class PurchasesJson{
	private List<Purchase> purchases;

	public PurchasesJson() {
		super();

	}

	public PurchasesJson(List<Purchase> purchases) {
		super();
		this.purchases = purchases;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	
}