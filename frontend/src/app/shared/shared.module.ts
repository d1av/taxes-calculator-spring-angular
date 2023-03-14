import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { FixedTaxComponent } from './components/fixed-tax/fixed-tax.component';
import { HourValueComponent } from './components/hour-value/hour-value.component';
import { VariableTaxComponent } from './components/variable-tax/variable-tax.component';



@NgModule({
  providers: [
    { provide: 'API_URL', useValue: environment.apiUrl }
  ],
  declarations: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent
  ]
})
export class SharedModule { }
