import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllLeavesComponent } from './get-all-leaves.component';

describe('GetAllLeavesComponent', () => {
  let component: GetAllLeavesComponent;
  let fixture: ComponentFixture<GetAllLeavesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetAllLeavesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetAllLeavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
