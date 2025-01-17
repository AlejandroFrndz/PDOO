/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.*;

/**
 *
 * @author alejandro
 */
public class CasillaPanel extends javax.swing.JPanel {
    
    Casilla casilla;
    
    /**
     * Creates new form CasillaPanel
     */
    public CasillaPanel() {
        initComponents();
    }
    
    public void set_casilla(Casilla cas){
        casilla = cas;
        Nombre.setText(cas.getNombre());
        Nombre.setVisible(true);
        
        Precio.setVisible(false);
        
        if(cas.getClass().getName().equals(Casilla_Calle.class.getName())){
            Casilla_Calle casi = (Casilla_Calle)cas;
            String precio = String.valueOf(casi.get_importe());
            Precio.setText(precio);
            Precio.setVisible(true);
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(casi.getTituloPropiedad());
            vistaPropiedad.setVisible(true);
            
        }
        
        if(cas.getClass().getName().equals(Casilla_Impuesto.class.getName())){
            Casilla_Impuesto casi = (Casilla_Impuesto)cas;
            String precio = String.valueOf(casi.get_importe());
            Precio.setText(precio);
            Precio.setVisible(true);
        }
        
        repaint();
        revalidate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre = new javax.swing.JTextField();
        Precio = new javax.swing.JTextField();

        setOpaque(false);

        Nombre.setText("jTextField1");
        Nombre.setEnabled(false);
        Nombre.setOpaque(false);
        Nombre.setSelectionColor(new java.awt.Color(1, 1, 1));

        Precio.setText("jTextField1");
        Precio.setEnabled(false);
        Precio.setOpaque(false);
        Precio.setSelectionColor(new java.awt.Color(1, 1, 1));
        Precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 153, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Precio;
    // End of variables declaration//GEN-END:variables
}
