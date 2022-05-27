import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CustomerServiceService } from '../services/customer-service.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers: any; // will be a model Customer
  errorObj: Object | undefined;

  dataToChild: Object | undefined;

  //errorMessage!: String | undefined;
  //errorMessage: String | null=null; // default value
  //errorMessage: String = ""; // initialize



  /*constructor(private http:HttpClient) { }*/
  constructor(private customerService: CustomerServiceService) { } // injection

  ngOnInit(): void {

    this.customerService.getCustomersList().subscribe(
      {
      next: data => {
          this.customers = data;
      } ,
      error : err=> {

        this.errorObj = err;
        console.error(err);

      }

    }
    );

    this.dataToChild = {
      customers : this.customers,
       errorMessage: this.errorObj,
    }

    // deprecated syntax
    /*
    this.http.get("http://localhost:8080/customers").subscribe(data=>{
      this.customers = data;
    }, error=> {
      console.error(error);
    });
    */
    // pefered
    /*
    this.http.get("http://localhost:8080/customers").subscribe({
      next: data => {
          this.customers = data;
      } ,
      error : err=> {

      console.error(err);

      }

    });
    */
  }

}
