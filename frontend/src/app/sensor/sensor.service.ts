import { Sensor } from './sensor';
import { User } from '../user/user';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Params, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { SensorKnowledge } from './createSensor/sensorKnowledge';
import { UnitKnowledge } from './createSensorKnowledge/unitKnowledge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class SensorService {
  constructor(private http: HttpClient) { }
   getSensor(sensorId: string): Observable<Object> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('sensorId', sensorId)
        .set('unitMeasureId', '1');

      return this.http.get("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/",  {headers} );
   }


   deleteSensor(sensorId: string, user: User) {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('sensorId', '' + sensorId)
        .set('weatherId', '' + user.weatherId)
        .set('token', '' + user.token);

      return this.http.delete("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/",  {headers} );
   }

   createSensor(sensorTypeId: string, user: User) {
       const headers = new HttpHeaders()
         .set('Content-Type', 'application/json')
         .set('token', user.token)

       return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor',
           JSON.stringify({ weatherId: user.weatherId, sensorTypeId: sensorTypeId }), {headers})
         .map(sensor => {
             return sensor;
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


   getAllUnitKnowledge(): Observable<UnitKnowledge[]> {
     const headers = new HttpHeaders()
       .set('Content-Type', 'application/json');

     return this.http.get('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/unitKnowledge', {headers})
      .map((response) => <UnitKnowledge[]> response);
   }

   getAllUnitKnowledgeBySensorKnowledge(sensorKnowledge: string): Observable<UnitKnowledge[]> {
     const headers = new HttpHeaders()
       .set('Content-Type', 'application/json')
       .set('sensorKnowledgeId', sensorKnowledge);

     return this.http.get('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/unitKnowledge/fromSensorKnowledge', {headers})
      .map((response) => <UnitKnowledge[]> response);
   }




   createSensorKnowledge(user: User, description: string, selectedUnitKnowledge: string){
     const headers = new HttpHeaders()
       .set('Content-Type', 'application/json')
       .set('token', '' + user.token)

     return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/sensorKnowledge',
           JSON.stringify({ description: description, selectedUnitKnowledge: selectedUnitKnowledge }), {headers});
   }

   createUnitKnowledge(user:User, symbol:string, name:string){
     const headers = new HttpHeaders()
       .set('Content-Type', 'application/json')
       .set('token', '' + user.token)

     return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/unitKnowledge',
         JSON.stringify({ symbol: symbol, name: name }), {headers});
   }
}
