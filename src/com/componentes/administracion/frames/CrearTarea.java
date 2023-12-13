/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.frames;

import com.componentes.administracion.controllers.DetalleController;
import com.componentes.administracion.controllers.EmpleadoController;
import com.componentes.administracion.controllers.MaestroController;
import com.componentes.administracion.controllers.ProyectoController;
import com.componentes.administracion.controllers.TareaProyectoController;
import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.modelo.Maestro;
import com.componentes.ulatina.modelo.Proyecto;
import com.componentes.ulatina.modelo.TareaProyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cultisof
 */
public class CrearTarea extends javax.swing.JFrame {

    boolean editando;
    Empleado empleadoConectado = new Empleado();
    TareaProyecto tareaProyecto = new TareaProyecto();
    TareaProyectoController tareaProyectoController = new TareaProyectoController();
    EmpleadoController empleadoController = new EmpleadoController();
    ProyectoController proyectoController = new ProyectoController();
    DetalleController detalleController = new DetalleController();
    MaestroController mestroController = new MaestroController();
    EntityManager em;
    List<Empleado> empleados = new ArrayList<Empleado>();
    List<Proyecto> proyectos = new ArrayList<Proyecto>();
    List<Detalle> estados = new ArrayList<Detalle>();
    List<Detalle> tipos = new ArrayList<Detalle>();

    /**
     * Creates new form CrearTarea
     */
    public CrearTarea(EntityManager em, Empleado empleado, TareaProyecto tareaProyecto, boolean editando) {
        this.editando = editando;
        this.em = em;
        this.empleadoConectado = empleado;
        this.tareaProyecto = tareaProyecto;
        initComponents();
        this.validarAccion();
        this.cargarOpciones();
        this.cargarDatos();

    }

    public void validarAccion() {
        if (editando) {
            this.jLabel1.setText("Editar Tarea");
            this.jButton5.setText("Guardar");
        }
    }

    public void cargarOpciones() {
        empleados = this.empleadoController.listar(em);
        proyectos = this.proyectoController.listar(em);
        Maestro estado = new Maestro();
        Maestro tipo = new Maestro();
        estado = this.mestroController.maestroPorCodigoGeneral(em, "ESTADO_TAREA");
        tipo = this.mestroController.maestroPorCodigoGeneral(em, "TIPO_TAREA_PROYECTO");
        estados = this.detalleController.listarPorMaestro(em, estado);
        tipos = this.detalleController.listarPorMaestro(em, tipo);

        for (Empleado empleado : empleados) {
            this.empleadoTxt.addItem(empleado.getCorreoEmpresa());
        }
        for (Proyecto proyecto : proyectos) {
            this.proyectoTxt.addItem(proyecto.getNombre());
        }
        for (Detalle detalle : estados) {
            this.estadoTxt.addItem(detalle.getDescripcion());
        }
        for (Detalle detalle : tipos) {
            this.tipoTxt.addItem(detalle.getDescripcion());
        }
    }

    public void cargarDatos() {
        if (editando) {
            this.tituloTarea.setText(this.tareaProyecto.getTituloTarea());

            for (int i = 0; i < this.empleadoTxt.getItemCount(); i++) {
                if (this.empleadoTxt.getItemAt(i).equals(this.tareaProyecto.getEmpleado().getCorreoEmpresa())) {
                    this.empleadoTxt.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < this.proyectoTxt.getItemCount(); i++) {
                if (this.proyectoTxt.getItemAt(i).equals(this.tareaProyecto.getProyecto().getNombre())) {
                    this.proyectoTxt.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < this.tipoTxt.getItemCount(); i++) {
                if (this.tipoTxt.getItemAt(i).equals(this.tareaProyecto.getTipoTarea().getDescripcion())) {
                    this.tipoTxt.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < this.estadoTxt.getItemCount(); i++) {
                if (this.estadoTxt.getItemAt(i).equals(this.tareaProyecto.getEstado().getDescripcion())) {
                    this.estadoTxt.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void guardarCambios() {
        this.tareaProyecto.setTituloTarea(this.tituloTarea.getText());
        for (Empleado empleado : empleados) {
            if (empleado.getCorreoEmpresa().equals(this.empleadoTxt.getSelectedItem().toString())) {
                this.tareaProyecto.setEmpleado(empleado);
            }
        }
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getNombre().equals(this.proyectoTxt.getSelectedItem().toString())) {
                this.tareaProyecto.setProyecto(proyecto);
            }
        }
        for (Detalle detalle : estados) {
            if (detalle.getDescripcion().equals(this.estadoTxt.getSelectedItem().toString())) {
                this.tareaProyecto.setEstado(detalle);
            }
        }
        for (Detalle detalle : tipos) {
            if (detalle.getDescripcion().equals(this.tipoTxt.getSelectedItem().toString())) {
                this.tareaProyecto.setTipoTarea(detalle);
            }
        }
    }

    public TareaProyecto guardarCampos() {
        TareaProyecto tareaProyecto = new TareaProyecto();
        tareaProyecto.setTituloTarea(this.tituloTarea.getText());
        tareaProyecto.setTiempoInvertido(0.0);
        for (Empleado empleado : empleados) {
            if (empleado.getCorreoEmpresa().equals(this.empleadoTxt.getSelectedItem().toString())) {
                tareaProyecto.setEmpleado(empleado);
            }
        }
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getNombre().equals(this.proyectoTxt.getSelectedItem().toString())) {
                tareaProyecto.setProyecto(proyecto);
            }
        }
        for (Detalle detalle : estados) {
            if (detalle.getDescripcion().equals(this.estadoTxt.getSelectedItem().toString())) {
                tareaProyecto.setEstado(detalle);
            }
        }
        for (Detalle detalle : tipos) {
            if (detalle.getDescripcion().equals(this.tipoTxt.getSelectedItem().toString())) {
                tareaProyecto.setTipoTarea(detalle);
            }
        }
        return tareaProyecto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField7 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField9 = new javax.swing.JPasswordField();
        jPasswordField8 = new javax.swing.JPasswordField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jPasswordField6 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        empleadoTxt = new javax.swing.JComboBox<>();
        estadoTxt = new javax.swing.JComboBox<>();
        proyectoTxt = new javax.swing.JComboBox<>();
        tipoTxt = new javax.swing.JComboBox<>();
        tituloTarea = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        jPasswordField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField7ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Genero");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Rol");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Telefono");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("ID");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Tiempo invertido");

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });

        jPasswordField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField9ActionPerformed(evt);
            }
        });

        jPasswordField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField8ActionPerformed(evt);
            }
        });

        jPasswordField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField4ActionPerformed(evt);
            }
        });

        jPasswordField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Tarea");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Crear Tarea");
        jButton5.setAlignmentX(2.0F);
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setMargin(new java.awt.Insets(140, 140, 140, 140));
        jButton5.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Titulo tarea");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Empleado");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Estado");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Proyecto");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Tipo Tarea");

        empleadoTxt.setEditable(true);
        empleadoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadoTxtActionPerformed(evt);
            }
        });

        estadoTxt.setEditable(true);

        proyectoTxt.setEditable(true);

        tipoTxt.setEditable(true);
        tipoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoTxtActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Regresar");
        jButton6.setAlignmentX(2.0F);
        jButton6.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setMargin(new java.awt.Insets(140, 140, 140, 140));
        jButton6.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(empleadoTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(estadoTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(proyectoTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(tituloTarea))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(empleadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(proyectoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ListarTareas listarTareas = new ListarTareas(this.em, this.empleadoConectado);
        if (editando) {
            if (!this.tituloTarea.getText().isEmpty()) {
                this.guardarCambios();
                this.tareaProyectoController.modificar(em, this.tareaProyecto);
                JOptionPane.showMessageDialog(null, "Se han guardado los cambios.");
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
            }
        } else {
            if (!this.tituloTarea.getText().isEmpty()) {
                this.tareaProyectoController.insertar(em, this.guardarCampos());
                JOptionPane.showMessageDialog(null, "Se ha guardado la tarea.");
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
            }
        }
        listarTareas.modeloTablaTarea = new DefaultTableModel();
        listarTareas.cargarTabla();
        listarTareas.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPasswordField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField4ActionPerformed

    private void jPasswordField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField6ActionPerformed

    private void jPasswordField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField7ActionPerformed

    private void jPasswordField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField8ActionPerformed

    private void jPasswordField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField9ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void empleadoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleadoTxtActionPerformed

    private void tipoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoTxtActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ListarTareas listarTareas = new ListarTareas(this.em, this.empleadoConectado);
        listarTareas.modeloTablaTarea = new DefaultTableModel();
        listarTareas.cargarTabla();
        listarTareas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> empleadoTxt;
    private javax.swing.JComboBox<String> estadoTxt;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField6;
    private javax.swing.JPasswordField jPasswordField7;
    private javax.swing.JPasswordField jPasswordField8;
    private javax.swing.JPasswordField jPasswordField9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox<String> proyectoTxt;
    private javax.swing.JComboBox<String> tipoTxt;
    private javax.swing.JTextField tituloTarea;
    // End of variables declaration//GEN-END:variables
}
