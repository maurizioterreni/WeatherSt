/**
 * New typescript file
 */
import { Component, Input, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { WeatherStationService } from '../weather-station.service';
import { User } from '../../user/user';



@Component({
  selector: 'app-createWeatherStation',
  templateUrl: 'createWeatherStation.component.html',
  styleUrls: ['createWeatherStation.component.css'],
  providers: [WeatherStationService]
})
export class CreateWeatherStationComponent  implements OnInit {
  private user : User;
  zoom: number = 15;

  // initial center position for the map
  lat: number = 43.71893;
  lng: number = 10.95459;
  titleName = '';
  urlImage = '';

  constructor(private weatherStationService: WeatherStationService, public dialogRef: MatDialogRef<CreateWeatherStationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = JSON.parse(sessionStorage.getItem("currentUser"));
  }

  ngOnInit() {
    if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            this.lat = position.coords.latitude;
            this.lng = position.coords.longitude;
          });
        } else {
          this.lat = 43.71893;
          this.lng = 10.95459;
    }
  }

  addTitle(title:string){
    this.titleName = title;
  }

  addUrl(url:string){
    this.urlImage = url;
  }

  clickedMarker() {
    console.log('clicked the marker' );
  }

  mapClicked($event: MouseEvent) {
  }

  markerDragEnd($event) {
    this.lat = $event.coords.lat;
    this.lng = $event.coords.lng;
  }

  createSensor(e){

    console.log(this.titleName + '  ' + this.urlImage);

    this.weatherStationService.createWeathrStation(this.user, this.titleName, this.urlImage, ''+this.lat, ''+this.lng)
      .subscribe(
        res => {
          console.log(res);
          this.dialogRef.close();
        },
        err => {
          console.log('ERROR');
          //openSnackBar("User or Password wrong", "undo");

      });
  }
}
