require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla_Sorpresa < Casilla
    
    def initialize (mazo, name)
      super(name)
      @mazo = mazo
    end
    
    def recibeJugador(iactual,todos)
      if(jugadorCorrecto(iactual,todos))
        sorpresa = @mazo.siguiente
        informe(iactual,todos)
        sorpresa.aplicarAJugador(iactual, todos)
      end
    end
    
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual,todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en una sorpresa. !Buena suerte!")

      end
    end
    
  end
end