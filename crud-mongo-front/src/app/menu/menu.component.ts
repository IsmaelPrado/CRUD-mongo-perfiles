import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PerfilService } from '../services/perfil.service';
import { Perfil } from '../model/perfil';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input() perfils: Perfil[] = [];
  searchText = '';

  @Output() filterEvent = new EventEmitter<string>();
  @Output() resetEvent = new EventEmitter<void>();

  constructor(private perfilService: PerfilService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadPerfils();
  }

  loadPerfils(): void {
    this.perfilService.list().subscribe(
      (perfils: Perfil[]) => {
        this.perfils = perfils;
      },
      (error: any) => {
        console.error("Error al cargar los perfiles", error);
      }
    );
  }

  clearFilter(): void {
    this.searchText = '';
    this.filterEvent.emit(this.searchText);
  }

  applyFilter(event: Event): void {
    if (event.target instanceof HTMLInputElement) {
      const searchText = event.target.value.toLowerCase();
      this.filterEvent.emit(searchText);
    }
  }

  logout(): void {
    // Elimina el token del localStorage
    localStorage.removeItem('token');
    // Redirige al usuario a la página de login
    this.router.navigate(['/']);
  }
}
