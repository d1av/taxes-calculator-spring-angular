import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { environment } from 'src/environments/environment';
import { FixedTaxComponent } from './components/fixed-tax/fixed-tax.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeModalComponent } from './components/home-modal/home-modal.component';
import { HourValueComponent } from './components/hour-value/hour-value.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { VariableTaxComponent } from './components/variable-tax/variable-tax.component';



@NgModule({
  providers: [
    { provide: 'API_URL', useValue: environment.apiUrl }
  ],
  declarations: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent,
    NavbarComponent,
    FooterComponent,
    HomeModalComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe
  ],
  exports: [
    FixedTaxComponent,
    VariableTaxComponent,
    HourValueComponent,
    NavbarComponent,
    FooterComponent
  ]
})
export class SharedModule { }
