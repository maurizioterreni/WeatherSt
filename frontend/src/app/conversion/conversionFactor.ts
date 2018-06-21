import { Serializable } from '../interface/serializzable';
export interface ConversionFactor {
    id: string;
    fromSymbol: string;
    toSymbol: string;
    conversionFactorMul: number;
    conversionFactorAdd: number;
    conversionFactorDiv: number;
}
