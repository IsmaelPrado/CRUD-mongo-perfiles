export class Perfil {
    id!: number;
    name: string;
    desc: string;
    imageURL: string;
    habilidades: string[];
    experienciaLaboral: string;
    educacion: string;
    idiomas: string[];
    ubicacion: string;
    disponibilidad: string;
    intereses: string[];
    referencias: string[];
    vacantes: any[];
  
    constructor(
      name: string,
      desc: string,
      imageURL: string,
      habilidades: string[],
      experienciaLaboral: string,
      educacion: string,
      idiomas: string[],
      ubicacion: string,
      disponibilidad: string,
      intereses: string[],
      referencias: string[],
      vacantes: any[]
    ) {
      this.name = name;
      this.desc = desc;
      this.imageURL = imageURL;
      this.habilidades = habilidades;
      this.experienciaLaboral = experienciaLaboral;
      this.educacion = educacion;
      this.idiomas = idiomas;
      this.ubicacion = ubicacion;
      this.disponibilidad = disponibilidad;
      this.intereses = intereses;
      this.referencias = referencias;
      this.vacantes = vacantes;
    }
  }