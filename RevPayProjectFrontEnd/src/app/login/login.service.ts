import { HttpClient, HttpParams, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserAccounts } from '../registration/registration.service';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  httpClient: HttpClient;
  private url: string = "http://localhost:8080/";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/form-data'
    })
  }
  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
   }
   loginUser(login : Login) : Observable<Object>
   {
      let body = new FormData();
      body.append('username',login.username);
      body.append('password',login.password);
      return this.httpClient.post(this.url + "login",body,{ withCredentials: true });
   }
   sendEmail(to : string, info : string){
    let params : HttpParams = new HttpParams().set('to', to).set('info',info);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    //headers.set('Content-Type', 'application/json');
    return this.httpClient.post(this.url + "email",JSON.stringify(' '),{params:params,headers:headers,withCredentials: true});
   }  
}

export interface Login{
  username : string;
  password : string;
}
