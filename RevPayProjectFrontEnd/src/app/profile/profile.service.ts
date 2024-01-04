import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BusinessAccounts, UserAccounts } from '../registration/registration.service';
import { Observable } from 'rxjs';
import { Money } from './profile.component';
import { Card } from '../cards/cards.component';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  
  private url: string = 'http://localhost:8080/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/form-data',
    }),
  };
  constructor(private httpClient: HttpClient) {}
  private user: UserAccounts | undefined;
  private username: string | undefined;
  private password: string | undefined;
  private sender: Money | undefined;
  private cookie: string | undefined;
  private senderid : number|undefined;
  setCookie(cookie: string)
  {
    this.cookie = cookie;
  }
  getCookie() : string | undefined{
    return this.cookie;
  }
  setPassword(password: string){
    this.password = password;
  }
  getPassword(): string | undefined{
    return this.password;
  }
  setUser(user: UserAccounts) {
    this.user = user;
  }
  getUser(): UserAccounts| undefined {
    return this.user;
  }
  setSender(senderid : number){
    this.senderid = senderid;
  }
  getSender(): number | undefined{
    return this.senderid;
  }
  setUsername(username: string){
    this.username = username;
  }
  getUsername(): string | undefined{
    return this.username;
  }
  getUserInfo(username: string): Observable<Object>{
    //let username : string = 'test';
    let params : HttpParams = new HttpParams().set('username',username);
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.get<UserAccounts>(this.url + "GetUserAccounts",{params:params,headers:headers,withCredentials: true});    
  }
  getUserInfoByEmail(email: string): Observable<Object>{
    //let username : string = 'test';
    let params : HttpParams = new HttpParams().set('email',email);
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.get<UserAccounts>(this.url + "GetUserAccounts",{params:params,headers:headers,withCredentials: true});    
  }
  getBusinessInfo(username: string): Observable<Object>{
    //let username : string = 'test';
    let params : HttpParams = new HttpParams().set('username',username);
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.get<BusinessAccounts>(this.url + "GetBusinessAccounts",{params:params,headers:headers,withCredentials: true});    
  }
  getBalance(username: string, selector : string): Observable<Object>{
    //let username : string = 'test';
    if(selector == "username"){
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "user_moneybyusername",{params:params,headers:headers,withCredentials: true});
  }
  
  else if(selector == "email")
  {
    let params : HttpParams = new HttpParams().set('email', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "user_moneybyemail",{params:params,headers:headers,withCredentials: true});
  }
  else if(selector == "phone")
  {
    let params : HttpParams = new HttpParams().set('phone', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "user_moneybyphone",{params:params,headers:headers,withCredentials: true});
  }
  return new Observable<Object>;
  }
  GetBusinessBalance(username: string): Observable<Object>{
    //let username : string = 'test';
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "business_moneybyusername",{params:params,headers:headers,withCredentials: true});

}
  postBalance(username: string,money:Money): Observable<Object>{
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    //headers.set('Content-Type', 'application/json');
    return this.httpClient.post(this.url + "user_moneybyusername",JSON.stringify(money),{params:params,headers:headers,withCredentials: true});
  }
  postBusinessBalance(username: string,money:Money): Observable<Object>{
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    //headers.set('Content-Type', 'application/json');
    return this.httpClient.post(this.url + "business_moneybyusername",JSON.stringify(money),{params:params,headers:headers,withCredentials: true});
  }
  postRequestGiveMoney(receiver: number, arg1: number, temptransaction: Transaction) {
    let params : HttpParams = new HttpParams().set('receiver', receiver).set('sender',arg1);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "TransactionSend",JSON.stringify(temptransaction),{params:params,headers:headers,withCredentials: true});
  }
  postRequestTransaction(sender: number, receiver: number, transaction:Transaction): Observable<Object> | null{
    let params : HttpParams = new HttpParams().set('receiver', receiver).set('sender',sender);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    //headers.set('Content-Type', 'application/json');
    return this.httpClient.post(this.url + "TransactionRequest",JSON.stringify(transaction),{params:params,headers:headers,withCredentials: true});
  }
getRequestsTransaction(sender: number): Observable<Object> | null{
    let params : HttpParams = new HttpParams().set('sender',sender);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "GetAllRequests",{params:params,headers:headers,withCredentials: true})
  }
  getPaymentsTransaction(sender: number): Observable<Object> | null{
    let params : HttpParams = new HttpParams().set('receiver',sender);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "GetAllRequestsToOthers",{params:params,headers:headers,withCredentials: true})
  }
  SendMoney(temptransaction : Transaction) : Observable<Object>
  {
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "TransactionResponse",JSON.stringify(temptransaction),{headers:headers,withCredentials: true});
  }
  SetCard(card:Card, username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "CardsUser",JSON.stringify(card),{params:params,headers:headers,withCredentials: true});
  }
  SetBusinessCard(card:Card, username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "CardsBusiness",JSON.stringify(card),{params:params,headers:headers,withCredentials: true});
  }
  getCards(username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "CardsUser",{params:params,headers:headers,withCredentials: true});
  }
  getBusinessCards(username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "CardsBusiness",{params:params,headers:headers,withCredentials: true});
  }
  SetLoan(loan:Loan, username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "BusinessLoan",JSON.stringify(loan),{params:params,headers:headers,withCredentials: true});
  }
  getLoans(username:string) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('username', username);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "BusinessLoan",{params:params,headers:headers,withCredentials: true});
  }
  setPaymentLoans(loanpayments: LoanPayments,id:number) : Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('loanid', id);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(this.url + "BusinessLoanPayment",JSON.stringify(loanpayments),{params:params,headers:headers,withCredentials: true});
  }
  getLoanPayments(id:number): Observable<Object>
  {
    let params : HttpParams = new HttpParams().set('loanid', id);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get(this.url + "BusinessLoanPayment",{params:params,headers:headers,withCredentials: true});
  }
}

export interface Transaction{
  transaction_id?:Number,
  amount?:Number,
  date_sent?:string,
  isComplete?:boolean,
  receiver?: Money,
  sender?: Money
}
export interface Loan{
  loans_id?:number,
  rate?:number,
  amount?:number,
  start_date?:string,
  end_date?:string,
  paidback?:boolean,
  receiver?:BusinessAccounts
}
export interface LoanPayments{
  loanpayments_id?:number,
  payment_amount:number,
  date:string,
  loan_id?:number
  loan?:Loan
}
