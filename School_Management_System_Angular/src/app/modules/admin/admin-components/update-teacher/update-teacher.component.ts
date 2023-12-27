import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-teacher',
  templateUrl: './update-teacher.component.html',
  styleUrl: './update-teacher.component.css'
})
export class UpdateTeacherComponent {

  validationForm: FormGroup;
  GENDER: string[] = ["Male", "Female", "Not Specified"];
  isSpinning: boolean;
  picker: any; // You can adjust the type based on the type of 'picker'

  teacherId:number=this.activatedRoute.snapshot.params['teacherId']

  constructor(
    private service: AdminService,
    private fb: FormBuilder,
    private activatedRoute:ActivatedRoute,
    private snackbar:MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getTeacherById();
    this.validationForm = this.fb.group({
      name: ['', Validators.required],
      gender: ['', Validators.required],
      department: ['', Validators.required],
      qualification: ['', Validators.required],
      address: ['', Validators.required],
      dateOfBirth: ['', Validators.required], // Changed from dob to dateOfBirth
    });
  }

  getTeacherById(){
    this.service.getTeacherById(this.teacherId).subscribe((res)=>{
      const teacher=res.teacherDto;
      this.validationForm.patchValue(teacher);
      console.log(res);
    })
  }

  updateTeacher() {
    this.service.updateTeacher(this.teacherId,this.validationForm.value).subscribe(
      (res)=>{
        console.log(res)
        if(res.id!=null) {
          this.snackbar.open("Teacher updated successfully.","Close",{duration:5000});
        }else{
          this.snackbar.open("Teacher not found.","Close",{duration:5000}); 
        }
      }
    )
  }

}
