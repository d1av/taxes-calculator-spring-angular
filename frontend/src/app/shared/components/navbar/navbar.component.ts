import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthApiService } from '../../authentication/auth-api.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: [ './navbar.component.scss' ]
})
export class NavbarComponent {

  @Input() isLogged?: boolean;

  constructor (private authApi: AuthApiService, private router: Router) { }

  logOut(): void {
    this.authApi.logout();
    this.isLogged = false;
    alert('Deslogado com sucesso.');
  }

}
