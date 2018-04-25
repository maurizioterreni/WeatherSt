package it.unifi.sam.terreni.weatherSt.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="user_properties")
public class UserPropertie extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@OneToMany(fetch = FetchType.EAGER, targetEntity=UnitMeasureKnowledge.class , cascade = CascadeType.REMOVE )
	private Set<UnitMeasureKnowledge> unitMeasure;
	@OneToOne
	@JoinColumn(name = "weather_id")
	private WeatherStation weatherStation;

	public static UserPropertieBuilder builder() {
		return new UserPropertieBuilder();
	}
	
	UserPropertie() {
		super();
		unitMeasure = new HashSet<>();
	}
	public UserPropertie(String uuid) {
		super(uuid);
		unitMeasure = new HashSet<>();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Set<UnitMeasureKnowledge> getUnitMeasure() {
		return unitMeasure;
	}
	public void setUnitMeasure(Set<UnitMeasureKnowledge> unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
	public WeatherStation getWeatherStation() {
		return weatherStation;
	}
	public void setWeatherStation(WeatherStation weatherStation) {
		this.weatherStation = weatherStation;
	}

	public static class UserPropertieBuilder{
		private UserRole userRole;
		private WeatherStation weatherStation;
		private Set<UnitMeasureKnowledge> unitMeasure;
		private User user;
		
		public UserPropertieBuilder userRole(UserRole userRole){
			this.userRole = userRole;
			return this;
		}
		
		public UserPropertieBuilder weatherStation(WeatherStation weatherStation){
			this.weatherStation = weatherStation;
			return this;
		}
		public UserPropertieBuilder unitMeasure(Set<UnitMeasureKnowledge> unitMeasure){
			this.unitMeasure = unitMeasure;
			return this;
		}
		public UserPropertieBuilder user(User user){
			this.user = user;
			return this;
		}
		
		public UserPropertie build() {
			UserPropertie obj = ModelFactory.userPropertie();
			
			obj.setUnitMeasure(unitMeasure);
			obj.setUser(user);
			obj.setUserRole(userRole);
			obj.setWeatherStation(weatherStation);
			
			return obj;
		}
		
		
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitUserPropertie(this);
	}

}
