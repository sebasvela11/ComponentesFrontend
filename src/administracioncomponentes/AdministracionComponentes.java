/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administracioncomponentes;

import com.componentes.ulatina.entitymanagerfactory.EntityManagerFactoryControlador;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.servicio.ServicioEmpleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sebas
 */
public class AdministracionComponentes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        List<Empleado> listaEmpleados = new ArrayList<Empleado>();
        EntityManagerFactoryControlador e = new EntityManagerFactoryControlador();
        e.startEntityManagerFactory();
        ServicioEmpleado se = new ServicioEmpleado();
        listaEmpleados = se.listar(e.getEm());
        for(Empleado empleado: listaEmpleados){
            System.out.println(empleado.getNombre() + " " + empleado.getApellidos());
        }
        
    }
    
}
