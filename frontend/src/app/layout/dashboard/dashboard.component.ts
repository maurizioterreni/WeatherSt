import { Component, OnInit } from '@angular/core';
import { Environment } from '../../local/environment';
import { WeatherStationService } from '../../services/weatherstation/weatherstation.service';
import { WeatherStation } from '../../models/weatherstation/weatherstation';

/**
 * @title Multi-row toolbar
 */
@Component({
  selector: 'dashboard-app',
  templateUrl: 'dashboard.html',
  styleUrls: ['dashboard.css'],
  providers: [WeatherStationService]
})


export class DashboardComponent implements OnInit {
  private environment: Environment;

  weatherstations: WeatherStation[];

  constructor(private weatherStationService: WeatherStationService){
    this.environment = new Environment();
  }

  ngOnInit() {
    this.weatherStationService.getAllWeathrStation()
      .subscribe((data: WeatherStation[]) => {
        this.weatherstations = data;
      });
  }

}
