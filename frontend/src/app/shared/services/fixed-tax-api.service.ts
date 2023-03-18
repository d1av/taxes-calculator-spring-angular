import { Injectable } from '@angular/core';
import { from } from 'rxjs';
import { ApiService } from './api.service';
import { FixedTaxResponse } from './response/fixedtax-response.types';

@Injectable({
  providedIn: 'root'
})
export class FixedTaxApiService {

  fixedTaxResponse: FixedTaxResponse = {} as FixedTaxResponse;

  constructor (private apiService: ApiService) { }

  public getFixedTaxById(fixedTaxId: string) {
    return from(this.apiService.get('fixedtaxes/' + fixedTaxId))
  }

  public getFixedTax(fixedTaxId: string) {
    this.getFixedTaxById(fixedTaxId);
    console.log(this.fixedTaxResponse);
    return this.fixedTaxResponse;
  }

  public updateFixedTax(requestObj: FixedTaxResponse) {
    return from(this.apiService.put('fixedtaxes/' + requestObj.id, requestObj));
  }

  public createFixedTax(requestObj: FixedTaxResponse) {
    console.log(requestObj);
    return from(this.apiService.post('fixedtaxes/', requestObj));
  }

}
