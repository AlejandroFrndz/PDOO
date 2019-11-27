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
      #@valor = -1
      #@mazo = nil
      #@tablero = nil
      @texto = ""
    end
    
    def initialize()
      #@tipo = tipo
      #@tablero = tablero
      #@valor = valor
      @texto = ""
      #@mazo = mazo
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
    
      
    
    
    def to_s()
      @texto;
    end
    
  end
end

