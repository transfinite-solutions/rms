import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  cart: any;

  constructor(private service : ServiceService,
    private router : Router) {
    
   }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.cart = this.service.cart;
    console.log(this.cart);
  }

  delete(item) {
    this.service.cart.splice(this.service.cart.indexOf(item), 1);
    console.log(this.service.cart);
  }

  checkout() {
    // let httpOptions : any = { 
    //   headers : new HttpHeaders({
    //     "Authorization": localStorage.getItem("token")
    //   })
    // };

    // let data = {

    // }

    // this.service.post("/rent", data, httpOptions).subscribe((res:any) => {

    // }, err => {
    //   console.log(err);
    // });
    this.router.navigateByUrl("/rent-checkout");
  }

}
