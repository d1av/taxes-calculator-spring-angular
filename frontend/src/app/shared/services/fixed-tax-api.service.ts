import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { ApiService } from './api.service';
import { FixedTaxResponse } from './response/fixedtax-response.types';

@Injectable({
  providedIn: 'root'
})
export class FixedTaxApiService {

  constructor (private apiService: ApiService) { }

  public getFixedTaxById(fixedTaxId: string): Observable<FixedTaxResponse> {
    return from(this.apiService.get('fixedtaxes/' + fixedTaxId));
  }

}
