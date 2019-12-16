/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.Jugador;
import civitas.Jugador_Especulador;
import civitas.TituloPropiedad;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

/**
 *
 * @author alejandro
 */
public class GestionarDialog extends javax.swing.JDialog {
    int gestionElegida, propiedadElegida;
    /**
     * Creates new form GestionarDialog
     */
    public GestionarDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        gestionElegida = -1;
        propiedadElegida = -1;
    }
    
    public void gestionar(Jugador jugador){
        setGestiones();
        setPropiedades(jugador);
        repaint();
    }
    
    public void setGestiones(){
        DefaultListModel gestiones = new DefaultListModel<>();
        ArrayList<String> opciones = new ArrayList<>(Arrays.asList("Vender","Hipotecar","Cancelar Hipoteca","Construir Casa","Construir Hotel","Terminar"));
        
        for(String s: opciones)
            gestiones.addElement(s);
        listaGestiones.setModel(gestiones);
    }
    
    public void setPropiedades(Jugador jugador){
        DefaultListModel propiedades = new DefaultListModel<>();
        
        for(TituloPropiedad p : jugador.getPropiedades())
            propiedades.addElement(p.getNombre());
        listaPropiedades.setModel(propiedades);
    }
    
    public int getGestion(){
        return gestionElegida;
    }
    
    public int getPropiedad(){
        return propiedadElegida;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label_Titulo = new javax.swing.JLabel();
        Label_Opciones = new javax.swing.JLabel();
        Label_Propiedades = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaGestiones = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPropiedades = new javax.swing.JList<>();
        Button_Realizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Label_Titulo.setText("Gestiones");
        Label_Titulo.setEnabled(false);

        Label_Opciones.setText("Opciones");
        Label_Opciones.setEnabled(false);

        Label_Propiedades.setText("Propiedades");
        Label_Propiedades.setEnabled(false);

        listaGestiones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaGestiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaGestionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaGestiones);

        listaPropiedades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaPropiedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaPropiedadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaPropiedades);

        Button_Realizar.setText("Realizar");
        Button_Realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_RealizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Label_Opciones))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(Label_Titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Button_Realizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Label_Propiedades)
                        .addGap(23, 23, 23)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Propiedades)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Titulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_Opciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Realizar))))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaGestionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaGestionesMouseClicked
        gestionElegida = listaGestiones.getSelectedIndex();        
    }//GEN-LAST:event_listaGestionesMouseClicked

    private void listaPropiedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPropiedadesMouseClicked
        propiedadElegida = listaPropiedades.getSelectedIndex();        
    }//GEN-LAST:event_listaPropiedadesMouseClicked

    private void Button_RealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_RealizarActionPerformed
        gestionElegida = listaGestiones.getSelectedIndex();
        propiedadElegida = listaPropiedades.getSelectedIndex();  
        
        if((gestionElegida != -1 && propiedadElegida != -1) || (gestionElegida == 5))
            this.dispose();
    }//GEN-LAST:event_Button_RealizarActionPerformed

    /**
     * @param args the command line arguments
     *
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog *
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarDialog dialog = new GestionarDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Realizar;
    private javax.swing.JLabel Label_Opciones;
    private javax.swing.JLabel Label_Propiedades;
    private javax.swing.JLabel Label_Titulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaGestiones;
    private javax.swing.JList<String> listaPropiedades;
    // End of variables declaration//GEN-END:variables
}
