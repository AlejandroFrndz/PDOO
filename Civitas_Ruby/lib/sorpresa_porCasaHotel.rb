module Civitas
  class Sorpresa_porCasaHotel < Sorpresa
    
    def initialize(valor, texto)
      super()
      @valor = valor
      @texto = texto
    end
    
    def aplicarAJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].modificarsaldo(@valor * todos[actual].cantidadCasasHoteles())
      end
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo porCasaHotel y " + to_s())
    end
  end
end