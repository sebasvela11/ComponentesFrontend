/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.modelo.Proyecto;
import com.componentes.ulatina.modelo.TareaProyecto;
import com.componentes.ulatina.servicio.ServicioTareaProyecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class TareaProyectoController implements Serializable{
    ServicioTareaProyecto servicioTareaProyecto = new ServicioTareaProyecto();

    public TareaProyectoController() {
        
    }
    
    public void insertar(EntityManager em, TareaProyecto tareaProyecto) {
        try {
            if (tareaProyecto != null) {
                servicioTareaProyecto.insertar(em, tareaProyecto);
            } else {
                throw new Exception("ERROR - Tarea proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modificar(EntityManager em, TareaProyecto tareaProyecto) {
        try {
            if (tareaProyecto != null) {
                servicioTareaProyecto.modificar(em, tareaProyecto);
            } else {
                throw new Exception("ERROR - Tarea proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TareaProyecto tareaProyectoPorId(EntityManager em, int id) {
        TareaProyecto tareaProyecto = new TareaProyecto();
        try {
            if (id != 0 && id > 0) {
                tareaProyecto = servicioTareaProyecto.tereaProyectoPorId(em, id);
                if (tareaProyecto == null) {
                    throw new Exception("ERROR - Tarea proyecto llegó nulo");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareaProyecto;
    }

    public List<TareaProyecto> listar(EntityManager em) {
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        try {
            tareasProyecto = servicioTareaProyecto.listar(em);
            if (tareasProyecto == null && tareasProyecto.isEmpty()) {
                throw new Exception("ERROR - Tareas proyecto no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasProyecto;
    }

    public List<TareaProyecto> listarPorProyecto(EntityManager em, Proyecto proyecto) {
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        try {
            if(proyecto != null){
                tareasProyecto = servicioTareaProyecto.listarPorProyecto(em, proyecto);
                if(tareasProyecto == null && tareasProyecto.isEmpty()){
                    throw new Exception("ERROR - Tareas proyecto no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasProyecto;
    }

    public List<TareaProyecto> listarPorEmpleado(EntityManager em, Empleado empleado) {
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        try {
            if(empleado != null){
                tareasProyecto = servicioTareaProyecto.listarPorEmpleado(em, empleado);
                if(tareasProyecto == null && tareasProyecto.isEmpty()){
                    throw new Exception("ERROR - Tareas proyecto no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasProyecto;
    }

    public List<TareaProyecto> listarPorEmpleadoProyecto(EntityManager em, Empleado empleado, Proyecto proyecto) {
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        try {
            if(empleado != null){
                tareasProyecto = servicioTareaProyecto.listarPorEmpleadoProyecto(em, empleado, proyecto);
                if(tareasProyecto == null && tareasProyecto.isEmpty()){
                    throw new Exception("ERROR - Tareas proyecto no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Empleado o proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasProyecto;
    }

    public List<TareaProyecto> listarPorTipo(EntityManager em, Detalle detalle) {
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        try {
            if(detalle != null){
                tareasProyecto = servicioTareaProyecto.listarPorTipo(em, detalle);
                if(tareasProyecto == null && tareasProyecto.isEmpty()){
                    throw new Exception("ERROR - Tareas proyecto no ecnotrados");
                }
            }else{
                throw new Exception("ERROR -  Tipo llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasProyecto;
    }
}
