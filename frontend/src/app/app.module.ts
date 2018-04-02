import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AppMaterialModules } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DialogMaps } from './dialog/maps/dialog.maps.component';
import { WeatherStationComponent } from './weatherStation/weatherStation.component';
import {AgmCoreModule} from '@agm/core';
import { RouterModule, Routes } from '@angular/router';
import { SensorComponent } from './sensor/sensor.component';
import { HttpClientModule } from '@angular/common/http';
import { TemperatureGaugeComponent } from './sensor/gauge/temperature/temperatureGauge.component';
import { WindDirectionGaugeComponent } from './sensor/gauge/windDirection/windDirectionGauge.component';


const appRoute: Routes = [
      {path: 'sensor/:weather', component: SensorComponent},
      {path: '', component: WeatherStationComponent}];

@NgModule({
  declarations: [AppComponent, HeaderComponent, FooterComponent, DialogMaps, SensorComponent, WeatherStationComponent, TemperatureGaugeComponent, WindDirectionGaugeComponent],
  imports: [BrowserModule, HttpClientModule, BrowserAnimationsModule, AppMaterialModules, FlexLayoutModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyB9t2Ki03ItPGImdj2sro-hMyBcQEsnloc'
    }),
    RouterModule.forRoot(appRoute)],
  providers: [] ,
  entryComponents: [DialogMaps],
  bootstrap: [AppComponent]
})
export class AppModule { }
