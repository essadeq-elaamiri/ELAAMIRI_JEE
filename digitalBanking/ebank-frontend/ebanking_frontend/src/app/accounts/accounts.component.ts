import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { AccountHistory } from '../models/accountHistory.model';
import { AccountsService } from '../services/accounts.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  searchAccountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountHistory$!: Observable<AccountHistory>;
  constructor(private formBuilder: FormBuilder, private accountService: AccountsService) { }

  ngOnInit(): void {
    this.searchAccountFormGroup = this.formBuilder.group(
      {accountId: this.formBuilder.control(''),}

    );
  }

  searchAccount(){
    //alert();
    let accountId = this.searchAccountFormGroup.value.accountId;
    this.accountHistory$ =  this.accountService.getAccount(accountId, this.currentPage, this.pageSize);
    /*
    this.accountService.getAccount(accountId, this.currentPage, this.pageSize).subscribe({
      next: accountHistory => {

      },
      error: err =>{
        console.log(err);
      }
    });
    */

  }

  goToPage(page: number){
    this.currentPage = page;
    this.searchAccount();
  }

}
