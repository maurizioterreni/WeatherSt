/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { SensorKnowledgeService } from './sensorKnowledge.service'
import { SensorKnowledge } from './sensorKnowledge';
import { CreateSensorService } from './createSensor.service';

@Component({
  selector: 'app-CreateSensor',
  templateUrl: 'createSensor.component.html',
  styleUrls: ['createSensor.component.css'],
  providers: [SensorKnowledgeService,CreateSensorService]
})
export class CreateSensorComponent  implements OnInit {
  // -----------------------------------------------------------------------//
  sensorKnowledges: SensorKnowledge[];
  selectedSensorKnowledge: string;
  // -----------------------------------------------------------------------//
  constructor(private _createSensorService: CreateSensorService, private _sensorKnowledgeService: SensorKnowledgeService, public dialogRef: MatDialogRef<CreateSensorComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.selectedSensorKnowledge = '0';
    this._sensorKnowledgeService.getAllSensorKnowledge()
    .subscribe(sensorKnowledges => this.sensorKnowledges = sensorKnowledges);
  }

  createSensor(e){
    this._createSensorService.createSensor(this.selectedSensorKnowledge)
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
