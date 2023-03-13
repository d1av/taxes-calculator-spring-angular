import { Component, OnInit } from '@angular/core';
import { TotalTaxApiService } from 'src/app/shared/services/total-tax-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.scss' ]
})
export class HomeComponent implements OnInit {

  apiResponse: any;

  constructor (private totalTaxService: TotalTaxApiService) { }

  ngOnInit(): void {
    this.callIdsFromApi().then((data) => {
      this.apiResponse = data;
      console.log(this.apiResponse);
    });
  }

  async callIdsFromApi(): Promise<any> {
    return await this.totalTaxService.getTotalTax();
  }
}
