import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(  private router: Router,
    private service: ServiceService) { }

  ngOnInit() {
  }

  login(){
    this.router.navigateByUrl('/Login'); 
  }

  logout() {
    let httpOptions : any = { 
      headers : new HttpHeaders({
        "Authorization": localStorage.getItem("token")
      })
    };
    this.service.get("/signout", httpOptions).subscribe(res => {
      this.router.navigateByUrl('');
    }, err => {
      alert("Error try again later");
    })
  }

  transport() {
    this.router.navigate(['/Transport', {categoryId: 1}]);
  }

  rent() {
    this.router.navigate(['/Rent', {categoryId: 2}]);
  }

  resources() {
    this.router.navigate(['/Resources', {categoryId: 3}]);
  }

  masonry() {
    this.router.navigate(['/Masonry', {categoryId: 4}]);
  }
}
