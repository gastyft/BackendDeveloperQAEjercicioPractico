 
package services;

import java.util.List;
import model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmpleadoRepository;

 @Service
public class EmpleadoService implements IEmpleado{
      @Autowired
    private EmpleadoRepository empleadoRepository;
       @Override
      public List <Empleado>  getEmpleado(){
          List<Empleado> listaEmpleado = empleadoRepository.findAll();
      return listaEmpleado;
      }
 @Override
    public void saveEmpleado (Empleado emp){
        empleadoRepository.save(emp);
    }
     @Override
    public void deleteEmpleado (Long id){
           empleadoRepository.deleteById(id);
    }
     @Override
    public Empleado findEmpleado (Long id){
         Empleado emp = empleadoRepository.findById(id).orElse(null);
        return emp;
    }
      @Override
    public boolean empleadoEstaIngresado(Long id) {
        Empleado empleado = findEmpleado(id);
        return empleado != null && empleado.getFechaIngreso() != null;
    }
     @Override
    public boolean empleadoEstaIngresadoAntes(Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado != null && empleado.getFechaIngreso() != null && empleado.getFechaEgreso() != null) {
            // Verifica si la fecha de egreso es posterior a la fecha de ingreso
            return empleado.getFechaEgreso().after(empleado.getFechaIngreso());
        }
        return false;
    }
}
