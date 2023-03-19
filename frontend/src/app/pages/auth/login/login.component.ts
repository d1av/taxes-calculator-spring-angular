import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthApiService } from 'src/app/shared/authentication/auth-api.service';
import AuthRequest from 'src/app/shared/authentication/models/auth-request.types';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  public loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private _authApiService: AuthApiService
  ) {
    this.loginForm = this.fb.group({
      name: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  public getReferences(nameField: string): AbstractControl {
    return this.loginForm.controls[nameField]
  }

  public async submit(): Promise<void> {
    if (this.loginForm.invalid) {
      alert("Preecha os dados corretamente!")
      return;
    }

    let bodyData: AuthRequest = {
      name: this.loginForm.value.name,
      password: this.loginForm.value.password
    }

    try {
      await this._authApiService.login(bodyData)
    } catch (error: any) {
      const errorMessage = error?.error?.error || 'Error ao realizar Login';
      alert(errorMessage);
    }

  }
}
