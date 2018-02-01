package it.unifi.sam.terreni.weatherSt.model.facotry;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.DirectionUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.HumidityUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.PressureUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.RainUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.SpeedUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.TemperatureUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.UvUnits;

public class UnitMeasureFamilyFacotry {
	public static UnitMeasureFamily createUnit(String unit) {
		if(DirectionUnits.getFromString(unit) != null) {
			return ModelFactory.directionUnit(DirectionUnits.getFromString(unit));
		}else if(HumidityUnits.getFromString(unit) != null) {
			return ModelFactory.humidityUnit(HumidityUnits.getFromString(unit));
		}else if(PressureUnits.getFromString(unit) != null) {
			return ModelFactory.pressureUnit(PressureUnits.getFromString(unit));
		}else if(RainUnits.getFromString(unit) != null) {
			return ModelFactory.rainUnit(RainUnits.getFromString(unit));
		}else if(SpeedUnits.getFromString(unit) != null) {
			return ModelFactory.speedUnit(SpeedUnits.getFromString(unit));
		}else if(TemperatureUnits.getFromString(unit) != null) {
			return ModelFactory.temperatureUnit(TemperatureUnits.getFromString(unit));
		}else if(UvUnits.getFromString(unit) != null) {
			return ModelFactory.uvUnit(UvUnits.getFromString(unit));
		}else {
			return null;
		}
	}
}
