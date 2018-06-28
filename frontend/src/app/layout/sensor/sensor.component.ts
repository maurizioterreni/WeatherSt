import { Component, OnInit } from '@angular/core';
import { Environment } from '../../local/environment';
import { Sensor } from '../../models/sensor/sensor';
import { SensorService } from '../../services/sensor/sensor.service';

/**
 * @title Multi-row toolbar
 */
@Component({
  selector: 'sensor-app',
  templateUrl: 'sensor.html',
  styleUrls: ['sensor.css'],
  providers: [SensorService]
})


export class SensorComponent implements OnInit {
  private environment: Environment;
  sensors: Sensor[];

  constructor(private sensorService: SensorService){
    this.sensors = [];
  }

  ngOnInit() {
    this.sensorService.getSensor(1)
      .subscribe((response: Sensor[]) => {
          this.sensors = response;
      });
  }
}
