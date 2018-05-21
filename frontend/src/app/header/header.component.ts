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
import { CreateSensorKnowledgeComponent } from '../sensor/createSensorKnowledge/createSensorKnowledge.component';
import { CreateUnitKnowledgeComponent } from '../sensor/createUnitKnowledge/createUnitKnowledge.component';
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
  constructor(public dialog: MatDialog,private authenticationService: AuthenticationService,private router: Router) {
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

      window.location.reload();
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

        window.location.reload();
     });
  }

  createWeatherStation() {
     let dialogRef = this.dialog.open(CreateWeatherStationComponent, {
       data: { }
     });

     dialogRef.afterClosed().subscribe(result => {
       window.location.reload();
     });
  }

  logOut(){
    this.authenticationService.logout();
    this.user = null;

    window.location.reload();
  }

  createSensorTypeKnowledge(){
    let dialogRef = this.dialog.open(CreateSensorKnowledgeComponent, {
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  createUnitMeasureKnowledge(){
    let dialogRef = this.dialog.open(CreateUnitKnowledgeComponent, {
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
