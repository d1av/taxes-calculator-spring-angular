import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-hour-value',
  templateUrl: './hour-value.component.html',
  styleUrls: [ './hour-value.component.scss' ]
})
export class HourValueComponent {
  @Input() hourValueId: string | undefined;
}
