import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-student',
  templateUrl: './post-student.component.html',
  styleUrls: ['./post-student.component.css']
})
export class PostStudentComponent {

  CLASS: string[] = ["Play", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th"];
  GENDER: string[] = ["Male", "Female", "Not Specified"];
  isSpinning: boolean = false;
  validationForm: FormGroup;
  picker: any;
  dateOfBirth: Date | undefined;
  address: string | undefined;
  name: string | undefined;
  mothername: string | undefined;
  fathername: string | undefined;
  class: string | undefined;







  constructor(
    private service: AdminService,
    private fb: FormBuilder,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.validationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      password: ['', Validators.required],
      checkPassword: ['', [Validators.required, this.confirmationValidator]],
      fathername: ['', Validators.required],
      mothername: ['', Validators.required],
      studentClass: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      address: ['', Validators.required],
      gender: ['', Validators.required]
    });
  }

  isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  postStudent(): void {
    this.service.addStudent(this.validationForm.value).subscribe(
      (res) => {
        console.log(res);
        if (res.id !== null) {
          this.snackbar.open("Student posted successfully", "Close", { duration: 5000 });
        } else {
          this.snackbar.open("Student already exists", "Close", { duration: 5000 });
        }
      });
  }

  private confirmationValidator(control: FormControl): { [s: string]: boolean } | null {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validationForm.get("password")?.value) {
      return { confirm: true, error: true };
    }
    return null;
  }
}
