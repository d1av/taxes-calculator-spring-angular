import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
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
  @Output() localStorageFixedTaxId: EventEmitter<any> = new EventEmitter;


  public fixedTaxData: FixedTaxResponse | undefined;

  fixedTaxForm: FormGroup = new FormGroup({});

  @Output() isDisabled = new EventEmitter<boolean>();

  constructor (private fixedTaxService: FixedTaxApiService) {
  }
  ngOnInit(): void {
    this.initializeForm();
  }

  async ngOnChanges(changes: SimpleChanges): Promise<void> {
    if (this.fixedTaxId) {
      this.fixedTaxService.getFixedTaxById(this.fixedTaxId).subscribe((data: FixedTaxResponse) => {
        this.fixedTaxData = data;
        this.initializeForm();
      });
    };
  }


  initializeForm() {
    const aTax = this.fixedTaxData;
    this.fixedTaxForm = new FormGroup({
      regionalCouncil: new FormControl(aTax?.regionalCouncil, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      taxOverWork: new FormControl(aTax?.taxOverWork, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      incomeTax: new FormControl(aTax?.incomeTax, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      accountant: new FormControl(aTax?.accountant, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      dentalShop: new FormControl(aTax?.dentalShop, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      transport: new FormControl(aTax?.transport, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      food: new FormControl(aTax?.food, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      education: new FormControl(aTax?.education, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
      otherFixedCosts: new FormControl(aTax?.otherFixedCosts, [ Validators.required, Validators.min(0), Validators.max(9999999999) ])
    });
  }

  updateFixedTax() {
    const requestObj: FixedTaxResponse = {
      ...this.fixedTaxForm.value,
      userId: localStorage.getItem('userId'),
      id: this.fixedTaxId
    };

    this.fixedTaxService.updateFixedTax(requestObj).subscribe(data => {
      this.fixedTaxData = data;
    });
  }

  createFixedTax() {
    const requestObj: FixedTaxResponse = {
      ...this.fixedTaxForm.value,
      userId: localStorage.getItem('userId'),
    };
    this.fixedTaxService.createFixedTax(requestObj).subscribe(data => {
      this.fixedTaxData = data;
      this.fixedTaxId = data.id;
      localStorage.setItem('fixedTaxId', data.id);
      localStorage.setItem('userId', data.userId);
      this.localStorageFixedTaxId.emit(data.id);
    });
  }

  get regionalCouncil() { return this.fixedTaxForm.get('regionalCouncil'); }
  get taxOverWork() { return this.fixedTaxForm.get('taxOverWork'); }
  get incomeTax() { return this.fixedTaxForm.get('incomeTax'); }
  get accountant() { return this.fixedTaxForm.get('accountant'); }
  get dentalShop() { return this.fixedTaxForm.get('dentalShop'); }
  get transport() { return this.fixedTaxForm.get('transport'); }
  get food() { return this.fixedTaxForm.get('food'); }
  get education() { return this.fixedTaxForm.get('education'); }
  get otherFixedCosts() { return this.fixedTaxForm.get('otherFixedCosts'); }
  get userId() { return this.fixedTaxForm.get('userId'); }

}
