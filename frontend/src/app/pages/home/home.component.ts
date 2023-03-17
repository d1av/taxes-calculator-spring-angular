import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TotalTaxResponse } from 'src/app/shared/services/response/total-tax-response.type';
import { TotalTaxApiService } from 'src/app/shared/services/total-tax-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.scss' ]
})
export class HomeComponent implements OnInit {

  isDisabled: boolean = false;

  totalTaxResponse: TotalTaxResponse | undefined;

  @Output() buttonClick = new EventEmitter();

  constructor (private totalTaxService: TotalTaxApiService) { }

  ngOnInit(): void {
    this.callIdsFromApi().then((data) => {
      this.totalTaxResponse = data;
    });
  }

  async callIdsFromApi(): Promise<any> {
    return await this.totalTaxService.getTotalTax();
  }

  sendClick() {
    this.buttonClick.emit();
  }

  theButtonIsDisabled(status: boolean) {
    this.waitMiliseconds(1000);
    this.isDisabled = status;
    return status;
  }

  waitMiliseconds(millisec: number) {
    return new Promise(resolve => {
      setTimeout(() => { resolve(''); }, millisec);
    });
  }

}
