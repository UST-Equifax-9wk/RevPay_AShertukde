import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService , Login} from './login.service';
import { CookieService } from 'ngx-cookie-service';
import { ProfileService } from '../profile/profile.service';
import { RegistrationService, UserAccounts } from '../registration/registration.service';
import { CustomcookieService } from '../customcookie.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login : Login = {
    username : "",
    password : ""
  };
  log : String | undefined;
constructor(private httpService:LoginService, private user:ProfileService, private router:Router, private cook:CustomcookieService, private register:RegistrationService){

}
submitlogin()
{
  this.httpService.loginUser(this.login).subscribe({
    next: data =>{
      if((data as any).message == "success")
      {
        //this.username.setUsername(this.login.username);
        this.user.setUsername(this.login.username);
        this.user.setPassword(this.login.password);
        this.user.setCookie(this.cook.getCookie("authorities"));
        this.router.navigateByUrl('/profile');
      }
      else{
        this.log = "Invalid Username and Password";
      }
    },
    error: e => console.log(e)
  });
}
forgotPass(){
  this.toggler = true;
  this.random_string = this.makeid(5); 
  
}


makeid(length : number) {
  let result = '';
  const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  const charactersLength = characters.length;
  let counter = 0;
  while (counter < length) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
    counter += 1;
  }
  return result;
}

submitEmail(){
  if(this.email != "")
  {
    this.toggler2 = true;
    this.httpService.sendEmail(this.email,this.random_string).subscribe({
      next: (data) => console.log(data),
      error: (e) => console.log(e)
    });
  }
}
submitAuthentication(){
  if(this.random_string == this.authentication){
    this.toggler3 = true;
    this.user.getUserInfoByEmail(this.email).subscribe({
        next: (data) => {
          this.userAccount = {
            userid: (data as any).userid,
            username: (data as any).username,
            password: "",
            email: (data as any).email,
            phone: (data as any).phone,
            firstname: (data as any).firstname,
            lastname: (data as any).lastname,
          };
          console.log(data);
        }
    });
  }
}
submitPassword(){
  if(this.password != "")
  {
    this.userAccount!.password = this.password;
    this.register.putUser(this.userAccount!).subscribe({
      next: (data) => this.message = "Success please login with new password",
      error: e => console.log(e)
    })
  }
}

userAccount : UserAccounts | undefined;
toggler : boolean = false;
toggler2 : boolean = false;
toggler3 : boolean = false;
username : string = "";
authentication : string = "";
random_string : string = "";
email : string = "";
password : string = "";
message : string | undefined;
}
