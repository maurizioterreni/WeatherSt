import { Measure } from './measure';
import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class MeasureService {
  private _measureurl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/measure/';
  constructor(private _http: Http) { }
   getMeasures(sensorId: string): Observable<Measure> {
      const headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('token', 'fsdfsdfsdf');
      headers.append('sensorId', sensorId);
      headers.append('Access-Control-Allow-Origin', '*');
      const options = new RequestOptions({ headers: headers });
      return this._http.get(this._measureurl, options)
      .map((response: Response) => <Measure> response.json())
      .do(data => console.log(JSON.stringify(data)));
   }
}
