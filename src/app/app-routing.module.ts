import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DocumentsComponent } from './documents/documents.component';
import { HeaderComponent } from './header/header.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { MasonryComponent } from './masonry/masonry.component';
import { RegisterComponent } from './register/register.component';
import { RentCheckoutComponent } from './rent-checkout/rent-checkout.component';
import { RentComponent } from './rent/rent.component';
import { ResourcesComponent } from './resources/resources.component';
import { ShopComponent } from './shop/shop.component';
import { TransportComponent } from './transport/transport.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { VendorComponent } from './vendor/vendor.component';

const routes: Routes = [

  {
    path: '',
    component: HomepageComponent
  },
  {
    path:'Header',
    component:HeaderComponent
  },
  {
    path:'Login',
    component:LoginComponent
  },

  {
    path:'Transport',
    component:TransportComponent
  },
  {
    path:'Rent',
    component:RentComponent
  },
  {
    path:'Resources',
    component:ResourcesComponent
  },
  {
    path:'Masonry',
    component:MasonryComponent
  },
  {
    path:'Shop',
    component:ShopComponent
  },
  {
    path:'Register',
    component:RegisterComponent
  },
  {
    path:'Dashboard',
    component:DashboardComponent
  },
  {
    path:'Vehicle',
    component:VehicleComponent
  },
  {
    path:'Document',
    component:DocumentsComponent
  },
  {
    path:'Vendor',
    component:VendorComponent
  },
  {
    path:'Cart',
    component: CartComponent
  },
  {
    path: 'rent-checkout',
    component: RentCheckoutComponent
  }



];

@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule, CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
