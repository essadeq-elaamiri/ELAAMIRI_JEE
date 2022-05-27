import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerstableComponent } from './customerstable.component';

describe('CustomerstableComponent', () => {
  let component: CustomerstableComponent;
  let fixture: ComponentFixture<CustomerstableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerstableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerstableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
