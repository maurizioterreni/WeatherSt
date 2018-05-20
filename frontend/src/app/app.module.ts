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
import { AgmCoreModule } from '@agm/core';
import { RouterModule, Routes } from '@angular/router';
import { SensorComponent } from './sensor/sensor.component';
import { DialogDeleteSensor } from './sensor/sheet/delete/dialogDeleteSensor.component';
import { DialogEditSensor } from './sensor/sheet/edit/dialogEditSensor.component';
import { HttpClientModule } from '@angular/common/http';
import { TemperatureGaugeComponent } from './sensor/gauge/temperature/temperatureGauge.component';
import { WindspeedGaugeComponent } from './sensor/gauge/windspeed/windspeedGauge.component';
import { WindDirectionGaugeComponent } from './sensor/gauge/windDirection/windDirectionGauge.component';
import { UVGaugeComponent } from './sensor/gauge/uv/uvGauge.component';
import { RainGaugeComponent } from './sensor/gauge/rain/rainGauge.component';
import { PressureGaugeComponent } from './sensor/gauge/pressure/pressureGauge.component';
import { HumidityGaugeComponent } from './sensor/gauge/humidity/humidityGauge.component';
import { LoginComponent } from './dialog/login/login.component';
import { RegisterComponent } from './dialog/register/register.component';
import { CreateSensorComponent } from './sensor/createSensor/createSensor.component';
import { CreateWeatherStationComponent } from './weatherStation/createWeatherStation/createWeatherStation.component';
import { CreateSensorKnowledgeComponent } from './sensor/createSensorKnowledge/createSensorKnowledge.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


const appRoute: Routes = [
      {path: 'sensor/', component: SensorComponent},
      {path: '', component: WeatherStationComponent},
      { path: 'login', component: LoginComponent },
      { path: 'createSensor', component: CreateSensorComponent },
      { path: 'createWeatherStation', component: CreateWeatherStationComponent },
      { path: 'createSensorKnowledgeComponent', component: CreateSensorKnowledgeComponent },
      { path: 'register', component: RegisterComponent },
      // otherwise redirect to home
      { path: '**', redirectTo: '' }];

@NgModule({
  declarations: [AppComponent, HeaderComponent, CreateSensorComponent, FooterComponent, DialogMaps,DialogDeleteSensor,
        DialogEditSensor ,SensorComponent, WeatherStationComponent,
        TemperatureGaugeComponent, PressureGaugeComponent, UVGaugeComponent, HumidityGaugeComponent,
        RainGaugeComponent, WindspeedGaugeComponent, WindDirectionGaugeComponent, LoginComponent,
        RegisterComponent, CreateWeatherStationComponent, CreateSensorKnowledgeComponent],
  imports: [BrowserModule, HttpClientModule, BrowserAnimationsModule, AppMaterialModules, FlexLayoutModule,FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyB9t2Ki03ItPGImdj2sro-hMyBcQEsnloc'
    }),
    RouterModule.forRoot(appRoute)],
  providers: [] ,
  entryComponents: [DialogMaps,DialogDeleteSensor,DialogEditSensor],
  bootstrap: [AppComponent]
})
export class AppModule { }
