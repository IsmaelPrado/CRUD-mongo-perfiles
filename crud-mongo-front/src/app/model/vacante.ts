export class Vacante {
    id!: number;
    name: string;
    perfilId: number;
  
    constructor(name: string, perfilId: number) {
      this.name = name;
      this.perfilId= perfilId;
    }
  }
  