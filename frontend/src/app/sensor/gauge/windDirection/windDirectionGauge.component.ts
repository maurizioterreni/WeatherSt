import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { Measure } from '../../../measure/measure';

@Component({
  selector: 'app-windDirection-gauge',
  templateUrl: './windDirectionGauge.html',
  styleUrls: ['./windDirectionGauge.css']
})
export class WindDirectionGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;

  constructor() {
  }

  ngOnInit() { }

  ngOnChanges(changes: SimpleChanges) {

  }
}
