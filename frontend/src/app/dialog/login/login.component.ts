import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { AuthenticationService } from './authentication.service';
import { AlertService } from './alert.service';
import { RegistrationService } from './registration.service'




@Component({
    moduleId: 'app-login',
    templateUrl: 'login.html',
    styleUrls: ['login.css'],
    providers: [AuthenticationService, AlertService,RegistrationService]
})

export class LoginComponent implements OnInit {
  returnUrl: string;
  constructor(
          private route: ActivatedRoute,
          private router: Router,
          private authenticationService: AuthenticationService,
          private registrationService: RegistrationService,
          private alertService: AlertService) { }
    ngOnInit() {
      // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '';
    }

    loginUser(e) {
  	   e.preventDefault();

  	   var username = e.target.elements[0].value;
  	   var password = e.target.elements[1].value;
       console.log(username);
       this.authenticationService.login(username, password)
          .subscribe(
            res => {
              if(res.status == true) {
                this.router.navigate([this.returnUrl]);
              }else{
                //this.loginError = true
                //this.loginAlert = res.message;
                console.log('ERROR');
          }
        },
          err => {
            return err;

          });
    }

    registrationUser(e){
      var username = e.target.elements[0].value;
      var password = e.target.elements[1].value;
      var email = e.target.elements[2].value;

      console.log('registration');

      this.registrationService.registration(username, password, email)
         .subscribe(
             data => {
                 //this.router.navigate([this.returnUrl]);
             },
             error => {
                 this.alertService.error(error);
               //  this.loading = false;
      });
    }
}
