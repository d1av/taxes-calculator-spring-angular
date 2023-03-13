import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HourValueComponent } from './hour-value.component';

describe('HourValueComponent', () => {
  let component: HourValueComponent;
  let fixture: ComponentFixture<HourValueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HourValueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HourValueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
