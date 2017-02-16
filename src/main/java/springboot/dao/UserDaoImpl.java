package springboot.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import springboot.entity.User;

@Repository
@Qualifier("userData")
public class UserDaoImpl implements UserDao{

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	private RestTemplate restTemplate = new RestTemplate();
	private static final String URL_USERS= "http://74.50.59.155:6000/api/users";

    
	@Override
	public List<User> getAllUsers() {
		
		//curl http://74.50.59.155:6000/api/users
		
        ResponseEntity<String> resultStr = restTemplate.getForEntity(URL_USERS, String.class);
        LOG.debug("GetUserList http-status: {}", resultStr.getStatusCode());
        LOG.debug("GetUserList body: {}", resultStr.getBody());

        System.out.println(resultStr);
        List<User> userList = response2UserList(resultStr);

        return userList;
	}
	
    private List<User> response2UserList(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Users users = mapper.readValue(response.getBody(), new TypeReference<Users>() {});

            List<User> userList = users.getUsers();	
            return userList;

        } catch (IOException e) {
            LOG.warn("IO-err. Failed to read JSON", e);
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            LOG.warn("RTE-err. Failed to read JSON", re);
            throw re;
        }
    }	

}
class Users{
	
	private List<User> users;
	
	public Users() {
		
	}
	
	public Users(ArrayList<User> users) {
		
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
