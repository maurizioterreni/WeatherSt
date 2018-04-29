/**
 * New typescript file
 */
import { Measure } from '../measure/measure';
import { MeasureService } from '../measure/measure.service';
import { Sensor } from './sensor';
import { SensorService } from './sensor.service';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { forkJoin } from 'rxjs/observable/forkJoin';


@Component({
  selector: 'app-sensor',
  styleUrls: ['sensor.css'],
  templateUrl: 'sensor.html'
})
export class SensorComponent {

  constructor(private http: HttpClient, private _sensor: SensorService, private route: ActivatedRoute) {}
  sensors: Sensor[];

  ngOnInit(): void {
    this.sensors = [];
    const sensorIdArray = this.route.snapshot.paramMap.get('weather').split('#');
    sensorIdArray.pop(); // rimuovo l'ultimo elemento della lista che è vuoto
    const sensorAsync = [];
    for (const id of sensorIdArray) {
        sensorAsync.push(this._sensor.getSensor(id));
    }
    forkJoin(sensorAsync).subscribe(results => {
      for (const i of results) {
        this.sensors.push(<Sensor> i);
      }
    });



  }
}
