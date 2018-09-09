import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../../models/sensor/sensor';

@Component({
  selector: 'pressure-card',
  templateUrl: './pressure.html',
  styleUrls: ['./pressure.css'],
  providers: []
})
export class PressureCardComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;

  constructor() {

  }

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges) {

  }
}
