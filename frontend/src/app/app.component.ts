import { WeatherStationService } from './weatherStation/weather-station.service';
import { WeatherStation } from './weatherStation/weatherstation';
import { Component, Inject } from '@angular/core';

import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';
import {DialogMaps} from './dialog/maps/dialog.maps.component';
import { SensorService } from './sensor/sensor.service';


@Component ({
   selector: 'app-root',
   templateUrl: 'app.component.html',
   styleUrls: ['app.component.css'],
   providers: [WeatherStationService, SensorService]
})
export class AppComponent {
}
