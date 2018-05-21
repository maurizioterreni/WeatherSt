/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { SensorService } from '../sensor.service';
import { UnitKnowledge } from '../createSensorKnowledge/UnitKnowledge';
import { User } from '../../user/user';



@Component({
  selector: 'app-createUnitKnowledge',
  templateUrl: 'createUnitKnowledge.component.html',
  styleUrls: ['createUnitKnowledge.component.css'],
  providers: [SensorService]
})
export class CreateUnitKnowledgeComponent  implements OnInit {
  private user : User;
  public name: string;
  public symbol: string;

  constructor(private _sensorService: SensorService, public dialogRef: MatDialogRef<CreateUnitKnowledgeComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() { }

  createSensor(e){
    this._sensorService.createUnitKnowledge(this.user, this.symbol, this.name)
    .subscribe(
      res => {
          this.dialogRef.close();
        },
        err => {
          console.log('ERROR');
          //openSnackBar("User or Password wrong", "undo");

      });
  }

  addName(str){
    this.name = str;
  }

  addSymbol(str){
    this.symbol = str;
  }
}
