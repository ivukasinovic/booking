import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {CertificatesComponent} from './certificates/certificates.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FormsModule} from '@angular/forms';
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
import { NumberOnlyDirective } from './number.directive';

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
    NumberOnlyDirective
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
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
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
