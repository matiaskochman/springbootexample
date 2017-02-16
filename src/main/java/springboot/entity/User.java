package springboot.entity;

public class User {

	private String username;
	private String email;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	

}
