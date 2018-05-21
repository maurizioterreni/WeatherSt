/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { SensorService } from '../sensor.service';
import { UnitKnowledge } from './UnitKnowledge';
import { User } from '../../user/user';



@Component({
  selector: 'app-createSensorKnowledge',
  templateUrl: 'createSensorKnowledge.component.html',
  styleUrls: ['createSensorKnowledge.component.css'],
  providers: [SensorService]
})
export class CreateSensorKnowledgeComponent  implements OnInit {
  private user : User;
  public description: string;
  unitKnowledges: UnitKnowledge[];
  // -----------------------------------------------------------------------//
  selectedUnitKnowledge: string;
  // -----------------------------------------------------------------------//
  constructor(private _sensorService: SensorService, public dialogRef: MatDialogRef<CreateSensorKnowledgeComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
    this.selectedUnitKnowledge = '0';
    this._sensorService.getAllUnitKnowledge()
      .subscribe(unitKnowledges => this.unitKnowledges = unitKnowledges);
  }

  createSensor(e){
    this._sensorService.createSensorKnowledge(this.user, this.description, this.selectedUnitKnowledge)
    .subscribe(
      res => {
          this.dialogRef.close();
        },
        err => {
          console.log('ERROR');
          //openSnackBar("User or Password wrong", "undo");

      });
  }

  addDescription(str){
    this.description = str;
  }
}
