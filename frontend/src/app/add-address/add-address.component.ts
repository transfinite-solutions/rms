import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.scss']
})
export class AddAddressComponent implements OnInit {

  user: any;
  addresses = [];
  address = {
    line1 : "",
    landmark: "",
    city: "",
    state: "",
    country: "",
    pincode: ""
  }

  constructor(
    private service: ServiceService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<AddAddressComponent>
  ) {
      this.user = data;
   }

  ngOnInit() {
  }

  addAddress() {
    this.addresses.push(this.address);
    let httpOptions = { headers : new HttpHeaders({ "Authorization": localStorage.getItem("token") }) };
    this.service.post("/user/" + this.user.userId + "/address", this.addresses, httpOptions).subscribe((res: any) => {
      console.log("success");
    }, err => {
      console.log(err);
      alert("Error try again later");
    }, () => {
      this.dialogRef.close();
    });
  }

}
