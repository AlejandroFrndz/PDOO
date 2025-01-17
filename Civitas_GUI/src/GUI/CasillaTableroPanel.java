/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.*;
import java.awt.Color;

/**
 *
 * @author alejandro
 */
public class CasillaTableroPanel extends javax.swing.JPanel {
    
    Casilla casilla;
    
    /**
     * Creates new form CasillaPanel
     */
    public CasillaTableroPanel() {
        initComponents();
        
        Is_Actual.setVisible(false);
    }
    
    public void set_casilla_tablero(Casilla cas){
        casilla = cas;
        Nombre.setText(cas.getNombre());
        Nombre.setVisible(true);
        
        Precio.setVisible(false);
        
        if(cas.getClass().getName().equals(Casilla_Calle.class.getName())){
            Casilla_Calle casi = (Casilla_Calle)cas;
            String precio = String.valueOf(casi.get_importe());
            Precio.setText(precio);
            Precio.setVisible(true);
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
    
    public void clearActual(){
        this.setBackground(Color.WHITE);
        //Is_Actual.setVisible(false);
    }
    
    public void setActual(){
        this.setBackground(Color.DARK_GRAY);
        //Is_Actual.setVisible(true);
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
        Is_Actual = new javax.swing.JTextField();

        setBackground(new java.awt.Color(254, 254, 254));
        setPreferredSize(new java.awt.Dimension(109, 70));

        Nombre.setText("jTextField1");
        Nombre.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        Nombre.setEnabled(false);

        Precio.setText("jTextField1");
        Precio.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        Precio.setEnabled(false);
        Precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioActionPerformed(evt);
            }
        });

        Is_Actual.setText("Actual");
        Is_Actual.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Is_Actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Is_Actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Is_Actual;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Precio;
    // End of variables declaration//GEN-END:variables
}
