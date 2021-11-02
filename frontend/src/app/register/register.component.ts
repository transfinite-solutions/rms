import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user = {
    name: "",
    email: "",
    phone: "",
    password: ""
  }

  // user = {
  //   name: "",
  //   email: "",
  //   phone: "",
  //   password: "",
  //   addresses: []
  // }

  // address = {
  //   line1 : "",
  //   landmark: "",
  //   city: "",
  //   state: "",
  //   country: "",
  //   pincode: ""
  // }

  constructor(private router: Router,
    private service: ServiceService) { }

  ngOnInit() {
  }

  register(){
    // this.user.addresses[0] = this.address;
    console.log(this.user);
    this.service.postData("/register", this.user).subscribe(res => {
      localStorage.setItem("token", "BEARER "+res.token);
      this.router.navigateByUrl('');
    }, err=> {
      console.log(err);
      alert("User cannot register try later");
    });
    // this.router.navigateByUrl('/Login');
  }


}
