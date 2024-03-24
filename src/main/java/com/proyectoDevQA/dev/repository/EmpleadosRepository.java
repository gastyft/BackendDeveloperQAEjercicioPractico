/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyectoDevQA.dev.repository;

import com.proyectoDevQA.dev.model.Empleados;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface EmpleadosRepository extends JpaRepository<Empleados,Long>  {
     Empleados findByDniAndCompania(int dni, String compania);

    public List<Empleados> findByDni(int dni);
}

