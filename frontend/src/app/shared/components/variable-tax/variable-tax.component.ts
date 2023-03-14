import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
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

  teste: string = '';

  constructor (private variableTaxService: VariableTaxApiService) { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if (this.variableTaxId) {
      this.variableTaxService.getVariableTaxById(this.variableTaxId).subscribe(data => {
        this.variableTaxData = data;
        console.log(this.variableTaxData);
      });
    }
  }

  stringify(): string {
    return JSON.stringify(this.variableTaxData);
  }

}
