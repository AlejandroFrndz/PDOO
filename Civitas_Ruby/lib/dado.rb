# encoding:utf-8
require 'singleton'

module Civitas
  class Dado
    include Singleton
    @@SalidaCarcel = 5
    
    attr_reader :ultimo_resultado
    
    def initialize()
      @random = rand(6)+1
      @ultimo_resultado = 0
      @debug = false
    end
    
    def tirar()
      if @debug === false
        @random = rand(6)+1
      else
        @random = 1
      end
      
      @ultimo_resultado = @random
      return @random
    end
    
    def salgoDeLaCarcel()
      self.tirar()
      
      if @ultimo_resultado >= @@SalidaCarcel
        return true
        
      else
        return false
        
      end
    end
    
    def quienEmpieza(n)
      return rand(n)
      
    end
      
    def setDebug(activar)
      if(activar)
        @debug = true
        Diario.instance.ocurre_evento("Modo Debug  del Dado activado")
      else
        @debug = false
        Diario.instance.ocurre_evento("Modo Debug  del Dado desactivado")
      end
      @debug 
      
    end
    
    private :initialize
  end
end