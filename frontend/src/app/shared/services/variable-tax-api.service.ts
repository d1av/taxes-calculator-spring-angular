import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { VariableTaxResponse } from './response/variabletax-response.type';

@Injectable({
  providedIn: 'root'
})
export class VariableTaxApiService {

  constructor (private apiService: ApiService) { }

  public getVariableTaxById(variableTaxesId: string): Promise<VariableTaxResponse> {
    return this.apiService.get('variabletaxes/' + variableTaxesId);
  }
}
