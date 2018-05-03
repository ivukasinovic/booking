import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';
import {CertificateListComponent} from './certificates/certificate-list/certificate-list.component';

const routes: Routes = [
  {path: 'certificates/new-certificate', component: CertificatComponent},
  {path: 'certificates/:id', component: CertificateDetailsComponent},
  {path: 'certificates', component: CertificateListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
