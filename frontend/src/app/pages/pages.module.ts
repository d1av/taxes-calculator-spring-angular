import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AuthModule } from './auth/auth.module';
import { LoginComponent } from './auth/login/login.component';
import { HomeModule } from './home/home.module';



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    HomeModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule
  ]
})
export class PagesModule { }
