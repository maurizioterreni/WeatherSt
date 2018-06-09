/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { SensorKnowledge } from './sensorKnowledge';
import { UnitKnowledge } from '../createSensorKnowledge/unitKnowledge';
import { SensorService } from '../sensor.service';
import { User } from '../../user/user';



@Component({
  selector: 'app-CreateSensor',
  templateUrl: 'createSensor.component.html',
  styleUrls: ['createSensor.component.css'],
  providers: [SensorService]
})
export class CreateSensorComponent  implements OnInit {
  private user : User;
  // -----------------------------------------------------------------------//
  sensorKnowledges: SensorKnowledge[];
  selectedSensorKnowledge: string;
  title: string;

  // -----------------------------------------------------------------------//
  constructor(private _sensorService: SensorService, public dialogRef: MatDialogRef<CreateSensorComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
    this.selectedSensorKnowledge = '0';
    this._sensorService.getAllSensorKnowledge()
        .subscribe(sensorKnowledges => this.sensorKnowledges = sensorKnowledges);
  }

  createSensor(e){

    this._sensorService.createSensor(this.selectedSensorKnowledge, this.title , this.user)
    .subscribe(
      res => {
          this.dialogRef.close(true);
        },
        err => {
          this.dialogRef.close(false);
          //openSnackBar("User or Password wrong", "undo");

      });
  }

  addTitle(title: string): void{
    this.title = title;
  }
}
