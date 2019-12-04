/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
import GUI.Dado;

/**
 *
 * @author alejandro
 */
public class CivitasJuego {
    private int indiceJugadorActual;
    private Tablero tablero;
    private EstadosJuego estado;
    private MazoSorpresas mazo;
    private ArrayList<Jugador> jugadores;
    private GestorEstados gestorEstados;
    
    CivitasJuego(ArrayList<String> nombres){
        jugadores = new ArrayList<>();
        
        for(int i = 0; i < nombres.size(); i++){
            Jugador jugador = new Jugador(nombres.get(i));
            jugadores.add(jugador);
        }
        
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        
        mazo = new MazoSorpresas();  
        
        inicializarTablero(tablero);
        inicializarMazoSorpresas(mazo);
    }
    
    private void inicializarTablero(Tablero table){
        tablero = new Tablero(14);
        
        
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Netflix", 10f, 1.75f, 150f, 300f, 250f)));      //1
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Intel", 30f, 1.75f, 250f, 500f, 250f)));        //2
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Samsung", 50f, 1.75f, 350f, 700f, 500f)));      //3
        tablero.añadeJuez();                                                                                        //4
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Oneplus", 50f, 1.75f, 350f, 700f, 500f)));      //5
        tablero.añadeCasilla(new Casilla_Sorpresa(mazo, "Sorpresa"));                                               //6
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Tencent", 70f, 1.75f, 450f, 900f, 500f)));      //7
        tablero.añadeCasilla(new Casilla_Impuesto(1000f, "Sociedades"));                                            //8
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("SpaceX", 70f, 1.75f, 450f, 900f, 500f)));       //9
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Facebook", 90f, 1.75f, 550f, 1100f, 750f)));    //10
        tablero.añadeCasilla(new Casilla_Sorpresa(mazo, "Sorpresa"));                                               //11
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Tesla", 90f, 1.75f, 550f, 1100f, 750f)));       //12
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Alphabet", 110f, 1.75f, 650f, 1300f, 750f)));   //13
        tablero.añadeCasilla(new Casilla_Sorpresa(mazo, "Sorpresa"));                                               //15
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Amazon", 110f, 1.75f, 650f, 1300f, 750f)));     //16  
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Apple", 130f, 1.75f, 750f, 1500f, 1000f)));     //17
        tablero.añadeCasilla(new Casilla("Silicon Valley"));                                                        //18
        tablero.añadeCasilla(new Casilla_Calle(new TituloPropiedad("Microsoft", 175f, 1.85f, 875f, 1750f, 1000f))); //19
        
    }
    
    private void inicializarMazoSorpresas(MazoSorpresas mazo){
        mazo.alMazo(new Sorpresa_convertirEspeculador(450));
        mazo.alMazo(new Sorpresa_pagarCobrar(75, "Tu inversion en una startup sale bien, cobra 75€"));
        mazo.alMazo(new Sorpresa_pagarCobrar(-75, "Te han pillado evadiendo impuestos, paga una multa de 75€"));
        mazo.alMazo(new Sorpresa_irACasilla(tablero, 9, "Ve a la sede de " + tablero.getCasilla(9).getNombre()));
        mazo.alMazo(new Sorpresa_irACasilla(tablero, 17, "Ve a la sede de " + tablero.getCasilla(17).getNombre()));
        mazo.alMazo(new Sorpresa_irACasilla(tablero, tablero.getCarcel(), "Han descubierto una de tus fabricas clandestinas en Vietnam. Ve a la cárcel"));
        mazo.alMazo(new Sorpresa_porCasaHotel(320, "Nuevos inversores entran en juego. Recibe 320€ por cada casa y hotel que poseas"));
        mazo.alMazo(new Sorpresa_porCasaHotel(-320, "Sube el IBI. Paga 320€ por cada casa y hotel que poseas"));
        mazo.alMazo(new Sorpresa_porJugador(250, "Una de tus patentes se ha vuelto muy popular. Recibe 250€ de cada jugador"));
        mazo.alMazo(new Sorpresa_porJugador(-250, "Tus competidores presentan una demanda colectiva contra ti y el juez les da la razon. Paga 250€ a cada jugador"));
        mazo.alMazo(new Sorpresa_irCarcel(tablero));
        mazo.alMazo(new Sorpresa_salirCarcel(mazo));

    }
    
    void actualizarInfo(){
        System.out.println(jugadores.get(indiceJugadorActual).toString());
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(tablero.getPorSalida() > 0)
            jugadores.get(indiceJugadorActual).pasaPorSalida();
    }
    
    private void pasarTurno(){
        indiceJugadorActual = (indiceJugadorActual+1)%jugadores.size();
    }
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
    
    public Boolean construirCasa(int ip){
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public Boolean construirHotel(int ip){
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    public Boolean vender(int ip){
        return jugadores.get(indiceJugadorActual).vender(ip);
    }
    
    public Boolean hipotecar(int ip){
        return jugadores.get(indiceJugadorActual).hipotecar(ip);
    }
    
    public Boolean cancelarHipoteca(int ip){
        return jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
    }
    
    public Boolean salirCarcelPagando(){
        return jugadores.get(indiceJugadorActual).salirCarcelPagando();
    }
    
    public Boolean salirCarcelTirando(){
        return jugadores.get(indiceJugadorActual).salirCarcelTirando();
    }
    
    public Boolean finalDelJuego(){
        for(int i=0; i<jugadores.size(); i++){
            if(jugadores.get(i).enBancarrota())
                return true;
        }
        
        return false;
    }
    
    public ArrayList<Jugador> ranking(){
        Collections.sort(jugadores);
        return jugadores;
    }
    
    public Casilla getCasillaActual(){
        return tablero.getCasilla(jugadores.get(indiceJugadorActual).getNumCasillaActual());
    }
    
    public Jugador getJugadorActual(){
        return jugadores.get(indiceJugadorActual);
    }
    
    public String infoJugadorTexto(){
        return jugadores.get(indiceJugadorActual).toString();
    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual,jugadores);
        contabilizarPasosPorSalida(jugadorActual);
    }
    
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual, estado);
        
        switch(operacion){
            case PASAR_TURNO:
                pasarTurno();
                siguientePasoCompletado(operacion);
            break;
            
            case AVANZAR:
                avanzaJugador();
                siguientePasoCompletado(operacion);
            break;
            
            default:
            break;
        }
        
        return operacion;
            
    }
    
    public boolean comprar(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        Casilla_Calle casilla = (Casilla_Calle) tablero.getCasilla(numCasillaActual);
        TituloPropiedad titulo = casilla.getTituloPropiedad();
        boolean res = jugadorActual.comprar(titulo);
        
        return res;
    }
    
    public Casilla getCasilla(int i){
        return tablero.getCasilla(i);
    }
}

