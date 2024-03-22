 
package com.proyectoDevQA.dev.service;

import com.proyectoDevQA.dev.model.Empleados;
import com.proyectoDevQA.dev.repository.EmpleadosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
 @Service
public class EmpleadosService implements IEmpleados{
      @Autowired
    private EmpleadosRepository empleadoRepository;
       @Override
      public List <Empleados>  getEmpleado(){
          List<Empleados> listaEmpleado = empleadoRepository.findAll();
      return listaEmpleado;
      }
 @Override
    public void saveEmpleado (Empleados emp){
        empleadoRepository.save(emp);
    }
     @Override
    public void deleteEmpleado (Long id){
           empleadoRepository.deleteById(id);
    }
     @Override
    public Empleados findEmpleado (Long id){
         Empleados emp = empleadoRepository.findById(id).orElse(null);
        return emp;
    }
      @Override
    public boolean isEmpleadoEstaIngresado(Long id) {  //En angular corrobora true o false si esta ingresado para poder cargar egreso. Si devuelve false no se puede cargar fecha de egreso
    Empleados empleado = findEmpleado(id);
    return empleado != null && empleado.getFechaIngreso() != null && empleado.getFechaEgreso() == null;
    }
     @Override
    public boolean isEmpleadoIgualCompania(Long id,String compania) { //DEVUELVE TRUE SI SON IGUALES EN ANGULAR IF TRUE ENTONCES CARTEL ER
        Empleados empleado = empleadoRepository.findById(id).orElse(null);
            if(compania!= null && empleado.getCompania() != null && empleado.getCompania().equals(compania))
        return true;
            else
          return false;
    
}
 }