import { WeatherStation } from '../../models/weatherStation/weatherstation';
import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BaseUrl } from '../../baseUrl';

@Injectable()
export class WeatherStationService {
  constructor(private _http: HttpClient) { }
   getAllWeathrStation(){
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')

      return this._http.get(BaseUrl.API_ENDPOINT + 'weatherstation/getAll' , {headers});
   }

  /* createWeathrStation(user:User, titleName:string, urlImage:string, lat: string, lng:string) {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('token', '' + user.token);

      return this._http.post('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/weatherstation' ,
        JSON.stringify({ longitude: lng, latitude: lat , description: titleName, image: urlImage}), {headers})
        .map((response) => <WeatherStation> response);
   }*/
}
