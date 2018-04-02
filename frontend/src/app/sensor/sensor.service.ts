import { Sensor } from './sensor';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Params, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class SensorService {
  private _sensorurl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/sensor/';
  constructor(private _http: HttpClient) { }
   getSensor(sensorId: string): Observable<Object> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('token', 'fsdfsdfsdf')
        .set('sensorId', sensorId)
        .set('unitMeasureId', '1');

      return this._http.get(this._sensorurl,  {headers} );
   }
}
