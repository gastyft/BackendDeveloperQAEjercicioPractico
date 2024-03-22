 
package com.proyectoDevQA.dev.controller;

import com.proyectoDevQA.dev.model.Empleados;
import com.proyectoDevQA.dev.service.IEmpleados;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "El dispositivo fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteEmp(@PathVariable Long id) {
        interEmpleado.deleteEmpleado(id);
        return "El dispositivo fue eliminado correctamente";
    }

    @PutMapping("/editar/{id}")
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
        return interEmpleado.empleadoEstaIngresado(id);
    }
  
@GetMapping("/compania/{id}/{compania}") // Endpoint para verificar si el empleado pertenece a la misma compañía
public boolean companiaIgual(@PathVariable Long id, @PathVariable String compania) {
    return interEmpleado.empleadoIgualCompania(id, compania);
}
  @PutMapping("/ingresoUsuario/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Empleados ingresoedit(@PathVariable Long id,
            @RequestParam ("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("fechaIngreso") Date nuevoIngreso,
            @RequestParam("fechaEgreso") Date nuevoEgreso,
            @RequestParam("dni") int nuevodni,
            @RequestParam("compania") String nuevacompania){
    Empleados emp= interEmpleado.findEmpleado(id);
 
    emp.setNombre(nuevoNombre);
    emp.setApellido(nuevoApellido);
    emp.setFechaIngreso(nuevoIngreso);
    emp.setFechaEgreso(nuevoEgreso);
    emp.setDni(nuevodni);
            emp.setCompania(nuevacompania);
    interEmpleado.saveEmpleado(emp);
    
    return emp;
    }
 
}