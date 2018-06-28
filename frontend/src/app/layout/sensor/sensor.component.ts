import { Component, OnInit } from '@angular/core';
import { Environment } from '../../local/environment';


/**
 * @title Multi-row toolbar
 */
@Component({
  selector: 'sensor-app',
  templateUrl: 'sensor.html',
  styleUrls: ['sensor.css'],
})


export class SensorComponent implements OnInit {
  private environment: Environment;


  constructor(){

  }

  ngOnInit() {

  }
}
