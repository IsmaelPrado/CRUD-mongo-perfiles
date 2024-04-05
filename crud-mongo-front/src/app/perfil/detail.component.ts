import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../services/perfil.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Perfil } from '../model/perfil';
import { Vacante } from '../model/vacante';
import { VacanteService } from '../services/vacante.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  perfil: Perfil | undefined;
  vacantes: Vacante[] = [];

  constructor(
    private perfilService: PerfilService,
    private vacanteService: VacanteService,
    private toast: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void{
    const id = this.activatedRoute.snapshot.params['id'];
    this.perfilService.detail(id).subscribe(
      data => {
        this.perfil = data;
        console.log(this.perfil);
        //this.getVacantes(id);
      },
      err => {
        this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['']);
      }
    )
  }

  /*getVacantes(id: number): void {
    this.vacanteService.listByPerfilId(id).subscribe(
      data => {
        this.vacantes = data;
        console.log('Vacantes del perfil', this.vacantes);
      },
      err => {
        this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'});
      }
    );
  }*/

}
