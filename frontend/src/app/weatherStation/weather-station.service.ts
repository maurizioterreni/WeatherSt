import { WeatherStation } from './weatherstation';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class WeatherStationService {
  private _weatherurl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/weatherstation/getAll';
  constructor(private _http: HttpClient) { }
   getAllWeathrStation(): Observable<WeatherStation[]> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('token', 'fsdfsdfsdf');

      return this._http.get(this._weatherurl , {headers})
        .map((response) => <WeatherStation[]> response);
   }
}
