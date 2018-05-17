import { ConversionFactor } from './ConversionFactor';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Params, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { SensorKnowledge } from './createSensor/sensorKnowledge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class ConversionFactorService {
  constructor(private http: HttpClient) { }
   getConversionFactorByFromId(unitId: string): Observable<ConversionFactor[]> {
     
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('fronUnitId', unitId);

      return this.http.get("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/conversion/getAllFromUnit",  {headers} )
        .map((response) => <ConversionFactor[]> response);
   }

   createConversionFactor(fronUnitId: string, toUnitId: string, conversionMul:string,conversionAdd:string, user: User) {
       const headers = new HttpHeaders()
         .set('Content-Type', 'application/json')
         .set('token', user.token)
         .set('fronUnitId', fronUnitId)
         .set('toUnitId', toUnitId)
         .set('conversionMul', conversionMul)
         .set('conversionAdd', conversionAdd);

       return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/conversion/', {headers})
         .map(conversionFactor => {
             return conversionFactor;
         });
   }

   editSensor(sensorId: string,sensorTypeId: string, user: User) {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('token', '' + user.token)
        .set('sensorId', '' + sensorId)
        .set('sensorKnowledgeId', '' + sensorTypeId);

        console.log(headers);

      return this.http.put("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/",  {headers} );

   }

   getAllSensorKnowledge(): Observable<SensorKnowledge[]> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')

      return this.http.get('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/sensorType' , {headers})
        .map((response) => <SensorKnowledge[]> response);
   }
}
