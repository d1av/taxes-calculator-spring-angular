import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthApiService } from 'src/app/shared/authentication/auth-api.service';
import RegisterRequest from 'src/app/shared/authentication/models/register-request.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: [ './register.component.scss' ]
})
export class RegisterComponent {
  public registerForm: FormGroup;

  constructor (
    private fb: FormBuilder,
    private _authApiService: AuthApiService,
    private toastr: ToastrService
  ) {
    this.registerForm = this.fb.group({
      name: [ '', Validators.required ],
      password: [ '', [ Validators.required, Validators.minLength(4) ] ]
    });
  }

  public getReferences(nameField: string): AbstractControl {
    return this.registerForm.controls[ nameField ];
  }

  public async submit(): Promise<void> {

    if (this.registerForm.invalid) {
      this.toastr.error("Preecha os dados corretamente!");
      return;
    }

    let bodyData: RegisterRequest = {
      name: this.registerForm.value.name,
      password: this.registerForm.value.password
    };


    try {
      await this._authApiService.register(bodyData);
    } catch (error: any) {
      const errorMessage = error?.error?.error || 'Error ao realizar Cadastro';
      this.toastr.error(errorMessage);
    }

  }
}
