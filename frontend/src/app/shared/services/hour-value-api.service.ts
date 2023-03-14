import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { HourValueResponse } from './response/hourvalue-response.types';

@Injectable({
  providedIn: 'root'
})
export class HourValueApiService {

  constructor (private apiService: ApiService) { }

  public getHourValueById(hourValueId: string): Promise<HourValueResponse> {
    return this.apiService.get('hourvalues/' + hourValueId);
  }

}
