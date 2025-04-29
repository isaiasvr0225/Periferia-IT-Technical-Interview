import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule} from '@angular/forms'
import { Task } from '../../types/task';
import { ButtonFormComponent } from '../button-form/button-form.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faArrowUp, faPlus, IconDefinition } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-add-task',
  standalone: true,
  imports: [CommonModule, FormsModule, ButtonFormComponent, FontAwesomeModule],
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.scss'
})
export class AddTaskComponent {
  @Output() onAddTask = new EventEmitter<Task>()

  taskTitle: string = '';
  taskDescription: string = '';
  showForm: boolean = false;
  faPlus: IconDefinition = faPlus;
  faArrowUp: IconDefinition = faArrowUp;

  onSubmit(){
    if(!this.taskTitle || !this.taskDescription){
      alert("Add a title.");
      return;
    }

    const newTask = {
      title: this.taskTitle,
      description: this.taskDescription
    }

    this.onAddTask.emit(newTask);
    this.taskTitle = '';
    this.taskDescription = '';
  }

  showAddForm(show: boolean){
    this.showForm = show;
  }
}
