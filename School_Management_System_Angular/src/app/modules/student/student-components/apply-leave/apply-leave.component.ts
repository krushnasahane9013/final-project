import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../../student-service/student.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-apply-leave',
  templateUrl: './apply-leave.component.html',
  styleUrls: ['./apply-leave.component.css'] // Fix styleUrl to styleUrls
})
export class ApplyLeaveComponent implements OnInit {

  isSpinning = false;
  validationForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private studentService: StudentService,
    private snackbar: MatSnackBar,
    private router: Router // Fix import from '@angular/router'
  ) { }

  ngOnInit(): void {
    this.validationForm = this.fb.group({
      subject: [null, [Validators.required]],
      body: [null, [Validators.required]]
    });
  }

  applyLeave() {
    this.isSpinning = true;
    console.log(this.validationForm.value);
    this.studentService.applyLeave(this.validationForm.value).subscribe(
      (res) => {
        console.log(res);
        this.isSpinning = false;
        if (res.id !== null) {
          this.snackbar.open('Leave Submitted successfully', 'SUCCESS', {
            duration: 5000
          });
          this.router.navigateByUrl('student/dashboard');
        } else {
          this.snackbar.open('Something went wrong', 'ERROR', {
            duration: 5000
          });
        }
      }
    );
  }
}
