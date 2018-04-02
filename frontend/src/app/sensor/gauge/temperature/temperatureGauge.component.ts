import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { Measure } from '../../../measure/measure';

@Component({
  selector: 'app-temperature-gauge',
  templateUrl: './temperatureGauge.html',
  styleUrls: ['./temperatureGauge.css']
})
export class TemperatureGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;

  constructor() {
  }

  ngOnInit() { }

  ngOnChanges(changes: SimpleChanges) {

  }
}
