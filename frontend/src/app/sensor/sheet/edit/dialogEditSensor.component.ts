import {Component, Inject,  Input, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { SensorService } from '../../sensor.service';
import { SensorKnowledge } from '../../createSensor/sensorKnowledge';
import {MatSnackBar} from '@angular/material';
import { User } from '../../../user/user';

@Component({
  selector: 'app-DialogEditSensor',
  templateUrl: 'dialogEditSensor.html',
  styleUrls: ['dialogEditSensor.css'],
  providers: [ SensorService ]
})
export class DialogEditSensor implements OnInit {
  private user : User;
  sensorKnowledges: SensorKnowledge[];
  selectedSensorKnowledge: string;

  constructor(
    private snackBar: MatSnackBar,
    private _sensor: SensorService,
    public dialogRef: MatDialogRef<DialogEditSensor>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));//sessionStorage  localStorage
    }

  ngOnInit() {
      this.selectedSensorKnowledge = '0';
      this._sensor.getAllSensorKnowledge()
        .subscribe(sensorKnowledges => this.sensorKnowledges = sensorKnowledges);
  }

  editSensor(e){

    this._sensor.editSensor(this.data.sensorId,this.selectedSensorKnowledge, this.user)
      .subscribe(
        res => {
            this.dialogRef.close();
          },
          err => {
            console.log('ERROR');
          //openSnackBar("User or Password wrong", "undo");

      });
  }

  onNoClick(): void {
    this.closeDialog();
  }

  closeDialog(): void{
    this.dialogRef.close();
  }
}
