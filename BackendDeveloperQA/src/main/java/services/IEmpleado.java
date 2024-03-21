
package services;

import java.util.List;
import model.Empleado;


public interface IEmpleado {
        public List <Empleado>  getEmpleado();

    public void saveEmpleado (Empleado emp);
    
    public void deleteEmpleado (Long id);
    
    public Empleado findEmpleado (Long id);
    
     boolean empleadoEstaIngresado(Long id);
     
     public boolean empleadoEstaIngresadoAntes(Long id);
}
