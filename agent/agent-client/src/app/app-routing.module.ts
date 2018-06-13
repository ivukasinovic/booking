import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {ReservationListComponent} from './reservation-list/reservation-list.component';
import {LodgingNewComponent} from './lodging-new/lodging-new.component';
import {MessagesComponent} from './messages/messages.component';
import {OccupancyComponent} from './occupancy/occupancy.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'reservations', component: ReservationListComponent },
  { path: 'lodging-new', component: LodgingNewComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'occupancy', component: OccupancyComponent }
];

@NgModule({
  exports: [RouterModule],
  imports: [ RouterModule.forRoot(routes) ],
})
export class AppRoutingModule { }
