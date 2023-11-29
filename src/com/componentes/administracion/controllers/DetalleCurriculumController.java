package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.DetalleCurriculum;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.servicio.ServicioDetalleCurriculum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class DetalleCurriculumController implements Serializable {

    ServicioDetalleCurriculum sevicioDetalleCurriculum = new ServicioDetalleCurriculum();

    public DetalleCurriculumController() {
    }

    public void insertar(EntityManager em, DetalleCurriculum detalleCurriculum) {
        try {
            if (detalleCurriculum != null) {
                sevicioDetalleCurriculum.insertar(em, detalleCurriculum);
            } else {
                throw new Exception("ERROR - Detalle curriculum llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DetalleCurriculum detalleCurriculumPorId(EntityManager em, int id) {
        DetalleCurriculum detalleCurriculum = new DetalleCurriculum();
        try {
            if (id != 0 && id < 0) {
                detalleCurriculum = sevicioDetalleCurriculum.detalleCurriculumPorId(em, id);
                if (detalleCurriculum == null) {
                    throw new Exception("ERROR - Detalle curriculum no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalleCurriculum;
    }

    public List<DetalleCurriculum> listar(EntityManager em) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            detallesCurriculum = sevicioDetalleCurriculum.listar(em);
            if (detallesCurriculum == null && !detallesCurriculum.isEmpty()) {
                throw new Exception("ERROR - Detalles curriculum no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesCurriculum;
    }

    public List<DetalleCurriculum> listarPorTipo(EntityManager em, Detalle detalle) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            if(detalle != null){
                detallesCurriculum = sevicioDetalleCurriculum.listarPorTipo(em, detalle);
                if(detallesCurriculum != null && !detallesCurriculum.isEmpty()){
                    throw new Exception("ERROR - Detalles curriculum no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Tipo llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesCurriculum;
    }

    public List<DetalleCurriculum> listarPorEmpleado(EntityManager em, Empleado empleado) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            if(empleado != null){
                detallesCurriculum = sevicioDetalleCurriculum.listarPorEmpleado(em, empleado);
                if(detallesCurriculum != null && !detallesCurriculum.isEmpty()){
                    throw new Exception("ERROR - Detalles curriculum no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesCurriculum;
    }

    public List<DetalleCurriculum> listarPorTipoEmpleado(EntityManager em, Empleado empleado, Detalle detalle) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            if(empleado != null && detalle != null){
                detallesCurriculum = sevicioDetalleCurriculum.listarPorTipoEmpleado(em, empleado, detalle);
                if(detallesCurriculum != null && !detallesCurriculum.isEmpty()){
                    throw new Exception("ERROR - Detalles curriculum no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Tipo llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesCurriculum;
    }
}
