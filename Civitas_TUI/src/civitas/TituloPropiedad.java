/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author alejandro
 */
public class TituloPropiedad {
    private float alquilerBase;
    private static final float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    private Jugador propietario;
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe ){
        nombre = nom;
        alquilerBase = ab;
        factorRevalorizacion = fr;
        hipotecaBase = hb;
        precioCompra = pc;
        precioEdificar = pe;
        propietario = null;
        numCasas = numHoteles = 0;
        hipotecado = false;
    }
    
    @Override
    public String toString(){
        String cadena = nombre + ": \n Alquiler Base: " + alquilerBase + "\n Factor de Revalorización: " + factorRevalorizacion +
                "\n Hipoteca Base: " + hipotecaBase + "\n Precio de Compra: " + precioCompra + "\n Precio Edificacion: " + precioEdificar + 
                "\n Número de casas: " + numCasas + "\n Número de hoteles: " + numHoteles + "\n Propietario: " + propietario.getNombre();
        
        if(hipotecado)
            cadena += "\n HIPOTECADA";
        else
            cadena += "\n NO HIPOTECADA";
        
        return cadena;
    }
    
    float getPrecioAlquiler(){
        if(hipotecado || propietarioEncarcelado())
            return 0;
        else
            return alquilerBase*(1+(numCasas*(float)0.5)+(numHoteles*(float)2.5)); 
    }
    
    float getImporteCancelarHipoteca(){
        return hipotecaBase*factorInteresesHipoteca;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    float getPrecioCompra(){
        return precioCompra;
    }
    
    Boolean cancelarHipoteca(Jugador jugador){
        if(hipotecado && esEsteElPropietario(jugador)){
            if(jugador.paga(getImporteCancelarHipoteca())){
                hipotecado = false;
                return true;
            }
            
            else
                return false;
        }
        
        else
            return false;
    }
    
    Boolean hipotecar(Jugador jugador){
        if(!hipotecado && esEsteElPropietario(jugador)){
            jugador.recibe(hipotecaBase);
            hipotecado = true; //cambio hecho para cancelar hipoteca, comprobar
            return true;
        }
        
        else
            return false;
    }
    
    private Boolean esEsteElPropietario(Jugador jugador){
        return propietario == jugador;
    }
    
    void tramitarAlquiler(Jugador jugador){
        if(tienePropietario() && !esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(alquilerBase);
            propietario.recibe(alquilerBase);
        }
    }
    
    public Boolean tienePropietario(){
        return propietario != null;
    }
    
    private Boolean propietarioEncarcelado(){
        if(tienePropietario())
            return propietario.isEncarcelado();
        else
            return false;
    }
    
    int cantidadCasasHoteles(){
        return numCasas + numHoteles;
    }
    
    Boolean derruirCasas(int n, Jugador jugador){
        if(esEsteElPropietario(jugador) && numCasas >= n){
            numCasas -= n;
            return true;
        }
        
        else
            return false;
    }
    
    private float getPrecioVenta(){
        return precioCompra + factorRevalorizacion * cantidadCasasHoteles() * precioEdificar;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    Boolean construirCasa(Jugador jugador){
        Boolean done = false;
        
        if(esEsteElPropietario(jugador)){
            if(jugador.paga(precioEdificar)){
                numCasas++;
                done = true;
            }
        }
        
        return done;
    }
    
    Boolean construirHotel(Jugador jugador){
        Boolean done = false;
        
        if(esEsteElPropietario(jugador)){
            if(jugador.paga(precioEdificar)){
                numHoteles++;
                done = true;
            }
        }
        
        return done;
    }
    
    Boolean comprar(Jugador jugador){
        Boolean done = false;
        
        if(!tienePropietario()){
            if(jugador.paga(precioCompra)){
                propietario = jugador;
                done = true;
            }
        }
        
        return true;
    }
    
    Boolean vender(Jugador jugador){
        Boolean done = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.recibe(getPrecioVenta());
            propietario = null;
            done = true;
        }
        
        return done;
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        propietario = jugador;
    }
    
    public Boolean getHipotecado(){
        return hipotecado;
    }
    
    private float getImporteHipoteca(){
        return hipotecaBase;
    }
}
