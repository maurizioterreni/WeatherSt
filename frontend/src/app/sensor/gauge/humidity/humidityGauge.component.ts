import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { MeasureChart } from '../../../measure/measureChart';
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
  maxQuantityArray: string[];
  minQuantityArray: string[];
  labelArray: string[];

  constructor(private meausureService: MeasureService) {
      this.maxQuantityArray = new Array();
      this.minQuantityArray = new Array();
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
        this.maxQuantityArray.length = 0;
        this.minQuantityArray.length = 0;
        for (const i of results) {
          let m = <MeasureChart> i;
          this.labelArray.push(m.dateTime);
          this.maxQuantityArray.push(m.maxQuantity);
          this.minQuantityArray.push(m.minQuantity);
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
                data: this.maxQuantityArray,
                borderColor: "#790000",
                fill: false
              },
              {
                data: this.minQuantityArray,
                borderColor: "#055782",
                fill: false
              }
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
        this.maxQuantityArray.length = 0;
        this.minQuantityArray.length = 0;
        for (const i of results) {
          let m = <MeasureChart> i;
          this.labelArray.push(m.dateTime);
          this.maxQuantityArray.push(m.maxQuantity);
          this.minQuantityArray.push(m.minQuantity);
        }
        this.initChart();
      });
  }
}
