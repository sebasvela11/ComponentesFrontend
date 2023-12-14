package com.componentes.administracion.controllers;

import com.componentes.ulatina.modelo.Horario;
import com.componentes.ulatina.servicio.ServicioHorario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class HorarioController implements Serializable {

    ServicioHorario servicioHorario = new ServicioHorario();

    public HorarioController() {
    }

    public void insertar(EntityManager em, Horario horario) {
        try {
            if (horario != null) {
                servicioHorario.insertar(em, horario);
            } else {
                throw new Exception("ERROR - Horario llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificar(EntityManager em, Horario horario) {
        try {
            if (horario != null) {
                servicioHorario.modificar(em, horario);
            } else {
                throw new Exception("ERROR - Horario llegó nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Horario> listar(EntityManager em) {
        List<Horario> horarios = new ArrayList<Horario>();
        try {
            horarios = servicioHorario.listar(em);
            if (horarios == null && !horarios.isEmpty()) {
                throw new Exception("ERROR - horarios no ecnotrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horarios;
    }

    public Horario horarioPorId(EntityManager em, Integer id) {
        Horario horario = new Horario();
        try {
            if (id != 0 && id > 0) {
                horario = servicioHorario.horarioPorId(em, id);
                if (horario == null) {
                    throw new Exception("ERROR - Horario no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    public Horario horarioPorEmpleado(EntityManager em, Integer empleado) {
        Horario horario = new Horario();
        try {
            if (empleado != 0 && empleado > 0) {
                horario = servicioHorario.horarioPorEmpleado(em, empleado);
                if (horario == null) {
                    throw new Exception("ERROR - Horario no encontrado");
                }
            } else {
                throw new Exception("ERROR - empleado no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    public Horario horarioPorIdEmpleado(EntityManager em, Integer id, Integer empleado) {
        Horario horario = new Horario();
        try {
            if (empleado != 0 && empleado > 0 && empleado != 0 && empleado > 0) {
                horario = servicioHorario.horarioPorIdEmpleado(em, id, empleado);
                if (horario == null) {
                    throw new Exception("ERROR - Horario no encontrado");
                }
            } else {
                throw new Exception("ERROR - Id no valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }
}
