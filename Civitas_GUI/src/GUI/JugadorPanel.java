/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.*;
import java.util.ArrayList;

/**
 *
 * @author alejandro
 */
public class JugadorPanel extends javax.swing.JPanel {
    
    Jugador jugador;
    
    /**
     * Creates new form JuegadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }
    
    public void setJugador(Jugador player){
        jugador = player;
        setVisible(true);
        Nombre.setText(jugador.getNombre());
        if(jugador.isEncarcelado())
            Encarcelado.setText("Encarcelado");
        else
            Encarcelado.setText("En Libertad");
        String saldo_text = String.valueOf(jugador.getSaldo());
        Saldo.setText(saldo_text);
        if(jugador.getClass().getName().equals(Jugador.class.getName()))
            Especulador.setText("Normal");
        else
            Especulador.setText("Especulador");
        
        
        
        repaint();
        revalidate();
        
        rellenaPropiedades(jugador.getPropiedades());
    }
    
    private void rellenaPropiedades(ArrayList<TituloPropiedad> lista){
        propiedades.removeAll();
        
        for(TituloPropiedad t : lista){
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            
            propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
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
        Saldo = new javax.swing.JTextField();
        Encarcelado = new javax.swing.JTextField();
        Especulador = new javax.swing.JTextField();
        Label_Name = new javax.swing.JLabel();
        Label_Saldo = new javax.swing.JLabel();
        propiedades = new javax.swing.JPanel();

        Nombre.setText("Nombre");
        Nombre.setEnabled(false);

        Saldo.setText("Saldo");
        Saldo.setEnabled(false);

        Encarcelado.setText("true");
        Encarcelado.setEnabled(false);
        Encarcelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncarceladoActionPerformed(evt);
            }
        });

        Especulador.setText("No");
        Especulador.setEnabled(false);

        Label_Name.setText("Nombre");
        Label_Name.setEnabled(false);

        Label_Saldo.setText("Saldo");
        Label_Saldo.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(Label_Name)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(Label_Saldo)
                            .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addComponent(propiedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Name)
                            .addComponent(Label_Saldo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(propiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EncarceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncarceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EncarceladoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Encarcelado;
    private javax.swing.JTextField Especulador;
    private javax.swing.JLabel Label_Name;
    private javax.swing.JLabel Label_Saldo;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Saldo;
    private javax.swing.JPanel propiedades;
    // End of variables declaration//GEN-END:variables
}
