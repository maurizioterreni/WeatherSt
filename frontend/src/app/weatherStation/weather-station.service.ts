import { WeatherStation } from './weatherstation';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { User } from '../user/user';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class WeatherStationService {
  constructor(private _http: HttpClient) { }
   getAllWeathrStation(): Observable<WeatherStation[]> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')

      return this._http.get('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/weatherstation/getAll' , {headers})
        .map((response) => <WeatherStation[]> response);
   }

   createWeathrStation(user:User, titleName:string, urlImage:string, lat: string, lng:string) {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('token', '' + user.token);

      return this._http.post('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/weatherstation' ,
        JSON.stringify({ longitude: lng, latitude: lat , description: titleName, image: urlImage}), {headers})
        .map((response) => <WeatherStation> response);
   }
}
