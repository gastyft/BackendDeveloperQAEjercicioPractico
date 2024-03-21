
package controller;


 
import java.sql.Date;
import model.Empleado;
 
 
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
import services.IEmpleado;
  @RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private IEmpleado interEmpleado;

    @GetMapping("/getempleadolist")
    public List<Empleado> getEmp() {
        return interEmpleado.getEmpleado();
    }

    @PostMapping("/crear")
    public String createUser(@RequestBody Empleado emp) {
        interEmpleado.saveEmpleado(emp);
        return "El dispositivo fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteEmp(@PathVariable Long id) {
        interEmpleado.deleteEmpleado(id);
        return "El dispositivo fue eliminado correctamente";
    }

    @PutMapping("/editar/{id}")
    public Empleado editEmp(@PathVariable Long id,
                            @RequestParam("nombre") String nuevoNombre,
                            @RequestParam("apellido") String nuevoApellido,
                            @RequestParam("fechaIngreso") Date nuevoIngreso,
                            @RequestParam("FechaEgreso") Date nuevoEgreso) {
        Empleado emp = interEmpleado.findEmpleado(id);
        emp.setNombre(nuevoNombre);
        emp.setApellido(nuevoApellido);
        emp.setFechaIngreso(nuevoIngreso);
        emp.setFechaEgreso(nuevoEgreso);
        interEmpleado.saveEmpleado(emp);
        return emp;
    }

    @GetMapping("/traer/{id}")
    public Empleado findEmpleado(@PathVariable Long id) {
        return interEmpleado.findEmpleado(id);
    }


 @GetMapping("/ingreso/{id}")
  public boolean ingresoVerifica(@PathVariable Long id){ 
        return interEmpleado.empleadoEstaIngresado(id);
    }
 
  @PutMapping("/ingresoUsuario/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Empleado ingresoedit(@PathVariable Long id,
            @RequestParam ("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("fechaIngreso") Date nuevoIngreso,
            @RequestParam("fechaEgreso") Date nuevoEgreso){
    Empleado emp= interEmpleado.findEmpleado(id);
 
    emp.setNombre(nuevoNombre);
    emp.setApellido(nuevoApellido);
    emp.setFechaIngreso(nuevoIngreso);
    emp.setFechaEgreso(nuevoEgreso);
    interEmpleado.saveEmpleado(emp);
    
    return emp;
    }
 
}
