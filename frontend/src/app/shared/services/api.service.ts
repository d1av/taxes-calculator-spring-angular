import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    protected http: HttpClient,
    @Inject('API_URL') private apiUrl: string
  ) { }

  public post(url: string, body: any): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.post(this.getUrl(url), body).subscribe({
        next: (v) => resolve(v),
        error: (v) => reject(v),
      });
    });
  }

  public get(url: string): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.get(this.getUrl(url)).subscribe({
        next: (v) => resolve(v),
        error: (e) => reject(e),
      });
    });
  }

  private getUrl(url: string): string {
    return `${this.apiUrl}/${url}`;
  }


  public put(url: string, body: any): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.put(this.getUrl(url), body).subscribe({
        next: (v) => resolve(v),
        error: (v) => reject(v)
      });
    })
  }
}
