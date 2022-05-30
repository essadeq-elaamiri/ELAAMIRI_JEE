import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AccountHistory } from '../models/accountHistory.model';
import { AccountsService } from '../services/accounts.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css'],
})
export class AccountsComponent implements OnInit {
  searchAccountFormGroup!: FormGroup;
  operationFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountHistory$!: Observable<AccountHistory>;
  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountsService
  ) {}

  ngOnInit(): void {
    this.searchAccountFormGroup = this.formBuilder.group({
      accountId: this.formBuilder.control(''),
    });

    this.operationFormGroup = this.formBuilder.group({
      operationType: this.formBuilder.control(null),
      operationAmount: this.formBuilder.control(100.0, Validators.min(100.0)),
      destinationAccount: this.formBuilder.control(null), // will be displyed if it is transfer
      description: this.formBuilder.control(null),
    });
  }

  searchAccount() {
    //alert();
    let accountId = this.searchAccountFormGroup.value.accountId;
    this.accountHistory$ = this.accountService.getAccount(
      accountId,
      this.currentPage,
      this.pageSize
    );
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

  goToPage(page: number) {
    this.currentPage = page;
    this.searchAccount();
  }

  applyOperation() {
    let accountId: String = this.searchAccountFormGroup.value.accountId;
    let operationType: String = this.operationFormGroup.value.operationType;
    let operationAmount: number = this.operationFormGroup.value.operationAmount;

    console.log(JSON.stringify(this.operationFormGroup.value));

    confirm(`Confirm operation ${operationType}? `);
    if (operationType == 'TRANSFER') {
      this.accountService
        .transfer(
          accountId,
          this.operationFormGroup.value.destinationAccount,
          operationAmount
        )
        .subscribe({
          next: (resp) => {
            alert('Success ' + operationType);
            this.searchAccount();
          },
          error: (err) => {
            alert('Error: Operation failed !');
            console.log(err);
          },
        });
    } else {
      if (operationType == 'DEBIT') {
        this.accountService
          .debit(
            accountId,
            operationAmount,
            this.operationFormGroup.value.description
          )
          .subscribe({
            next: (resp) => {
              alert('Success ' + operationType);
              this.searchAccount();
            },
            error: (err) => {
              alert('Error: Operation failed !');
              console.log(err);
            },
          });
      } else if (operationType == 'CREDIT') {
        this.accountService
          .credit(
            accountId,
            operationAmount,
            this.operationFormGroup.value.description
          )
          .subscribe({
            next: (resp) => {
              alert('Success ' + operationType);
              this.searchAccount();
            },
            error: (err) => {
              alert('Error: Operation failed !');
              console.log(err);
            },
          });
      }
    }
  }
}
