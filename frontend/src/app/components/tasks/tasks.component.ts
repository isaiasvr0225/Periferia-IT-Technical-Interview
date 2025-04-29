import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Task } from '../../types/task';
import { CommonModule } from '@angular/common';
import { TaskItemComponent } from './task-item/task-item.component';
import { AddTaskComponent } from '../add-task/add-task.component';
import { JsonApiRequest } from '../../types/json-api';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [CommonModule, TaskItemComponent, AddTaskComponent],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.scss'
})
export class TasksComponent implements OnInit{
  tasks: Task[] = [];

  constructor(private taskService: TaskService){}

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks(){
    this.taskService.getAllTasks().subscribe((data) => {
      this.tasks = data.content;
    });
  }

  deleteTask(task: Task){
    this.taskService.removeTask(task).subscribe(() => {
      this.getAllTasks();
    });
  }

  toggleReady(task: Task){
    this.taskService.updateTaskStatus(task).subscribe(() => {
      this.getAllTasks();
    });
  }

  addTask(task: Task){

    const newTask: JsonApiRequest<Task> = {
      data: {
        type: 'tasks',
        attributes: task
      }
    };

    this.taskService.addTask(newTask).subscribe(() => {
      this.getAllTasks();
    });
  }
}
