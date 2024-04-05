import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './perfil/list.component';
import { DetailComponent } from './perfil/detail.component';
import { CreateComponent } from './perfil/create.component';
import { UpdateComponent } from './perfil/update.component';
import { VacanteListComponent } from './perfil/vacante-list.component'; // Importa el componente de lista de vacantes
import { VacanteDetailComponent } from './perfil/vacante-detail.component'; // Importa el componente de detalles de vacante
import { VacanteCreateComponent } from './perfil/vacante-create.component'; // Importa el componente de creación de vacante
import { VacanteUpdateComponent } from './perfil/vacante-update.component'; // Importa el componente de actualización de vacante
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guard/Auth.guard';

const routes: Routes = [
  { path: '', component:LoginComponent, pathMatch: 'full' },
  {path: 'list', component: ListComponent, canActivate: [AuthGuard]}, // Redirige al listado de perfiles por defecto
  { path: 'detail/:id', component: DetailComponent, canActivate: [AuthGuard] },
  { path: 'create', component: CreateComponent , canActivate: [AuthGuard] },
  { path: 'update/:id', component: UpdateComponent , canActivate: [AuthGuard] },
  { path: ':perfilId/vacante', component: VacanteListComponent , canActivate: [AuthGuard] }, // Ruta para listar vacantes del perfil
  //{ path: ':perfilId/vacante/:vacanteId', component: VacanteDetailComponent }, // Ruta para detalles de vacante
  { path: ':perfilId/vacante/create', component: VacanteCreateComponent , canActivate: [AuthGuard] }, // Ruta para crear vacante
  { path: ':perfilId/:id/vacante/:vacanteId/update', component: VacanteUpdateComponent , canActivate: [AuthGuard] }, // Ruta para actualizar vacante
  { path: '**', redirectTo: '/perfil', pathMatch: 'full' }, // Redirige a la lista de perfiles si la ruta no coincide
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
