import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {

  customerId!: String;
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.customerId =  this.route.snapshot.params['id']; // retrieve id from url
    //
  }

}
