import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { AdminGuard } from '../../auth/guards/admin-guard/admin.guard';
import { PostStudentComponent } from './admin-components/post-student/post-student.component';
import { AllStudentsComponent } from './admin-components/all-students/all-students.component';
import { UpdateStudentComponent } from './admin-components/update-student/update-student.component';
import { PayFeeComponent } from './admin-components/pay-fee/pay-fee.component';
import { AllLeavesComponent } from './admin-components/all-leaves/all-leaves.component';
import { GetAllTeachersComponent } from './admin-components/get-all-teachers/get-all-teachers.component';
import { UpdateTeacherComponent } from './admin-components/update-teacher/update-teacher.component';
import { PostTeacherComponent } from './admin-components/post-teacher/post-teacher.component';

const routes: Routes = [
  {path:"dashboard",component:DashboardComponent,canActivate:[AdminGuard]},
  {path:"student",component:PostStudentComponent,canActivate:[AdminGuard]},
  {path:"students",component:AllStudentsComponent,canActivate:[AdminGuard]},
  {path:"student/:studentId",component:UpdateStudentComponent,canActivate:[AdminGuard]},
  {path:"fee/:studentId",component:PayFeeComponent,canActivate:[AdminGuard]},
  {path:"leaves",component:AllLeavesComponent,canActivate:[AdminGuard]},
  {path:"teacher",component:PostTeacherComponent,canActivate:[AdminGuard]},
  {path:"teachers",component:GetAllTeachersComponent,canActivate:[AdminGuard]},
  {path:"teacher/:teacherId",component:UpdateTeacherComponent,canActivate:[AdminGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
