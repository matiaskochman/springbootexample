package springboot.composite;

import java.util.HashSet;
import java.util.Set;

public class ProductAggregated {
	private Long id;
	private String face;
	private Double price;
	private Integer size;
	private Integer sold;
	private Set<String> usernameSet = new HashSet<String>();
	
	
	public ProductAggregated(Long id, String face, Double price, Integer size, Set<String> usernameList) {
		super();
		this.id = id;
		this.face = face;
		this.price = price;
		this.size = size;
		this.usernameSet = usernameList;
	}
	public ProductAggregated() {
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

	public Set<String> getUsernameSet() {
		return usernameSet;
	}
	public void setUsernameSet(Set<String> usernameSet) {
		this.usernameSet = usernameSet;
	}
	
	
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductAggregated other = (ProductAggregated) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ProductAggregated [id=" + id + ", face=" + face + ", price=" + price + ", size=" + size + ", sold="
				+ sold + ", usernameSet=" + usernameSet + "]";
	}

	
	
}
