import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../../student-service/student.service';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css'] // Corrected 'styleUrl' to 'styleUrls'
})
export class UpdateStudentComponent implements OnInit {
  student: any;
  validationForm: FormGroup;
  isSpinning = false;

  CLASS: string[] = ["Play", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th"];
  GENDER: string[] = ["Male", "Female", "Not Specified"];

  email: string | undefined; // Declare the email property
  date: Date | undefined;
  address: string | undefined;
  name: string | undefined;
  fathername: string | undefined; // Declare the fathername property
  mothername: string | undefined; // Declare the mothername property
  studentClass: string | undefined; // Declare the studentClass property
  gender: string | undefined;


  constructor(
    private service: StudentService,
    private activatedRoute: ActivatedRoute, // Corrected 'activateRoute' to 'activatedRoute'
    private fb: FormBuilder,
    private snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.validationForm = this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      fatherName: ['', Validators.required],
      motherName: ['', Validators.required],
      studentClass: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      address: ['', Validators.required],
      gender: ['', Validators.required]
    });

    this.getStudentById();
  }

  getStudentById() {
    this.service.getStudentById().subscribe(
      (res) => {
        console.log(res);
        const student = res.studentDto;
        this.validationForm.patchValue(student);
      }
    );
  }

  updateStudent() {
    this.isSpinning = true;
    this.service.updateStudent(this.validationForm.value).subscribe(
      (res) => {
        console.log(res);
        this.isSpinning = false;
        if (res.id != null) {
          this.snackbar.open('Record updated successfully.', 'Close', { duration: 5000 });
          this.getStudentById();
        } else {
          this.snackbar.open('Student not found.', 'Close', { duration: 5000 });
        }
      }
    );
  }
}
