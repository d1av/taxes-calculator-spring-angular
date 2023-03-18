import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MonthlyRoutingModule } from './monthly-routing.module';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MonthlyRoutingModule,
    RouterModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class MonthlyModule { }
