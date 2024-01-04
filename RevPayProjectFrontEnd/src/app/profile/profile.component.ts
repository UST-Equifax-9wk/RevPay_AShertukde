import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProfileService, Transaction } from './profile.service';
import { BusinessAccounts, UserAccounts } from '../registration/registration.service';
import { CustomcookieService } from '../customcookie.service';
@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  constructor(
    private profileService: ProfileService,
    private router: Router,
    private Cook: CustomcookieService
  ) {
    this.username = Cook.getCookie('username');
    let authorities = Cook.getCookie('authorities');
    if (!this.username || this.username == '') {
      this.router.navigateByUrl('/login');
    } else if (authorities == '[BUSINESS]') {
      this.profileService.setUsername(this.username);
      this.router.navigateByUrl('/profilebusiness');
    } else if (authorities == '[USER]') {
      this.profileService.setUsername(this.username);
      this.profileService.getUserInfo(this.username!).subscribe({
        next: (data) => {
          this.user = {
            userid: (data as any).userid,
            username: (data as any).username,
            password: (data as any).password,
            email: (data as any).email,
            phone: (data as any).phone,
            firstname: (data as any).firstname,
            lastname: (data as any).lastname,
          };

          this.profileService.setUser(this.user);
        },
        error: (e) => console.log(e),
      });
      this.profileService.getBalance(this.username!, 'username').subscribe({
        next: (data) => {
          if (data) {
            this.money = {
              money_id: (data as any).money_id,
              balance: (data as any).balance,
            };
            this.getAllRequests();
          }
        },
        error: (e) => console.log(e),
      });
    }
  }
  submitBalance() {
    this.balance = this.roundingnum(this.balance);
    let tempmoney: Money = { balance: this.balance };
    this.profileService.postBalance(this.username!, tempmoney).subscribe({
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
  getSender() {
    if (
      this.identifier &&
      this.identifier != this.username &&
      this.identifier != this.user?.phone &&
      this.identifier != this.user?.email
    ) {
      this.profileService
        .getBalance(this.identifier, this.selection)
        .subscribe({
          next: (data) => {
            if (data) {
              this.sender = (data as any).money_id;
              this.message = 'valid user who has a balance';
            } else {
              this.message = 'invalid user/could not find user';
            }
          },
          error: (e) => console.log(e),
        });
    }else{
      this.message = 'invalid user';
    }
  }
  getReceiver() {
    if (
      this.identifier2 &&
      this.identifier2 != this.username &&
      this.identifier2 != this.user?.phone &&
      this.identifier2 != this.user?.email
    ) {
      this.profileService
        .getBalance(this.identifier2, this.selection2)
        .subscribe({
          next: (data) => {
            if (data) {
              this.receiver = (data as any).money_id;
              this.message2 = 'valid user who has a balance';
            } else {
              this.message2 = 'invalid user/could not find user';
            }
          },
          error: (e) => console.log(e),
        });
    }
    else{
      this.message2 = 'invalid user';
    }
  }
  getBusiness() {
    if (
      this.identifier3 &&
      this.identifier3 != this.username
    ) {
      this.profileService
        .GetBusinessBalance(this.identifier3)
        .subscribe({
          next: (data) => {
            if (data) {
              this.business = (data as any).money_id;
              this.message3 = 'valid business that has a balance';
            } else {
              this.message3 = 'invalid user/could not find user';
            }
          },
          error: (e) => console.log(e),
        });
    }else{
      this.message3 = 'invalid user';
    }
  }
  sendingBusinessMoney(){
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
    this.amount3 = this.roundingnum(this.amount3);
    let temptransaction: Transaction = {
      amount: this.amount3,
      date_sent: result,
    };
    if (
      this.identifier3 != null &&
      this.money &&
      this.business &&
      this.amount3 > 0
    ) {
      this.profileService
        .postRequestGiveMoney(
          this.business,
          this.money!.money_id!,
          temptransaction
        )
        ?.subscribe({
          next: (data) => {
            if (data) {
              this.transaction = {
                transaction_id: (data as any).transaction_id,
                amount: (data as any).amount,
                isComplete: (data as any).isComplete,
              };
              this.message3 = 'successfully sent request';
              this.money!.balance =
                (this.money as any).balance - (data as any).amount;
              this.money!.balance = this.roundingnum(this.money!.balance);
            } else {
              this.message3 = 'could not request';
            }
          },
          error: (e) => console.log(e),
        });
    }
  }
  sendingMoney() {
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
    this.amount2 = this.roundingnum(this.amount2);
    let temptransaction: Transaction = {
      amount: this.amount2,
      date_sent: result,
    };
    if (
      this.identifier2 != null &&
      this.money &&
      this.receiver &&
      this.amount2 > 0
    ) {
      this.profileService
        .postRequestGiveMoney(
          this.receiver,
          this.money!.money_id!,
          temptransaction
        )
        ?.subscribe({
          next: (data) => {
            if (data) {
              this.transaction = {
                transaction_id: (data as any).transaction_id,
                amount: (data as any).amount,
                isComplete: (data as any).isComplete,
              };
              this.message2 = 'successfully sent request';
              this.money!.balance =
                (this.money as any).balance - (data as any).amount;
              this.money!.balance = this.roundingnum(this.money!.balance);
            } else {
              this.message2 = 'could not request';
            }
          },
          error: (e) => console.log(e),
        });
    }
  }
  requestMoney() {
    this.amount = this.roundingnum(this.amount);
    let temptransaction: Transaction = {
      amount: this.amount,
      isComplete: false,
    };
    if (
      this.identifier != null &&
      this.money &&
      this.sender &&
      this.amount > 0
    ) {
      this.profileService
        .postRequestTransaction(
          this.sender,
          this.money!.money_id!,
          temptransaction
        )
        ?.subscribe({
          next: (data) => {
            if (data) {
              this.transaction = {
                transaction_id: (data as any).transaction_id,
                amount: (data as any).amount,
                isComplete: (data as any).isComplete,
              };
              this.message = 'successfully sent request';
            } else {
              this.message = 'could not request';
            }
          },
          error: (e) => console.log(e),
        });
    }
  }
  rounding(num: number): string {
    return (Math.round(num * 100) / 100).toFixed(2);
  }
  roundingnum(num: number): number {
    return Math.round(num * 100) / 100;
  }
  highlightSelected(element: any) {
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
    let temptransaction: Transaction = {
      transaction_id: (element as any).transaction_id,
      date_sent: result,
    };
    this.profileService.SendMoney(temptransaction).subscribe({
      next: (data) => {
        if ((data as any).isComplete == true) {
          this.transactions = this.transactions.filter(
            (i) => i.transaction_id != (data as any).transaction_id
          );
          this.money!.balance =
            (this.money as any).balance - (data as any).amount;
          this.money!.balance = this.roundingnum(this.money!.balance);
        }
      },
      error: (e) => console.log(e),
    });
  }
  getAllRequests() {
    if (this.money && this.transactions.length == 0) {
      this.profileService
        .getRequestsTransaction(this.money?.money_id!)
        ?.subscribe({
          next: (data) => {
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

            for (let ele in data) {
              let temptransaction: Transaction = {
                transaction_id: (data as any)[ele].transaction_id,
                amount: (data as any)[ele].amount,
                date_sent: result,
                isComplete: (data as any)[ele].isComplete,
                receiver: (data as any)[ele].receiver,
              };

              if (temptransaction.isComplete == false) {
                this.transactions.push(temptransaction);
              }
            }
          },
          error: (e) => console.log(e),
        });
    }
  }
  private username: string | undefined;
  authorities: string | undefined;
  user: UserAccounts | undefined;
  money: Money | undefined;
  business: number | undefined;
  balance: number = 0;
  amount: number = 0;
  amount2: number = 0;
  amount3: number = 0;
  message3: string = "";
  identifier: string | undefined;
  selection: string = 'username';
  identifier2: string | undefined;
  identifier3: string | undefined;
  selection2: string = 'username';
  transaction: Transaction | undefined;
  transactions: Array<Transaction> = [];
  sender: number | undefined;
  receiver: number | undefined;
  message: string | undefined;
  message2: string | undefined;
}
export interface Money {
  money_id?: number;
  balance?: number;
  userAccounts?: UserAccounts;
  businessAccounts?: BusinessAccounts;
}
