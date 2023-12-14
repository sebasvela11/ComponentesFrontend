
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.modelo.Proyecto;
import com.componentes.ulatina.servicio.ServicioProyecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class ProyectoController implements Serializable {

    ServicioProyecto servicioProyecto = new ServicioProyecto();

    public ProyectoController() {
        
    }

    public void insertar(EntityManager em, Proyecto proyecto) {
        try {
            if (proyecto != null) {
                servicioProyecto.insertar(em, proyecto);
            } else {
                throw new Exception("ERROR - Proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modificar(EntityManager em, Proyecto proyecto) {
        try {
            if (proyecto != null) {
                servicioProyecto.modificar(em, proyecto);
            } else {
                throw new Exception("ERROR - Proyecto llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Proyecto proyectoPorId(EntityManager em, int id) {
        Proyecto proyecto = new Proyecto();
        try {
            if (id != 0 && id > 0) {
                proyecto = servicioProyecto.proyectoPorId(em, id);
                if (proyecto == null) {
                    throw new Exception("ERROR - Proyecto no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyecto;
    }

    public List<Proyecto> listar(EntityManager em) {
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            proyectos = servicioProyecto.listar(em);
            if (proyectos == null && proyectos.isEmpty()) {
                throw new Exception("ERROR - Proyectos no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }

    public List<Proyecto> listarPorEmpleado(EntityManager em, Empleado empleado) {
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            if(empleado != null){
                proyectos = servicioProyecto.listarPorEmpleado(em, empleado);
                if(proyectos != null && proyectos.isEmpty()){
                    throw new Exception("ERROR - Proyectos no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }

    public List<Proyecto> listarPorEstado(EntityManager em, Detalle detalle) {
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            if(detalle != null){
                proyectos = servicioProyecto.listarPorEstado(em, detalle);
                if(proyectos != null && !proyectos.isEmpty()){
                    throw new Exception("ERROR - Proyectos no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Estado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }

    public List<Proyecto> listarPorEstadoEmpleado(EntityManager em, Detalle detalle, Empleado empleado) {
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            if(detalle != null && empleado != null){
                proyectos = servicioProyecto.listarPorEstadoEmpleado(em, detalle, empleado);
                if(proyectos != null && !proyectos.isEmpty()){
                    throw new Exception("ERROR - Proyectos no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Estado o empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }
}
