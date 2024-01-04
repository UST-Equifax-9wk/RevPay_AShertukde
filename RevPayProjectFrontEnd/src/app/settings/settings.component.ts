import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile/profile.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CustomcookieService } from '../customcookie.service';
import { Router } from '@angular/router';
import { BusinessAccounts, RegistrationService, UserAccounts } from '../registration/registration.service';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
})
export class SettingsComponent implements OnInit{
  constructor(private profileService: ProfileService, private Cook:CustomcookieService, private router:Router, private registrationService:RegistrationService) {}
  ngOnInit(): void {
    this.username = this.Cook.getCookie('username');
    this.authorities = this.Cook.getCookie('authorities');
    if (!this.username || this.username == '') {
      this.router.navigateByUrl('/login');
    }
    else if(this.authorities=="[USER]"){
      this.profileService.getUserInfo(this.username!).subscribe({
        next: (data) => {
          this.user = {
            userid: (data as any).userid,
            username: (data as any).username,
            email: (data as any).email,
            phone: (data as any).phone,
            firstname: (data as any).firstname,
            lastname: (data as any).lastname,
          };
        },
        error: (e) => console.log(e)
      });
    }
    else if(this.authorities=="[BUSINESS]")
    {
      this.profileService.getBusinessInfo(this.username!).subscribe({
        next: data=>{
          this.business = {
            business_id: (data as any).business_id,
            username: (data as any).username,
            password: (data as any).password,          
            phone: (data as any).phone,
            business_name: (data as any).business_name,
            email: (data as any).email
          };
          this.business.password = "";
        },
        error: (e) => console.log(e)
      });
    }
  }
  changeUser(){
    if(this.authorities == "[USER]"){
    if(this.firstname != ""){
      this.user!.firstname = this.firstname;
    }
    if(this.lastname != ""){
      this.user!.lastname = this.lastname;
    }
    if(this.usernamechange != ""){
      this.user!.username = this.usernamechange;
    }
    if(this.phone != ""){
      this.user!.phone = this.phone;
    }
    if(this.email != ""){
      this.user!.email = this.email;
    }
    if(this.password != ""){
      this.user!.password = this.password;
    }
    if(this.user){
    this.registrationService.putUser(this.user!).subscribe({
      next: data => {
        this.user = {
          userid: (data as any).userid,
            username: (data as any).username,
            email: (data as any).email,
            phone: (data as any).phone,
            firstname: (data as any).firstname,
            lastname: (data as any).lastname,
        };
        this.usernamechange = this.user.username;
        this.firstname = this.user.firstname;
        this.lastname = this.user.lastname;
        this.email = this.user.email;
        this.phone= this.user.phone;
        this.Cook.setCookie("username",this.user.username,1);
        this.message = "successfully updated your settings";
      },
      error: e => this.message = "there was a problem changing your settings please try different settings"
    });
  }
  }
  else if(this.authorities == "[BUSINESS]")
  {
    if(this.firstname != ""){
      this.business!.business_name = this.firstname;
    }
    if(this.usernamechange != ""){
      this.business!.username = this.usernamechange;
    }
    if(this.phone != ""){
      this.business!.phone = this.phone;
    }
    if(this.email != ""){
      this.business!.email = this.email;
    }
    if(this.password != ""){
      this.business!.password = this.password;
    }
    if(this.business)
    {
      this.registrationService.putBusiness(this.business).subscribe({
        next: data => {
          this.business = {
            business_id: (data as any).business_id,            
              username: (data as any).username,
              password:"",
              email: (data as any).email,              
              phone: (data as any).phone,
              business_name: (data as any).business_name,

          };
          this.usernamechange = this.business.username;
          this.firstname = this.business.business_name;
          this.email = this.business.email;
          this.phone= this.business.phone;
          this.Cook.setCookie("username",this.business.username,1);
          this.message = "successfully updated your settings";
        },
        error: e => this.message = "there was a problem changing your settings please try different settings"
      });
    }
  }
  }
username : string | undefined;
user: UserAccounts | undefined;
firstname: string = "";
lastname: string = "";
usernamechange: string = "";
phone: string = "";
email: string = "";
password: string = "";
message: string | undefined;
authorities: string | undefined;
business: BusinessAccounts = {
  username: "",
  password: "",
  phone: "",
  business_name: "",
  email: ""
}
}
