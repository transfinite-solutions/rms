import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.scss']
})
export class ResourcesComponent implements OnInit {

  categoryId;
  itemList: any;
  placeholderImage = "https://picsum.photos/seed/picsum/350/200";
  constructor(
    private service : ServiceService,
    private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit() {
    this.categoryId = this.activatedRoute.snapshot.paramMap.get('categoryId');
    this.fetchData();
  }

  fetchData(){
    let httpOptions : any = { 
      headers : new HttpHeaders({
        "Authorization": localStorage.getItem("token")
      })
    };
    this.service.get("/stock/category/"+ this.categoryId, httpOptions).subscribe((response : any) => {
      console.table(response);
      this.itemList = response;
    }, (err : any) => {
      console.log(err);
    });
  }

  handleSubmit(item) {
    this.service.cart.indexOf(item) === -1 ? this.service.cart.push(item) : alert("Item already in your cart");
    // this.service.cart.push(item);
    console.log(this.service.cart);
  }
}
