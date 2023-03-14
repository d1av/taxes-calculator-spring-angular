import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FixedTaxApiService } from '../../services/fixed-tax-api.service';
import { FixedTaxResponse } from '../../services/response/fixedtax-response.types';

@Component({
  selector: 'app-fixed-tax',
  templateUrl: './fixed-tax.component.html',
  styleUrls: [ './fixed-tax.component.scss' ]
})
export class FixedTaxComponent implements OnChanges {
  @Input() fixedTaxId: string | undefined;

  public fixedTaxData: FixedTaxResponse | undefined;

  constructor (private fixedTaxService: FixedTaxApiService) {

  }
  async ngOnChanges(changes: SimpleChanges): Promise<void> {
    if (this.fixedTaxId) {
      this.fixedTaxService.getFixedTaxById(this.fixedTaxId).subscribe(data => {
        this.fixedTaxData = data;
        console.log(this.fixedTaxData);
      });
    }
  }

  stringify(): string {
    return JSON.stringify(this.fixedTaxData);
  }

}
