import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import * as decode from 'jwt-decode';
import {AuthService} from './auth.service';
// Omogucava da samo odredjene role mogu da pristupe odredjenoj komponenti, dodat u route expectedRole
@Injectable()
export class RoleGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    console.log(tokenPayload);
    if (
      !this.auth.isAuthenticated() ||
      tokenPayload.role !== expectedRole) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

}
