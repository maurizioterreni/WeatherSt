/**
 * New typescript file
 */
import { Component, Input, OnInit } from '@angular/core';
import { AppMaterialModules } from '../material.module';


@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.css'],
})
export class HeaderComponent  implements OnInit {
  // -----------------------------------------------------------------------//
  public title = 'Weather Station';
  // -----------------------------------------------------------------------//
  constructor() {
  }

  ngOnInit() {
  }
}
