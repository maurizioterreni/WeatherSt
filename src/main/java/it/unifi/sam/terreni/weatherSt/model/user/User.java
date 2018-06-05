package it.unifi.sam.terreni.weatherSt.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;
	@OneToOne(mappedBy="user", cascade = CascadeType.REMOVE)
	private UserPropertie propertie;
	
	User(){
		super(); 
	}
	
	public User(String uuid) {
		super(uuid);
	}
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public UserPropertie getPropertie() {
		return propertie;
	}

	public void setPropertie(UserPropertie propertie) {
		this.propertie = propertie;
	}


	public static class UserBuilder{
		private String username;
		private String password;
		private String email;
		private UserPropertie propertie;
		
		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		public UserBuilder propertie(UserPropertie propertie) {
			this.propertie = propertie;
			return this;
		}
		
		public User build() {
			User user = ModelFactory.user();
			
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			user.setPropertie(propertie);
			propertie.setUser(user);
			
			return user;
		}
	}


	@Override
	public void accept(UsageVisitor visitor) {
		
	}
}
