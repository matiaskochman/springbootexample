package springboot.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboot.composite.ProductAggregated;
import springboot.service.PurchaseService;

@Controller
@RequestMapping("/api/recent_purchases/")
public class RecentPurchasesController {

	@Autowired
	private PurchaseService purchaseService;	
	
	@RequestMapping(value="/{username:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getPopularPurchases(@PathVariable String username,
			@RequestHeader MultiValueMap<String, String> rawHeaders){
		
		
		List<ProductAggregated>list = this.purchaseService.getPopularPurchasesByUser(username);
		
        
		if(list!=null){

			String etag = generateEtag(username, list);
			return ResponseEntity.ok()
					.cacheControl(CacheControl.maxAge(120, TimeUnit.SECONDS))
					.eTag(etag).body(list);
		}else{
			
			return new ResponseEntity<Object>("User with username of "+username+" was not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}

	}

	private String generateEtag(String username, List<ProductAggregated> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(username+"-");
		
		for (ProductAggregated productAggregated : list) {
			sb.append(productAggregated.getId().toString().substring(0,1));
		}

		return sb.toString();
	}
}
