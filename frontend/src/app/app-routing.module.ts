import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';
import {CertificateListComponent} from './certificates/certificate-list/certificate-list.component';
import {LoginComponent} from './login/login.component';
import {AuthGuardService} from './auth-guard.service';
import {RegistrationComponent} from './registration/registration.component';

const routes: Routes = [
  {path: 'certificates/new-certificate', component: CertificatComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'certificates/:id', component: CertificateDetailsComponent},
  {path: 'certificates', component: CertificateListComponent,
    canActivate: [AuthGuardService], data: {expectedRole: 'ADMIN'}}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
