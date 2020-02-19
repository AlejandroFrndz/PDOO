#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'sorpresa.rb'
require_relative 'diario.rb'

module Civitas
  class MazoSorpresas
    
    attr_reader :ultimaSorpresa

    def init()
      @sorpresas = Array.new()
      @cartasEspeciales = Array.new()
      @barajada = false;
      @usadas = 0;
    end
    
    def initialize(modo = false)
      init()
      @debug = modo;
      if @debug then 
        Diario.instance().ocurre_evento("Modo debug del Mazo activado")
      end
    end
    
    def alMazo(s)
      if !@barajada then
        @sorpresas.push(s)
      end
    end
    
    def siguiente()
      if !@debug && (@usadas >= @sorpresas.count() || !@barajada) then
        @sorpresas.shuffle
        @barajada = true
        @usadas = 0
      end
      
      @usadas = @usadas + 1
      extraida = @sorpresas[0]
      @sorpresas.delete_at(0)
      @sorpresas.push(extraida)
      @ultimaSorpresa = extraida
      return extraida
    end
    
    def inhabilitarCartaEspecial(sorpresa)
      remove = nil
      remove = @sorpresas.delete(sorpresa)
      if remove != nil then
        @cartasEspeciales.push(sorpresa)
        Diario.instance.ocurre_evento("Se inhabilita la carta especial" + sorpresa.to_s())
        return true
      else
        return false
      end
    end
    
    def habilitarCartaEspecial(sorpresa)
      remove = nil
      remove = @cartasEspeciales.delete(sorpresa)
      if remove != nil then
        @sorpresas.push(sorpresa)
        Diario.instance.ocurre_evento("Se habilita la carta especial " + sorpresa.to_s())
        return true
      else
        return false
      end
    end
      
    private :init
  end
end
