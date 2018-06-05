import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import {AuthModule} from './auth/auth.module';
import {FormsModule} from '@angular/forms';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import {LodgingService} from './lodging.service';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ReservationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppRoutingModule,
    AuthModule,
    FormsModule
  ],
  providers: [LodgingService],
  bootstrap: [AppComponent]
})
export class AppModule {}
