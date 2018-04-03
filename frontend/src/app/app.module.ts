import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatCheckboxModule, MatStepperModule, MatSelectModule} from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { AppRoutingModule } from './/app-routing.module';
import { KeyStoreComponent } from './key-store/key-store.component';
import { SelectKeyStoreComponent } from './select-key-store/select-key-store.component';
import { CertificatComponent } from './certificat/certificat.component';

@NgModule({
  declarations: [
    AppComponent,
    KeyStoreComponent,
    SelectKeyStoreComponent,
    CertificatComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatButtonModule,
    MatMenuModule,
    MatStepperModule,
    MatSelectModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
