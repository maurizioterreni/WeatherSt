import { TestBed, inject } from '@angular/core/testing';

import { WeatherStationService } from './weather-station.service';

describe('WeatherStationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WeatherStationService]
    });
  });

  it('should be created', inject([WeatherStationService], (service: WeatherStationService) => {
    expect(service).toBeTruthy();
  }));
});
