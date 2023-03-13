import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FixedTaxComponent } from './fixed-tax.component';

describe('FixedTaxComponent', () => {
  let component: FixedTaxComponent;
  let fixture: ComponentFixture<FixedTaxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FixedTaxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FixedTaxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
