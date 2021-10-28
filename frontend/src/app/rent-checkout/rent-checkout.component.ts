import { HttpHeaders } from '@angular/common/http';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-rent-checkout',
  templateUrl: './rent-checkout.component.html',
  styleUrls: ['./rent-checkout.component.scss']
})
export class RentCheckoutComponent implements OnInit {

  user;
  items = [];
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

  rent = {
    customer: null,
    pickupDate: null,
    dropDate: null,
    createdAt: new Date(),
    status: "PROCESSING",
    totalPrice: 0.0,
    paymentStatus: "UNPAID",
    orderStocks: []
  }

  constructor( private router: Router, private service : ServiceService ) { }

  ngOnInit() {
    this.httpOptions = { headers : new HttpHeaders({ "Authorization": localStorage.getItem("token") }) };
    this.fetchData();
  }

  fetchData() {
    this.service.get("/get-user", this.httpOptions).subscribe(res => {
      this.user = res;
      if(this.user.addresses.length != 0) {
        this.address = this.user.addresses[0];
      }
    }, err => { console.log("Login expired"); });
    this.rent.totalPrice = this.service.cart.map(val => val.rate).reduce((prev: any, next: any) => prev + next, 0);
    this.service.cart.forEach((element: any) => {
      this.rent.orderStocks.push({
        stockId: element.stock_id,
        rate: element.rate,
        durationType: element.durationType,
        durationTime: element.durationTime,
        availability: element.availability
      });
    });
  }

  addAddress() {
    console.log("add address");
  }

  proceed() {
    console.log(this.rent);
    if(this.rent.pickupDate == null || this.rent.pickupDate == undefined) {
      alert("Pickup date is required");
    } else if (this.rent.dropDate == null || this.rent.dropDate == null) {
      alert("Drop date is required");
    } else {
      this.service.post("/rent/customer/"+ this.user.userId + "/address/" + this.address.addressId, this.rent, this.httpOptions).subscribe((res: any) => {
        console.log(this.rent);
        alert("Your order is placed");
        this.router.navigateByUrl("");
      }, err=> {
         alert("Error try again later: "); 
         console.log(err);
      });
    }
  }

}
