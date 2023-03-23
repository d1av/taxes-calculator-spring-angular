import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './pages/auth/auth.module';
import { HomeComponent } from './pages/home/home.component';
import { PagesModule } from './pages/pages.module';
import { AuthInterceptorService } from './shared/authentication/auth-interceptor.service';
import { SharedModule } from './shared/shared.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    AppRoutingModule,
    RouterModule,
    PagesModule,
    HttpClientModule,
    SharedModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule,
    NgbModule,
    NgHttpLoaderModule.forRoot(),
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
