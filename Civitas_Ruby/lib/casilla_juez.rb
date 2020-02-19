require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla_Juez < Casilla
    
    def initialize (numCasillaCarcel, name)
      super(name)
      @@CARCEL = numCasillaCarcel
    end
    
    def recibeJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].encarcelar(@@CARCEL)
      end
    end
    
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual,todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en el juez")
      end
    end
    
  end
end