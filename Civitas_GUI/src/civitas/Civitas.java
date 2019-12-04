/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
import GUI.*;

/**
 *
 * @author alejandrof
 */
public class Civitas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CivitasView vista = new CivitasView();
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(true);
        
        CapturaNombres capturador = new CapturaNombres(vista,true);
        
        ArrayList<String> nombres = new ArrayList<>();
        
        nombres = capturador.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        juego.getJugadorActual().numCasillaActual = 1;
        Casilla_Calle casilla_actual = (Casilla_Calle)juego.getCasillaActual();
        juego.getJugadorActual().comprar(casilla_actual.getTituloPropiedad());
        
        juego.getJugadorActual().numCasillaActual = 2;
        casilla_actual = (Casilla_Calle)juego.getCasillaActual();
        juego.getJugadorActual().comprar(casilla_actual.getTituloPropiedad());
        
        juego.getJugadorActual().numCasillaActual = 3;
        casilla_actual = (Casilla_Calle)juego.getCasillaActual();
        juego.getJugadorActual().comprar(casilla_actual.getTituloPropiedad());
                
        Controlador controlador = new Controlador(juego, vista);
        
        vista.setCivitasJuego(juego);
        vista.actualizaVista();
        vista.setTablero();

    }
    
}