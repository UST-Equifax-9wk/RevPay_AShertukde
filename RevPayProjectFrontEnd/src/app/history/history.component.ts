import { Component, OnInit } from '@angular/core';
import { Loan, LoanPayments, ProfileService, Transaction } from '../profile/profile.service';
import { CustomcookieService } from '../customcookie.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Money } from '../profile/profile.component';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css',
})
export class HistoryComponent implements OnInit {
  constructor(
    private profileService: ProfileService,
    private router: Router,
    private Cook: CustomcookieService
  ) {}
  ngOnInit(): void {
    this.username = this.Cook.getCookie('username');
    this.authorities = this.Cook.getCookie('authorities');
    if (!this.username || this.username == '') {
      this.router.navigateByUrl('/login');
    } else if (this.authorities == '[USER]') {
      this.profileService.getBalance(this.username!, 'username').subscribe({
        next: (data) => {
          if (data) {
            this.money_id = (data as any).money_id;
            this.balance = (data as any).balance;
          }
          if (this.transactions.length == 0 && this.money_id) {
            this.profileService
              .getRequestsTransaction(this.money_id)
              ?.subscribe({
                next: (data) => {
                  for (let ele in data) {
                    let temptransaction: Transaction2 = {
                      transaction_id: (data as any)[ele].transaction_id,
                      date_sent: (data as any)[ele].date_sent,
                      amount: parseFloat((data as any)[ele].amount).toFixed(2),
                      isComplete: (data as any)[ele].isComplete,
                      receiver: (data as any)[ele].receiver,
                    };

                    if (temptransaction.isComplete == true) {
                      this.transactions.push(temptransaction);
                    }
                  }
                },
                error: (e) => console.log(e),
              });
            this.profileService
              .getPaymentsTransaction(this.money_id)
              ?.subscribe({
                next: (data) => {
                  for (let ele in data) {
                    let temptransaction: Transaction2 = {
                      transaction_id: (data as any)[ele].transaction_id,
                      date_sent: (data as any)[ele].date_sent,
                      amount: parseFloat((data as any)[ele].amount).toFixed(2),
                      isComplete: (data as any)[ele].isComplete,
                      sender: (data as any)[ele].sender,
                    };

                    if (temptransaction.isComplete == true) {
                      this.transactions.push(temptransaction);
                    }
                  }
                },
                error: (e) => console.log(e),
              });
          }
        },
        error: (e) => console.log(e),
      });
    } else if (this.authorities == '[BUSINESS]') {
      this.profileService.GetBusinessBalance(this.username!).subscribe({
        next: (data) => {
          if (data) {
            this.money_id = (data as any).money_id;
            this.balance = (data as any).balance;
          }
          if (this.transactions.length == 0 && this.money_id) {
            this.profileService
              .getRequestsTransaction(this.money_id)
              ?.subscribe({
                next: (data) => {
                  for (let ele in data) {
                    let temptransaction: Transaction2 = {
                      transaction_id: (data as any)[ele].transaction_id,
                      date_sent: (data as any)[ele].date_sent,
                      amount: parseFloat((data as any)[ele].amount).toFixed(2),
                      isComplete: (data as any)[ele].isComplete,
                      receiver: (data as any)[ele].receiver,
                    };

                    if (temptransaction.isComplete == true) {
                      this.transactions.push(temptransaction);
                    }
                  }
                },
                error: (e) => console.log(e),
              });
            this.profileService
              .getPaymentsTransaction(this.money_id)
              ?.subscribe({
                next: (data) => {
                  for (let ele in data) {
                    let temptransaction: Transaction2 = {
                      transaction_id: (data as any)[ele].transaction_id,
                      date_sent: (data as any)[ele].date_sent,
                      amount: parseFloat((data as any)[ele].amount).toFixed(2),
                      isComplete: (data as any)[ele].isComplete,
                      sender: (data as any)[ele].sender,
                    };

                    if (temptransaction.isComplete == true) {
                      this.transactions.push(temptransaction);
                    }
                  }
                },
                error: (e) => console.log(e),
              });
          }
          if (this.loans.length == 0 && this.money_id) {
            this.profileService.getLoans(this.username!).subscribe({
              next: (data) => {
                for (let ele in data) {
                  let temploan: Loan = {
                    loans_id: (data as any)[ele].loans_id,
                    amount: (data as any)[ele].amount,
                    paidback: (data as any)[ele].paidback,
                    receiver: (data as any)[ele].receiver,
                    end_date: (data as any)[ele].end_date
                  };
                  this.loans.push(temploan);                  
                }
              },
              error: (e) => console.log(e),
            });
          }
        },
        error: (e) => console.log(e),
      });
    }
  }
  loans: Array<Loan> = [];
  loanpayments: Array<LoanPayments> = [];
  loan_loanpayments: Map<Loan, Array<LoanPayments>> = new Map();
  transactions: Array<Transaction2> = [];
  username: string | undefined;
  money_id: number | undefined;
  balance: number = 0;
  authorities: string | undefined;
}

export interface Transaction2 {
  transaction_id?: Number;
  amount?: string;
  date_sent?: string;
  isComplete?: boolean;
  receiver?: Money;
  sender?: Money;
}
