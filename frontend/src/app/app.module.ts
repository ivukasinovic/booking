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
import {AuthService} from './auth.service';
import {RoleGuardService} from './role-guard.service';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    CertificatComponent,
    CertificatesComponent,
    NavbarComponent,
    CertificateDetailsComponent,
    CertificateListComponent,
    LoginComponent,
    RegistrationComponent
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
    RoleGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
