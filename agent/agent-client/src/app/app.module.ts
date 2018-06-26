import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import {AuthModule} from './auth/auth.module';
import {FormsModule} from '@angular/forms';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import {LodgingService} from './lodging.service';
import { LodgingNewComponent } from './lodging-new/lodging-new.component';
import { MessagesComponent } from './messages/messages.component';
import { OccupancyComponent } from './occupancy/occupancy.component';
import { LodgingListComponent } from './lodging-list/lodging-list.component';
import { PricePlanNewComponent } from './price-plan-new/price-plan-new.component';
import {CarouselModule} from 'ngx-bootstrap/carousel';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ReservationListComponent,
    LodgingNewComponent,
    MessagesComponent,
    OccupancyComponent,
    LodgingListComponent,
    PricePlanNewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppRoutingModule,
    AuthModule,
    FormsModule,
    CarouselModule.forRoot()
  ],
  providers: [LodgingService],
  bootstrap: [AppComponent]
})
export class AppModule {}
