import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FixedTaxApiService } from '../../services/fixed-tax-api.service';
import { FixedTaxResponse } from '../../services/response/fixedtax-response.types';

@Component({
  selector: 'app-fixed-tax',
  templateUrl: './fixed-tax.component.html',
  styleUrls: [ './fixed-tax.component.scss' ]
})
export class FixedTaxComponent implements OnChanges, OnInit {
  @Input() fixedTaxId: string | undefined;

  public fixedTaxData: FixedTaxResponse | undefined;

  fixedTaxForm: FormGroup = new FormGroup({});

  constructor (private fixedTaxService: FixedTaxApiService) {
  }
  ngOnInit(): void {
    this.initializeForm();
  }

  async ngOnChanges(changes: SimpleChanges): Promise<void> {
    if (this.fixedTaxId) {
      this.fixedTaxService.getFixedTaxById(this.fixedTaxId).subscribe(data => {
        this.fixedTaxData = data;
        console.log(this.fixedTaxData);
        this.initializeForm();
      });
    }
  }

  initializeForm() {
    const aTax = this.fixedTaxData;
    this.fixedTaxForm = new FormGroup({
      regionalCouncil: new FormControl(aTax?.regionalCouncil, Validators.required),
      taxOverWork: new FormControl(aTax?.taxOverWork, Validators.required),
      incomeTax: new FormControl(aTax?.incomeTax, Validators.required),
      accountant: new FormControl(aTax?.accountant, Validators.required),
      dentalShop: new FormControl(aTax?.dentalShop, Validators.required),
      transport: new FormControl(aTax?.transport, Validators.required),
      food: new FormControl(aTax?.food, Validators.required),
      education: new FormControl(aTax?.education, Validators.required),
      otherFixedCosts: new FormControl(aTax?.otherFixedCosts, Validators.required)
    });
  }

  stringify(): string {
    return JSON.stringify(this.fixedTaxData);
  }

  saveFixedTax() {
    console.log(this.fixedTaxForm.value);
  }

  updateFixedTax() {
    const requestObj: FixedTaxResponse = {
      ...this.fixedTaxForm.value,
      userId: localStorage.getItem('userId'),
      id: this.fixedTaxId
    };

    this.fixedTaxService.updateFixedTax(requestObj).subscribe(data => {
      console.log(data);
      this.fixedTaxData = data;
    });
  }

  createFixedTax() {
    const requestObj: FixedTaxResponse = {
      ...this.fixedTaxForm.value,
      userId: localStorage.getItem('userId')
    };

    this.fixedTaxService.createFixedTax(requestObj).subscribe(data => {
      console.log(data);
      this.fixedTaxData = data;
    });
  }

}
