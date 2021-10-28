import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username;
  password;

  constructor( 
    private router: Router,
    private service : ServiceService) { }

  ngOnInit() {

  }
register(){
    this.router.navigateByUrl('/Register');
}

afterlogin(){

    if(this.username == undefined || this.username == null) {
      alert("Username is required");
    }
    if(this.password == undefined || this.password == null) {
      alert("password is required");
    }

    let loginData = {
      username : this.username,
      password : this.password
    }

    this.service.postData("/authenticate", loginData).subscribe(res => {
      localStorage.setItem("token", "BEARER "+res.token);
      this.router.navigateByUrl('');
    }, err=> {
      console.log(err);
      alert("Username or password is incorrect");
    });
}

}
