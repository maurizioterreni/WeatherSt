import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { ConversionFactor } from '../../../conversion/conversionFactor';
import { ConversionFactorService } from '../../../conversion/ConversionFactor.service';
import { MeasureChart } from '../../../measure/measureChart';
import { Chart } from 'chart.js';
import { MatTabChangeEvent } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MeasureService } from '../../../measure/measure.service';

@Component({
  selector: 'app-temperature-gauge',
  templateUrl: './temperatureGauge.html',
  styleUrls: ['./temperatureGauge.css'],
  providers: [MeasureService, ConversionFactorService]
})
export class TemperatureGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;
  sensorOld: Sensor;
  chart: Chart;
  fromDate: Date;
  toDate: Date;
  conversionFactors: ConversionFactor[];
  maxQuantityArray: string[];
  minQuantityArray: string[];
  labelArray: string[];
  unitConverterSelected = '';

  constructor(private meausureService: MeasureService, private conversionService: ConversionFactorService) {
    this.maxQuantityArray = new Array();
    this.minQuantityArray = new Array();
    this.labelArray = new Array();
  }

  ngOnInit() {
    this.fromDate = new Date();
    this.toDate = new Date();

    this.fromDate.setHours(0,0,0,0);
    this.toDate.setHours(23,59,59,999);

    this.sensorOld = this.sensor;
    this.unitConverterSelected = ''+this.sensor.unitKnowledgeId;
    this.conversionService.getConversionFactorByFromId(this.unitConverterSelected)
      .subscribe(conversionFactors => this.conversionFactors = conversionFactors);
  }

  ngOnChanges(changes: SimpleChanges) {

  }


  onChangeObj(event) {
    let value = new Number(event.value);
    console.log(value);
    if(value >= 0){
      this.changeMeasue(this.conversionFactors[event.value].conversionFactorMul,
        this.conversionFactors[event.value].conversionFactorAdd,
        this.conversionFactors[event.value].toSymbol);
    }else{
      console.log(this.sensorOld);
      this.sensor = this.sensorOld;
    }
  }

  changeMeasue(mul,add,symbol){
    this.sensor.measure.quantity = (this.sensor.measure.quantity * mul) + add;
    this.sensor.minMeasure.quantity = (this.sensor.minMeasure.quantity * mul) + add;
    this.sensor.maxMeasure.quantity = (this.sensor.maxMeasure.quantity * mul) + add;

    this.sensor.symbol = symbol;
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
    this.chart = new Chart('canvas-TemperatureGaugeComponent',{
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
