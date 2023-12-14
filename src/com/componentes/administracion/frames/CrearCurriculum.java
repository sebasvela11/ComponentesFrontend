/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.frames;

import com.componentes.administracion.controllers.CurriculumController;
import com.componentes.administracion.controllers.DetalleController;
import com.componentes.administracion.controllers.DetalleCurriculumController;
import com.componentes.administracion.controllers.EmpleadoController;
import com.componentes.administracion.controllers.HorarioController;
import com.componentes.administracion.controllers.MaestroController;
import com.componentes.ulatina.modelo.Curriculum;
import com.componentes.ulatina.modelo.Detalle;
import com.componentes.ulatina.modelo.DetalleCurriculum;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.modelo.Horario;
import com.componentes.ulatina.modelo.Maestro;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class CrearCurriculum extends javax.swing.JFrame {

    DefaultTableModel modeloTablaDetalles = new DefaultTableModel();
    Empleado empleado = new Empleado();
    Horario horario = new Horario();
    Curriculum curriculum = new Curriculum();
    EmpleadoController empleadoController = new EmpleadoController();
    HorarioController horarioController = new HorarioController();
    CurriculumController curriculumController = new CurriculumController();
    DetalleCurriculumController detalleCurriculumController = new DetalleCurriculumController();
    DetalleController detalleController = new DetalleController();
    MaestroController maestroController = new MaestroController();
    Empleado empleadoConectado = new Empleado();
    ArrayList<Object[]> datos = new ArrayList<Object[]>();
    List<Detalle> tipos = new ArrayList<Detalle>();
    EntityManager em;

    /**
     * Creates new form ListarEmpleados
     */
    public CrearCurriculum(EntityManager em, Empleado empleado, Horario horario, Empleado empleadoConectado) {
        this.em = em;
        this.empleadoConectado = empleadoConectado;
        this.horario = horario;
        this.empleado = empleado;
        this.initComponents();
        this.cargarTabla();
        this.cargarOpciones();
    }

    public void cargarTabla() {
        this.modeloTablaDetalles = new DefaultTableModel();
        ArrayList<Object> nombresColumna = new ArrayList<Object>();
        nombresColumna.add("Titulo");
        nombresColumna.add("Descripcion");
        nombresColumna.add("Fecha Inicio");
        nombresColumna.add("Fecha Final");
        nombresColumna.add("Tipo");
        for (Object columna : nombresColumna) {
            this.modeloTablaDetalles.addColumn(columna);
        }
        if (!datos.isEmpty()) {
            for (Object[] dato : datos) {
                this.modeloTablaDetalles.addRow(dato);
            }
        }
        this.jTable13.setModel(this.modeloTablaDetalles);
    }

    public void agregarFila() {
        Object[] nuevaFila = new Object[]{this.jTextField4.getText(), this.jTextField1.getText(), this.jTextField2.getText(), this.jTextField3.getText(), jComboBox1.getSelectedItem().toString()};
        this.datos.add(nuevaFila);
    }

    public void eliminarFila() {
        this.datos.remove(this.jTable13.getSelectedRow());
    }

    public void cargarOpciones() {
        Maestro tipo = new Maestro();
        tipo = this.maestroController.maestroPorCodigoGeneral(em, "TIPO_DETALLE_CURRICULUM");
        this.tipos = this.detalleController.listarPorMaestro(em, tipo);
        for (Detalle detalle : this.tipos) {
            String opcion = new String();
            switch (detalle.getCodigoGeneral()) {
                case "TIPO_DETALLE_EXPERIENCIA":
                    opcion = "Experiencia";
                    break;
                case "TIPO_DETALLE_ESTUDIOS":
                    opcion = "Estudios";
                    break;
                case "TIPO_DETALLE_HABILIDAD":
                    opcion = "Habilidad";
                    break;
            }
            this.jComboBox1.addItem(opcion);
        }
    }

    public void vaciarCampos() {
        this.jTextField4.setText("");
        this.jTextField1.setText("");
        this.jTextField2.setText("");
        this.jTextField3.setText("");
        this.jComboBox1.setSelectedIndex(0);
    }

    public boolean validarInformacion() {
        try {
            Date date1 = Date.valueOf(this.jTextField2.getText());
            Date date2 = Date.valueOf(this.jTextField3.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void guardarDatos() {
        this.empleadoController.insertar(this.em, this.empleado);
        Empleado empleadoInsertado = this.empleadoController.validarUsuario(this.em, this.empleado.getCorreoEmpresa(), this.empleado.getContrasena());
        this.horario.setId(this.calcularMayorId());
        this.horario.setEmpleado(empleadoInsertado.getId());
        this.horarioController.insertar(em, this.horario);
        this.curriculum.setEmpleado(empleadoInsertado);
        this.curriculumController.insertar(em, curriculum);
        Curriculum curriculumInsertado = this.curriculumController.listarPorEmpleado(em, empleadoInsertado);
        for (int i = 0; i < this.jTable13.getRowCount(); i++) {
            DetalleCurriculum detalleCurriculum = new DetalleCurriculum();
            detalleCurriculum.setCurriculum(curriculumInsertado);
            detalleCurriculum.setTitulo(String.valueOf(this.modeloTablaDetalles.getValueAt(i, 0)));
            detalleCurriculum.setDescripcion(String.valueOf(this.modeloTablaDetalles.getValueAt(i, 1)));
            detalleCurriculum.setFechaInicio(Date.valueOf(String.valueOf(this.modeloTablaDetalles.getValueAt(i, 2))));
            detalleCurriculum.setFechaFinal(Date.valueOf(String.valueOf(this.modeloTablaDetalles.getValueAt(i, 3))));

            switch (String.valueOf(this.modeloTablaDetalles.getValueAt(i, 4))) {
                case "Experiencia":
                    detalleCurriculum.setTipoDetalleCurriculum(this.detalleController.detallePorCodigoGeneral(em, "TIPO_DETALLE_EXPERIENCIA"));
                    break;
                case "Estudios":
                    detalleCurriculum.setTipoDetalleCurriculum(this.detalleController.detallePorCodigoGeneral(em, "TIPO_DETALLE_ESTUDIOS"));
                    break;
                case "Habilidad":
                    detalleCurriculum.setTipoDetalleCurriculum(this.detalleController.detallePorCodigoGeneral(em, "TIPO_DETALLE_HABILIDAD"));
                    break;
            }
            this.detalleCurriculumController.insertar(em, detalleCurriculum);
        }
    }

    public Integer calcularMayorId() {
        List<Horario> horarios = new ArrayList<Horario>();
        Integer maximo = 0;
        this.horarioController.listar(this.em);
        for(Horario horario: horarios){
            if(horario.getId() > maximo){
                maximo = horario.getId();
            }
        }
        return maximo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mostrar Proyectos de los Empleados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(452, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("x");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Listar Proyectos");

        jTable12 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable12.setGridColor(new java.awt.Color(255, 255, 255));
        jTable12.setRowHeight(50);
        jScrollPane12.setViewportView(jTable12);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Crear Curriculum");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jButton37.setBackground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Agregar");
        jButton37.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton37.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel5.setText("Descripcion:");

        jLabel6.setText("Fecha Inicial");

        jLabel7.setText("Fecha Final");

        jLabel8.setText("Titulo");

        jLabel10.setText("Tipo Curriculum");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton38.setBackground(new java.awt.Color(255, 255, 255));
        jButton38.setText("Finalizar");
        jButton38.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton38.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Remover");
        jButton39.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton39.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jTable13 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable13.setGridColor(new java.awt.Color(255, 255, 255));
        jTable13.setRowHeight(50);
        jScrollPane13.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10))
                .addContainerGap(261, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap(202, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        if (!this.jTextField4.getText().isEmpty() && !this.jTextField1.getText().isEmpty()
                && !this.jTextField2.getText().isEmpty() && !this.jTextField3.getText().isEmpty() && this.validarInformacion()) {
            this.agregarFila();
            this.cargarTabla();
            this.vaciarCampos();
        } else {
            if (this.validarInformacion()) {
                JOptionPane.showMessageDialog(null, "Los campos no deben estar vaciós");
            } else {
                JOptionPane.showMessageDialog(null, "Campos no validos");
            }
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        ListarEmpleados listarEmpleados = new ListarEmpleados(this.em, this.empleadoConectado);
        int opcion = JOptionPane.showConfirmDialog(null, "No se podrán hacer mas cambios a este listado.",
                "¿Desea continuar?", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (opcion == 0) {
            this.guardarDatos();
            listarEmpleados.modeloTablaEmpleado = new DefaultTableModel();
            listarEmpleados.cargarTabla();
            listarEmpleados.setVisible(true);
            this.dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        if (this.jTable13.getSelectedRow() != -1 && this.jTable13.getSelectedRow() > -1) {
            this.eliminarFila();
            this.cargarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila.");
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
