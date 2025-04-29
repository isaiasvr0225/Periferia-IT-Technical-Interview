import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPlus, IconDefinition } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-button-form',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './button-form.component.html',
  styleUrl: './button-form.component.scss'
})
export class ButtonFormComponent {
  @Input() icon: IconDefinition = faPlus;
  @Input() color: string = '';
  @Output() isFormShowing = new EventEmitter();

  showForm(){
    this.isFormShowing.emit();
  }
}
