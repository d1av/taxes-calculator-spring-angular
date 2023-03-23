import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home-modal',
  templateUrl: './home-modal.component.html',
  styleUrls: [ './home-modal.component.scss' ]
})
export class HomeModalComponent {

  @Input() name: string | undefined;

  constructor (public activeModal: NgbActiveModal) { }

  dismissModal() {
    this.activeModal.dismiss('Cross click');
  }

  closeModal() {
    this.activeModal.close('Close click');
  }

}
