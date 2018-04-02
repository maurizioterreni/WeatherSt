import { Serializable } from '../interface/serializzable';
import { Measure } from '../measure/measure';
export interface Sensor {
  description: string;
  symbol: string;
  name: string;
  maxMeasure: Measure;
  measure: Measure;
  minMeasure: Measure;
  conversionFactor: number;
}
