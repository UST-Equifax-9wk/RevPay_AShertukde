import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { HistoryComponent } from './history/history.component';
import { CardsComponent } from './cards/cards.component';
import { SettingsComponent } from './settings/settings.component';
import { BussinessregistrationComponent } from './bussinessregistration/bussinessregistration.component';
import { ProfilebusinessComponent } from './profilebusiness/profilebusiness.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: 'login', component: LoginComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'history', component: HistoryComponent},
  { path: 'cards', component: CardsComponent},
  { path: 'settings', component: SettingsComponent},
  { path: 'businessregistration', component: BussinessregistrationComponent},
  { path: 'profilebusiness', component: ProfilebusinessComponent},
];
