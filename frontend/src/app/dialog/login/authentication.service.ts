import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('Accept', 'application/json');

        return this.http.post<any>('http://localhost:8080/WeatherSt-0.0.1-SNAPSHOT/rest/1.0/login', JSON.stringify({ username: username, password: password }), {headers})
            .map(user => {
                // login successful if there's a jwt token in the response
              if (user){
                localStorage.setItem('currentUser', JSON.stringify(user));
              }

              return user;
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
      //  this.router.navigate('');
    }
}
