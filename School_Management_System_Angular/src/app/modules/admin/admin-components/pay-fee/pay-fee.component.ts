import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pay-fee',
  templateUrl: './pay-fee.component.html',
  styleUrls: ['./pay-fee.component.css']
})
export class PayFeeComponent implements OnInit {

  studentId: number;

  validationForm: FormGroup;
  isSpinning: boolean = false;
  months: string[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  amount: number | undefined;
  givenBy: string | undefined;
  description: string | undefined;
  selectedMonth: string | undefined;

  constructor(
    private service: AdminService,
    private activatedroute: ActivatedRoute,
    private fb: FormBuilder,
    private snackbar: MatSnackBar
  ) {
    this.studentId = this.activatedroute.snapshot.params['studentId'];
  }

  ngOnInit() {
    this.validationForm = this.fb.group({
      amount: [null, [Validators.required, Validators.min(0)]],
      month: [null, Validators.required],
      givenBy: [null, Validators.required],
      description: [null, Validators.required]
    });
  }

  payFee() {
    this.service.payFee(this.studentId, this.validationForm.value).subscribe(
      (res) => {
        console.log(res);
        if (res.id != null) {
          this.snackbar.open("Fee Paid successfully", "Close", { duration: 5000 });
        } else {
          this.snackbar.open("Something went wrong", "Close", { duration: 5000 });
        }
      },
      (error) => {
        console.error(error);
        this.snackbar.open("An error occurred", "Close", { duration: 5000 });
      }
    );
  }
}
