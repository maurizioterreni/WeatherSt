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
import { Chart } from 'chart.js';


@Component({
  selector: 'app-sensor',
  styleUrls: ['sensor.css'],
  templateUrl: 'sensor.html'
})
export class SensorComponent {
  chart = [];
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
    console.log(sensorAsync);
    forkJoin(sensorAsync).subscribe(results => {
      for (const i of results) {
        console.log(i);
        this.sensors.push(<Sensor> i);
      }
    });



  }

  onTabChange(event: MatTabChangeEvent) {
    console.log('event => ', event);
    console.log('index => ', event.index);
    console.log('tab => ', event.tab);

    const temp_max: number = [10,20,30,20];

    const temp_min: number = [15,21,12,10];


    this.chart = new Chart('canvas',{
      type: 'line',
      data: {
        labels: ['1','2','3','4'],
        datasets: [
              {
                data: temp_max,
                borderColor: "#3cba9f",
                fill: false
              },
              {
                data: temp_min,
                borderColor: "#ffcc00",
                fill: false
              },
            ]
          },
          options: {
            legend: {
              display: false
            },
            scales: {
              xAxes: [{
                display: true
              }],
              yAxes: [{
                display: true
              }],
            }
          }
        });
  }
}
