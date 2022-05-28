import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  constructor() { }

  @Input() customersSearchformGroup: FormGroup | undefined;
  @Input() searchCustomers: Function | undefined;
  ngOnInit(): void {
  }

}
