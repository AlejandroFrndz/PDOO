require_relative 'sorpresa.rb'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_irCarcel < Sorpresa
    
    def initialize(tablero)
      super()
      @tablero = tablero
    end
    
    def aplicarAJugador(acutal, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].encarcelar(@tablero.getCarcel())
      end
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo irCarcel y " + to_s())
    end
    
  end
end