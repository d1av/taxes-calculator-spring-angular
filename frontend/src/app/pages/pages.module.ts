import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HomeModule } from './home/home.module';
import { LoginComponent } from './auth/login/login.component';



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    HomeModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class PagesModule { }
