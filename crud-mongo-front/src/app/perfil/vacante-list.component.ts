import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { Vacante } from '../model/vacante';
import { VacanteService } from '../services/vacante.service';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-vacante-list',
  templateUrl: './vacante-list.component.html',
  styleUrls: ['./vacante-list.component.css']
})
export class VacanteListComponent{

  nombreVacante: string = '';
  preguntas: string[] = [];
  respuestas: string[][] = [[]];
  puntuaciones: number[][] = [[]];

  trackByFn(index: number, item: any) {
    return index; // o podrías usar un identificador único si lo tienes
  }

  agregarPregunta(): void {
    this.preguntas.push('');
    this.respuestas.push([]);
    this.puntuaciones.push([]);
  }

  agregarRespuesta(indexPregunta: number): void {
    this.respuestas[indexPregunta].push('');
    this.puntuaciones[indexPregunta].push(0); // Inicializar la puntuación en 0
  }

  eliminarRespuesta(indexPregunta: number, indexRespuesta: number): void {
    this.respuestas[indexPregunta].splice(indexRespuesta, 1);
    this.puntuaciones[indexPregunta].splice(indexRespuesta, 1);
  }

  eliminarPregunta(indexPregunta: number): void {
    this.preguntas.splice(indexPregunta, 1); // Eliminar la pregunta del array de preguntas
    this.respuestas.splice(indexPregunta, 1); // Eliminar las respuestas asociadas a la pregunta
    this.puntuaciones.splice(indexPregunta, 1); // Eliminar las puntuaciones asociadas a la pregunta
}


  guardarVacante(): void {
    // Aquí puedes enviar la información de la vacante al backend para ser almacenada
    console.log('Vacante guardada:', this.nombreVacante);
    console.log('Preguntas:', this.preguntas);
    console.log('Respuestas:', this.respuestas);
    console.log('Puntuaciones:', this.puntuaciones);
    // Lógica para enviar la información al backend
  }

}
