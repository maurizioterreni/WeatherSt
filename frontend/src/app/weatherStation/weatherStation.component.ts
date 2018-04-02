/**
 * New typescript file
 */
import { WeatherStationService } from './weather-station.service';
import { WeatherStation } from './weatherstation';
import { Component, Inject } from '@angular/core';

import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';
import {DialogMaps} from '../dialog/maps/dialog.maps.component';


@Component ({
   selector: 'app-weatherstation',
   templateUrl: 'weather-station.html',
   styleUrls: ['weather-station.css'],
   providers: [WeatherStationService]
})
export class WeatherStationComponent {
     status = true;

     weatherStations: WeatherStation[];
     constructor(private _weatherStation: WeatherStationService, public dialog: MatDialog, private router: Router) {}
     ngOnInit(): void {
      this._weatherStation.getAllWeathrStation()
      .subscribe(weatherStations => this.weatherStations = weatherStations);
    }
    openWeatherStation(sensorsId: number[]) {
      let parsing = '';
      for (const entry of sensorsId) {
        parsing += entry + '#';
      }
      this.router.navigate(['/sensor', parsing]);
    }
    openMap(longitude: string, latitude: string) {
       this.dialog.open(DialogMaps, {
        data: {
          lat: latitude,
          lng: longitude
        }
      });
     }

}
