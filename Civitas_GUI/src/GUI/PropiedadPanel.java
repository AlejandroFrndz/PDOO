/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.TituloPropiedad;
/**
 *
 * @author alejandro
 */
public class PropiedadPanel extends javax.swing.JPanel {
    
    TituloPropiedad tituloPropiedad;
    
    /**
     * Creates new form PropiedadPanel
     */
    public PropiedadPanel() {
        initComponents();
    }

    public void setPropiedad(TituloPropiedad property){
        tituloPropiedad = property;
        setVisible(true);
        if(tituloPropiedad.getHipotecado())
            hipotecada.setText("Hipotecada");
        else
            hipotecada.setText("No hipotecada");
        
        nombre.setText(tituloPropiedad.getNombre());
        String aux = String.valueOf(tituloPropiedad.getNumCasas());
        num_casas.setText(aux);
        aux = String.valueOf(tituloPropiedad.getNumHoteles());
        num_hoteles.setText(aux);
        
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

        Label_Name = new javax.swing.JLabel();
        Label_Casas = new javax.swing.JLabel();
        Label_Hoteles = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        num_casas = new javax.swing.JTextField();
        num_hoteles = new javax.swing.JTextField();
        hipotecada = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Label_Name.setText("Nombre");
        Label_Name.setEnabled(false);

        Label_Casas.setText("Casas");
        Label_Casas.setEnabled(false);

        Label_Hoteles.setText("Hoteles");
        Label_Hoteles.setEnabled(false);

        nombre.setText("jTextField1");
        nombre.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        nombre.setEnabled(false);
        nombre.setSelectedTextColor(new java.awt.Color(1, 1, 1));
        nombre.setSelectionColor(new java.awt.Color(1, 1, 0));

        num_casas.setText("jTextField2");
        num_casas.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        num_casas.setEnabled(false);
        num_casas.setSelectedTextColor(new java.awt.Color(1, 1, 1));
        num_casas.setSelectionColor(new java.awt.Color(17, 5, 0));

        num_hoteles.setText("jTextField3");
        num_hoteles.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        num_hoteles.setEnabled(false);
        num_hoteles.setSelectedTextColor(new java.awt.Color(1, 1, 1));
        num_hoteles.setSelectionColor(new java.awt.Color(1, 1, 0));

        hipotecada.setText("jTextField4");
        hipotecada.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        hipotecada.setEnabled(false);
        hipotecada.setSelectedTextColor(new java.awt.Color(1, 1, 1));
        hipotecada.setSelectionColor(new java.awt.Color(17, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(Label_Name)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(Label_Casas)
                        .addComponent(num_casas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(Label_Hoteles)
                        .addComponent(num_hoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hipotecada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Hoteles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_hoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Label_Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_Casas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num_casas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hipotecada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_Casas;
    private javax.swing.JLabel Label_Hoteles;
    private javax.swing.JLabel Label_Name;
    private javax.swing.JTextField hipotecada;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField num_casas;
    private javax.swing.JTextField num_hoteles;
    // End of variables declaration//GEN-END:variables
}
