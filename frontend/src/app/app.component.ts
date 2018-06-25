import { Component } from '@angular/core';
import { Environment } from './local/environment';
import { MediaMatcher } from '@angular/cdk/layout';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private environment: Environment;
  fixed = false;
  top = 0;
  bottom = 0;

  constructor() {
    this.environment = new Environment();
  }

}
