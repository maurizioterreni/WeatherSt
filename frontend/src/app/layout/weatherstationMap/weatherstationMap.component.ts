import { Component, OnInit } from '@angular/core';
import { WeatherStationService } from '../../services/weatherstation/weatherstation.service';
import { WeatherStation } from '../../models/weatherstation/weatherstation';
import { Marker } from './marker';

@Component({
  selector: 'weatherstationMap-map',
  styleUrls: ['weatherstationMap.css'],
  templateUrl: 'weatherstationMap.html',
  providers: [WeatherStationService]
})

export class WeatherStationMapComponent  implements OnInit {
  weatherstations: WeatherStation[];
  markers: Marker[];
  lat = 51.678418;
  lng = 7.809007;

  constructor(private weatherStationService: WeatherStationService) {
    this.weatherstations = [];
    this.markers = [];
  }

  ngOnInit() {
    this.weatherstations = this.weatherStationService.getAllWeathrStation();
    this.addMarker();
  }

  addMarker() {
    for(const wt of this.weatherstations){
      this.markers.push({
        lat: wt.latitude,
        lng: wt.longitude,
        label: wt.description
        draggable: false
      });
    }
  }

}
