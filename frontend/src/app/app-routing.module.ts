import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CertificatComponent} from './certificates/certificat/certificat.component';
import {SelectKeyStoreComponent} from './certificates/select-key-store/select-key-store.component';
import {KeyStoreComponent} from './certificates/key-store/key-store.component';
import {NewKeyStoreComponent} from './certificates/new-key-store/new-key-store.component';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';

const routes: Routes = [
  {path: 'certificates/newCertificate', component: CertificatComponent},
  {path: 'selectKeyStore', component: SelectKeyStoreComponent},
  {path: 'createKeyStore', component: KeyStoreComponent},
  {path: 'certificates/newKeyStore', component: NewKeyStoreComponent},
  {path: 'certificates/keyStore', component: KeyStoreComponent},
  {path: 'certificates/detail/:id', component: CertificateDetailsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
