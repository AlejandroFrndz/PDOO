#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_casilla.rb"
require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla
    attr_reader :nombre, :tituloPropiedad, :tipo #añado tipo para hacer la comprabacion sobre las sorpresas
    
    def initialize(tipo, nombre = nil, cantidad = 0, titulo = nil, mazo = nil)
      @nombre  = nombre;
      @importe = cantidad;
      @tituloPropiedad = titulo;
      @tipo = tipo;
      @sorpresa = nil;
      @mazo = mazo;
    end

    def self.newDescanso(nombre)
      new(TipoCasilla::DESCANSO, nombre)
    end
    
    def self.newCarcel(nombre, carcel)
      @@CARCEL = carcel
      new(TipoCasilla::DESCANSO, nombre)
    end
    
    def self.newCalle(titulo)
      new(TipoCasilla::CALLE,titulo.nombre, titulo.precioCompra, titulo)
    end
    
    def self.newImpuesto(cantidad, nombre)
      new(TipoCasilla::IMPUESTO, nombre, cantidad)
    end
    
    def self.newJuez(nombre)
      new(TipoCasilla::JUEZ, nombre)
    end
    
    def self.newSorpresa(mazo,nombre)
      new(TipoCasilla::SORPRESA, nombre, 0, nil, mazo)
    end
    
    def jugadorCorrecto(actual, todos)
      actual < todos.count() && actual > -1
    end
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual, todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en la casilla " + to_s)
      end
    end
    
    def recibeJugador_juez(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].encarcelar(@@CARCEL)
      end
    end
    
    def recibeJugador_impuesto(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].pagaImpuesto(@importe)
      end
    end
    
    def to_s()
      cadena = @nombre.to_s + " de tipo: " + @tipo.to_s + "\n"
      
      if(@tipo == TipoCasilla::CALLE || @tipo == TipoCasilla::IMPUESTO)
        cadena += "Precio: " + @importe.to_s + "\n"
      end
      
      cadena += "\n"
    end
    
    def recibeJugador(iactual, todos)
      if(@tipo != nil)
        case @tipo
        when TipoCasilla::CALLE
          recibeJugador_calle(iactual, todos)
        when TipoCasilla::IMPUESTO
          recibeJugador_impuesto(iactual, todos)
        when TipoCasilla::JUEZ
          recibeJugador_juez(iactual, todos)
        when TipoCasilla::SORPRESA
          recibeJugador_sorpresa(iactual, todos)
        else
          informe(iactual, todos)
        end
      end
    end
    
    def recibeJugador_calle(iactual,todos)
      if(jugadorCorrecto(iactual,todos))
        informe(iactual,todos)
        jugador = Jugador.new(todos[iactual])
        
        if(!@tituloPropiedad.tienePropietario)
          jugador.puedeComprarCasilla
        else
          @tituloPropiedad.tramitarAlquiler(todos[iactual])
        end
      end
    end
    
    def recibeJugador_sorpresa(iactual,todos)
      if(jugadorCorrecto(iactual,todos))
        sorpresa = @mazo.siguiente
        informe(iactual,todos)
        sorpresa.aplicarAJugador(iactual, todos)
      end
    end
    
    private :informe, :initialize, :recibeJugador_juez, :recibeJugador_impuesto, :recibeJugador_sorpresa, :recibeJugador_calle
  end
end
