import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {CertificatesComponent} from './certificates/certificates.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';
import {CertificateListComponent} from './certificates/certificate-list/certificate-list.component';
import {CertificatesService} from './certificates/certificates.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {TokenInterceptor} from './token-interceptor';
import {AuthGuardService} from './auth-guard.service';
import {AuthService} from './services/auth.service';
import {RoleGuardService} from './role-guard.service';
import { RegistrationComponent } from './registration/registration.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import {UserService} from './services/user.service';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SearchComponent } from './search/search.component';
import {SearchService} from './services/search.service';

import {ReserveService} from './services/reserve.service';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import { MyReservationsComponent } from './my-reservations/my-reservations.component';
import { RateAndCommentComponent } from './rate-and-comment/rate-and-comment.component';
import { SendMessageComponent } from './send-message/send-message.component';
import { MakeReservationComponent } from './make-reservation/make-reservation.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { MyMessagesComponent } from './my-messages/my-messages.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CarouselModule} from 'ngx-bootstrap/carousel';
import { CertificateRequestComponent } from './certificates/certificate-request/certificate-request.component';
import { CsrListComponent } from './certificates/csr-list/csr-list.component';
@NgModule({
  declarations: [
    AppComponent,
    CertificatComponent,
    CertificatesComponent,
    NavbarComponent,
    CertificateDetailsComponent,
    CertificateListComponent,
    LoginComponent,
    RegistrationComponent,
    UserProfileComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    ResetPasswordComponent,
    SearchComponent,
    MyReservationsComponent,
    RateAndCommentComponent,
    SendMessageComponent,
    MakeReservationComponent,
    UserInfoComponent,
    MyMessagesComponent,
    CertificateRequestComponent,
    CsrListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    NgbModule.forRoot(),
    CarouselModule.forRoot()
  ],
  providers: [CertificatesService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    AuthGuardService,
    AuthService,
    RoleGuardService,
    SearchService,
    UserService,
    ReserveService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
