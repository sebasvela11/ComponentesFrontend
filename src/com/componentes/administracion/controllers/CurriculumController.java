/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Curriculum;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.servicio.ServicioCurriculum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sebas
 */
public class CurriculumController implements Serializable{
    ServicioCurriculum servicioCurriculum = new ServicioCurriculum();

    public CurriculumController() {
    }
            
    public void insertar(EntityManager em, Curriculum curriculum) {
        try {
            if (curriculum != null) {
                servicioCurriculum.insertar(em, curriculum);
            } else {
                throw new Exception("ERROR - Curriculum llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modificar(EntityManager em, Curriculum curriculum) {
        try {
            if (curriculum != null) {
                servicioCurriculum.modificar(em, curriculum);
            } else {
                throw new Exception("ERROR - curriculum llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Curriculum curriculumPorId(EntityManager em, int id) {
        Curriculum curriculum = new Curriculum();
        try {
            if (id != 0 && id > 0) {
                curriculum = servicioCurriculum.curriculumPorId(em, id);
                if (curriculum == null) {
                    throw new Exception("ERROR - Curriculum no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curriculum;
    }
    
    public Curriculum listarPorEmpleado(EntityManager em, Empleado empleado) {
        Curriculum curriculum = new Curriculum();
        try {
            if(empleado != null){
                curriculum = servicioCurriculum.curriculumPorEmpleado(em, empleado);
                if(curriculum == null){
                    throw new Exception("ERROR - Curriculum no ecnotrado");
                }
            }else{
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curriculum;
    }
    
    public List<Curriculum> listar(EntityManager em) {
        List<Curriculum> curriculums = new ArrayList<Curriculum>();
        try {
            curriculums = servicioCurriculum.listar(em);
            if (curriculums == null && curriculums.isEmpty()) {
                throw new Exception("ERROR - Curriculums no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curriculums;
    }
}
