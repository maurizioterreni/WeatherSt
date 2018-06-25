import { Component } from '@angular/core';
import { Environment } from '../../local/environment';

/**
 * @title Multi-row toolbar
 */
@Component({
  selector: 'dashboard-app',
  templateUrl: 'dashboard.html',
  styleUrls: ['dashboard.css'],
})
export class Dashboard {
  private environment: Environment;

  constructor(){
    this.environment = new Environment();
  }

}
