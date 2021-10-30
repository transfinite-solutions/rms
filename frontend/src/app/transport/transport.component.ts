import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-transport',
  templateUrl: './transport.component.html',
  styleUrls: ['./transport.component.scss']
})
export class TransportComponent implements OnInit {

  categoryId;
  itemList: any;
  placeholderImage = "https://picsum.photos/seed/picsum/350/200";
  constructor(
    private service : ServiceService,
    private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit() {
    this.categoryId = this.activatedRoute.snapshot.paramMap.get('categoryId');
    console.log(this.categoryId);
    this.fetchData();
  }

  fetchData(){
    let httpOptions : any = { 
      headers : new HttpHeaders({
        "Authorization": localStorage.getItem("token")
      })
    };
    this.service.get("/stock/category/" + this.categoryId, httpOptions).subscribe((response : any) => {
      this.itemList = response;
      console.log(this.itemList);
    }, (err : any) => {
      console.log(err);
    });
  }

  handleSubmit(item) {
    // this.service.cart.indexOf(item) === -1 ? this.service.cart.push(item) : alert("Item already in your cart");
    // // this.service.cart.push(item);
    // console.log(this.service.cart);

    // let found = this.service.cart.some(el => el.stock_id != item.stock_id);
    // let index = this.service.cart.indexOf(item);
    // if(!found) {
    //   item.quantity = 1;
    //   this.service.cart.push(item);
    // } else {
    //   this.service.cart[index].quantity !== item.quantity ? this.service.cart[index].quantity += 1 : alert("item not available");
    // }

    // let found = this.service.cart.some(el => el.product_id == item.product_id);
    // console.log(found);
    // let index = this.service.cart.findIndex(el => el.product_id = item.product_id);
    // console.log(index);
    // if(!found) {
    //   this.service.cart.push({
    //     availability: item.availability,
    //     category_categoryId: item.category_categoryId,
    //     description: item.description,
    //     durationTime: item.durationTime,
    //     durationType: item.durationType,
    //     imageUrl: item.imageUrl,
    //     name: item.name,
    //     product_id: item.product_id,
    //     product_product_id: item.product_product_id,
    //     quantity: 1,
    //     rate: item.rate,
    //     registrationNumber: item.registrationNumber,
    //     stock_id: item.stock_id,
    //     type: item.type,
    //     user_user_id: item.user_user_id
    //   });
    // } else {
    //   this.service.cart[index].quantity !== item.quantity ? this.service.cart[index].quantity += 1 : alert("No more stock available");
    // }
    // console.log(this.service.cart);

    let exists = false;
    this.service.cart.forEach(element => {
      if(element.product_id === item.product_id && element.quantity < item.quantity) {
        element.quantity += 1;
        console.log(this.service.cart);
        exists = true;
        return false;
      } else if(element.product_id === item.product_id && element.quantity >= item.quantity) {
        alert("Exceeded available quantity");
        console.log(this.service.cart);
        exists = true;
        return false;
      }
    });
    if(!exists) {
      this.service.cart.push({
            availability: item.availability,
            category_categoryId: item.category_categoryId,
            description: item.description,
            durationTime: item.durationTime,
            durationType: item.durationType,
            imageUrl: item.imageUrl,
            name: item.name,
            product_id: item.product_id,
            product_product_id: item.product_product_id,
            quantity: 1,
            rate: item.rate,
            registrationNumber: item.registrationNumber,
            stock_id: item.stock_id,
            type: item.type,
            user_user_id: item.user_user_id
      });
      console.log(this.service.cart);
    }
  }

}
