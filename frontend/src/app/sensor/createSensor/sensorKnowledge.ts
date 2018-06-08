import { UnitKnowledge } from '../createSensorKnowledge/unitKnowledge';

export interface SensorKnowledge{
  description: string;
  id: number;
  unitMeasureDtos: UnitKnowledge[];
}
