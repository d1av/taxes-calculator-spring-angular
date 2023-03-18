import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { AuthModule } from './auth/auth.module';
import { HomeModule } from './home/home.module';
import { MonthlyComponent } from './monthly/monthly.component';
import { MonthlyModule } from './monthly/monthly.module';



@NgModule({
  declarations: [
    MonthlyComponent
  ],
  imports: [
    HomeModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule,
    SharedModule,
    MonthlyModule,
    RouterModule
  ]
})
export class PagesModule { }
