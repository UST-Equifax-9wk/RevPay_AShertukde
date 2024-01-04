import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { RegistrationService, UserAccounts } from './registration.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule,RouterModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  message: String | undefined;
  user: UserAccounts = {
    username: "",
    password: "",
    email: "",
    phone: "",
    firstname: "",
    lastname: ""
  };
 
  constructor(private httpService: RegistrationService, private router: Router) {
  
  }
  registerUser(){
    for(let key in this.user)
    {
      if(this.user[key as keyof UserAccounts] == "")
      {
        this.message = "error please enter a(n) " + key;
        return;
      }
    }
    let regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im
    if(!regex.test(this.user.phone))
    {
      this.message = "invalid phone number";
      return;
    }
    this.httpService.putUser(this.user).subscribe({
      next: data =>{ 
      this.user = {
        userid:(data as any).userid,
        username:this.user.username,
        password:this.user.password,
        email:this.user.email,
        phone:this.user.phone,
        firstname:this.user.firstname,
        lastname:this.user.lastname
          };
          (data as any).password = (this.user).password;
      if(JSON.stringify(data) == JSON.stringify(this.user))
      {
        this.message = "successful register";
      }
      else{
        this.message = "not able to register";
      }
    },
    error: e => console.log(e)
      });
    
  }
}
