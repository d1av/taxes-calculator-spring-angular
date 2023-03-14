import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-variable-tax',
  templateUrl: './variable-tax.component.html',
  styleUrls: [ './variable-tax.component.scss' ]
})
export class VariableTaxComponent {
  @Input() variableTaxId: string | undefined;
}
