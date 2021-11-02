import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { HeaderComponent } from './header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { TransportComponent } from './transport/transport.component';
import { RentComponent } from './rent/rent.component';
import { MasonryComponent } from './masonry/masonry.component';
import { ResourcesComponent } from './resources/resources.component';
import { ShopComponent } from './shop/shop.component';
import { MatCardModule, MatDatepickerModule, MatNativeDateModule, MatRadioModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule,MatButtonModule, MatStepperModule, MatIconModule} from '@angular/material';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VendorComponent } from './vendor/vendor.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { DocumentsComponent } from './documents/documents.component';
import { MatSidenavModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component';
import { RentCheckoutComponent } from './rent-checkout/rent-checkout.component';
import { CardComponent } from './card/card.component';
import { AddAddressComponent } from './add-address/add-address.component';
import { SellCheckoutComponent } from './sell-checkout/sell-checkout.component';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    HeaderComponent,
    LoginComponent,
    TransportComponent,
    RentComponent,
    MasonryComponent,
    ResourcesComponent,
    ShopComponent,
    RegisterComponent,
    DashboardComponent,
    VendorComponent,
    VehicleComponent,
    DocumentsComponent,
    CartComponent,
    RentCheckoutComponent,
    CardComponent,
    AddAddressComponent,
    SellCheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatSidenavModule,
    MatStepperModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
