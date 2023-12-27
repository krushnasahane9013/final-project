import { Component } from '@angular/core';
import { StudentService } from '../../student-service/student.service';

@Component({
  selector: 'app-get-all-leaves',
  templateUrl: './get-all-leaves.component.html',
  styleUrl: './get-all-leaves.component.css'
})
export class GetAllLeavesComponent {

  isSpinning=false;
  leaves:any;

  constructor(
    private studentService:StudentService
  ){}

  ngOnInit():void {
    this.getAllLeaves();
  }

  getAllLeaves(){
    this.isSpinning=true;
    this.studentService.getAllAppliedLeaveByStudentId().subscribe((res)=>{
      console.log(res);
      this.isSpinning=false;
      this.leaves=res;
    })
  }
}
