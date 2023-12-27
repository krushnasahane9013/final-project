import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../auth/services/stroage/storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] // Fix the property name to 'styleUrls'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
      private service: AuthService,
      private fb: FormBuilder,
      private router: Router,
      private snackbar: MatSnackBar // Fix the spelling to 'MatSnackBar'
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    console.log(this.loginForm.value);

    this.service.login(
      this.loginForm.get('email')!.value,
      this.loginForm.get('password')!.value,
    ).subscribe(
      (response) => {
        console.log(response);

        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("admin/dashboard");
        } else if (StorageService.isStudentLoggedIn) {
          this.router.navigateByUrl("student/dashboard");
        }
      }),
      (error) => {
        if (error.status == 406) {
          this.snackbar.open("User is not active", "Close", {
            duration: 5000
          });
        } else {
          this.snackbar.open("Bad Credentials", "Close", {
            duration: 5000
          });
        }
      }
  }
}
