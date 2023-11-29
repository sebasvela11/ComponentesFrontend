/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.servicio.ServicioDetalle;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class DetalleController implements Serializable{
    ServicioDetalle servicioDeatalle = new ServicioDetalle();

    public DetalleController() {
    }
    
    public Detalle detallePorId(EntityManager em, int id) {
        Detalle detalle = new Detalle();
        try{
            if (id != 0 && id < 0) {
                detalle = servicioDeatalle.detallePorId(em, id);
                if (detalle == null) {
                    throw new Exception("ERROR - Detalle no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detalle;
    }

    public Detalle detallePorCodigoGeneral(EntityManager em, String codigoGeneral) {
        Detalle detalle = new Detalle();
        try{
            if (!codigoGeneral.isEmpty()) {
                detalle = servicioDeatalle.detallePorCodigoGeneral(em, codigoGeneral);
                if (detalle == null) {
                    throw new Exception("ERROR - Detalle no encontrado");
                }
            } else {
                throw new Exception("ERROR - Codigo general no valido no valido");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detalle;
    }
}
