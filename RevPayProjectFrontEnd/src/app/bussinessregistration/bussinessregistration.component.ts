import { Component } from '@angular/core';
import {
  BusinessAccounts,
  RegistrationService,
  UserAccounts,
} from '../registration/registration.service';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-bussinessregistration',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet, RouterModule],
  templateUrl: './bussinessregistration.component.html',
  styleUrl: './bussinessregistration.component.css',
})
export class BussinessregistrationComponent {
  message: String | undefined;
  user: BusinessAccounts = {
    username: '',
    password: '',
    phone: '',
    business_name: '',
    email: '',
  };

  constructor(
    private httpService: RegistrationService,
    private router: Router
  ) {}
  registerUser() {
    for (let key in this.user) {
      if (this.user[key as keyof BusinessAccounts] == '') {
        this.message = 'error please enter a(n) ' + key;
        return;
      }
    }
    let regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im
    if(!regex.test(this.user.phone))
    {
      this.message = "invalid phone number";
      return;
    }
    this.httpService.putBusiness(this.user).subscribe({
      next: (data) => {
        this.user = {
          business_id: (data as any).business_id,
          username: this.user.username,
          password: this.user.password,          
          phone: this.user.phone,
          business_name: this.user.business_name,
          email: this.user.email
        };
        (data as any).password = this.user.password;
        if (JSON.stringify(data) == JSON.stringify(this.user)) {
          this.message = 'successful register';
        } else {
          this.message = 'not able to register';
        }
      },
      error: (e) => console.log(e),
    });
  }
}
