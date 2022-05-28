import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { CustomersComponent } from './customers/customers.component';
import { AccountsComponent } from './accounts/accounts.component';
import { CustomerstableComponent } from './customerstable/customerstable.component';
import { AlertComponentComponent } from './alert-component/alert-component.component';
import { LoadingSpinnerComponent } from './loading-spinner/loading-spinner.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchFormComponent } from './search-form/search-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CustomersComponent,
    AccountsComponent,
    CustomerstableComponent,
    AlertComponentComponent,
    LoadingSpinnerComponent,
    SearchFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
