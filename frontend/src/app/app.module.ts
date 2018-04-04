import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCheckboxModule, MatInputModule, MatSelectModule, MatStepperModule} from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {AppRoutingModule} from './/app-routing.module';
import {KeyStoreComponent} from './certificates/key-store/key-store.component';
import {SelectKeyStoreComponent} from './certificates/select-key-store/select-key-store.component';
import {CertificatComponent} from './certificates/certificat/certificat.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {CertificatesComponent} from './certificates/certificates.component';
import {NavbarComponent} from './navbar/navbar.component';
import {NewKeyStoreComponent} from './certificates/new-key-store/new-key-store.component';
import {CertificatesService} from './certificates/certificates.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    KeyStoreComponent,
    SelectKeyStoreComponent,
    CertificatComponent,
    CertificatesComponent,
    NavbarComponent,
    NewKeyStoreComponent
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
    HttpClientModule,
    FormsModule
  ],
  providers: [CertificatesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
