import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { ApiService } from './api.service';
import { HourValueResponse } from './response/hourvalue-response.types';
import { MonthlyResponse } from './response/monthly.types';

@Injectable({
  providedIn: 'root'
})
export class HourValueApiService {

  constructor (private apiService: ApiService) { }

  updateHourValue(requestObj: HourValueResponse): Observable<HourValueResponse> {
    return from(this.apiService.put('hourvalues/' + requestObj.id, requestObj));
  }

  createHourValue(requestObj: HourValueResponse): Observable<HourValueResponse> {
    return from(this.apiService.post('hourvalues/', requestObj));
  }

  public getHourValueById(hourValueId: string): Observable<HourValueResponse> {
    return from(this.apiService.get('hourvalues/' + hourValueId));
  }

  public getMonthlyHourValue(userId: string): Observable<MonthlyResponse> {
    return from(this.apiService.get('hourvalues/monthly/' + userId));
  }
}
