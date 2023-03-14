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
        } else {
          localStorage.removeItem('fixedTaxId');
        }

        if (response.variableTaxId) {
          localStorage.setItem('variableTaxId', response.variableTaxId);
        } else {
          localStorage.removeItem('variableTaxId');
        }
        
        if (response.hourValueId) {
          localStorage.setItem('hourValueId', response.hourValueId);
        } else {
          localStorage.removeItem('hourValueId');
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
