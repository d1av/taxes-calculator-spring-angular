import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { ApiService } from './api.service';
import { VariableTaxResponse } from './response/variabletax-response.type';

@Injectable({
  providedIn: 'root'
})
export class VariableTaxApiService {

  constructor (private apiService: ApiService) { }

  public getVariableTaxById(variableTaxesId: string): Observable<VariableTaxResponse> {
    return from(this.apiService.get('variabletaxes/' + variableTaxesId));
  }
}
