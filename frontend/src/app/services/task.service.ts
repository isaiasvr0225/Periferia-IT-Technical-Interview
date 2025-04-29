import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page, Task } from '../types/task';
import { JsonApiRequest, JsonApiResponse } from '../types/json-api';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl: string = "http://localhost:8090/api/v1/tasks";

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(this.apiUrl);
  }

  removeTask(task: Task): Observable<Task>{
    return this.http.delete<Task>(`${this.apiUrl}/${task.id}`);
  }

  updateTaskStatus(task: Task): Observable<Task>{
    return this.http.put<Task>(`${this.apiUrl}/${task.id}/update-status?status=Completed`, null);
  }

  addTask(task: JsonApiRequest<Task>): Observable<JsonApiRequest<Task>>{
    return this.http.post<JsonApiRequest<Task>>(this.apiUrl, task);
  }
}
