/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoTexto;

import java.util.ArrayList;
import civitas.*;

/**
 *
 * @author alejandro
 */
public class Controlador {
    private CivitasJuego juego;
    private VistaTextual vista;
    
    public Controlador(CivitasJuego jue, VistaTextual vist){
        juego = jue;
        vista = vist;
    }
    
    public void juega(){
        vista.setCivitasJuego(juego);
        boolean prueba = juego.finalDelJuego();
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            OperacionesJuego operacion = vista.juegoModel.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            if(operacion != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            if(!vista.juegoModel.finalDelJuego()){
                switch(operacion){
                    case COMPRAR:
                        Casilla_Calle calle = (Casilla_Calle) vista.juegoModel.getCasillaActual();
                        if(!calle.getTituloPropiedad().tienePropietario()){
                            Respuestas respuesta = vista.comprar();
                            if(respuesta == Respuestas.SI)
                                vista.juegoModel.comprar();
                        }
                        vista.juegoModel.siguientePasoCompletado(operacion);
                    break;
                    
                    case GESTIONAR:
                        vista.gestionar();
                        if(vista.iGestion != 5){
                            OperacionInmobiliaria gestion = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.iGestion], vista.iPropiedad);
                            switch(gestion.getGestion()){
                                case VENDER:
                                    vista.juegoModel.vender(gestion.getNumPropiedad());
                                break;
                                
                                case HIPOTECAR:
                                    vista.juegoModel.hipotecar(gestion.getNumPropiedad());
                                break;
                                
                                case CANCELAR_HIPOTECA:
                                    vista.juegoModel.cancelarHipoteca(gestion.getNumPropiedad());
                                break;
                                
                                case CONSTRUIR_CASA:
                                    vista.juegoModel.construirCasa(gestion.getNumPropiedad());
                                break;
                                
                                case CONSTRUIR_HOTEL:
                                    vista.juegoModel.construirHotel(gestion.getNumPropiedad());
                                break;
                            }
                        }
                        
                        else{
                            vista.juegoModel.siguientePasoCompletado(operacion);
                        }
                    break;
                    
                    case SALIR_CARCEL:
                        SalidasCarcel metodo = vista.salirCarcel();
                        
                        if(metodo == SalidasCarcel.PAGANDO)
                            vista.juegoModel.salirCarcelPagando();
                        else
                            vista.juegoModel.salirCarcelTirando();
                    break;
                }
            }
        }
        
        ArrayList<Jugador> clasificacion = vista.juegoModel.ranking();
        
        System.out.println("\nCLASIFICACIÓN FINAL");
        
        for(int i = clasificacion.size()-1; i >= 0; i--)
            System.out.println(clasificacion.get(i));
    }
}
