import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { VariableTaxResponse } from '../../services/response/variabletax-response.type';
import { VariableTaxApiService } from '../../services/variable-tax-api.service';

@Component({
  selector: 'app-variable-tax',
  templateUrl: './variable-tax.component.html',
  styleUrls: [ './variable-tax.component.scss' ]
})
export class VariableTaxComponent implements OnChanges {
  @Input() variableTaxId: string | undefined;

  variableTaxData: VariableTaxResponse | undefined;
  variableTaxForm: FormGroup = new FormGroup({});

  isDisabled: boolean = false;

  constructor (private variableTaxService: VariableTaxApiService) { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if (this.variableTaxId) {
      this.variableTaxService.getVariableTaxById(this.variableTaxId).subscribe(data => {
        this.variableTaxData = data;
      });
    }
  }

  initializeForm(): void {
    const aTax = this.variableTaxData;
    this.variableTaxForm = new FormGroup({
      creditCard: new FormControl(aTax?.creditCard, [ Validators.required, Validators.min(1), Validators.max(9999999999) ]),
      dentalShop: new FormControl(aTax?.dentalShop, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      prosthetist: new FormControl(aTax?.prosthetist, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      travel: new FormControl(aTax?.travel, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      weekend: new FormControl(aTax?.weekend, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
    });
  }

  updateVariableTax() {
    const requestObj: VariableTaxResponse = {
      ...this.variableTaxForm.value,
      userId: localStorage.getItem('userId'),
      id: this.variableTaxId
    };
    this.isDisabled = true;
    this.variableTaxService.updateVariableTax(requestObj).subscribe(data => {
      console.log(data);
      this.variableTaxData = data;
      this.isDisabled = false;
    });
  }

  createVariableTax() {
    const requestObj: VariableTaxResponse = {
      ...this.variableTaxForm.value,
      userId: localStorage.getItem('userId'),
    };
    this.isDisabled = true;
    this.variableTaxService.createVariableTax(requestObj).subscribe(data => {
      console.log(data);
      this.variableTaxData = data;
      this.isDisabled = false;
    });
  }

  get dentalShop() { return this.variableTaxForm.get('dentalShop'); }
  get creditCard() { return this.variableTaxForm.get('creditCard'); }
  get prosthetist() { return this.variableTaxForm.get('prosthetist'); }
  get travel() { return this.variableTaxForm.get('travel'); }
  get weekend() { return this.variableTaxForm.get('weekend'); }

}
