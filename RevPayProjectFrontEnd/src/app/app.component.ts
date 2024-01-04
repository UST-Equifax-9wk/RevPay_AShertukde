import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent} from './registration/registration.component';
import { ProfileService } from './profile/profile.service';
import { CustomcookieService } from './customcookie.service';
@Component({
  selector: 'app-root',
  standalone: true,
  imports:[RouterOutlet,RouterModule,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{
  title = 'RevPay';
  constructor(private profileService: ProfileService, private cook : CustomcookieService ){

  }
  deleteCookies(){
    this.cook.deleteCookie("authorities");
    this.cook.deleteCookie("username");
  }

}