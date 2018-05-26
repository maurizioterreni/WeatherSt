/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { SensorService } from '../../sensor/sensor.service';
import { UnitKnowledge } from '../../sensor/createUnitKnowledge/unitKnowledge';
import { ConversionFactorService } from '../ConversionFactor.service';
import { User } from '../../user/user';



@Component({
  selector: 'app-createConversionFactor',
  templateUrl: 'createConversionFactor.component.html',
  styleUrls: ['createConversionFactor.component.css'],
  providers: [SensorService, ConversionFactorService]
})
export class CreateConversionFactorComponent  implements OnInit {
  private user : User;
  // -----------------------------------------------------------------------//
  unitKnowledges: UnitKnowledge[];
  // -----------------------------------------------------------------------//
  selectedToUnitKnowledge: string;
  selectedFromUnitKnowledge: string;

  add: string;
  mul: string;

  constructor(private _sensorService: SensorService, private conversionFactorService: ConversionFactorService,
    public dialogRef: MatDialogRef<CreateConversionFactorComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
    this._sensorService.getAllUnitKnowledge()
      .subscribe(unitKnowledges => this.unitKnowledges = unitKnowledges);
  }

  createConversionFactor(e){
    this.conversionFactorService.createConversionFactor(
            this.selectedFromUnitKnowledge, this.selectedToUnitKnowledge, this.mul, this.add, this.user)
              .subscribe(() => {
                      this.dialogRef.close();
                  },
                  err => {
                    console.log(err);
          //openSnackBar("User or Password wrong", "undo");

              });

  }

  writeAdd(str:string){
    this.add = str;
  }

  writeMul(str:string){
    this.mul = str;
  }

  getSymbol(id){
    if(Number(id) > 0){
      for (let i of this.unitKnowledges){
        if(i.id == id)
          return i.symbol;
        }
    }

    return '';
  }
}
