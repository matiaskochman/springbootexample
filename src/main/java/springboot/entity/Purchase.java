package springboot.entity;

import java.util.Date;

public class Purchase {
	private Long id;
	private String username;
	private Integer productId;
	private Date date;
	
	public Purchase() {
		super();
	}
	
	public Purchase(Long id, String username, Integer productId, Date date) {
		super();
		this.id = id;
		this.username = username;
		this.productId = productId;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
