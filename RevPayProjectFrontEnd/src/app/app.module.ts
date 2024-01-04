import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { routes } from './app.routes';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RegistrationService } from './registration/registration.service';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.service';
import { ProfileService } from './profile/profile.service';
import { CustomcookieService } from './customcookie.service';
import { HistoryComponent } from './history/history.component';
import { CardsComponent } from './cards/cards.component';
import { SettingsComponent } from './settings/settings.component';
import { BussinessregistrationComponent } from './bussinessregistration/bussinessregistration.component';
import { ProfilebusinessComponent } from './profilebusiness/profilebusiness.component';



@NgModule({
  declarations: [
    
  ],
  imports: [
    AppComponent,
    BrowserModule,
    RegistrationComponent,
    FormsModule,
    CommonModule,
    LoginComponent,
    RouterModule.forRoot(routes),
    RouterModule,
    HttpClientModule,
    CardsComponent,
    HistoryComponent,
    SettingsComponent,
    BussinessregistrationComponent,
    ProfilebusinessComponent
  ],
  providers:[RegistrationService, LoginService, ProfileService, CustomcookieService]
})
export class AppModule { }
