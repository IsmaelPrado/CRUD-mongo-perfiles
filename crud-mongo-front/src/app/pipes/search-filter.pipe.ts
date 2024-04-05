import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {
  transform(items: any[], searchText: string): any[] {
    if (!items || !searchText) {
      return items;
    }

    searchText = searchText.toLowerCase();

    const filteredItems = items.filter(item => {
      // Verificar si el nombre existe y luego aplicar el filtro
      return item && item.name && item.name.toLowerCase().includes(searchText);
    });

    return filteredItems;
  }
}
