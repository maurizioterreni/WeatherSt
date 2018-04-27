import { SensorKnowledge } from './sensorKnowledge';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class SensorKnowledgeService {
  private _sensorKnowledgeurl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/sensorType';
  constructor(private _http: HttpClient) { }
   getAllSensorKnowledge(): Observable<SensorKnowledge[]> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')

      return this._http.get(this._sensorKnowledgeurl , {headers})
        .map((response) => <SensorKnowledge[]> response);
   }
}
