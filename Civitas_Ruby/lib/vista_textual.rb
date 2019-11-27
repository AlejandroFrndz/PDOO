#encoding:utf-8
require_relative 'operaciones_juego.rb'
require_relative 'civitas_juego.rb'
require 'io/console'
require_relative 'jugador.rb'
require_relative 'titulo_propiedad.rb'
require_relative 'civitas_juego.rb'
require_relative 'operacion_inmobiliaria.rb'
require_relative 'gestiones_inmobiliarias.rb'
require_relative 'diario.rb'
require_relative 'salidas_carcel.rb'
require_relative 'respuestas.rb'


module Civitas

  class Vista_textual
    attr_reader :juegoModel, :iGestion, :iPropiedad
    def initialize()
      @iGestion = -1
      @iPropiedad = -1
      @juegoModel
      @separador = "====================="
    end

    def mostrar_estado(estado)
      puts estado
    end

    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end



    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end

    
    def comprar()
      respuestas = [Respuestas::NO, Respuestas::SI]
      respuestas_s = ["no", "si"]
      opcion = menu("¿Quieres comprar la calle?", respuestas_s)
      
      return respuestas[opcion]
    end

    def gestionar
      
      respuestas = [GestionesInmobiliarias::VENDER, GestionesInmobiliarias::HIPOTECAR, GestionesInmobiliarias::CANCELAR_HIPOTECA, GestionesInmobiliarias::CONSTRUIR_CASA, GestionesInmobiliarias::CONSTRUIR_HOTEL, GestionesInmobiliarias::TERMINAR]
      respuestas_s = ["vender", "hipotecar", "cancelar_hipoteca", "construir_casa", "construir_hotel", "terminar"]
      opcion = menu("Elige la gestión inmobiliria que quieres realizar",respuestas_s)
      @iGestion = opcion
      
   
      
      if(@iGestion != 5)
        propiedades_name = []
        propiedades_titulo = @juegoModel.getJugadorActual().propiedades
        for i in 0..propiedades_titulo.size()-1
          propiedades_name.push(propiedades_titulo[i].nombre)
        end
        
        opcion = menu("¿Sobre qué propiedad?", propiedades_name)
        @iPropiedad = opcion
      end
      
      
    end


    def mostrarSiguienteOperacion(operacion)
      puts operacion
    end

    def mostrarEventos
      while Diario.instance.eventos_pendientes()
        puts Diario.instance.leer_evento()
      end
    end

    def setCivitasJuego(civitas)
         @juegoModel=civitas
         self.actualizarVista
    end

    def actualizarVista
      puts @juegoModel.getJugadorActual().to_s()
      puts @juegoModel.getCasillaActual().to_s()
    end
    
    def salirCarcel
      formas_salir = [SalidasCarcel::PAGANDO, SalidasCarcel::TIRANDO]
      formas_salir_s = ["pagando","tirando"]
      opcion = menu("Elige la forma para salir de la carcel", formas_salir_s)
     
      return formas_salir[opcion]
    end

    
  end

end
