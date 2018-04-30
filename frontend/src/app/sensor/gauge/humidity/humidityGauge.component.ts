import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { Measure } from '../../../measure/measure';
import { Chart } from 'chart.js';
import { MatTabChangeEvent } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MeasureService } from '../../../measure/measure.service';

@Component({
  selector: 'app-humidity-gauge',
  templateUrl: './humidityGauge.html',
  styleUrls: ['./humidityGauge.css'],
  providers: [MeasureService]
})
export class HumidityGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;
  chart: Chart;
  fromDate: Date;
  toDate: Date;
  quantityArray: string[];
  labelArray: string[];

  constructor(private meausureService: MeasureService) {
      this.quantityArray = new Array();
      this.labelArray = new Array();
  }

  ngOnInit() {
    this.fromDate = new Date();
    this.toDate = new Date();

    this.fromDate.setHours(0,0,0,0);
    this.toDate.setHours(23,59,59,999);
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    if(type == 'fromDate'){
      this.fromDate = new Date(event.value);
      this.fromDate.setHours(0,0,0,0);
    }
    if(type == 'toDate'){
      this.toDate = new Date(event.value);
      this.toDate.setHours(23,59,59,999);
      //this.toDate.setHours(23,59,59,59);
    }
  }

  drawChart(event: MatDatepickerInputEvent<Date>){
    this.meausureService.getMeasureBetweenDate(this.sensor.id,this.fromDate.getTime(),this.toDate.getTime())
      .subscribe(results => {
        this.labelArray.length = 0;
        this.quantityArray.length = 0;
        for (const i of results) {
          let m = <Measure> i;
          this.labelArray.push(m.dateTime);
          this.quantityArray.push(m.quantity);
        }
        this.initChart();
      });
  }

  initChart(){
    if (this.chart) this.chart.destroy();
    this.chart = new Chart('canvas-HumidityGaugeComponent',{
      type: 'line',
      data: {
        labels: this.labelArray,
        datasets: [
              {
                data: this.quantityArray,
                borderColor: "#3cba9f",
                fill: false
              },
            ]
          },
          options: {
            legend: {
              display: false
            },
            responsive: true,

            scales: {
              xAxes: [{
                display: false
              }],
              yAxes: [{
                display: true
              }],
            }
          }
        });
  }

  onTabChange(event: MatTabChangeEvent) {
    this.meausureService.getMeasureBetweenDate(this.sensor.id,this.fromDate.getTime(),this.toDate.getTime())
      .subscribe(results => {
        this.labelArray.length = 0;
        this.quantityArray.length = 0;
        for (const i of results) {
          let m = <Measure> i;
          this.labelArray.push(m.dateTime);
          this.quantityArray.push(m.quantity);
        }
        this.initChart();
      });
  }
}
