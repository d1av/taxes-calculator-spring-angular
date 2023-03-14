import { Component, OnInit } from '@angular/core';
import { HourValueApiService } from 'src/app/shared/services/hour-value-api.service';
import { MonthlyResponse } from 'src/app/shared/services/response/monthly.types';

@Component({
  selector: 'app-monthly',
  templateUrl: './monthly.component.html',
  styleUrls: [ './monthly.component.scss' ]
})
export class MonthlyComponent implements OnInit {

  monthlyData: MonthlyResponse | undefined;

  constructor (private hourValueService: HourValueApiService) { }

  ngOnInit(): void {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.hourValueService.getMonthlyHourValue(userId).subscribe(data => {
        this.monthlyData = data;
        console.log(data);
      });
    }
  }

  stringify(): string {
    return JSON.stringify(this.monthlyData);
  }

}
