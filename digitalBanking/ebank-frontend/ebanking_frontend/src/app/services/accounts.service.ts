import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http: HttpClient) { }

  public getAccount(accountId: String, page:number, size: number){
    return this.http(environment.backendBaseURL+"/accounts/"+accountId+"/history?page="+page+"&size="+size);
  }

}
