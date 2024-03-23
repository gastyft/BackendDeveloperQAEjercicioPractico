 
package com.proyectoDevQA.dev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

 
@Entity
@Getter @Setter

public class Empleados {
    @Id //Etiqueta identificadora de PrimaryKey
    @GeneratedValue(strategy= GenerationType.SEQUENCE) // AUTOINCREMENTAL
     private Long id;
     private  String nombre;
     private String apellido;
     
     private String compania;
     //Esta etiqueta no permite que se repita un dni
     private int dni;
     @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") // Formato para la entrada
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") // Formato para la salida JSON
     @Temporal(TemporalType.TIMESTAMP)//Como debe mapear en la base de datos de tipo fecha
    private String fechaIngreso;
      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") // Formato para la entrada
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") // Formato para la salida JSON
    @Temporal(TemporalType.TIMESTAMP) //Como debe mapear en la base de datos de tipo fecha
    private String fechaEgreso=null;
    
    
}