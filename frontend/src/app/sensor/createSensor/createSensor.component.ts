/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { SensorKnowledge } from './sensorKnowledge';
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
  // -----------------------------------------------------------------------//
  constructor(private _sensorService: SensorService, public dialogRef: MatDialogRef<CreateSensorComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(localStorage.getItem("currentUser"));
  }

  ngOnInit() {
    this.selectedSensorKnowledge = '0';
    this._sensorService.getAllSensorKnowledge()
    .subscribe(sensorKnowledges => this.sensorKnowledges = sensorKnowledges);
  }

  createSensor(e){

    this._sensorService.createSensor(this.selectedSensorKnowledge, this.user)
    .subscribe(
      res => {
          this.dialogRef.close();
        },
        err => {
          console.log('ERROR');
          //openSnackBar("User or Password wrong", "undo");

      });
  }
}
