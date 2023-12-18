package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Curriculum;
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
    
    public void modificar(EntityManager em, DetalleCurriculum detalleCurriculum) {
        try {
            if (detalleCurriculum != null) {
                sevicioDetalleCurriculum.modificar(em, detalleCurriculum);
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

    public List<DetalleCurriculum> listarPorCurriculum(EntityManager em, Curriculum curriculum) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            if(curriculum != null){
                detallesCurriculum = sevicioDetalleCurriculum.listarPorCurriculum(em, curriculum);
                if(detallesCurriculum != null && detallesCurriculum.isEmpty()){
                    throw new Exception("ERROR - Detalles curriculum no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Curriculum llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesCurriculum;
    }

    public List<DetalleCurriculum> listarPorTipoEmpleado(EntityManager em, Curriculum curriculum, Detalle detalle) {
        List<DetalleCurriculum> detallesCurriculum = new ArrayList<DetalleCurriculum>();
        try {
            if(curriculum != null && detalle != null){
                detallesCurriculum = sevicioDetalleCurriculum.listarPorTipoCurriculum(em, curriculum, detalle);
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
