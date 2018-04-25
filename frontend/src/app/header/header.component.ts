/**
 * New typescript file
 */
import { User } from '../user/user';
import { Component, Input, OnInit } from '@angular/core';
import { AppMaterialModules } from '../material.module';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';
import { LoginComponent } from '../dialog/login/login.component';
import { AuthenticationService } from '../dialog/login/authentication.service';



@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.css'],
  providers: [AuthenticationService]
})
export class HeaderComponent  implements OnInit {
  // -----------------------------------------------------------------------//
  public title = 'Weather Station';
  public user : User;
  // -----------------------------------------------------------------------//
  constructor(public dialog: MatDialog, private authenticationService: AuthenticationService,) {
    this.user = JSON.parse(localStorage.getItem("currentUser"));
    console.log(this.user);
  }

  ngOnInit() {
  }

  openLogin() {
     this.dialog.open(LoginComponent, {});
  }
  logOut(){
    this.authenticationService.logout();
  }
}
