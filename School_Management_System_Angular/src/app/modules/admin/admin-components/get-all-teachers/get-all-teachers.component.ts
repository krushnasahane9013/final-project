import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router'; // Import Router from '@angular/router'
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-get-all-teachers',
  templateUrl: './get-all-teachers.component.html',
  styleUrls: ['./get-all-teachers.component.css'] // Fix the styleUrl to styleUrls
})
export class GetAllTeachersComponent implements OnInit {

  teachers: any;

  constructor(private service: AdminService, private snackbar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getAllTeachers();
  }

  getAllTeachers() {
    this.service.getAllTeachers().subscribe((res) => {
      console.log(res);
      this.teachers = res;
    });
  }

  deleteTeacher(teacherId: number) {
    console.log(teacherId);
    this.service.deleteTeacher(teacherId).subscribe((res) => {
      console.log(res);
      this.getAllTeachers();
      this.snackbar.open("Teacher deleted successfully", "Close", { duration: 5000 });
    });
  }

  navigateToUpdate(url: string, teacherId: number): void {
    this.router.navigate([url, teacherId]);
  }
}
