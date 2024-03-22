 
package com.proyectoDevQA.dev.controller;

import com.proyectoDevQA.dev.model.Empleados;
import com.proyectoDevQA.dev.service.IEmpleados;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 @RestController

@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private IEmpleados interEmpleado;

    @GetMapping("/getempleadoslist")
    public List<Empleados> getEmp() {
        return interEmpleado.getEmpleado();
    }

    @PostMapping("/crear")
    public String createUser(@RequestBody Empleados emp) {
        interEmpleado.saveEmpleado(emp);
        return "El empleado fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteEmp(@PathVariable Long id) {
        interEmpleado.deleteEmpleado(id);
        return "El empleado fue eliminado correctamente";
    }

    @PutMapping("/editar/{id}") // NO TIENE VALIDACION
    public Empleados editEmp(@PathVariable Long id,
                            @RequestParam("nombre") String nuevoNombre,
                            @RequestParam("apellido") String nuevoApellido,
                            @RequestParam("fechaIngreso") Date nuevoIngreso,
                            @RequestParam("FechaEgreso") Date nuevoEgreso,
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
  
@GetMapping("/compania/{id}/{compania}") // Endpoint para verificar si el empleado pertenece a la misma compañía
public boolean companiaIgual(@PathVariable Long id, @PathVariable String compania) {
    return interEmpleado.isEmpleadoIgualCompania(id, compania);
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
  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date ingresoDate= dateFormat.parse(nuevoIngreso);
    Date egresoDate=dateFormat.parse(nuevoEgreso);
    emp.setNombre(nuevoNombre);
    emp.setApellido(nuevoApellido);
    emp.setFechaIngreso(ingresoDate);
    emp.setFechaEgreso(egresoDate);
    emp.setDni(nuevodni);
            emp.setCompania(nuevacompania);
              if(interEmpleado.isEmpleadoIgualCompania(id, emp.getCompania()))
                  return "Ya existe empleado con esa compania";

              else
              {
                  interEmpleado.saveEmpleado(emp);
      return emp;
                    
              }
                
    }
 
}