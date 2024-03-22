
package com.proyectoDevQA.dev.service;

import com.proyectoDevQA.dev.model.Empleados;
import java.util.List;


public interface IEmpleados{
        public List <Empleados>  getEmpleado();

    public void saveEmpleado (Empleados emp);
    
    public void deleteEmpleado (Long id);
    
    public Empleados findEmpleado (Long id);
    
     boolean empleadoEstaIngresado(Long id);
     
  public boolean empleadoIgualCompania(Long id,String compania);
}
