import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root' // disponible in all the application
  // no need to be declared in providers[]
})
export class CustomerServiceService {
  constructor(private http: HttpClient) { }

  /*
  public getCustomersList() : Observable<any>{ // any for result datatype
    return this.http.get("http://localhost:8080/customers");
  }
*/
  public getCustomersList() : Observable<Array<Customer>>{ // any for result datatype
    return this.http.get<Array<Customer>>("http://localhost:8080/customers");
  }

}
