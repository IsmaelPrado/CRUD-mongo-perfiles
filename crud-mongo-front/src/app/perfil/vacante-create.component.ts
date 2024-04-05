import { Component, OnInit } from '@angular/core';
import { Vacante } from '../model/vacante';
import { PerfilService } from '../services/perfil.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { VacanteService } from '../services/vacante.service';
import { Route } from '@angular/router';

@Component({
  selector: 'app-vacante-create',
  templateUrl: './vacante-create.component.html',
  styleUrls: ['./vacante-create.component.css']
})
export class VacanteCreateComponent implements OnInit {

  name!: string;
  perfilId!: number;

  constructor(
    private vacanteService: VacanteService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.perfilId = +params['perfilId']; // Obtener el perfilId de los parámetros de la URL y convertirlo a número
    });
  }

  onCreate(): void{
    const vacante = new Vacante(this.name, this.perfilId);
    this.vacanteService.create(this.perfilId, vacante).subscribe(
      data => {
        this.toast.success(data.message, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['']);
      },
      err => {
        this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'});
      }
    )
  }
}
