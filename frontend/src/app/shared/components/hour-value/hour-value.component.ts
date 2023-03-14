import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { HourValueApiService } from '../../services/hour-value-api.service';
import { HourValueResponse } from '../../services/response/hourvalue-response.types';

@Component({
  selector: 'app-hour-value',
  templateUrl: './hour-value.component.html',
  styleUrls: [ './hour-value.component.scss' ]
})
export class HourValueComponent implements OnChanges {
  @Input() hourValueId: string | undefined;

  hourValueData: HourValueResponse | undefined;

  constructor (private hourValueService: HourValueApiService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.hourValueId) {
      this.hourValueService.getHourValueById(this.hourValueId).subscribe(data => {
        this.hourValueData = data;
        console.log(this.hourValueData);
      });
    }
  }

  stringify(): string {
    return JSON.stringify(this.hourValueData);
  }
}
