import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    addWeatherLikes(user:User, weatherId:string) {
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('token','' +  user.token)
          .set('weatherId','' +  weatherId);


        return this.http.post<any>("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/user/addWeatherLike/", JSON.stringify(null), {headers})
            .map(user => {
                // login successful if there's a jwt token in the response
              if (user){
                sessionStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
            });
    }

    removeWeatherLikes(user:User, weatherId:string) {
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('token','' +  user.token)
          .set('weatherId','' +  weatherId);


        return this.http.delete("http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/user/removeWeatherLike/", {headers})
            .map(user => {
                // login successful if there's a jwt token in the response
              if (user){
                sessionStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
            });
    }

    addUnitKnowledgeUser(user:User, unitId:string) {
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('Accept', 'application/json')
          .set('token',  '' + user.token)
          .set('unitId',  '' + unitId);

        return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/user/addUnitMeasure/',  JSON.stringify(null), {headers})
            .map(user => {
                // login successful if there's a jwt token in the response
              if (user){
                sessionStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
            });
    }


    removeUnitKnowledgeUser(user:User, unitId:string) {//se id non presente nella lista dell'utente il servizio restituisce un 200 ok senza fare nulla
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('Accept', 'application/json')
          .set('token', '' + user.token)
          .set('unitId', '' + unitId);

        return this.http.delete('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/user/removeUnitMeasure/', {headers})
            .map(user => {
                // login successful if there's a jwt token in the response
              if (user){
                sessionStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
            });
    }
}
