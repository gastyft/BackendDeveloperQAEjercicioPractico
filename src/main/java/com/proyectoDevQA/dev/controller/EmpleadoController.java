 
package com.proyectoDevQA.dev.controller;

import com.proyectoDevQA.dev.model.Empleados;
import com.proyectoDevQA.dev.service.IEmpleados;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

 @RestController
@RequestMapping("empleados")
@CrossOrigin(origins="https://desafiodevqa.web.app")

public class EmpleadoController {
    @Autowired
    private IEmpleados interEmpleado;
 
  @GetMapping("/")
    public String home() {
        return "index"; // Devuelve el nombre de la vista que quieres mostrar en la ruta raíz
    }
    @GetMapping("/getempleadoslist") //Metodo GET para obtener la lista de empleados
    public List<Empleados> getEmp() {
        return interEmpleado.getEmpleado();
    }

    @PostMapping("/crear")    //Metodo POST para crear un empleado nuevo
    public String createUser(@RequestBody Empleados emp) {
              interEmpleado.saveEmpleado(emp);
        return "El empleado ha sido guardado";
         }
    


    @DeleteMapping("/borrar/{id}")  //Metodo para borrar un empleado
    public ResponseEntity<String> deleteEmp(@PathVariable Long id) {
        interEmpleado.deleteEmpleado(id);
        return ResponseEntity.status(HttpStatus.OK).body("El empleado fue eliminado correctamente");
    }

    @PutMapping("/editar/{id}") // NO TIENE VALIDACION. Metodo Update
    public Empleados editEmp(@PathVariable Long id,
                            @RequestParam("nombre") String nuevoNombre,
                            @RequestParam("apellido") String nuevoApellido,
                            @RequestParam("fechaIngreso") String nuevoIngreso,
                            @RequestParam("FechaEgreso") String nuevoEgreso,
                            @RequestParam("dni") int nuevoDni,
                            @RequestParam("compania") String nuevaCompania){
        Empleados emp = interEmpleado.findEmpleado(id);
        emp.setNombre(nuevoNombre);
        emp.setApellido(nuevoApellido);
        emp.setFechaIngreso(nuevoIngreso);
        emp.setFechaEgreso(nuevoEgreso);
         emp.setDni(nuevoDni);
            emp.setCompania(nuevaCompania);
        interEmpleado.saveEmpleado(emp);
        return emp;
    }

    @GetMapping("/traer/{id}")
    public Empleados findEmpleado(@PathVariable Long id) {
        return interEmpleado.findEmpleado(id);
    }


 @GetMapping("/ingreso/{id}")   //Endpoint donde corrobora si el empleado esta solo ingresado
  public boolean ingresoVerifica(@PathVariable Long id){ 
        return interEmpleado.isEmpleadoEstaIngresado(id);
    }
  
@GetMapping("/compania/{dni}/{compania}") // Endpoint para verificar si el empleado pertenece a la misma compañía
public boolean companiaIgual(@PathVariable int dni, @PathVariable String compania) {
    return interEmpleado.existeEmpleadoConDniYCompania(dni, compania);
}
  @PutMapping("/editarusuario/{id}") 
    public Object ingresoedit(@PathVariable Long id,
            @RequestParam ("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
          @RequestParam("fechaIngreso") String nuevoIngreso,
        @RequestParam("fechaEgreso") String nuevoEgreso,
            @RequestParam("dni") int nuevodni,
            @RequestParam("compania") String nuevacompania) throws ParseException{
    Empleados emp= interEmpleado.findEmpleado(id);
    // Instancio un DateFormat para poder convertir un String en formato Fecha y hora debido a que al pasar los parametros sin eso 
    //en Postman lanzaba una Exception
 
    
  
    emp.setNombre(nuevoNombre);
    emp.setApellido(nuevoApellido);
    emp.setFechaIngreso(nuevoIngreso);
    emp.setFechaEgreso(nuevoEgreso);
    emp.setDni(nuevodni);
            emp.setCompania(nuevacompania);
              if(interEmpleado.existeEmpleadoConDniYCompania(nuevodni, emp.getCompania()))
                  return "Ya existe empleado con esa compania";

              else
              {
                  interEmpleado.saveEmpleado(emp);
      return emp;
                    
              }
                
    }
@GetMapping("/empleadosPorDni/{dni}")
public List<Empleados> getEmpleadosPorDNI(@PathVariable int dni) {
    return interEmpleado.getEmpleadosPorDNI(dni);
}
}
 
