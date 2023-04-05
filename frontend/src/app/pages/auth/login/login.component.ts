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
      let errorMessage = error.error?.errors[0]?.message || 'Error ao realizar Login';

      if ("Wrong user or password" === error.error?.errors[0]?.message) {
        errorMessage = "Usuário ou senha errado.";
      }
      if ("Please check the username or password." === error.error?.errors[0]?.message) {
        errorMessage = "Usuário ou senha errado.";
      }
      this.toastrService.error(errorMessage);
    }

  }

  public getMaskAllLetters(times: number): string {
    let stringOfA = '';
    for (let i = 0; i < times; i++) {
      stringOfA = stringOfA + 'A';
    }
    return stringOfA;
  }

  get name() { return this.loginForm.get('name'); }
  get password() { return this.loginForm.get('password'); }
}
