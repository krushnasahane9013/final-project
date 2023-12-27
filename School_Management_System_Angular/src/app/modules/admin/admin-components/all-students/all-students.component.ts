import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router'; // Import Router from '@angular/router'

@Component({
  selector: 'app-all-students',
  templateUrl: './all-students.component.html',
  styleUrls: ['./all-students.component.css'] // Fix the styleUrl to styleUrls
})
export class AllStudentsComponent implements OnInit {

  students: any;
  fathername:string|undefined;
  mothername:string|undefined;
  

  constructor(private service: AdminService, private snackbar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getAllStudents();
  }

  getAllStudents() {
    this.service.getAllStudents().subscribe((res) => {
      console.log(res);
      this.students = res;
    });
  }

  deleteStudent(studentId: number) {
    console.log(studentId);
    this.service.deleteStudent(studentId).subscribe((res) => {
      console.log(res);
      this.getAllStudents();
      this.snackbar.open("Student deleted successfully", "Close", { duration: 5000 });
    });
  }

  navigateToFee(route: string): void {
    // Assuming you have a route defined for the fee page, navigate to it
    this.router.navigate([route]);
  }
  
  navigateToUpdate(route: string): void {
    // Assuming you have a route defined for the student update page, navigate to it
    this.router.navigate([route]);
  }
  
}
