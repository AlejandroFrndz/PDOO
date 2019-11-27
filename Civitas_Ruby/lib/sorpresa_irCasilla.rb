require_relative 'sorpresa.rb'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_irACasilla < Sorpresa
    
    def initialize(tablero, valor, texto)
      super()
      @tablero = tablero
      @valor = valor
      @texto = texto
    end
    
    def aplicarAJugador(actual, todos)
      if(jugadorCorrecto(actual,todos))
        informe(actual, todos)
        casillaActual = todos[actual].getNumCasillaActual()
        tirada = @tablero.calcularTirada(casillaActual, @valor)
        @tablero.nuevaPosicion(casillaActual, tirada)
        #recibeJugador por implementar
      end
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo irACasilla y " + to_s())
    end
  end
end