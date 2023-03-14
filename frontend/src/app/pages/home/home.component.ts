import { Component, OnInit } from '@angular/core';
import { TotalTaxResponse } from 'src/app/shared/services/response/total-tax-response.type';
import { TotalTaxApiService } from 'src/app/shared/services/total-tax-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.scss' ]
})
export class HomeComponent implements OnInit {

  totalTaxResponse: TotalTaxResponse | undefined;

  constructor (private totalTaxService: TotalTaxApiService) { }

  ngOnInit(): void {
    this.callIdsFromApi().then((data) => {
      this.totalTaxResponse = data;
      console.log(this.totalTaxResponse);
    });
  }

  async callIdsFromApi(): Promise<any> {
    return await this.totalTaxService.getTotalTax();
  }
}
