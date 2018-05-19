import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { ConversionFactor } from '../../../conversion/conversionFactor';
import { ConversionFactorService } from '../../../conversion/ConversionFactor.service';
import { MeasureChart } from '../../../measure/measureChart';
import { Chart } from 'chart.js';
import { MatTabChangeEvent } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MeasureService } from '../../../measure/measure.service';
import { Measure } from '../../../measure/measure';

@Component({
  selector: 'app-temperature-gauge',
  templateUrl: './temperatureGauge.html',
  styleUrls: ['./temperatureGauge.css'],
  providers: [MeasureService, ConversionFactorService]
})
export class TemperatureGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;
  chart: Chart;
  fromDate: Date;
  toDate: Date;
  conversionFactors: ConversionFactor[];
  maxQuantityArray: string[];
  minQuantityArray: string[];
  labelArray: string[];
  unitConverterSelected = -1;

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

    //this.unitConverterSelected = this.sensor.unitKnowledgeId;
    this.conversionService.getConversionFactorByFromId(''+this.sensor.unitKnowledgeId)
      .subscribe(conversionFactors => this.conversionFactors = conversionFactors);
  }

  ngOnChanges(changes: SimpleChanges) {

  }


  onChangeObj(event) {
    this.unitConverterSelected = event.value + 0;
  }

  isConversionfactor(): boolean{
    if(this.unitConverterSelected > -1){
      return true;
    }

    return false;
  }

  getMeasureSymbol(measure: Measure){
    if(this.isConversionfactor()){
        return this.conversionFactors[this.unitConverterSelected].toSymbol;
    }

    return measure.symbol;
  }

  getMeasureQuantity(measure: Measure) : string{
    if(this.isConversionfactor()){
      let mul = this.conversionFactors[this.unitConverterSelected].conversionFactorMul + 0;
      let add = this.conversionFactors[this.unitConverterSelected].conversionFactorAdd + 0;
      let qt = ((Number(measure.quantity) * mul) + add);
      return qt + '';
    }

    return measure.quantity;
  }


  getMeasureQuantityByStr(measure: string) : string{
    if(this.isConversionfactor()){
      let mul = this.conversionFactors[this.unitConverterSelected].conversionFactorMul + 0;
      let add = this.conversionFactors[this.unitConverterSelected].conversionFactorAdd + 0;
      let qt = ((Number(measure) * mul) + add);
      return qt + '';
    }

    return measure;
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
          this.maxQuantityArray.push(this.getMeasureQuantityByStr(m.maxQuantity));
          this.minQuantityArray.push(this.getMeasureQuantityByStr(m.minQuantity));
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
          this.maxQuantityArray.push(this.getMeasureQuantityByStr(m.maxQuantity));
          this.minQuantityArray.push(this.getMeasureQuantityByStr(m.minQuantity));
        }
        this.initChart();
      });
  }


    checkConversionFactor(conversionFactors: ConversionFactor[]): boolean{
      if(conversionFactors != null && conversionFactors.length > 0)
        return true;

      return false;
    }
}
