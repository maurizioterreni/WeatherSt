import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef,MAT_DIALOG_DATA } from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { AuthenticationService } from './authentication.service';
import { RegistrationService } from './registration.service';
import {MatSnackBar} from '@angular/material';




@Component({
    moduleId: 'app-login',
    templateUrl: 'login.html',
    styleUrls: ['login.css'],
    providers: [AuthenticationService,RegistrationService]
})

export class LoginComponent implements OnInit {

  hide = true;

  constructor(
          private authenticationService: AuthenticationService,
          private registrationService: RegistrationService,
          public dialogRef: MatDialogRef<LoginComponent>,
          public snackBar: MatSnackBar) { }
    ngOnInit() {
      // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
    }

    loginUser(e) {
  	   e.preventDefault();

  	   var username = e.target.elements[0].value;
  	   var password = e.target.elements[1].value;

       this.authenticationService.login(username, password)
          .subscribe(
            res => {
                this.dialogRef.close();
        },
          err => {
            console.log('ERROR');
            //openSnackBar("User or Password wrong", "undo");
            this.snackBar.open("User or Password wrong", null, {
              duration: 3000,
            });

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
                 this.dialogRef.close();
             },
             error => {
               console.log('ERROR');
               //openSnackBar("User or Password wrong", "undo");
               this.snackBar.open("User or Password wrong", null, {
                 duration: 3000,
               });
      });
    }
}
