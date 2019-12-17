#!/bin/env ruby
# encoding: utf-8
require_relative "titulo_propiedad.rb"
require_relative 'dado.rb'
require_relative 'diario.rb'

module Civitas
  class Jugador
    
    attr_reader :nombre, :numCasillaActual, :propiedades, :puedeComprar, :saldo , :encarcelado, :puedeComprar, :salvoconducto
    
    @@CASASMAX = 4
    @@CASASPORHOTEL = 4
    @@PASOPORSALIDA = 1000
    @@PRECIOLIBERTAD = 200
    @@HOTELESMAX = 4
    @@SALDOINICIAL = 7500
    
    def initialize(name)
      @nombre = name
      @propiedades = []
      @encarcelado = false
      @numCasillaActual = 0
      @puedeComprar = true
      @saldo = @@SALDOINICIAL
      @salvoconducto = nil
    end
    
    def self.copia(orig)
      jugador = new(orig.nombre)
      jugador.from(orig)
      return jugador
    end
    
    def from(orig)
      @nombre = orig.nombre
      @propiedades = []
      for i in (0..orig.propiedades.length-1)
        @propiedades.push(orig.propiedades[i])
      end
      @encarcelado = orig.encarcelado
      @numCasillaActual = orig.numCasillaActual
      @puedeComprar = orig.puedeComprar
      @saldo = orig.saldo
      @salvoconducto = orig.salvoconducto
    end
    
    def self.getCasasMax()
      return @@CASASMAX
    end
    
    def self.getCasasPorHotel()
      return @@CASASPORHOTEL
    end
    
    def self.getHotelesMax()
      return @@HOTELESMAX
    end
    
    def self.getPremioPasoPorSalida()
      return @@PASOPORSALIDA
    end
    
    def self.getPrecioLibertad
      return @@PRECIOLIBERTAD
    end
    
    def cantidadCasasHoteles()
      amount = 0
      
      for i in (0..@propiedades.length)
        amount += @propiedades[i].cantidadCasasHoteles()
      end
      
      return amount
    end
    
    def enBancarrota()
      return @saldo <= 0
    end
    
    def existeLaPropiedad(ip)
      return ip >= 0 && ip < @propiedades.length
    end
    
    def puedoEdificarCasa(propiedad)
      return propiedad.numCasas < @@CASASMAX && @saldo >= propiedad.precioEdificar && @propiedades.include?(propiedad)
    end
    
    def puedoEdificarHotel(propiedad)
      return propiedad.numHoteles < @@HOTELESMAX && @saldo >= propiedad.precioEdificar && @propiedades.include?(propiedad) && propiedad.numCasas == @@CASASMAX
    end
    
    def debeSerEncarcelado()
      if @encarcelado
        return false
      else
        if !tieneSalvoconducto()
          return true
         
        else
          perderSalvoConducto()
          Diario.instance.ocurre_evento("¡ #{@nombre} se libra de la cárcel!")
          return false
        end
        
      end
      
    end
    
    def encarcelar(numCasillaCarcel)
      if debeSerEncarcelado()
        moverACasilla(numCasillaCarcel)
        @encarcelado = true
        Diario.instance.ocurre_evento("#{@nombre} ha sido encarcelado")
      end
      
      @encarcelado
    end
    
    def obtenerSalvoconducto(sorpresa)
      if @encarcelado
        return false
      else
        @salvoconducto = sorpresa
        return true
        
      end
    end
    
    def perderSalvoConducto()
      if @salvoconducto != nil
        @salvoconducto.usada()
        @salvoconducto = nil
      end
    end
    
    def tieneSalvoconducto()
      return @salvoconducto != nil
    end
    
    def puedeComprarCasilla()
      @puedeComprar = !@encarcelado
      @puedeComprar
    end
    
    def paga(cantidad)
      return modificarSaldo(cantidad*(-1))
    end
    
    def pagaImpuesto(cantidad)
      if @encarcelado
        return false
      else
        return paga(cantidad)
      end
    end
    
    def pagaAlquiler(cantidad)
      if(@encarcelado)
        return false
      else
        return paga(cantidad)
      end
    end
    
    def recibe(cantidad)
      if @encarcelado
        return false
        
      else
        return modificarSaldo(cantidad)
      end
    end
    
    def modificarSaldo(cantidad)
      @saldo += cantidad
      
      if cantidad >= 0
        Diario.instance.ocurre_evento("El saldo de #{@nombre} se ha incrementado #{cantidad}")
      else
        Diario.instance.ocurre_evento("El saldo de #{@nombre} se ha decrementado #{cantidad}") 
      end
    end
    
    def moverACasilla(numCasilla)
      if @encarcelado
        return false
        
      else
        @numCasillaActual = numCasilla
        #@puedeComprar = false
        Diario.instance.ocurre_evento("#{@nombre} se me mueve a la casilla nº #{numCasilla}")
      end
    end
    
    def puedoGastar(cantidad)
      if @encarcelado
        return false
      else
        return @saldo >= cantidad
      end
    end
    
    def vender(ip)
      if @encarcelado
        return false
      else
        if(existeLaPropiedad(ip))
          if @propiedades[ip].vender(self)
            Diario.instance.ocurre_evento("#{@nombre} ha vendido #{@propiedades[ip].nombre}")
            @propiedades.delete_at(ip);
            return true
          else
            return false
          end
          
        else
          return false
        end
      end
    end
    
    def tieneAlgoQueGestionar()     
      return !@propiedades.empty?
    end
    
    def puedeSalirCarcelPagando()
      return @saldo >= @@PRECIOLIBERTAD
    end
    
    def salirCarcelPagando()
      if @encarcelado && puedeSalirCarcelPagando()
        if paga(@@PRECIOLIBERTAD)
          @encarcelado = false
          Diario.instance.ocurre_evento("#{@nombre} ha comprado su libertad y ya no esta encarcelado")
          return true
          
        else
          return false
        end
        
      else
        return false
      end
    end
    
    def salirCarcelTirando()
      
      puts "\n\n se esta repitiendo el salirCarceTirando y no deberia \n\n"
      
      if Dado.instance.salgoDeLaCarcel()
        @encarcelado = false
        Diario.instance.ocurre_evento("¡#{@nombre} ha conseguido salir de la cárcel!")
        return true
      else
        return false
      end
    end
    
    def pasaPorSalida()
      modificarSaldo(@@PASOPORSALIDA)
      Diario.instance.ocurre_evento("#{@nombr} ha pasado por la salida y recibe bonus de #{@@PASOPORSALIDA}")
      return true
    end
    
    def <=>(otro)
      return @saldo <=> otro.saldo
    end
    
    def to_s()
      cadena = "#{@nombre} \n Casilla Actual: #{@numCasillaActual} \nSaldo: #{@saldo} \nPropiedades: "
      if(!@propiedades.empty?)
        for i in (0..@propiedades.length-1)
          cadena += "#{@propiedades[i].nombre}, "
        end
      else
        cadena += "Ninguna"
      end
      
      cadena += "\n Puede Comprar: "
      
      if @puedeComprar
        cadena += "SI"
      else
        cadena += "NO"
      end
      
      cadena += "\n Está encarcelado: "
      
      if @encarcelado
        cadena += "SI"
      else
        cadena += "NO"
      end
      
      cadena
    end
    
    def construirCasa(ip)
      result = false
      if(@encarcelado)
        return result
      end
      
      if(!@encarcelado && existeLaPropiedad(ip))
        propiedad = @propiedades[ip]
        puedoEdificarCasa = puedoEdificarCasa(propiedad)
        
        if(puedoEdificarCasa)
          result = propiedad.construirCasa(self);
          Diario.instance.ocurre_evento("El jugador #{@nombre} construye casa en la propiedad #{ip}")
        end
        
      end
      
      return result
      
    end
    
    def construirHotel(ip)
      result = false
      if(@encarcelado)
        return result
      end
      
      if(!@encarcelado && existeLaPropiedad(ip))
        propiedad = @propiedades[ip]
        puedoEdificarHotel = puedoEdificarHotel(propiedad)
        
        if(puedoEdificarHotel)
          result = propiedad.construirHotel(self);
          propiedad.derruirCasas(@@CASASPORHOTEL, self)
          Diario.instance.ocurre_evento("El jugador #{@nombre} construye hotel en la propiedad #{ip}")
        end
        
      end
      
      return result
    end
    
    def hipotecar(ip)
      result = false
      
      if (@encarcelado)
        return result
      end
      
      if existeLaPropiedad(ip)
        propiedad = @propiedades[ip]
        result = propiedad.hipotecar(self)
      end
      
      if(result)
        Diario.instance.ocurre_evento("El jugador #{@nombre} hipoteca la propiedad #{ip}")
      end
      
      return result
    end
    
    def cancelarHipoteca(ip)
      result = false
      
      if(@encarcelado)
        return result
      end
      
      if (existeLaPropiedad(ip))
        titulo = @propiedades[ip]
        cantidad = titulo.getImporteCancelarHipoteca()
        puedoGastar = puedoGastar(cantidad)
        
        if puedoGastar
          result = titulo.cancelarHipoteca(self)
        end
      end
      if result
          Diario.instance.ocurre_evento("El jugador #{@nombre} cancela la hipoteca de #{ip}")
      end
      return result
    end
    
    def comprar(titulo)
      result = false
      
      if(@encarcelado)
        return result
      end
      
      if(@puedeComprar)
        precio = titulo.precioCompra()
        
        if(puedoGastar(precio))
          result = titulo.comprar(self)
        end
      end
      
      if(result)
        @propiedades.push(titulo)
        Diario.instance.ocurre_evento("El jugador #{@nombre} compra la propiedad #{titulo.to_s()}")
      end
    end
    
    def puede_comprar
      @puedeComprar
    end
    
    private :existeLaPropiedad, :perderSalvoConducto, :puedeSalirCarcelPagando, :puedoEdificarCasa, :puedoEdificarHotel, :puedoGastar
    protected :debeSerEncarcelado,  :saldo #:from #volver a colocar nombre :propiedades, poner a protected supuestamente
    private_class_method :getCasasMax, :getHotelesMax, :getPrecioLibertad, :getPremioPasoPorSalida
  end
end

