
package administracioncomponentes;

import com.componentes.administracion.frames.InicioSesion;
import com.componentes.ulatina.entitymanagerfactory.EntityManagerFactoryControlador;

public class main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactoryControlador e = new EntityManagerFactoryControlador();
        e.startEntityManagerFactory();
        InicioSesion iniciarSesion = new InicioSesion();
        iniciarSesion.setEm(e.getEm());
        iniciarSesion.setVisible(true);
    }
    
}
