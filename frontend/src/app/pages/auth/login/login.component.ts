import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthApiService } from 'src/app/shared/authentication/auth-api.service';
import AuthRequest from 'src/app/shared/authentication/models/auth-request.types';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.scss' ]
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup = new FormGroup({});

  constructor (
    private toastrService: ToastrService,
    private _authApiService: AuthApiService,
  ) {
    this.initializeForm();
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  public initializeForm(): void {
    this.loginForm = new FormGroup({
      name: new FormControl('', [ Validators.required ]),
      password: new FormControl('', [ Validators.required ])
    });
  }


  public getReferences(nameField: string): AbstractControl {
    return this.loginForm.controls[ nameField ];
  }

  public async submit(): Promise<void> {
    if (this.loginForm.invalid) {
      this.toastrService.error("Preecha os dados corretamente!");
      return;
    }

    let bodyData: AuthRequest = {
      name: this.loginForm.value.name,
      password: this.loginForm.value.password
    };

    try {
      await this._authApiService.login(bodyData);
    } catch (error: any) {
      const errorMessage = error?.error?.error || 'Error ao realizar Login';
      this.toastrService.error(errorMessage);
    }

  }

  get name() { return this.loginForm.get('name'); }
  get password() { return this.loginForm.get('password'); }
}
