import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

//Components
import { ListComponent } from './perfil/list.component';
import { DetailComponent } from './perfil/detail.component';
import { CreateComponent } from './perfil/create.component';
import { UpdateComponent } from './perfil/update.component';
import { MenuComponent } from './menu/menu.component';


//External
//import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { ToastrModule } from 'ngx-toastr';
import { PerfilService } from './services/perfil.service';
import { VacanteCreateComponent } from './perfil/vacante-create.component';
import { VacanteDetailComponent } from './perfil/vacante-detail.component';
import { VacanteListComponent } from './perfil/vacante-list.component';
import { VacanteUpdateComponent } from './perfil/vacante-update.component';
import { SearchFilterPipe } from './pipes/search-filter.pipe';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    DetailComponent,
    CreateComponent,
    UpdateComponent,
    MenuComponent,
    VacanteCreateComponent,
    VacanteDetailComponent,
    VacanteListComponent,
    VacanteUpdateComponent,
    SearchFilterPipe,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //SweetAlert2Module.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [PerfilService, SearchFilterPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
