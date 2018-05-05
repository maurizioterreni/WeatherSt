import { Sensor } from './sensor';
import { User } from '../user/user';
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

      console.log(sensorId);
      console.log(headers);
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


   getAllSensorKnowledge(): Observable<SensorKnowledge[]> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')

      return this.http.get('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/sensorType' , {headers})
        .map((response) => <SensorKnowledge[]> response);
   }
}
