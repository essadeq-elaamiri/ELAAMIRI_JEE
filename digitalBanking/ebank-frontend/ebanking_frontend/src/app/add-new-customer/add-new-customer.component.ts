import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Customer } from '../models/customer.model';
import { CustomerServiceService } from '../services/customer-service.service';

@Component({
  selector: 'app-add-new-customer',
  templateUrl: './add-new-customer.component.html',
  styleUrls: ['./add-new-customer.component.css']
})
export class AddNewCustomerComponent implements OnInit {

  constructor(private customerService: CustomerServiceService , private formBuilder: FormBuilder) { }

  addNewCustomerFromGroup: FormGroup | undefined;
  constomer: Customer | undefined;

  ngOnInit(): void {
    this.addNewCustomerFromGroup = this.formBuilder.group({
        name : this.formBuilder.control(""),
        email: this.formBuilder.control(""),
    });
  }

  addNewCustomer(){
    this.constomer = this.addNewCustomerFromGroup?.value;
    this.customerService.saveCustomer(this.constomer!).subscribe({
      next: data =>{
        alert("addrd")
      },
      error: err=>{
        console.error(err);
      }
    });
  }

}
