import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../services/perfil.service';
import { ToastrService } from 'ngx-toastr';
import { Perfil } from '../model/perfil';
import Swal from 'sweetalert2';
import { StorageService } from '../services/storage.service';
import { Router } from '@angular/router';
import { VacanteService } from '../services/vacante.service';
import { SearchFilterPipe } from '../pipes/search-filter.pipe';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  perfils: Perfil[] = [];
  originalPerfils: Perfil[] = [];
  showMessage: boolean = false; // Variable para gestionar la visibilidad del mensaje
  searchText: string = ''; // Propiedad para almacenar el texto de búsqueda
  perfilId: number = 1; 


  constructor(
    public perfilService: PerfilService,
    private toast: ToastrService,
    private storageService: StorageService,
    private router: Router,
    private vacanteService: VacanteService,
    private searchFilterPipe: SearchFilterPipe
  ) { }

  ngOnInit(): void {
    this.getProducts();
    console.log(this.perfils);
  }

  getProducts(): void {
    this.perfilService.list().subscribe(
      data => {
        this.perfils = data;
        this.originalPerfils = [...data]; // Copia original de los perfiles
        console.log(this.perfils);
        this.perfilService.finishLoading();
      },
      err => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.perfilService.finishLoading();
      }
    )
  }

  // Método para obtener las vacantes de un perfil específico


  onDelete(id:number): void{
    Swal.fire({
      title: 'Are you sure?',
      text: 'You cannot undo',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if(result.value){
        console.log('Deleted product' + id);
        this.perfilService.delete(id).subscribe(
          data => {
            this.toast.success(data.message, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
            this.getProducts();
          },
          err => {
            this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'});
          }
        )
      } else if(result.dismiss === Swal.DismissReason.cancel){
        Swal.fire(
          'Canceled',
          'Product not deleted',
          'error'
        )
      }
    })
  }

  setProduct(perfil: Perfil) {
    this.router.navigate(['/detail', perfil.id]);
  }
  

  applyFilter(searchText: string): void {
    this.searchText = searchText.toLowerCase(); // Actualizar la propiedad searchText en minúsculas
  
    // Filtrar los perfiles en tiempo real
    this.perfils = this.originalPerfils.filter(perfil =>
      perfil.name.toLowerCase().includes(this.searchText)
    );
  
    this.showMessage = this.perfils.length === 0; // Mostrar mensaje si no hay resultados
  }
  

  resetPerfils(): void {
    this.searchText = ''; // Limpiar el texto de búsqueda
    this.getProducts(); // Obtener de nuevo los perfiles sin filtro
  }

}
