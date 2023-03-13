import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VariableTaxComponent } from './variable-tax.component';

describe('VariableTaxComponent', () => {
  let component: VariableTaxComponent;
  let fixture: ComponentFixture<VariableTaxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VariableTaxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VariableTaxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
