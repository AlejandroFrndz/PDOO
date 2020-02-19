/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import civitas.*;

/**
 *
 * @author alejandro
 */
public class Controlador {
    private CivitasJuego juego;
    private CivitasView vista;
    
    public Controlador(CivitasJuego jue, CivitasView vist){
        juego = jue;
        vista = vist;
    }
    
    public void juega(){
        vista.setCivitasJuego(juego);
        
        while(!juego.finalDelJuego()){
            vista.actualizaVista();
            //vista.pausa();
            OperacionesJuego operacion = vista.juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            if(operacion != OperacionesJuego.PASAR_TURNO && Diario.getInstance().eventosPendientes()){
                vista.mostrarEventos();
            }
            if(!vista.juego.finalDelJuego()){
                switch(operacion){
                    case COMPRAR:
                        if(vista.juego.getCasillaActual() instanceof civitas.Casilla_Calle){
                            Casilla_Calle calle = (Casilla_Calle) vista.juego.getCasillaActual();
                            if(!calle.getTituloPropiedad().tienePropietario()){
                                Respuestas respuesta = vista.comprar();
                                if(respuesta == Respuestas.SI)
                                    vista.juego.comprar();
                            }
                        }
                        vista.juego.siguientePasoCompletado(operacion);
                    break;
                    
                    case GESTIONAR:
                        vista.gestionar();
                        if(vista.getGestion() != 5){
                            OperacionInmobiliaria gestion = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()], vista.getPropiedad());
                            switch(gestion.getGestion()){
                                case VENDER:
                                    vista.juego.vender(gestion.getNumPropiedad());
                                break;
                                
                                case HIPOTECAR:
                                    vista.juego.hipotecar(gestion.getNumPropiedad());
                                break;
                                
                                case CANCELAR_HIPOTECA:
                                    vista.juego.cancelarHipoteca(gestion.getNumPropiedad());
                                break;
                                
                                case CONSTRUIR_CASA:
                                    vista.juego.construirCasa(gestion.getNumPropiedad());
                                break;
                                
                                case CONSTRUIR_HOTEL:
                                    vista.juego.construirHotel(gestion.getNumPropiedad());
                                break;
                            }
                        }
                        
                        else{
                            vista.juego.siguientePasoCompletado(operacion);
                        }
                    break;
                    
                    case SALIR_CARCEL:
                        SalidasCarcel metodo = vista.salirCarcel();
                        Boolean exito;
                        if(metodo == SalidasCarcel.PAGANDO)
                            exito = vista.juego.salirCarcelPagando();
                        else
                            exito = vista.juego.salirCarcelTirando();
                        
                        if(!exito)
                            vista.juego.siguientePasoCompletado(operacion);
                    break;
                }
            }
        }
        
        juego.ranking();
    }
}
