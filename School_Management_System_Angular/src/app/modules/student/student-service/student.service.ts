import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/stroage/storage.service'; // Corrected the typo in "stroage" to "storage"

const BASIC_URL = ["http://localhost:8080/"]; // Removed unnecessary square brackets

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getStudentById(): Observable<any> {
    return this.http.get<any>(`${BASIC_URL}api/student/${StorageService.getUserId()}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  applyLeave(studentLeaveDto: any): Observable<any> {
    return this.http.post<[]>(`${BASIC_URL}api/student/leave`, studentLeaveDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllAppliedLeaveByStudentId(): Observable<any> {
    return this.http.get<any>(`${BASIC_URL}api/student/leave/${StorageService.getUserId()}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  updateStudent(studentLeaveDto: any): Observable<any> {
    return this.http.put<any>(`${BASIC_URL}api/student/${StorageService.getUserId()}`, studentLeaveDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization', `Bearer ${StorageService.getToken()}` // Add a space after "Bearer"
    );
  }
}
