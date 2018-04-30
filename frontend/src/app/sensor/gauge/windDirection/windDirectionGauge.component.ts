import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { Measure } from '../../../measure/measure';
import { Chart } from 'chart.js';
import { MatTabChangeEvent } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MeasureService } from '../../../measure/measure.service';

@Component({
  selector: 'app-windDirection-gauge',
  templateUrl: './windDirectionGauge.html',
  styleUrls: ['./windDirectionGauge.css'],
  providers: [MeasureService]
})
export class WindDirectionGaugeComponent implements OnInit, OnChanges {
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
    this.chart = new Chart('canvas-WindDirectionGaugeComponent',{
      type: 'polarArea',
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

  getAngle(angle: number) : string{
    if(angle >= 0 && angle < 22) return "N";
    else if(angle >= 22 && angle < 45) return "NNE";
    else if(angle >= 45 && angle < 67) return "NE";
    else if(angle >= 67 && angle < 90) return "ENE";
    else if(angle >= 90 && angle < 112) return "E";
    else if(angle >= 112 && angle < 135) return "ESE";
    else if(angle >= 135 && angle < 157) return "SE";
    else if(angle >= 157 && angle < 180) return "SSE";
    else if(angle >= 180 && angle < 202) return "SSE";
    else if(angle >= 202 && angle < 225) return "SSW";
    else if(angle >= 225 && angle < 247) return "SW";
    else if(angle >= 247 && angle < 270) return "WSW";
    else if(angle >= 270 && angle < 292) return "W";
    else if(angle >= 292 && angle < 315) return "WNW";
    else if(angle >= 315 && angle < 337) return "NW";
    else if(angle >= 337 && angle < 360) return "NNW";
    else return "NaN";
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
