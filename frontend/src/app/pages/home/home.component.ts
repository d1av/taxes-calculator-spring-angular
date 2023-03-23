import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HomeModalComponent } from 'src/app/shared/components/home-modal/home-modal.component';
import { TotalTaxResponse } from 'src/app/shared/services/response/total-tax-response.type';
import { TotalTaxApiService } from 'src/app/shared/services/total-tax-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.scss' ]
})
export class HomeComponent implements OnInit {


  isDisabled: boolean = true;

  totalTaxResponse: TotalTaxResponse | undefined;

  localStorageHourValueId: string | undefined | null;
  localStorageFixedTaxId: string | null | undefined;
  localStorageVariableTaxId: string | undefined | null;

  constructor (private totalTaxService: TotalTaxApiService, private router: Router, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.callIdsFromApi().then((data) => {
      this.totalTaxResponse = data;
      this.checkLocalStorage();
    });
    this.showMonthlyValueOrNot();
    
    if (!localStorage.getItem("tutorialDone")) {
      this.openHelpModal();
      localStorage.setItem("tutorialDone", "true");
    }
  }

  async callIdsFromApi(): Promise<any> {
    return await this.totalTaxService.getTotalTax();
  }

  sendClick() {
    this.router.navigateByUrl('/hourvalue/monthly');
  }

  getFixedTaxId($event: any) {
    this.localStorageFixedTaxId = $event;
    this.showMonthlyValueOrNot();
  }
  getHourValueId($event: any) {
    this.localStorageHourValueId = $event;
    this.showMonthlyValueOrNot();
  }
  getVariableTaxId($event: any) {
    this.localStorageVariableTaxId = $event;
    this.showMonthlyValueOrNot();
  }

  showMonthlyValueOrNot() {
    this.checkLocalStorage();
    if (this.localStorageFixedTaxId != null && this.localStorageHourValueId != null && this.localStorageVariableTaxId != null) {
      this.isDisabled = false;
    }
  }

  checkLocalStorage() {
    this.localStorageFixedTaxId = localStorage.getItem('fixedTaxId');
    this.localStorageHourValueId = localStorage.getItem('hourValueId');
    this.localStorageVariableTaxId = localStorage.getItem('variableTaxId');
  }

  openHelpModal() {
    this.modalService.open(HomeModalComponent);
  }
}
