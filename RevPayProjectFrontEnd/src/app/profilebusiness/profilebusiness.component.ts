import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CustomcookieService } from '../customcookie.service';
import { Loan, LoanPayments, ProfileService } from '../profile/profile.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BusinessAccounts } from '../registration/registration.service';
import { Money } from '../profile/profile.component';

@Component({
  selector: 'app-profilebusiness',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './profilebusiness.component.html',
  styleUrl: './profilebusiness.component.css',
})
export class ProfilebusinessComponent {
  constructor(
    private profileService: ProfileService,
    private router: Router,
    private Cook: CustomcookieService
  ) {
    this.username = Cook.getCookie('username');
    let authorities = Cook.getCookie('authorities');
    if (!this.username || this.username == '') {
      this.router.navigateByUrl('/login');
    } else if (authorities == '[USER]') {
      this.profileService.setUsername(this.username);
      this.router.navigateByUrl('/profile');
    } else if (authorities == '[BUSINESS]') {
      this.profileService.setUsername(this.username);
      this.profileService.getBusinessInfo(this.username!).subscribe({
        next: (data) => {
          this.user = {
            business_id: (data as any).business_id,
          username: (data as any).username,
          password: (data as any).password,          
          phone: (data as any).phone,
          business_name: (data as any).business_name,
          email: (data as any).email
          };

          //this.profileService.setUser(this.user);
        },
        error: (e) => console.log(e),
      });
      this.GetBalance();
    }
  }
  GetBalance(){
    this.profileService.GetBusinessBalance(this.username!).subscribe({
      next: (data) => {
        if (data) {
          this.money = {
            money_id: (data as any).money_id,
            balance: (data as any).balance,
          };  
          this.GetLoans();        
        }
      },
      error: (e) => console.log(e),
    });
  }
  rounding(num: number): string {
    return (Math.round(num * 100) / 100).toFixed(2);
  }
  roundingnum(num: number): number {
    return Math.round(num * 100) / 100;
  }
  submitBalance() {
    this.balance = this.roundingnum(this.balance);
    let tempmoney: Money = { balance: this.balance };
    this.profileService.postBusinessBalance(this.username!, tempmoney).subscribe({
      next: (data) => {
        if (data) {
          this.money = {
            money_id: (data as any).money_id,
            balance: (data as any).balance,
          };
        }
      },
    });
  }
  submitLoan(){
    this.amount = this.roundingnum(this.amount);
    let date = new Date();
    let day = `0${date.getDate()}`.slice(-2); //("0"+date.getDate()).slice(-2);
    let month = `0${date.getMonth() + 1}`.slice(-2);
    let year = date.getFullYear();
    let result = (
      month.toString() +
      '-' +
      day.toString() +
      '-' +
      year.toString()
    ).toString();
    this.loan = {
      rate:0.1,
      amount:this.amount,
      start_date:result
    };
    if(this.amount > 0)
    {
      this.profileService.SetLoan(this.loan,this.username!).subscribe({
        next: data =>{
          if(data){
            this.money!.balance = this.roundingnum(this.money?.balance + (data as any).amount);
            this.message = "successfully got a loan";
          }
          else{
            this.message = "Could not get a loan";
          }
          this.GetLoans();
        },
        error: e=>console.log(e)
      });
    }
    else{
      this.message = "invalid input";
    }
  }
  GetLoans(){
    this.loans = [];
    if(this.money && this.username){
      this.profileService.getLoans(this.username!).subscribe({
        next: data=> {
          for (let ele in data) {
            let temploan: Loan = {
              loans_id: (data as any)[ele].loans_id,
              amount: (data as any)[ele].amount,
              paidback: (data as any)[ele].paidback,
              receiver: (data as any)[ele].receiver,
            };

            if (temploan.paidback == false) {
              this.loans.push(temploan);

            }
          }

        },
        error: e=> console.log(e)
      });
    }
  }
  paymentLoan(loan:Loan){
    let date = new Date();
    let day = `0${date.getDate()}`.slice(-2); //("0"+date.getDate()).slice(-2);
    let month = `0${date.getMonth() + 1}`.slice(-2);
    let year = date.getFullYear();
    let result = (
      month.toString() +
      '-' +
      day.toString() +
      '-' +
      year.toString()
    ).toString();
    let payment : LoanPayments = {
      payment_amount:this.amount2,
      date:result
    };
    this.amount2 = 0;
    this.profileService.setPaymentLoans(payment,loan.loans_id!).subscribe({
      next: data=>{
        this.money!.balance = this.roundingnum((this.money as any).balance - (data as any).payment_amount);
        this.GetLoans();
      }

    })
  }
  money: Money | undefined;
  balance: number = 0;
  username : string | undefined;
  user : BusinessAccounts | undefined;
  amount: number = 0;
  amount2: number = 0;
  message: string = "";
  loan : Loan | undefined;
  loans : Array<Loan> = [];
}
