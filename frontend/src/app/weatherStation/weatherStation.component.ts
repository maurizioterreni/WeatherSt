/**
 * New typescript file
 */
import { WeatherStationService } from './weather-station.service';
import { WeatherStation } from './weatherstation';
import { Component, Inject } from '@angular/core';
import { User } from '../user/user';
import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';
import {DialogMaps} from '../dialog/maps/dialog.maps.component';
import { UserService } from '../user/user.service';


@Component ({
   selector: 'app-weatherstation',
   templateUrl: 'weather-station.html',
   styleUrls: ['weather-station.css'],
   providers: [WeatherStationService,UserService]
})
export class WeatherStationComponent {
    status = true;
    public user : User;
    public weatherLikes = [];
    checked = false;

    weatherStations: WeatherStation[];
    constructor(private _weatherStation: WeatherStationService,private _user: UserService, public dialog: MatDialog, private router: Router) {
      if(sessionStorage.getItem("currentUser")){
        this.user = JSON.parse(sessionStorage.getItem("currentUser"));
        this.weatherLikes = this.user.weatherLikes;
      }
    }
    ngOnInit(): void {

      this._weatherStation.getAllWeathrStation()
        .subscribe(weatherStations => this.weatherStations = weatherStations);
    }
    openWeatherStation(sensorsId: number[], weatherId: string) {
      sessionStorage.setItem('currentWeatherSelected', weatherId);
      sessionStorage.setItem('currentSensorSelected', JSON.stringify(sensorsId));

      this.router.navigate(['/sensor','']);
    }
    openMap(longitude: string, latitude: string) {
       this.dialog.open(DialogMaps, {
        data: {
          lat: latitude,
          lng: longitude
        }
      });
     }

     isLiked(weatherId){
       for(const i of this.weatherLikes){
         if(i == weatherId){
           return true;
         }
       }
       return false;
     }

     weatherLike(weatherId, isLiked:boolean){
       if(!isLiked){
          this._user.addWeatherLikes(this.user, weatherId)
            .subscribe(
                res => {
                  this.user = JSON.parse(sessionStorage.getItem("currentUser"));

                  this.weatherLikes = this.user.weatherLikes;
               },
                 err => {
                   console.log(err);
                 });
        }else{
          this._user.removeWeatherLikes(this.user, weatherId)
            .subscribe(
                res => {
                  this.user = JSON.parse(sessionStorage.getItem("currentUser"));

                  this.weatherLikes = this.user.weatherLikes;
               },
                 err => {
                   console.log(err);
                 });
        }
     }
}
