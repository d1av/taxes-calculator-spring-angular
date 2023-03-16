import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HourValueApiService } from '../../services/hour-value-api.service';
import { HourValueResponse } from '../../services/response/hourvalue-response.types';

@Component({
  selector: 'app-hour-value',
  templateUrl: './hour-value.component.html',
  styleUrls: [ './hour-value.component.scss' ]
})
export class HourValueComponent implements OnChanges, OnInit {
  @Input() hourValueId: string | undefined;

  hourValueData: HourValueResponse | undefined;

  hourValueForm: FormGroup = new FormGroup({});

  isDisabled: boolean = false;

  constructor (private hourValueService: HourValueApiService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.hourValueId) {
      this.hourValueService.getHourValueById(this.hourValueId).subscribe(data => {
        this.hourValueData = data;
        this.initializeForm();
      });
    }
  }

  initializeForm(): void {
    const aTax = this.hourValueData;
    this.hourValueForm = new FormGroup({
      daysOfWork: new FormControl(aTax?.daysOfWork, [ Validators.required, Validators.min(1), Validators.max(31) ]),
      expectedSalary: new FormControl(aTax?.expectedSalary, [ Validators.required, Validators.min(0), Validators.max(9999999999) ]),
    });
  }

  updateHourValue() {
    const requestObj: HourValueResponse = {
      ...this.hourValueForm.value,
      userId: localStorage.getItem('userId'),
      personalHourValue: 0,
      id: this.hourValueId
    };
    this.isDisabled = true;
    this.hourValueService.updateHourValue(requestObj).subscribe(data => {
      console.log(data);
      this.hourValueData = data;
      this.isDisabled = false;
    });
  }

  createHourValue() {
    const requestObj: HourValueResponse = {
      ...this.hourValueForm.value,
      userId: localStorage.getItem('userId'),
      personalHourValue: 0,
    };
    this.isDisabled = true;
    this.hourValueService.createHourValue(requestObj).subscribe(data => {
      console.log(data);
      this.hourValueData = data;
      this.isDisabled = false;
    });
  }

  get daysOfWork() { return this.hourValueForm.get('daysOfWork'); }
  get expectedSalary() { return this.hourValueForm.get('expectedSalary'); }
  // get personalHourValue() { return this.hourValueForm.get('personalHourValue'); }

}
