/**
 * New typescript file
 */
import { User } from '../user/user';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { AppMaterialModules } from '../material.module';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { LoginComponent } from '../dialog/login/login.component';
import { CreateSensorComponent } from '../sensor/createSensor/createSensor.component';
import { CreateWeatherStationComponent } from '../weatherStation/createWeatherStation/createWeatherStation.component';
import { AuthenticationService } from '../dialog/login/authentication.service';



@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.css'],
  providers: [AuthenticationService]
})
export class HeaderComponent implements OnInit {
  // -----------------------------------------------------------------------//
  public title = 'Weather Station';
  public user : User;
  // -----------------------------------------------------------------------//
  constructor(public dialog: MatDialog,private authenticationService: AuthenticationService,) {
    if(sessionStorage.getItem("currentUser"))
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
  }

  openLogin() {
    let dialogRef = this.dialog.open(LoginComponent, {
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(this.user == null)
        this.user = JSON.parse(sessionStorage.getItem("currentUser"));

      this.router.navigate(['/','']);
    });
  //   this.dialog.open(LoginComponent, {});
  }

  createSensor() {
     let dialogRef = this.dialog.open(CreateSensorComponent, {
       data: { weatherId: this.user.weatherId }
     });

     dialogRef.afterClosed().subscribe(result => {
       if(this.user == null)
         this.user = JSON.parse(sessionStorage.getItem("currentUser"));
     });
  }

  createWeatherStation() {
     let dialogRef = this.dialog.open(CreateWeatherStationComponent, {
       data: { }
     });

     dialogRef.afterClosed().subscribe(result => {

     });
  }

  logOut(){
    this.authenticationService.logout();
    this.user = null;

    this.router.navigate(['/','']);
  }
}
