import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './student-components/dashboard/dashboard.component';
import { StudentGuard } from '../../auth/guards/student-guard/student.guard';
import { ApplyLeaveComponent } from './student-components/apply-leave/apply-leave.component';
import { GetAllLeavesComponent } from './student-components/get-all-leaves/get-all-leaves.component';
import { UpdateStudentComponent } from './student-components/update-student/update-student.component';
import { PayFeeComponent } from '../admin/admin-components/pay-fee/pay-fee.component';
import { AdminGuard } from '../../auth/guards/admin-guard/admin.guard';

const routes: Routes = [
  {path:"dashboard",component:DashboardComponent,canActivate:[StudentGuard]},
  {path:"leave",component:ApplyLeaveComponent,canActivate:[StudentGuard]},
  {path:"leaves",component:GetAllLeavesComponent,canActivate:[StudentGuard]},
 

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
