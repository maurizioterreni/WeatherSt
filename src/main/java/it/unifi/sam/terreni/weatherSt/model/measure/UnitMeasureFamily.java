package it.unifi.sam.terreni.weatherSt.model.measure;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "units_type")
public abstract class UnitMeasureFamily extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	protected UnitMeasureFamily(){
		super();
	}
	
	protected UnitMeasureFamily(String uuid) {
		super(uuid);
	}
	
}
