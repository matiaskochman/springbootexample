package springboot.entity;

public class Product {

	private Long id;
	private String face;
	private Double price;
	private Integer size;
	
	
	
	public Product(Long id, String face, Double price, Integer size) {
		super();
		this.id = id;
		this.face = face;
		this.price = price;
		this.size = size;
	}
	
	
	public Product() {
		super();

	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
