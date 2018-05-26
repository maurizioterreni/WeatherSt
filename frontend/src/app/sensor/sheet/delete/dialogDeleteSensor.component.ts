import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { SensorService } from '../../sensor.service';
import {MatSnackBar} from '@angular/material';
import { User } from '../../../user/user';

@Component({
  selector: 'app-DialogDeleteSensor',
  templateUrl: 'dialogDeleteSensor.html',
  styleUrls: ['dialogDeleteSensor.css'],
  providers: [ SensorService ]
})
export class DialogDeleteSensor {
  private user : User;

  constructor(
    private snackBar: MatSnackBar,
    private _sensor: SensorService,
    public dialogRef: MatDialogRef<DialogDeleteSensor>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
    }


  cancelSensor(): void{
    if(this.user != null){
      this._sensor.deleteSensor(this.data.sensorId, this.user)
        .subscribe((res: any) => {
          this.snackBar.open("Sensor deleted correctly", null, {duration: 3000,});
            this.closeDialog(Number(this.data.sensorId));
          },
          err => {
            this.snackBar.open("Generic Error!", null, {duration: 3000,});
            this.closeDialog(-1);
          });
    }else{
      this.snackBar.open("User not valid", null, {duration: 3000,});
    }

    //this.closeDialog(Number(this.data.sensorId));

  }

  onNoClick(): void {
    this.closeDialog(-1);
  }

  closeDialog(sensorId: number): void{
    this.dialogRef.close(sensorId);
  }
}
