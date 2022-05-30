import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { Customer } from '../models/customer.model';
import { CustomerServiceService } from '../services/customer-service.service';

@Component({
  selector: 'app-customerstable',
  templateUrl: './customerstable.component.html',
  styleUrls: ['./customerstable.component.css'],
})
export class CustomerstableComponent implements OnInit {
  @Input() customersList: any; // get data from parent
  @Input() errorObj: Object | undefined;
  @Input() deleteCustomer!: Function;
  @Input() customers$!: Observable<Array<Customer>>;

  constructor(private customerService: CustomerServiceService, private router: Router) {}

  ngOnInit(): void {
    //console.log(this.deleteCustomer);
  }

  handleCustomerAccountsList(customerId: String){
    this.router.navigateByUrl("/customer-accounts/"+customerId);

  }
}
