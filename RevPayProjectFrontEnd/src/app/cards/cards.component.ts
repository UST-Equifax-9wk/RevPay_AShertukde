import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CustomcookieService } from '../customcookie.service';
import { Router, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import moment from 'moment';
import { ProfileService } from '../profile/profile.service';

@Component({
  selector: 'app-cards',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.css',
})
export class CardsComponent implements OnInit {
  constructor(
    private Cook: CustomcookieService,
    private router: Router,
    private cardService: ProfileService
  ) {}
  ngOnInit(): void {
    this.username = this.Cook.getCookie('username');
    this.authorities = this.Cook.getCookie('authorities');
    if (!this.username || this.username == '') {
      this.router.navigateByUrl('/login');
    } else if(this.authorities == '[USER]'){
      this.getAllCards();
    }
    else if(this.authorities == '[BUSINESS]')
    {
      this.getAllBusinessCards();
    }
  }
  getAllCards(){
    this.cardService.getCards(this.username!).subscribe({
      next: (data) =>{
        if(data){
          for(let element in data)
          {
            let tempcard : Card = {
              cards_id : (data as any)[element].cards_id,
              card_number : (data as any)[element].card_number,
              card_type : (data as any)[element].card_type,
              expiration_date : (data as any)[element].expiration_date
            }
            this.cards = this.cards.filter(
              (i) => i.cards_id != tempcard.cards_id
            );
            this.cards.push(tempcard);            
          }
          

        }
      },
      error: (e) => console.log(e)
    })
  }
  getAllBusinessCards(){
    this.cardService.getBusinessCards(this.username!).subscribe({
      next: (data) =>{
        if(data){
          for(let element in data)
          {
            let tempcard : Card = {
              cards_id : (data as any)[element].cards_id,
              card_number : (data as any)[element].card_number,
              card_type : (data as any)[element].card_type,
              expiration_date : (data as any)[element].expiration_date
            }
            this.cards = this.cards.filter(
              (i) => i.cards_id != tempcard.cards_id
            );
            this.cards.push(tempcard);            
          }
          

        }
      },
      error: (e) => console.log(e)
    })
  }
  registerCard() {
    if (
      /^\d+$/.test(this.card.card_number) &&
      this.card.card_number.length == 16
    ) {
      let tempdate = '01/' + this.card.expiration_date;
      if (
        this.isValidDate(tempdate) &&
        this.card.expiration_date.length == 5 &&
        this.card.expiration_date[2] == '/'
      ) {
        if(this.authorities == '[USER]'){
        this.cardService.SetCard(this.card, this.username!).subscribe({
          next: (data) => {
            if(data){
            this.message = 'Successfully  Registered Card';
            this.getAllCards();
            }
            else{
              this.message = 'Failed to register Card';
            }
          },
          error: (e) => this.message = 'Failed to register Card',
        });
      }
      else if(this.authorities == '[BUSINESS]')
      {
        this.cardService.SetBusinessCard(this.card, this.username!).subscribe({
          next: (data) => {
            if(data){
            this.message = 'Successfully  Registered Card';
            this.getAllBusinessCards();
            }
            else{
              this.message = 'Failed to register Card';
            }
          },
          error: (e) => this.message = 'Failed to register Card',
        });
      }
      } else {
        this.message = 'Invalid Expiration Date Format';
      }
    } else {
      this.message = 'Invalid Card Number';
    }
  }
  isValidDate(s: string): boolean {
    // format D(D)/M(M)/(YY)YY
    var dateFormat = /^\d{1,4}[\.|\/|-]\d{1,2}[\.|\/|-]\d{1,4}$/;

    if (dateFormat.test(s)) {
      // remove any leading zeros from date values
      s = s.replace(/0*(\d*)/gi, '$1');
      var dateArray: any;
      dateArray = s.split(/[\.|\/|-]/);

      // correct month value
      dateArray[1] = dateArray[1] - 1;
      // correct year value
      if (dateArray[2].length < 4) {
        // correct year value
        dateArray[2] =
          parseInt(dateArray[2]) < 50
            ? 2000 + parseInt(dateArray[2])
            : 1900 + parseInt(dateArray[2]);
      }

      var testDate = new Date(dateArray[2], dateArray[1], dateArray[0]);
      if (
        testDate.getDate() != dateArray[0] ||
        testDate.getMonth() != dateArray[1] ||
        testDate.getFullYear() != dateArray[2]
      ) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
  message: string | undefined;
  authorities: string = "";
  username: string | undefined;
  card: Card = {
    card_number: '',
    card_type: 'debit',
    expiration_date: '',
  };
  cards: Array<Card> = [];
}

export interface Card {
  cards_id ?: number;
  card_number: string;
  card_type: string;
  expiration_date: string;
}
