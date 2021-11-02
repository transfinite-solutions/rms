import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-sell-checkout',
  templateUrl: './sell-checkout.component.html',
  styleUrls: ['./sell-checkout.component.scss']
})
export class SellCheckoutComponent implements OnInit {

  user;
  items = [];
  sellItem: any;
  httpOptions: any;

  address = {
    addressId: null,
    line1 : "",
    landmark: "",
    city: "",
    state: "",
    country: "",
    pincode: "",
    tag: ""
  }

  stock = {
    stockId: 0,
    rate: 0.0,
    durationType: "",
    durationTime: 0,
    availability: ""
  }

  sell = {
    customer: null,
    createdAt: new Date(),
    status: "PROCESSING",
    totalPrice: 0.0,
    paymentStatus: "UNPAID",
    orderStocks: []
  }

  
  constructor( private router: Router, private service : ServiceService, private dialog: MatDialog ) { }

  ngOnInit() {
    this.httpOptions = { headers : new HttpHeaders({ "Authorization": localStorage.getItem("token") }) };
    this.fetchData();
  }

  fetchData() {
    this.sellItem = this.service.cart.filter(el => el.type == "sell");

    this.sell.totalPrice = this.sellItem.map(val => val.rate * val.quantity).reduce((prev: any, next: any) => prev + next, 0);
    this.sellItem.forEach(element => {
      console.log(element);
      this.service.get("/stock/product/" + element.product_id + "/availability/AVAILABLE", this.httpOptions).subscribe(res => {
        console.log(res);
        if(res.length == 0) {
          alert("Product in cart no longer available");
          this.sellItem.filter(el => !(el.product_id == element.product_id));
          this.router.navigateByUrl("");
        } else if(element.quantity <= res.length) {
          for(let i=0; i<element.quantity; i++) {
            res[i].product = null;
            this.sell.orderStocks.push(res[i]);
          }
        } else {
          alert("Product in cart no longer available");
          element.quantity = res.length;
          this.router.navigateByUrl("");
        }
      }, err => {
        console.log(err);
      });
      console.log(this.sell.orderStocks);
    });
  }

}
