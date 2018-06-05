package it.unifi.sam.terreni.weatherSt.model.usage;

public interface Usage {

	public void accept(UsageVisitor visitor);
	
}
