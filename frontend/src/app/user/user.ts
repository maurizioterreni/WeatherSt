import { Serializable } from '../interface/serializzable';
export interface User {
    username: string;
    password: string;
    email: string;
    weatherId: number;
    token: string;
    userRole: string;
    unitMeasure: number[];
    weatherLikes: number[];
}
