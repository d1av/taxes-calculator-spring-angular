import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthApiService } from 'src/app/shared/authentication/auth-api.service';
import RegisterRequest from 'src/app/shared/authentication/models/register-request.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public registerForm: FormGroup = new FormGroup({});

  constructor (
    private fb: FormBuilder,
    private _authApiService: AuthApiService,
    private toastr: ToastrService
  ) {
    this.initializeForm();
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.registerForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  // public getReferences(nameField: string): AbstractControl {
  //   return this.registerForm.controls[ nameField ];
  // }

  public getMaskAllLetters(times: number): string {
    let stringOfA = '';
    for (let i = 0; i < times; i++) {
      stringOfA = stringOfA + 'A';
    }
    return stringOfA;
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
      let errorMessage = error.error?.errors[0]?.message || 'Erro ao realizar Cadastro';
      if ("Please select another username." === error.error?.errors[0]?.message) {
        errorMessage = "Selecione outro nome de usuário.";
      }
      if ("'name' must be between 6 and 200 characters" === error.error?.errors[0]?.message) {
        errorMessage = "O nome deve ter entre 6 e 200 caracteres.";
      }
      if ("'password' must be between 6 and 255 characters"=== error.error?.errors[0]?.message) {
        errorMessage = "A senha deve ter entre 6 e 255 caracteres.";
      }
      this.toastr.error(errorMessage);
    }

  }

  get name() { return this.registerForm.get('name'); }
  get password() { return this.registerForm.get('password'); }
}
