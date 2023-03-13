import { Injectable } from '@angular/core';
import { AuthApiService } from '../authentication/auth-api.service';
import { ApiService } from './api.service';
import { TotalTaxResponse } from './response/total-tax-response.type';

@Injectable({
  providedIn: 'root'
})
export class TotalTaxApiService {

  constructor (private apiService: ApiService, private authApi: AuthApiService) { }

  async getTotalTax(): Promise<TotalTaxResponse> {
    const userId = localStorage.getItem('userId');
    try {
      if (userId) {
        const response = await this.apiService.get(`totaltaxes/${ userId }`);
        response.userId = userId;
        if (response.fixedTaxId) {
          localStorage.setItem('fixedTaxId', response.fixedTaxId);
        }
        if (response.fixedTaxId) {
          localStorage.setItem('variableTaxId', response.variableTaxId);
        }
        if (response.fixedTaxId) {
          localStorage.setItem('hourValueId', response.hourValueId);
        }
        return response;
      }
    } catch (err: any) {
      this.authApi.logout();
      alert(`${ err.error.message } : ${ err.error.errors[ 0 ].message }`);
    }

    return {} as TotalTaxResponse;
  }
}
