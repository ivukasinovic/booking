import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {MatTabsModule} from '@angular/material/tabs';

// import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { TypeLodgingComponent } from './type-lodging/type-lodging.component';
import { CategoryLodgingComponent } from './category-lodging/category-lodging.component';
import { AdditionalLodgingComponent } from './additional-lodging/additional-lodging.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {RouterModule} from '@angular/router';
import {AuthService} from './auth.service';
import {RoleGuardService} from './role-guard.service';
import {AuthGuardService} from './auth-guard-service.service';
import { NavbarComponent } from './navbar/navbar.component';
import {HttpClientModule} from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { AddAgentComponent } from './add-agent/add-agent.component';
import { CommentComponent } from './comment/comment.component';

// import {MatCardModule} from '@angular/material/card';
// import {MatCheckbox} from "@angular/material/checkbox";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TypeLodgingComponent,
    CategoryLodgingComponent,
    AdditionalLodgingComponent,
    LoginComponent,
    NavbarComponent,
    AdminComponent,
    AddAgentComponent,
    CommentComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    HttpClientModule,
    RouterModule  // .forRoot([{ path: '', component: LoginComponent}])
    // MatCardModule
  ],
  providers: [
    AuthGuardService,
    AuthService,
    RoleGuardService],
  bootstrap: [AppComponent],
})
export class AppModule { }
