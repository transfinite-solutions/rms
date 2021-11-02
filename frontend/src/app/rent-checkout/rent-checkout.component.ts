import { HttpHeaders } from '@angular/common/http';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { AddAddressComponent } from '../add-address/add-address.component';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-rent-checkout',
  templateUrl: './rent-checkout.component.html',
  styleUrls: ['./rent-checkout.component.scss']
})
export class RentCheckoutComponent implements OnInit {

  user;
  items = [];
  rentItem: any;
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

  constructor( private router: Router, private service : ServiceService, private dialog: MatDialog ) { }

  ngOnInit() {
    this.httpOptions = { headers : new HttpHeaders({ "Authorization": localStorage.getItem("token") }) };
    this.fetchData();
  }

  fetchData() {
    
    this.rentItem = this.service.cart.filter(el => el.type == "rent");

    this.rent.totalPrice = this.rentItem.map(val => val.rate * val.quantity).reduce((prev: any, next: any) => prev + next, 0);
    this.rentItem.forEach(element => {
      console.log(element);
      this.service.get("/stock/product/" + element.product_id + "/availability/AVAILABLE", this.httpOptions).subscribe(res => {
        console.log(res);
        if(res.length == 0) {
          alert("Product in cart no longer available");
          this.rentItem.filter(el => !(el.product_id == element.product_id));
          this.router.navigateByUrl("");
        } else if(element.quantity <= res.length) {
          for(let i=0; i<element.quantity; i++) {
            res[i].product = null;
            this.rent.orderStocks.push(res[i]);
          }
        } else {
          alert("Product in cart no longer available");
          element.quantity = res.length;
          this.router.navigateByUrl("");
        }
      }, err => {
        console.log(err);
      });
      console.log(this.rent.orderStocks);
    });
    // this.service.cart.forEach((element: any) => {
    //   this.rent.orderStocks.push({
    //     stockId: element.stock_id,
    //     rate: element.rate,
    //     durationType: element.durationType,
    //     durationTime: element.durationTime,
    //     availability: element.availability
    //   });
    // });
    this.getUserDetails();
  }

  getUserDetails() {
    this.service.get("/get-user", this.httpOptions).subscribe(res => {
      this.user = res;
      if(this.user.addresses.length != 0) {
        this.address = this.user.addresses[0];
      }
    }, err => { 
      console.log("Login expired");
      this.router.navigateByUrl("Login");
    });
  }

  addAddress() {
    console.log("add address");
    const dialogRef = this.dialog.open(AddAddressComponent, {width: "70%",height: "86vh",panelClass: 'full-width-dialog', data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.getUserDetails();
    });
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
