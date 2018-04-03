import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CertificatComponent} from './certificat/certificat.component';

const routes: Routes = [
  { path: 'certificat', component: CertificatComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
