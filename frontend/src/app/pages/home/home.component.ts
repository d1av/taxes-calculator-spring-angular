import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { HomeModalComponent } from 'src/app/shared/components/home-modal/home-modal.component';
import { TotalTaxResponse } from 'src/app/shared/services/response/total-tax-response.type';
import { TotalTaxApiService } from 'src/app/shared/services/total-tax-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.scss' ]
})
export class HomeComponent implements OnInit, OnChanges {
  isDisabled: boolean = true;

  totalTaxResponse: TotalTaxResponse | undefined;

  localStorageHourValueId: string | undefined | null;
  localStorageFixedTaxId: string | null | undefined;
  localStorageVariableTaxId: string | undefined | null;

  constructor (private totalTaxService: TotalTaxApiService, private router: Router,
    private modalService: NgbModal, private toastr: ToastrService) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.showMonthlyValueOrNot();
  }

  ngOnInit(): void {
    this.callIdsFromApi().then((data) => {
      this.totalTaxResponse = data;
      this.checkLocalStorage();
      this.showMonthlyValueOrNot();
    });

    if (!localStorage.getItem("tutorialDone")) {
      this.openHelpModal();
      localStorage.setItem("tutorialDone", "true");
    }
    this.showMonthlyValueOrNot();
  }

  async callIdsFromApi(): Promise<any> {
    return await this.totalTaxService.getTotalTax();
  }

  sendClick() {
    this.router.navigateByUrl('/hourvalue/monthly');
  }

  getFixedTaxId($event: string) {
    this.localStorageFixedTaxId = $event;
    this.showMonthlyValueOrNot();
    this.toastr.success("A Taxa fixa foi atualizada com sucesso.", "Atualizado!", { timeOut: 2000 });
  }
  getHourValueId($event: string) {
    this.localStorageHourValueId = $event;
    this.showMonthlyValueOrNot();
    this.toastr.success("Os dias trabalhados e o salário esperado foram atualizados com sucesso.", "Atualizado!", { timeOut: 2000 });
  }
  getVariableTaxId($event: string) {
    this.localStorageVariableTaxId = $event;
    this.showMonthlyValueOrNot();
    this.toastr.success("A Taxa Variavel foi atualizada com sucesso.", "Atualizado!", { timeOut: 2000 });
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
