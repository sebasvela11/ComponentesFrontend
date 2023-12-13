package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.servicio.ServicioEmpleado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Velazquez
 */
public class EmpleadoController implements Serializable {

    ServicioEmpleado servicioEmpleado = new ServicioEmpleado();

    public EmpleadoController() {
    }

    public void insertar(EntityManager em, Empleado empleado) {
        try {
            if (empleado != null) {
                servicioEmpleado.insertar(em, empleado);
            } else {
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modificar(EntityManager em, Empleado empleado) {
        try {
            if (empleado != null) {
                servicioEmpleado.modificar(em, empleado);
            } else {
                throw new Exception("ERROR - Empleado llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Empleado validarUsuario(EntityManager em, String creedencial, String contrasena) {
        Empleado empleado = new Empleado();
        try {
            if (!creedencial.isEmpty() && !contrasena.isEmpty()) {
                empleado = servicioEmpleado.validarUsuario(em, creedencial, contrasena);
            } else {
                throw new Exception("ERROR - Correo o contraseña no valida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public Empleado empleadoPorId(EntityManager em, int id) {
        Empleado empleado = new Empleado();
        try {
            if (id != 0 && id > 0) {
                empleado = servicioEmpleado.empleadoPorId(em, id);
                if (empleado == null) {
                    throw new Exception("ERROR - Usuario no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public List<Empleado> listar(EntityManager em) {
        List<Empleado> empleados = new ArrayList<Empleado>();
        try {
            empleados = servicioEmpleado.listar(em);
            if (empleados == null && !empleados.isEmpty()) {
                throw new Exception("ERROR - Usuarios no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public List<Empleado> listarPorRol(EntityManager em, Detalle detalle) {
        List<Empleado> empleados = new ArrayList<Empleado>();
        try {
            if(detalle != null){
                empleados = servicioEmpleado.listarPorRol(em, detalle);
                if(empleados != null && !empleados.isEmpty()){
                    throw new Exception("ERROR - Usuarios no ecnotrados");
                }
            }else{
                throw new Exception("ERROR - Rol llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
