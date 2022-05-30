import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  searchAccountFormGroup!: FormGroup;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.searchAccountFormGroup = this.formBuilder.group(
      {accountId: this.formBuilder.control(''),}

    );
  }

  searchAccount(){
    alert(this.searchAccountFormGroup.value.accountId);
  }

}
