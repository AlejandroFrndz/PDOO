/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author alejandro
 */
public class CivitasView extends javax.swing.JFrame {
    
    CivitasJuego juego;
    JugadorPanel jugadorPanel;
    CasillaTableroPanel[] vistaCasilla;
    int oldActual = 0;
    GestionarDialog gestionarD = new GestionarDialog(this);
    
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        
        
        vistaCasilla = new CasillaTableroPanel[20];
        jugadorPanel = new JugadorPanel();
        Contenedor_Vista_Jugador.add(jugadorPanel);
                
        vistaCasilla[0] = new CasillaTableroPanel();
        Panel_C0.add(vistaCasilla[0]);
        
        vistaCasilla[1] = new CasillaTableroPanel();
        Panel_C1.add(vistaCasilla[1]);
        
        vistaCasilla[2] = new CasillaTableroPanel();
        Panel_C2.add(vistaCasilla[2]);
        
        vistaCasilla[3] = new CasillaTableroPanel();
        Panel_C3.add(vistaCasilla[3]);
        
        vistaCasilla[4] = new CasillaTableroPanel();
        Panel_C4.add(vistaCasilla[4]);
        
        vistaCasilla[5] = new CasillaTableroPanel();
        Panel_C5.add(vistaCasilla[5]);
        
        vistaCasilla[6] = new CasillaTableroPanel();
        Panel_C6.add(vistaCasilla[6]);
        
        vistaCasilla[7] = new CasillaTableroPanel();
        Panel_C7.add(vistaCasilla[7]);
        
        vistaCasilla[8] = new CasillaTableroPanel();
        Panel_C8.add(vistaCasilla[8]);
        
        vistaCasilla[9] = new CasillaTableroPanel();
        Panel_C9.add(vistaCasilla[9]);
        
        vistaCasilla[10] = new CasillaTableroPanel();
        Panel_C10.add(vistaCasilla[10]);
        
        vistaCasilla[11] = new CasillaTableroPanel();
        Panel_C11.add(vistaCasilla[11]);
        
        vistaCasilla[12] = new CasillaTableroPanel();
        Panel_C12.add(vistaCasilla[12]);
        
        vistaCasilla[13] = new CasillaTableroPanel();
        Panel_C13.add(vistaCasilla[13]);
        
        vistaCasilla[14] = new CasillaTableroPanel();
        Panel_C14.add(vistaCasilla[14]);
        
        vistaCasilla[15] = new CasillaTableroPanel();
        Panel_C15.add(vistaCasilla[15]);
        
        vistaCasilla[16] = new CasillaTableroPanel();
        Panel_C16.add(vistaCasilla[16]);
        
        vistaCasilla[17] = new CasillaTableroPanel();
        Panel_C17.add(vistaCasilla[17]);
        
        vistaCasilla[18] = new CasillaTableroPanel();
        Panel_C18.add(vistaCasilla[18]);
        
        vistaCasilla[19] = new CasillaTableroPanel();
        Panel_C19.add(vistaCasilla[19]);
        
        Label_Ranking.setVisible(false);
        TextArea_Ranking.setVisible(false);
        
        repaint();
        revalidate();
    }
    public void gestionar(){
            
        gestionarD = new GestionarDialog(this);
        gestionarD.gestionar(juego.getJugadorActual());
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        gestionarD.setVisible(true);
    }
    public void setCivitasJuego(CivitasJuego game){
        juego = game;
        setVisible(true);
    }
    
    public void actualizaVista(){
        jugadorPanel.setJugador(juego.getJugadorActual());
        vistaCasilla[oldActual].clearActual();
        vistaCasilla[juego.getIndiceCasillaActual()].setActual();
        oldActual = juego.getIndiceCasillaActual();
        
        if(juego.finalDelJuego()){
            Label_Ranking.setVisible(true);
            TextArea_Ranking.setVisible(true);
            ArrayList<Jugador> ranking = juego.ranking();
            String cadena = "";
            for(int i = ranking.size()-1; i >= 0; i--){
                cadena += ranking.get(i).getNombre() + "\t" + String.valueOf(ranking.get(i).getSaldo()) + "\n";
            }
            TextArea_Ranking.setText(cadena);
        }
        
        gestionar();
        
        repaint();
        revalidate();
    }
    
    public void mostrarSiguienteOperacion(OperacionesJuego operacion){
        Text_SiguienteOP.setText(operacion.toString());
        actualizaVista();
    }
    
    public void setTablero(){
        vistaCasilla[0].set_casilla_tablero(juego.getCasilla(0));
        vistaCasilla[1].set_casilla_tablero(juego.getCasilla(1));
        vistaCasilla[2].set_casilla_tablero(juego.getCasilla(2));
        vistaCasilla[3].set_casilla_tablero(juego.getCasilla(3));
        vistaCasilla[4].set_casilla_tablero(juego.getCasilla(4));
        vistaCasilla[5].set_casilla_tablero(juego.getCasilla(5));
        vistaCasilla[6].set_casilla_tablero(juego.getCasilla(6));
        vistaCasilla[7].set_casilla_tablero(juego.getCasilla(7));
        vistaCasilla[8].set_casilla_tablero(juego.getCasilla(8));
        vistaCasilla[9].set_casilla_tablero(juego.getCasilla(9));
        vistaCasilla[10].set_casilla_tablero(juego.getCasilla(10));
        vistaCasilla[11].set_casilla_tablero(juego.getCasilla(11));
        vistaCasilla[12].set_casilla_tablero(juego.getCasilla(12));
        vistaCasilla[13].set_casilla_tablero(juego.getCasilla(13));
        vistaCasilla[14].set_casilla_tablero(juego.getCasilla(14));
        vistaCasilla[15].set_casilla_tablero(juego.getCasilla(15));
        vistaCasilla[16].set_casilla_tablero(juego.getCasilla(16));
        vistaCasilla[17].set_casilla_tablero(juego.getCasilla(17));
        vistaCasilla[18].set_casilla_tablero(juego.getCasilla(18));
        vistaCasilla[19].set_casilla_tablero(juego.getCasilla(19));
    }
    
    void mostrarEventos(){
        DiarioDialog diariod = new DiarioDialog(this);
        diariod.setVisible(true);
    }
    
    Respuestas comprar(){
        int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?", "Compra", JOptionPane.YES_NO_OPTION);
        return Respuestas.values()[opcion];
    }
    
    SalidasCarcel salirCarcel(){
        String[] opciones = {"PAGANDO,TIRANDO"};
        
        int respuesta= JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?", "Salir de la cárcel", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
        return SalidasCarcel.values()[respuesta];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor_Vista_Jugador = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        Panel_C0 = new javax.swing.JPanel();
        Panel_C19 = new javax.swing.JPanel();
        Panel_C18 = new javax.swing.JPanel();
        Panel_C17 = new javax.swing.JPanel();
        Panel_C15 = new javax.swing.JPanel();
        Panel_C1 = new javax.swing.JPanel();
        Panel_C2 = new javax.swing.JPanel();
        Panel_C3 = new javax.swing.JPanel();
        Panel_C4 = new javax.swing.JPanel();
        Panel_C6 = new javax.swing.JPanel();
        Panel_C7 = new javax.swing.JPanel();
        Panel_C8 = new javax.swing.JPanel();
        Panel_C11 = new javax.swing.JPanel();
        Panel_C12 = new javax.swing.JPanel();
        Panel_C13 = new javax.swing.JPanel();
        Panel_C14 = new javax.swing.JPanel();
        Panel_C5 = new javax.swing.JPanel();
        Panel_C10 = new javax.swing.JPanel();
        Panel_C16 = new javax.swing.JPanel();
        Panel_C9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        CIVITAS = new javax.swing.JLabel();
        Text_SiguienteOP = new javax.swing.JTextField();
        Label_SiguienteOP = new javax.swing.JLabel();
        Label_Ranking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea_Ranking = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        Contenedor_Vista_Jugador.setBorder(javax.swing.BorderFactory.createTitledBorder("Jugador"));

        Titulo.setText("Civitas");
        Titulo.setEnabled(false);

        Panel_C0.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C0.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_C0.setEnabled(false);

        Panel_C19.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C19.setEnabled(false);

        Panel_C18.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C18.setEnabled(false);

        Panel_C17.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C17.setEnabled(false);

        Panel_C15.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C15.setEnabled(false);

        Panel_C1.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C1.setEnabled(false);

        Panel_C2.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C2.setEnabled(false);

        Panel_C3.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C3.setEnabled(false);

        Panel_C4.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C4.setEnabled(false);

        Panel_C6.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C6.setEnabled(false);

        Panel_C7.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C7.setEnabled(false);

        Panel_C8.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C8.setEnabled(false);

        Panel_C11.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C11.setEnabled(false);

        Panel_C12.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C12.setEnabled(false);

        Panel_C13.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C13.setEnabled(false);

        Panel_C14.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C14.setEnabled(false);

        Panel_C5.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C5.setEnabled(false);

        Panel_C10.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C10.setEnabled(false);

        Panel_C16.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C16.setEnabled(false);

        Panel_C9.setBackground(new java.awt.Color(254, 254, 254));
        Panel_C9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 143, 31)));
        Panel_C9.setEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 215, 176));

        CIVITAS.setFont(new java.awt.Font("SansSerif", 3, 36)); // NOI18N
        CIVITAS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CIVITAS.setText("CIVITAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(CIVITAS, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(CIVITAS, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        Text_SiguienteOP.setText("jTextField1");
        Text_SiguienteOP.setDisabledTextColor(new java.awt.Color(5, 3, 1));
        Text_SiguienteOP.setEnabled(false);
        Text_SiguienteOP.setOpaque(false);

        Label_SiguienteOP.setText("Siguiente Operación");

        Label_Ranking.setText("RANKING");

        TextArea_Ranking.setColumns(20);
        TextArea_Ranking.setRows(5);
        TextArea_Ranking.setBorder(null);
        TextArea_Ranking.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        TextArea_Ranking.setEnabled(false);
        TextArea_Ranking.setOpaque(false);
        jScrollPane1.setViewportView(TextArea_Ranking);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Contenedor_Vista_Jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(Titulo)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Label_Ranking)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Label_SiguienteOP)
                    .addComponent(Text_SiguienteOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Panel_C15, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Panel_C17, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Panel_C18, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Panel_C19, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(510, 510, 510)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_C6, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(Panel_C7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(Panel_C8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C14, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C13, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C12, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C11, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C10, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Panel_C0, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Panel_C16, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Panel_C1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Panel_C5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Panel_C9, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))))
                .addGap(459, 459, 459))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Panel_C0, Panel_C1, Panel_C10, Panel_C11, Panel_C12, Panel_C13, Panel_C14, Panel_C15, Panel_C16, Panel_C17, Panel_C18, Panel_C19, Panel_C2, Panel_C3, Panel_C4, Panel_C5, Panel_C6, Panel_C7, Panel_C8, Panel_C9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Contenedor_Vista_Jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Panel_C0, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_C6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(Panel_C19, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_C18, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(Panel_C7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_C17, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(Panel_C8, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_C9, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(Panel_C16, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Panel_C14, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C11, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C10, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C12, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(Panel_C15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(215, 215, 215))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_SiguienteOP)
                            .addComponent(Label_Ranking))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Text_SiguienteOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(723, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Panel_C0, Panel_C1, Panel_C10, Panel_C11, Panel_C12, Panel_C13, Panel_C14, Panel_C15, Panel_C16, Panel_C17, Panel_C18, Panel_C19, Panel_C2, Panel_C3, Panel_C4, Panel_C5, Panel_C6, Panel_C7, Panel_C8, Panel_C9});

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CIVITAS;
    private javax.swing.JPanel Contenedor_Vista_Jugador;
    private javax.swing.JLabel Label_Ranking;
    private javax.swing.JLabel Label_SiguienteOP;
    private javax.swing.JPanel Panel_C0;
    private javax.swing.JPanel Panel_C1;
    private javax.swing.JPanel Panel_C10;
    private javax.swing.JPanel Panel_C11;
    private javax.swing.JPanel Panel_C12;
    private javax.swing.JPanel Panel_C13;
    private javax.swing.JPanel Panel_C14;
    private javax.swing.JPanel Panel_C15;
    private javax.swing.JPanel Panel_C16;
    private javax.swing.JPanel Panel_C17;
    private javax.swing.JPanel Panel_C18;
    private javax.swing.JPanel Panel_C19;
    private javax.swing.JPanel Panel_C2;
    private javax.swing.JPanel Panel_C3;
    private javax.swing.JPanel Panel_C4;
    private javax.swing.JPanel Panel_C5;
    private javax.swing.JPanel Panel_C6;
    private javax.swing.JPanel Panel_C7;
    private javax.swing.JPanel Panel_C8;
    private javax.swing.JPanel Panel_C9;
    private javax.swing.JTextArea TextArea_Ranking;
    private javax.swing.JTextField Text_SiguienteOP;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
