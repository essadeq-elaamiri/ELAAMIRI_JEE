import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccountHistory } from '../models/accountHistory.model';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http: HttpClient) { }

  public getAccount(accountId: String, page:number, size: number): Observable<AccountHistory>{
    return this.http.get<AccountHistory>(environment.backendBaseURL+"/accounts/"+accountId+"/history?page="+page+"&size="+size);
  }

}
