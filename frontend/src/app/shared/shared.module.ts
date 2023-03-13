import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { environment } from 'src/environments/environment';



@NgModule({
  providers: [
    { provide: 'API_URL', useValue: environment.apiUrl }
  ],
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
