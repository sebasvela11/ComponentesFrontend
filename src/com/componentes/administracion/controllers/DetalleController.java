/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.Maestro;
import com.componentes.ulatina.servicio.ServicioDetalle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class DetalleController implements Serializable {

    ServicioDetalle servicioDetalle = new ServicioDetalle();

    public DetalleController() {
    }

    public Detalle detallePorId(EntityManager em, int id) {
        Detalle detalle = new Detalle();
        try {
            if (id != 0 && id < 0) {
                detalle = servicioDetalle.detallePorId(em, id);
                if (detalle == null) {
                    throw new Exception("ERROR - Detalle no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalle;
    }

    public Detalle detallePorCodigoGeneral(EntityManager em, String codigoGeneral) {
        Detalle detalle = new Detalle();
        try {
            if (!codigoGeneral.isEmpty()) {
                detalle = servicioDetalle.detallePorCodigoGeneral(em, codigoGeneral);
                if (detalle == null) {
                    throw new Exception("ERROR - Detalle no encontrado");
                }
            } else {
                throw new Exception("ERROR - Codigo general no valido no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalle;
    }

    public List<Detalle> listarPorMaestro(EntityManager em, Maestro maestro) {
        List<Detalle> detalles = new ArrayList<Detalle>();
        try {
            if (maestro != null) {
                detalles = servicioDetalle.detallePorMaestro(em, maestro);
                if (detalles == null && detalles.isEmpty()) {
                    throw new Exception("ERROR - Detalles no ecnotrados");
                }
            } else {
                throw new Exception("ERROR - Maestro llego nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalles;
    }
}
