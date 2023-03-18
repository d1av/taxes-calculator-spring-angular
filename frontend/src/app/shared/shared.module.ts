import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { FixedTaxComponent } from './components/fixed-tax/fixed-tax.component';
import { HourValueComponent } from './components/hour-value/hour-value.component';
import { VariableTaxComponent } from './components/variable-tax/variable-tax.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';



@NgModule({
  providers: [
    { provide: 'API_URL', useValue: environment.apiUrl }
  ],
  declarations: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent,
    NavbarComponent
  ]
})
export class SharedModule { }
