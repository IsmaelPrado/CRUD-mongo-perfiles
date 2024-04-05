import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../services/perfil.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Perfil } from '../model/perfil';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  id!: number;
  perfil: Perfil = {
    id: 0,
    name: '',
    desc: '',
    imageURL: '',
    habilidades: [], // Asegúrate de inicializar las propiedades según la estructura de Perfil
    experienciaLaboral: '',
    educacion: '',
    idiomas: [],
    ubicacion: '',
    disponibilidad: '',
    intereses: [],
    referencias: [],
    vacantes: []
  };

  constructor(
    private perfilService: PerfilService,
    private toast: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  onUpdate(): void {
    this.perfilService.update(this.id, this.perfil).subscribe(
      data => {
        this.toast.success(data.message, 'OK', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.router.navigate(['/list']);
      },
      err => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      }
    );
  }

  getProduct(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.perfilService.detail(this.id).subscribe(
      data => {
        this.perfil = data;
        console.log(this.perfil);
      },
      err => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.router.navigate(['/list']);
      }
    );
  }

  agregarHabilidad() {
    const nuevasHabilidades = [...this.perfil.habilidades, ''];
    this.perfil.habilidades = nuevasHabilidades;
  }
  
  eliminarHabilidad(index: number) {
    const nuevasHabilidades = this.perfil.habilidades.filter((_, i) => i !== index);
    this.perfil.habilidades = nuevasHabilidades;
  }

  trackByFn(index: number, item: any) {
    return index; // o podrías usar un identificador único si lo tienes
  }
  
  

  agregarReferencia() {
    const nuevasReferencias = [...this.perfil.referencias, ''];
    this.perfil.referencias = nuevasReferencias;
  }
  eliminarReferencia(index: number) {
    const nuevasReferencias = this.perfil.referencias.filter((_, i) => i !== index);
    this.perfil.referencias = nuevasReferencias;
  }
  agregarInteres() {
    const nuevosIntereses = [...this.perfil.intereses, ''];
    this.perfil.intereses = nuevosIntereses;
  }
  eliminarInteres(index: number) {
    const nuevosIntereses = this.perfil.intereses.filter((_, i) => i !== index);
    this.perfil.intereses = nuevosIntereses;
  }
  agregarIdioma() {
    const nuevosIdiomas = [...this.perfil.idiomas, ''];
    this.perfil.idiomas = nuevosIdiomas;
  }
  eliminarIdioma(index: number) {
    const nuevosIdiomas = this.perfil.idiomas.filter((_, i) => i !== index);
    this.perfil.idiomas = nuevosIdiomas;
  }

}
