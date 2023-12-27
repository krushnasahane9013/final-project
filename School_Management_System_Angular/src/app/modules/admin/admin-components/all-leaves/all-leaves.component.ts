import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-all-leaves',
  templateUrl: './all-leaves.component.html',
  styleUrls: ['./all-leaves.component.css'] // Corrected 'styleUrl' to 'styleUrls'
})
export class AllLeavesComponent implements OnInit {
  isSpinning = false;
  leaves: any;

  constructor(
    private adminService: AdminService,
    private snackbar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getAllLeaves();
  }

  getAllLeaves() {
    this.isSpinning = true;
    this.adminService.getAllAppliedLeaves().subscribe(
      (res) => {
        console.log(res);
        this.isSpinning = false;
        this.leaves = res;
      }
    );
  }

  changeLeaveStatus(leaveId: number, status: string) {
    this.isSpinning = true;
    this.adminService.changeLeaveStatus(leaveId, status).subscribe(
      (res) => {
        console.log(res);
        this.isSpinning = false;
        if (res.id != null) {
          this.snackbar.open('Leave approved successfully', 'Close', {
            duration: 5000
          });
          this.getAllLeaves();
        } else {
          this.snackbar.open('Something went wrong', 'ERROR', { duration: 5000 });
        }
      }
    );
  }
}
