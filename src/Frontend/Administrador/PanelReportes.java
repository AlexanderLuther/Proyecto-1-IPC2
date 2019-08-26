package Frontend.Administrador;

/**
 *
 * @author helmuthluther
 */
public class PanelReportes extends javax.swing.JPanel {
   
    private ReporteDeClientes reporteDeClientes;
    private ReporteDeRutas reporteDeRutas;
    private ReporteDeRutasPopulares reporteDeRutasPopulares;
    private ReporteDeGanancias reporteDeGanancias;
    
    public PanelReportes() {
        this.reporteDeClientes = new ReporteDeClientes(null, true);
        this.reporteDeRutas = new ReporteDeRutas(null, true);
        this.reporteDeRutasPopulares = new ReporteDeRutasPopulares(null, true);
        this.reporteDeGanancias = new ReporteDeGanancias(null, true);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        botonReporteDeRutas = new rojerusan.RSButtonIconI();
        botonReporteDeGanancias = new rojerusan.RSButtonIconI();
        botonReporteClientes = new rojerusan.RSButtonIconI();
        botonReporteDeRutasPopulares = new rojerusan.RSButtonIconI();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        botonReporteDeRutas.setBackground(new java.awt.Color(51, 153, 0));
        botonReporteDeRutas.setForeground(new java.awt.Color(0, 102, 153));
        botonReporteDeRutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reporte.png"))); // NOI18N
        botonReporteDeRutas.setText("Reporte de Rutas");
        botonReporteDeRutas.setColorHover(new java.awt.Color(153, 153, 153));
        botonReporteDeRutas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonReporteDeRutas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReporteDeRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteDeRutasActionPerformed(evt);
            }
        });

        botonReporteDeGanancias.setBackground(new java.awt.Color(51, 153, 0));
        botonReporteDeGanancias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reporte.png"))); // NOI18N
        botonReporteDeGanancias.setText("Reporte de Ganancias");
        botonReporteDeGanancias.setColorHover(new java.awt.Color(153, 153, 153));
        botonReporteDeGanancias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonReporteDeGanancias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReporteDeGanancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteDeGananciasActionPerformed(evt);
            }
        });

        botonReporteClientes.setBackground(new java.awt.Color(51, 153, 0));
        botonReporteClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reporte.png"))); // NOI18N
        botonReporteClientes.setText("Reporte de Clientes");
        botonReporteClientes.setColorHover(new java.awt.Color(153, 153, 153));
        botonReporteClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonReporteClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReporteClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteClientesActionPerformed(evt);
            }
        });

        botonReporteDeRutasPopulares.setBackground(new java.awt.Color(51, 153, 0));
        botonReporteDeRutasPopulares.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reporte.png"))); // NOI18N
        botonReporteDeRutasPopulares.setText("Reporte de Rutas Populares");
        botonReporteDeRutasPopulares.setColorHover(new java.awt.Color(153, 153, 153));
        botonReporteDeRutasPopulares.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonReporteDeRutasPopulares.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReporteDeRutasPopulares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteDeRutasPopularesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonReporteDeRutasPopulares, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonReporteClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonReporteDeGanancias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonReporteDeRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(botonReporteDeRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botonReporteDeGanancias, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botonReporteClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botonReporteDeRutasPopulares, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 53, 53, 54);
        add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jSeparator1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    /*
    Metodo encargado de inicializar y mostrar el JDialog ReporteDeRutas
    */
    private void botonReporteDeRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteDeRutasActionPerformed
        reporteDeRutas.setLocationRelativeTo(this);
        reporteDeRutas.inicializarCasillasSeleccion();
        reporteDeRutas.inicializarEtiquetas();
        reporteDeRutas.obtenerRutas(0);
        reporteDeRutas.setVisible(true);
    }//GEN-LAST:event_botonReporteDeRutasActionPerformed
    /*
    Metodo encargado de mostrar el JDialog ObtenerFechas
    */
    private void botonReporteDeGananciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteDeGananciasActionPerformed
        reporteDeGanancias.obtenerFechas();
    }//GEN-LAST:event_botonReporteDeGananciasActionPerformed
    /*
    Metodo encargado de mostrar el JDialog ObtenerFechas
    */
    private void botonReporteDeRutasPopularesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteDeRutasPopularesActionPerformed
        reporteDeRutasPopulares.obtenerFechas();
    }//GEN-LAST:event_botonReporteDeRutasPopularesActionPerformed
    /*
    Metodo encargado de inicializar y mostrar el JDialog ReporteDeClientes
    */
    private void botonReporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteClientesActionPerformed
        reporteDeClientes.setLocationRelativeTo(this);
        reporteDeClientes.inicializarCasillasSeleccion();
        reporteDeClientes.inicializarEtiquetas();
        reporteDeClientes.inicializarTablaPaquetes();
        reporteDeClientes.obtenerClientes(0);
        reporteDeClientes.setVisible(true);
    }//GEN-LAST:event_botonReporteClientesActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconI botonReporteClientes;
    private rojerusan.RSButtonIconI botonReporteDeGanancias;
    private rojerusan.RSButtonIconI botonReporteDeRutas;
    private rojerusan.RSButtonIconI botonReporteDeRutasPopulares;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
