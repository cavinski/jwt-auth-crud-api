import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class TaskService {

  private api = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) {}

  getTasks(): Observable<any> {
    return this.http.get(`${this.api}/tasks`);
  }

  createTask(task: any) {
    return this.http.post(`${this.api}/tasks`, task);
  }

  updateTask(id: number, task: any) {
    return this.http.put(`${this.api}/tasks/${id}`, task);
  }

  deleteTask(id: number) {
    return this.http.delete(`${this.api}/tasks/${id}`);
  }

}
