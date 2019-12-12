require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla_Calle < Casilla
    attr_reader :tituloPropiedad
    
    def initialize (titulo)
      super(titulo.nombre)
      @tituloPropiedad = titulo
      @importe = titulo.precioCompra
    end
    
    def recibeJugador(actual, todos)
      if(jugadorCorrecto(actual,todos))
        informe(actual,todos)
        jugador = Jugador.new(todos[actual])
        
        if(!@tituloPropiedad.tienePropietario)
          jugador.puedeComprarCasilla
        else
          @tituloPropiedad.tramitarAlquiler(todos[actual])
        end
      end
    end
    
    def to_s()
      cadena = "#{@nombre} Precio: #{@importe}"
      return cadena
    end
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual,todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en la casilla " + to_s)

      end
    end
    
  end
end