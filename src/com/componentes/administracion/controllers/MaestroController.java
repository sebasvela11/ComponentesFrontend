/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Maestro;
import com.componentes.ulatina.servicio.ServicioMaestro;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class MaestroController implements Serializable{
    ServicioMaestro servicioMaestro = new ServicioMaestro();

    public MaestroController() {
        
    }
    
    public Maestro maestroPorId(EntityManager em, int id) {
        Maestro maestro = new Maestro();
        try{
            if (id != 0 && id < 0) {
                maestro = servicioMaestro.maestroPorId(em, id);
                if (maestro == null) {
                    throw new Exception("ERROR - Maestro no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return maestro;
    }

    public Maestro maestroPorCodigoGeneral(EntityManager em, String codigoGeneral) {
        Maestro maestro = new Maestro();
        try{
            if (!codigoGeneral.isEmpty()) {
                maestro = servicioMaestro.maestroPorCodigoGeneral(em, codigoGeneral);
                if (maestro == null) {
                    throw new Exception("ERROR - Maestro no encontrado");
                }
            } else {
                throw new Exception("ERROR - Codigo general no valido no valido");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return maestro;
    }
}
