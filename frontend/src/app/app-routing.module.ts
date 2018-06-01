import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CertificatComponent} from './certificates/certificate-new/certificate-new.component';
import {CertificateDetailsComponent} from './certificates/certificate-details/certificate-details.component';
import {CertificateListComponent} from './certificates/certificate-list/certificate-list.component';
import {LoginComponent} from './login/login.component';
import {AuthGuardService} from './auth-guard.service';
import {RegistrationComponent} from './registration/registration.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {SearchComponent} from './search/search.component';

const routes: Routes = [
  {path: 'certificates/new-certificate', component: CertificatComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'certificates/:id', component: CertificateDetailsComponent},
  {path: 'certificates', component: CertificateListComponent,
    canActivate: [AuthGuardService]},
  {path: 'profile', component: UserProfileComponent,
    canActivate: [AuthGuardService]},
  {path: 'change-password', component: ChangePasswordComponent,
    canActivate: [AuthGuardService]},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'reset-password/:id', component: ResetPasswordComponent},
  {path: 'search', component: SearchComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
