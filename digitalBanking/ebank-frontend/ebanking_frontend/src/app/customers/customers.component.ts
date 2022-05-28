import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { catchError, Observable, throwError } from 'rxjs';
import { Customer } from '../models/customer.model';
import { CustomerServiceService } from '../services/customer-service.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers$!: Observable<Array<Customer>>; // will be a model Customer
  // convention de nomage : tous les variables de type Obesrvable, se termine avec un $
  //customers: any; // will be a model Customer
  errorObj: Object | undefined;
  errorMsg: String | undefined;

  customersSearchformGroup: FormGroup | undefined ;

  //errorMessage!: String | undefined;
  //errorMessage: String | null=null; // default value
  //errorMessage: String = ""; // initialize



  /*constructor(private http:HttpClient) { }*/
  constructor(private customerService: CustomerServiceService, private formBuilder: FormBuilder) { } // injection

  ngOnInit(): void {

    // init form
    this.customersSearchformGroup = this.formBuilder.group({
      searchKeyword: this.formBuilder.control("Search key word goes here"), // initial/ default value/
    });

    this.customers$ = this.customerService.getCustomersList().pipe(
      catchError(err => {
        this.errorMsg = err.message;
        this.errorObj = err;
        return throwError(err);
      })
    ); //traiter les erreurs
    }

    searchCustomers(){
      //alert("hello");
      this.customers$ = this.customerService.getCustomersList("ss");
    }

    //======================== where in ngOnInit()
    //this.customers$ = this.customerService.getCustomersList(); // et faire subscibe dans html


    /*
    this.customerService.getCustomersList().subscribe(
      {
      next: data => {
          this.customers = data;
      } ,
      error : err=> {

        this.errorObj = err;
        this.errorMsg = err.message;
        console.error(err);

      }

    }

    );
    */




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
