package it.unifi.sam.terreni.weatherSt.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;

@Entity
@Table(name="user_properties")
public class UserPropertie extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@OneToMany( targetEntity=UnitMeasureKnowledge.class , cascade = CascadeType.REMOVE )
	private Set<UnitMeasureKnowledge> unitMeasure;

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


}
