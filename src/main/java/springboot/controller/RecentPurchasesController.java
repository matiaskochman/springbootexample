package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Object> getPopularPurchases(@PathVariable String username){
		
		//List<ProductAggregated> list = this.purchaseService.getAllPopularPurchases();
		
		List<ProductAggregated>list = this.purchaseService.getPopularPurchasesByUser(username);
		
		if(list!=null){
			return new ResponseEntity<>(list,HttpStatus.OK);
		}else{
			
			return new ResponseEntity<Object>("User with username of "+username+" was not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
		}

	}
}
