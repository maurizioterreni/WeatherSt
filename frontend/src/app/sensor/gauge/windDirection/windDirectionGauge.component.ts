import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Sensor } from '../../sensor';
import { ConversionFactor } from '../../../conversion/conversionFactor';
import { ConversionFactorService } from '../../../conversion/ConversionFactor.service';
import { Measure } from '../../../measure/measure';
import { MeasureChart } from '../../../measure/measureChart';
import { Chart } from 'chart.js';
import { MatTabChangeEvent } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MeasureService } from '../../../measure/measure.service';
import { UserService } from '../../../user/user.service';
import { User } from '../../../user/user';

@Component({
  selector: 'app-windDirection-gauge',
  templateUrl: './windDirectionGauge.html',
  styleUrls: ['./windDirectionGauge.css'],
  providers: [MeasureService, ConversionFactorService, UserService]
})
export class WindDirectionGaugeComponent implements OnInit, OnChanges {
  @Input() sensor: Sensor;
  chart: Chart;
  fromDate: Date;
  toDate: Date;
  quantityArray: string[];
  labelArray: string[];
  conversionFactors: ConversionFactor[];
  unitConverterSelected = -1;
  user: User;

  constructor(private meausureService: MeasureService, private userService: UserService,private conversionService: ConversionFactorService) {
      this.quantityArray = new Array();
      this.labelArray = new Array();
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
    this.fromDate = new Date();
    this.toDate = new Date();

    this.fromDate.setHours(0,0,0,0);
    this.toDate.setHours(23,59,59,999);

    //this.unitConverterSelected = this.sensor.unitKnowledgeId;
    this.conversionService.getConversionFactorByFromId(''+this.sensor.unitKnowledgeId)
      .subscribe(conversionFactors => {
        this.conversionFactors = conversionFactors;
        if(this.user){
          let i = 0;
          for(const unit of this.user.unitMeasure){
            for(const conversion of this.conversionFactors){
              if(Number(unit) == Number(conversion.id)){
                this.unitConverterSelected = i;
              }
              i = i + 1;
            }
          }
        }
      });
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  onChangeObj(event) {

    if(this.isConversionfactor() && this.user != null){
      this.userService.removeUnitKnowledgeUser(this.user, this.conversionFactors[this.unitConverterSelected].id)
        .subscribe(user => {
          if (user){
            sessionStorage.setItem('currentUser', JSON.stringify(user));
          }
        });
    }



    this.unitConverterSelected = event.value + 0;

    if(this.isConversionfactor() && this.user != null){
      this.userService.addUnitKnowledgeUser(this.user, this.conversionFactors[this.unitConverterSelected].id)
        .subscribe(user => {
          if (user){
            sessionStorage.setItem('currentUser', JSON.stringify(user));
          }
        });
    }
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
      let div = this.conversionFactors[this.unitConverterSelected].conversionFactorDiv + 0;
      let qt = (((Number(measure.quantity) * mul) + add))/(div);
      return qt + '';
    }

    return measure.quantity;
  }


  getMeasureQuantityByStr(measure: string) : string{
    if(this.isConversionfactor()){
      let mul = this.conversionFactors[this.unitConverterSelected].conversionFactorMul + 0;
      let add = this.conversionFactors[this.unitConverterSelected].conversionFactorAdd + 0;
      let div = this.conversionFactors[this.unitConverterSelected].conversionFactorDiv + 0;
      let qt = (((Number(measure) * mul) + add))/(div);
      return qt + '';
    }

    return measure;
  }

  checkConversionFactor(conversionFactors: ConversionFactor[]): boolean{
    if(conversionFactors != null && conversionFactors.length > 0)
      return true;

    return false;
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
        this.getCountDirection(results);
        this.initChart();
      });
  }

  initChart(){
    if (this.chart) this.chart.destroy();
    this.chart = new Chart('canvas-WindDirectionGaugeComponent',{
      type: 'bar',
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

  getAngle(angleStr: string) : string{
    let angle = Number(angleStr);
    if(angle >= 0 && angle < 22) return "N";
    else if(angle >= 22 && angle < 45) return "NNE";
    else if(angle >= 45 && angle < 67) return "NE";
    else if(angle >= 67 && angle < 90) return "ENE";
    else if(angle >= 90 && angle < 112) return "E";
    else if(angle >= 112 && angle < 135) return "ESE";
    else if(angle >= 135 && angle < 157) return "SE";
    else if(angle >= 157 && angle < 180) return "SSE";
    else if(angle >= 180 && angle < 202) return "S";
    else if(angle >= 202 && angle < 225) return "SSW";
    else if(angle >= 225 && angle < 247) return "SW";
    else if(angle >= 247 && angle < 270) return "WSW";
    else if(angle >= 270 && angle < 292) return "W";
    else if(angle >= 292 && angle < 315) return "WNW";
    else if(angle >= 315 && angle < 337) return "NW";
    else if(angle >= 337 && angle < 360) return "NNW";
    else return "NaN";
  }

  getCountDirection(results: any) : void{
    let N = 0;
    let NNE = 0;
    let NE = 0;
    let ENE = 0;
    let E = 0;
    let ESE = 0;
    let SE = 0;
    let SSE = 0;
    let S = 0;
    let SSW = 0;
    let SW = 0;
    let WSW = 0;
    let W = 0;
    let WNW = 0;
    let NW = 0;
    let NNW = 0;
    for (const i of results) {
      let m = <MeasureChart> i;
      let angle = Number(this.getMeasureQuantityByStr(m.quantity));
      if(angle >= 0 && angle < 22) {
        N = N + 1;
      }else if(angle >= 22 && angle < 45){
        NNE = NNE + 1;
      }else if(angle >= 45 && angle < 67){
        NE = NE + 1;
      }else if(angle >= 67 && angle < 90){
        ENE = ENE + 1;
      }else if(angle >= 90 && angle < 112){
        E = E + 1;
      }else if(angle >= 112 && angle < 135){
        ESE = ESE + 1;
      }else if(angle >= 135 && angle < 157){
        SE = SE + 1;
      }else if(angle >= 157 && angle < 180){
        SSE = SSE + 1;
      }else if(angle >= 180 && angle < 202){
        S = S + 1;
      }else if(angle >= 202 && angle < 225){
        SSW = SSW + 1;
      }else if(angle >= 225 && angle < 247){
        SW = SW + 1;
      }else if(angle >= 247 && angle < 270){
        WSW = WSW + 1;
      }else if(angle >= 270 && angle < 292){
        W = W + 1;
      }else if(angle >= 292 && angle < 315){
        WNW = WNW + 1;
      }else if(angle >= 315 && angle < 337){
        NW = NW + 1;
      }else if(angle >= 337 && angle < 360){
        NNW = NNW + 1;
      }
    }
    this.quantityArray.push(String(N));
    this.labelArray.push("N");
    this.quantityArray.push(String(NNE));
    this.labelArray.push("NNE");
    this.quantityArray.push(String(NE));
    this.labelArray.push("NE");
    this.quantityArray.push(String(ENE));
    this.labelArray.push("ENE");
    this.quantityArray.push(String(E));
    this.labelArray.push("E");
    this.quantityArray.push(String(ESE));
    this.labelArray.push("ESE");
    this.quantityArray.push(String(SE));
    this.labelArray.push("SE");
    this.quantityArray.push(String(SSE));
    this.labelArray.push("SSE");
    this.quantityArray.push(String(S));
    this.labelArray.push("S");
    this.quantityArray.push(String(SSW));
    this.labelArray.push("SSW");
    this.quantityArray.push(String(SW));
    this.labelArray.push("SW");
    this.quantityArray.push(String(WSW));
    this.labelArray.push("WSW");
    this.quantityArray.push(String(W));
    this.labelArray.push("W");
    this.quantityArray.push(String(WNW));
    this.labelArray.push("WNW");
    this.quantityArray.push(String(NW));
    this.labelArray.push("Nw");
    this.quantityArray.push(String(NNW));
    this.labelArray.push("NNW");
  }

  onTabChange(event: MatTabChangeEvent) {
    this.meausureService.getMeasureBetweenDate(this.sensor.id,this.fromDate.getTime(),this.toDate.getTime())
      .subscribe(results => {
        this.labelArray.length = 0;
        this.quantityArray.length = 0;
        this.getCountDirection(results);
        this.initChart();
      });
  }
}
