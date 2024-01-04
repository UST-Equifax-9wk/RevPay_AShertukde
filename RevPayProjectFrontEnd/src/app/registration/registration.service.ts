import { HttpClient, HttpParams, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private url: string = "http://localhost:8080/";
  httpClient: HttpClient;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
   }
  
  putUser(user: UserAccounts): Observable<Object>{
    return this.httpClient.post<UserAccounts>(this.url + "UserAccounts",JSON.stringify(user),this.httpOptions);
  }
  putBusiness(business: BusinessAccounts): Observable<Object>{
    return this.httpClient.post<BusinessAccounts>(this.url+"BusinessAccounts",JSON.stringify(business),this.httpOptions)
  }
}

export interface UserAccounts{
  userid? : Number | undefined;
  username: string;
  password?: string | undefined;
  email: string;
  phone: string;
  firstname: string;
  lastname: string;
}
export interface Obj {
  value: string;
}
export interface BusinessAccounts{
  business_id? : number,
  username : string,
  password : string,
  phone : string,
  business_name : string,
  email: string  
}
