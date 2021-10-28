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
    this.service.cart.indexOf(item) === -1 ? this.service.cart.push(item) : alert("Item already in your cart");
    // this.service.cart.push(item);
    console.log(this.service.cart);

    // let found = this.service.cart.some(el => el.stock_id != item.stock_id);
    // let index = this.service.cart.indexOf(item);
    // if(!found) {
    //   item.quantity = 1;
    //   this.service.cart.push(item);
    // } else {
    //   this.service.cart[index].quantity !== item.quantity ? this.service.cart[index].quantity += 1 : alert("item not available");
    // }
  }

}
