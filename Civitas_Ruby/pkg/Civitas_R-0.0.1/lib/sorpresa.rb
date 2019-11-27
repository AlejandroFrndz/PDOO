#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_sorpresa.rb'
require_relative 'mazo_sorpresas.rb'
require_relative 'tablero.rb'

module Civitas
  class Sorpresa
    
    def init()
      @valor = -1
      @mazo = nil
      @tablero = nil
      @texto = ""
    end
    
    def initialize(tipo, tablero=nil, valor=0, texto="", mazo=nil)
      @tipo = tipo
      @tablero = tablero
      @valor = valor
      @texto = texto
      @mazo = mazo
    end
        
    def self.newPAGARCOBRAR(valor, texto)
        new(Tipo_Sorpresa::PAGARCOBRAR, nil, valor, texto)
    end
    
    def self.newIRCASILLA(tablero, valor, texto)
      new(Tipo_Sorpresa::IRCASILLA, tablero, valor, texto)
    end
    
    def self.newPORCASAHOTEL(valor, texto)
      new(Tipo_Sorpresa::PORCASAHOTEL, nil, valor, texto)
    end
    
    def self.newPORJUGADOR(valor, texto)
      new(Tipo_Sorpresa::PORJUGADOR, nil, valor, texto)
    end
    
    def self.newIRCARCEL(tablero)
      new(Tipo_Sorpresa::IRCARCEL, tablero, -1, "IrCarcel")
    end
    
    def self.newSALIRCARCEL(mazo)
      new(Tipo_Sorpresa::SALIRCARCEL, nil, -1, "SalirCarcel", mazo)
    end
    
    def jugadorCorrecto(actual, todos)
      actual < todos.count() && actual > -1
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo " + @tipo.to_s + " y " + to_s())
    end
    
    def aplicarAJugador(actual, todos)
      unless todos == nil then
        case @tipo
        when Tipo_Sorpresa::IRCARCEL then aplicarAJugador_irCarcel(actual, todos)
        when Tipo_Sorpresa::IRCASILLA then aplicarAJugador_irACasilla(actual, todos)
        when Tipo_Sorpresa::PAGARCOBRAR then aplicarAJugador_pagarCobrar(actual, todos)
        when Tipo_Sorpresa::PORCASAHOTEL then aplicarAJugador_porCasaHotel(actual, todos)
        when Tipo_Sorpresa::PORJUGADOR then aplicarAJugador_porJugador(actual, todos)
        when Tipo_Sorpresa::SALIRCARCEL then aplicarAJugador_salirCarcel(actual, todos)
        else
          return 1
        end
      end
    end
    
    def aplicarAJugador_irCarcel(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].encarcelar(@tablero.getCarcel())
      end
    end
    
    def aplicarAJugador_irACasilla(actual, todos)
      if(jugadorCorrecto(actual,todos))
        informe(actual, todos)
        casillaActual = todos[actual].getNumCasillaActual()
        tirada = @tablero.calcularTirada(casillaActual, @valor)
        @tablero.nuevaPosicion(casillaActual, tirada)
        #recibeJugador por implementar
      end
    end
    
    def aplicarAJugador_pagarCobrar(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].modificarSaldo(@valor)
      end
    end
    
    def aplicarAJugador_porCasaHotel(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].modificarsaldo(@valor * todos[actual].cantidadCasasHoteles())
      end
    end
    
    def aplicarAJugador_porJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        pagarcobrar = Sorpresa.newPAGARCOBRAR(Tipo_Sorpresa::PAGARCOBRAR, @valor*-1, "")
        for i in 0..todos.count() do
          if(i != actual)
            pagarcobrar.aplicarAJugador(i, todos)
          end
        end
        pagarcobrar2 = Sorpresa.newPAGARCOBRAR(Tipo_Sorpresa::PAGARCOBRAR, @valor*(todos.count()-1), "")
        pagarcobrar2.aplicarAJugador(actual, todos)
      end
    end
    
    def aplicarAJugador_salirCarcel(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        
        salvoconducto = false;
        for i in 0..todos.count()
          if(!salvoconducto)
            salvoconducto = todos[i].tieneSalvoConduncto()
        end
      end
      
        if(!salvoconducto)
          todos[actual].obtenerSalvoconducto(self)
          self.salirDelMazo();
        end
      end
    end
    
    
    def salirDelMazo()
      if(@tipo == Tipo_Sorpresa::SALIRCARCEL)
        @mazo.inhabilitarCartaEspecial(self)
      end
    end
    
    def usada()
      if(@tipo == Tipo_Sorpresa::SALIRCARCEL)
        @mazo.habilitarCartaEspecial(self)
      end
    end
    
    def to_s()
      @texto;
    end
    
    private :initialize, :aplicarAJugador_irACasilla, :aplicarAJugador_irCarcel, :aplicarAJugador_pagarCobrar, :aplicarAJugador_porCasaHotel, :aplicarAJugador_porJugador, :aplicarAJugador_salirCarcel, :informe, :init
  end
end

