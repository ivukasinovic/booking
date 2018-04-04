import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class Interceptor implements HttpInterceptor {
  keyStoreName: string;
  keyStorePw: string;
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.keyStoreName = localStorage.getItem('keyStoreName');
    this.keyStorePw = localStorage.getItem('keyStorePw');
    console.log('USO' + this.keyStoreName + this.keyStorePw);
    if (( this.keyStoreName != null) && (this.keyStorePw != null)) {
      req = req.clone({headers: req.headers.set('keyStoreName', this.keyStoreName)});
      req = req.clone({headers: req.headers.set('keyStorePw', this.keyStorePw)});
    }
    return next.handle(req);
  }
}
