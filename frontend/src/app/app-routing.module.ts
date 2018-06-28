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
import {SendMessageComponent} from './send-message/send-message.component';
import {RateAndCommentComponent} from './rate-and-comment/rate-and-comment.component';
import {MakeReservationComponent} from './make-reservation/make-reservation.component';
import {CertificateRequestComponent} from "./certificates/certificate-request/certificate-request.component";
import {CsrListComponent} from "./certificates/csr-list/csr-list.component";

const routes: Routes = [
  {path: 'certificates/new-certificate', component: CertificatComponent},
  {path: 'certificates/certificate-request', component: CertificateRequestComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'certificates/:id', component: CertificateDetailsComponent},
  {path: 'certificates', component: CertificateListComponent,
    canActivate: [AuthGuardService]},
  {path: 'csr-list', component: CsrListComponent},
  {path: 'profile', component: UserProfileComponent,
    canActivate: [AuthGuardService]},
  {path: 'change-password', component: ChangePasswordComponent,
    canActivate: [AuthGuardService]},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'reset-password/:id', component: ResetPasswordComponent},
  {path: 'search', component: SearchComponent},
  {path: 'send-message', component: SendMessageComponent},
  {path: 'rate-and-comment', component: RateAndCommentComponent},
  {path: 'make-reservation/:id/:dateS/:dateE', component: MakeReservationComponent},
  {path: 'make-reservation', component: MakeReservationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
