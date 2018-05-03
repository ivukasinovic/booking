import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatCheckboxModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatSelectModule,
  MatStepperModule,
  MatTableModule
} from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {AppRoutingModule} from './app-routing.module';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {CertificatesComponent} from './certificates/certificates.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FormsModule} from '@angular/forms';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';
import {CertificateListComponent} from './certificates/certificate-list/certificate-list.component';
import {CertificatesService} from './certificates/certificates.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    CertificatComponent,
    CertificatesComponent,
    NavbarComponent,
    CertificateDetailsComponent,
    CertificateListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatButtonModule,
    MatMenuModule,
    MatStepperModule,
    MatSelectModule,
    MatToolbarModule,
    AppRoutingModule,
    MatInputModule,
    MatListModule,
    MatIconModule,
    MatTableModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [CertificatesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
