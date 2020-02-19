require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla_Impuesto < Casilla
    
    def initialize (cantidad, nombre)
      super(nombre)
      @importe = cantidad
    end
    
    def recibeJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].pagaImpuesto(@importe)
      end
    end
    
    def to_s()
      cadena = "#{@nombre} Precio: #{@importe}"
      return cadena
    end
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual,todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en el impuesto de " + to_s)

      end
    end
    
  end
end