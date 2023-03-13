import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { TotalTaxResponse } from './response/total-tax-response.type';

@Injectable({
  providedIn: 'root'
})
export class TotalTaxApiService {

  constructor (private apiService: ApiService) { }

  async getTotalTax(): Promise<TotalTaxResponse> {
    const userId = localStorage.getItem('userId');
    try {
      if (userId) {
        return await this.apiService.get(`fixedtaxes`);
      }
    } catch (err: any) {
      this.apiService
      alert(`${ err.error.message } : ${ err.error.errors[0].message }`);
    }

    return {} as TotalTaxResponse;
  }
}
