import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './pages/about/about.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AuthGuard } from './shared/authentication/auth.guard';
import { HomeModalComponent } from './shared/components/home-modal/home-modal.component';

const routes: Routes = [
  {
    path: '',
    canActivate: [ AuthGuard ],
    loadChildren: () =>
      import('./pages/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: 'hourvalue',
    canActivate: [ AuthGuard ],
    loadChildren: () =>
      import('./pages/monthly/monthly.module').then((m) => m.MonthlyModule),
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./pages/auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'about',
    canActivate: [ AuthGuard ],
    component: AboutComponent
  },
  {
    path: 'test',
    component: HomeModalComponent
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
