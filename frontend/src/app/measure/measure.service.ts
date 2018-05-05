import { Measure } from './measure';
import { MeasureChart } from './measureChart';
import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class MeasureService {
  private _getMeasuresUrl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/measure/';
  private _getMeasureBetweenDateUrl = 'http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/measure/getMeasureByDate';
  constructor(private _http: HttpClient) { }

   getMeasures(sensorId: string): Observable<Measure> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('sensorId', sensorId)
        .set('Access-Control-Allow-Origin', '*');


      return this._http.get(this._getMeasuresUrl,  {headers} )
        .map((response: Response) => <Measure> response.json());
   }


   getMeasureBetweenDate(sensorId: number, fromDate: number, toDate: number): Observable<MeasureChart[]> {

     //console.log('fromDate: ' + fromDate + ' toDate: ' + toDate);
     const headers = new HttpHeaders()
       .set('Content-Type', 'application/json')
       .set('sensorId', '' + sensorId)
       .set('fromDate', '' + fromDate)
       .set('toDate', '' + toDate)
       .set('Access-Control-Allow-Origin', '*');

       return this._http.get(this._getMeasureBetweenDateUrl,  {headers} )
         .map((response) => <MeasureChart[]> response);

   }
}
