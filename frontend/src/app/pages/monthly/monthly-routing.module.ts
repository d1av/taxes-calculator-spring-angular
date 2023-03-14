import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MonthlyComponent } from './monthly.component';

const routes: Routes = [
  {
    path: 'monthly', component: MonthlyComponent
  }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class MonthlyRoutingModule { }
