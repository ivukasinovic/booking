import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CertificatComponent} from './certificat/certificat.component';
import {SelectKeyStoreComponent} from './select-key-store/select-key-store.component';
import {KeyStoreComponent} from './key-store/key-store.component';

const routes: Routes = [
  { path: '', redirectTo: '/certificat', pathMatch: 'full' },
  { path: 'certificat', component: CertificatComponent },
  { path: 'selectKeyStore', component: SelectKeyStoreComponent },
  { path: 'createKeyStore', component: KeyStoreComponent },

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
