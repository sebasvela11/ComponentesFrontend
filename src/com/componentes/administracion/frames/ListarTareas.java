/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.componentes.administracion.frames;

import com.componentes.administracion.controllers.DetalleController;
import com.componentes.administracion.controllers.TareaProyectoController;
import com.componentes.ulatina.modelo.Empleado;
import com.componentes.ulatina.modelo.TareaProyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author mateo
 */
public class ListarTareas extends javax.swing.JFrame {

    DefaultTableModel modeloTablaTarea = new DefaultTableModel();
    TareaProyectoController tareaProyectoController = new TareaProyectoController();
    Empleado empleadoConectado = new Empleado();
    EntityManager em;

    /**
     * Creates new form ListarProyectoEmpleado
     */
    public ListarTareas(EntityManager em, Empleado empleado) {
        this.em = em;
        this.empleadoConectado = empleado;
        initComponents();
        this.cargarTabla();
        this.validarPermisos();
    }

    public void cargarTabla() {
        ArrayList<Object> nombresColumna = new ArrayList<Object>();
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        List<TareaProyecto> tareasProyecto = new ArrayList<TareaProyecto>();
        TareaProyectoController tareaProyectoController = new TareaProyectoController();

        nombresColumna.add("Id");
        nombresColumna.add("Nombre");
        nombresColumna.add("Empleado");
        nombresColumna.add("Proyecto");
        nombresColumna.add("Tiempo invertido");
        nombresColumna.add("Estado");
        nombresColumna.add("Tipo");
        for (Object columna : nombresColumna) {
            modeloTablaTarea.addColumn(columna);
        }

        this.jTable13.setModel(modeloTablaTarea);
        if (this.empleadoConectado.getRol().getCodigoGeneral().equals("ROL_EMPLEADO")) {
            tareasProyecto = tareaProyectoController.listarPorEmpleado(em, empleadoConectado);
        } else {
            tareasProyecto = tareaProyectoController.listar(em);
        }
        for (TareaProyecto tareaProyecto : tareasProyecto) {
            String estado = new String();
            String tipo = new String();
            switch (tareaProyecto.getEstado().getCodigoGeneral()) {
                case "ESTADO_TAREA_EN_PROCESO":
                    estado = "En Proceso";
                    break;
                case "ESTADO_TAREA_FINALIZADA":
                    estado = "Finalizada";
                    break;
                case "ESTADO_TAREA_PAUSADA":
                    estado = "Pausada";
                    break;
                case "ESTADO_TAREA_PENDIENTE":
                    estado = "Pendiente";
                    break;
            }
            switch (tareaProyecto.getTipoTarea().getCodigoGeneral()) {
                case "TIPO_TAREA_PERMANENTE":
                    tipo = "Permanente";
                    break;
                case "TIPO_TAREA_ESPECIFICA":
                    tipo = "Especifica";
                    break;
            }
            Object[] informacion = new Object[]{tareaProyecto.getId(), tareaProyecto.getTituloTarea(),
                tareaProyecto.getEmpleado().getNombre() + " " + tareaProyecto.getEmpleado().getApellidos(),
                tareaProyecto.getProyecto().getNombre(), tareaProyecto.getTiempoInvertido(), estado,
                tipo};
            datos.add(informacion);
        }
        for (Object[] datosEmpleados : datos) {
            modeloTablaTarea.addRow(datosEmpleados);
        }
        TableColumnModel columnModel = jTable13.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);

        this.jTable13.setModel(modeloTablaTarea);
        this.jTable13.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void validarPermisos() {
        if (this.empleadoConectado.getRol().getCodigoGeneral().equals("ROL_EMPLEADO")) {
            this.jButton10.setVisible(false);
            this.jButton11.setVisible(false);
            this.jButton39.setVisible(false);
            this.jButton40.setVisible(false);
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Empleado getEmpleadoConectado() {
        return empleadoConectado;
    }

    public void setEmpleadoConectado(Empleado empleadoConectado) {
        this.empleadoConectado = empleadoConectado;
    }

    public boolean validarNumero(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Listar Tareas");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton9.setText("Inicio");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(204, 204, 204));
        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setText("Empleados");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(204, 204, 204));
        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setText("Proyectos");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setText("Cerrar Sesion");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton13.setText("Perfil");
        jButton13.setBorder(null);
        jButton13.setBorderPainted(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        jButton39.setBackground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Editar");
        jButton39.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton39.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(255, 255, 255));
        jButton40.setText("Agregar");
        jButton40.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton40.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(255, 255, 255));
        jButton41.setText("Detalle");
        jButton41.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton41.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setBackground(new java.awt.Color(255, 255, 255));
        jButton42.setText("Agregar Timepo");
        jButton42.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton42.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setBackground(new java.awt.Color(255, 255, 255));
        jButton43.setText("Cambiar Estado");
        jButton43.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        jButton43.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        MenuPrincipal menuPrincipal = new MenuPrincipal(this.em, this.empleadoConectado);
        this.setVisible(false);
        menuPrincipal.setEmpleadoConectado(empleadoConectado);
        menuPrincipal.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ListarEmpleados listarEmpleados = new ListarEmpleados(this.em, this.empleadoConectado);
        this.setVisible(false);
        listarEmpleados.setEmpleadoConectado(empleadoConectado);
        listarEmpleados.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ListarProyecto listarProyecto = new ListarProyecto(this.em, this.empleadoConectado);
        this.setVisible(false);
        listarProyecto.setEmpleadoConectado(empleadoConectado);
        listarProyecto.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        InicioSesion incioSesion = new InicioSesion(this.em);
        this.setVisible(false);
        incioSesion.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        if (this.jTable13.getSelectedRow() != -1 && this.jTable13.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(modeloTablaTarea.getValueAt(this.jTable13.getSelectedRow(), 0)));
            TareaProyecto tareaProyecto = tareaProyectoController.tareaProyectoPorId(em, id);
            CrearTarea crearTarea = new CrearTarea(this.em,this.empleadoConectado, tareaProyecto, true);
            this.setVisible(false);
            crearTarea.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila.");
        }

    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        CrearTarea crearTarea = new CrearTarea(this.em,this.empleadoConectado, new TareaProyecto(), false);
        this.setVisible(false);
        crearTarea.setVisible(true);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        if (this.jTable13.getSelectedRow() != -1 && this.jTable13.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(modeloTablaTarea.getValueAt(this.jTable13.getSelectedRow(), 0)));
            TareaProyecto tareaProyecto = tareaProyectoController.tareaProyectoPorId(em, id);
            DetalleTarea detalleTarea = new DetalleTarea(tareaProyecto, this);
            this.setVisible(false);
            detalleTarea.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila.");
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        VerPerfil verPerfil = new VerPerfil(this.em, this.empleadoConectado);
        this.setVisible(false);
        verPerfil.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        if (this.jTable13.getSelectedRow() != -1 && this.jTable13.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(modeloTablaTarea.getValueAt(this.jTable13.getSelectedRow(), 0)));
            TareaProyecto tarea = this.tareaProyectoController.tareaProyectoPorId(em, id);
            String name = JOptionPane.showInputDialog("Introduzca el tiempo en horas.");
            if (this.validarNumero(name)) {
                double tiempoTotal = tarea.getTiempoInvertido() + Double.parseDouble(name);
                tarea.setTiempoInvertido(tiempoTotal);
                this.tareaProyectoController.modificar(em, tarea);
                modeloTablaTarea = new DefaultTableModel();
                this.cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Horas no validas.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila.");
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        DetalleController detalleController = new DetalleController();
        if (this.jTable13.getSelectedRow() != -1 && this.jTable13.getSelectedRow() > -1) {
            int id = Integer.parseInt(String.valueOf(modeloTablaTarea.getValueAt(this.jTable13.getSelectedRow(), 0)));
            TareaProyecto tarea = this.tareaProyectoController.tareaProyectoPorId(em, id);
            Object[] estados = {"En Proceso", "Finalizada", "Pausada", "Pendiente"};
            int posicion = 0;
            switch (tarea.getEstado().getCodigoGeneral()) {
                case "ESTADO_TAREA_EN_PROCESO":
                    posicion = 0;
                    break;
                case "ESTADO_TAREA_FINALIZADA":
                    posicion = 1;
                    break;
                case "ESTADO_TAREA_PAUSADA":
                    posicion = 2;
                    break;
                case "ESTADO_TAREA_PENDIENTE":
                    posicion = 3;
                    break;
            }
            Object opcion = JOptionPane.showInputDialog(null, "Selecciona un color", "Elegir", JOptionPane.QUESTION_MESSAGE, null, estados, estados[posicion]);
            String nuevoEstado = new String();
            switch (opcion.toString()) {
                case "En Proceso":
                    nuevoEstado = "ESTADO_TAREA_EN_PROCESO";
                    break;
                case "Finalizada":
                    nuevoEstado = "ESTADO_TAREA_FINALIZADA";
                    break;
                case "Pausada":
                    nuevoEstado = "ESTADO_TAREA_PAUSADA";
                    break;
                case "Pendiente":
                    nuevoEstado = "ESTADO_TAREA_PENDIENTE";
                    break;
            }
            tarea.setEstado(detalleController.detallePorCodigoGeneral(em, nuevoEstado));
            tareaProyectoController.modificar(em, tarea);
            modeloTablaTarea = new DefaultTableModel();
            this.cargarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila.");
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable13;
    // End of variables declaration//GEN-END:variables
}
