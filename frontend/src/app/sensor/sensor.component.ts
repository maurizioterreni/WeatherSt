/**
 * New typescript file
 */
import { Measure } from '../measure/measure';
import { MeasureService } from '../measure/measure.service';
import { Sensor } from './sensor';
import { User } from '../user/user';
import { SensorService } from './sensor.service';
import { DialogDeleteSensor } from './sheet/delete/dialogDeleteSensor.component';
import { DialogEditSensor } from './sheet/edit/dialogEditSensor.component';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA} from '@angular/material';
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
  sensors: Sensor[];
  weatherStationId: number;
  user: User;
  sensorIdArray: number[];
  constructor(public dialog: MatDialog,private http: HttpClient, private _sensor: SensorService, private route: ActivatedRoute) {
    this.user = JSON.parse(sessionStorage.getItem("currentUser"));//sessionStorage  localStorage
    this.weatherStationId = Number(sessionStorage.getItem("currentWeatherSelected"));//sessionStorage  localStorage
    this.sensorIdArray = JSON.parse(sessionStorage.getItem("currentSensorSelected"));
  }


  ngOnInit(): void {
    this.sensors = [];
    //const sensorIdArray = this.route.snapshot.paramMap.get('weather').split('#');
    this.sensorIdArray = JSON.parse(sessionStorage.getItem("currentSensorSelected"));
    //sensorIdArray.pop(); // rimuovo l'ultimo elemento della lista che è vuoto
    const sensorAsync = [];
    for (const id of this.sensorIdArray) {
        sensorAsync.push(this._sensor.getSensor(''+id));
    }
    forkJoin(sensorAsync).subscribe(results => {
      console.log(results);
      for (const i of results) {
        if(i != null)
          this.sensors.push(<Sensor> i);
      }
    });
  }

  openDeleteSensorSheet(sensorId:string): void {
    let dialogRef = this.dialog.open(DialogDeleteSensor, {
      data: { sensorId: sensorId }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result > 0)
        this.removeSensorWithId(result);
    });
  }

  openEditSensorSheet(sensorId:string): void{
    console.log('edit sensor')
    let dialogRef = this.dialog.open(DialogEditSensor, {
      data: { sensorId: sensorId }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }

  removeSensorWithId(sensorId: number){
    let tempSensors = this.sensors;
    this.sensors = [];
    this.sensorIdArray = [];
    for (const sensor of tempSensors) {
      if(!(sensor.id == sensorId)){
        this.sensors.push(sensor);
        this.sensorIdArray.push(sensor.id);
      }
    }
    sessionStorage.setItem('currentSensorSelected', JSON.stringify(this.sensorIdArray));
  }
}
