package pokemonGym

/**
 * @author seb
 */
package object gim {
  
  // TODO esto debería retornar un Option[String] (no siempre tengo una mejor rutina)
  def dameLaMejorRutina(estadoInicial: Estado,
      // TODO el criterio podría ser un Ordering[Estado]
                        funcionCriterio: (Estado => Int),
                        listaDeRutinas: Rutina*) : String = {
    val listaDeEstadosDeRutinas = listaDeRutinas.map {
      rutina => ejecutar(estadoInicial, rutina)
    }
    val listaDeEstadosOrdenadosSegunCriterio = listaDeEstadosDeRutinas.filter {
      estado => estado match {
        case Invalido(_,_) => false
        case _ => true
      }
      // TODO de hecho esto está usando el implicit Ordering[Int] (casi lo mismo pero pueden definir el orden directamente
    }.sortBy(funcionCriterio)
    if (listaDeEstadosOrdenadosSegunCriterio.isEmpty) {
      /*
       * TODO Cuidado!: no usar null, si algo puede tener un resultado o no usen Option o si algo puede terminar
       * en error usen Try
       */
      null
    } else {
      val mejorEstado = listaDeEstadosOrdenadosSegunCriterio.last
      val indiceDelMejorEstado = listaDeEstadosDeRutinas.indexOf(mejorEstado)
      listaDeRutinas(indiceDelMejorEstado).nombre
    }
  }
  
  def ejecutar(estadoInicial : Estado, rutina: Rutina): Estado = {
    ejecutar(estadoInicial, rutina.actividades.toSeq : _ *)
  }
  
  // TODO este método tiene mucho comportamiento:
  // - hacer que las actividades sean funciones Estado => Estado y cambiar ese match gigante por un "apply"
  // - Dormido podría cambiar de estado en el map y no acá
  def ejecutar(estadoInicial : Estado, listaDeActividades: Actividad*): Estado = {
    listaDeActividades.foldLeft(estadoInicial) { (resultadoAnterior, actividadActual) => {

      val estadoActual =  resultadoAnterior match {
        case Dormido(pok, 0) => OK(pok)
        case Dormido(pok, cont) => Dormido(pok, cont - 1)
        case _ => resultadoAnterior
      }
      
      val estadoDespuesDeActividad: Estado = actividadActual match {
        case RealizarAtaque(ataque @ Ataque(tipo, _, efecto)) if estadoActual.pokemon.conoceAtaque(ataque) && estadoActual.pokemon.puntosDeAtaque(ataque) > 0 =>
          val estadoDspDeRealizarAtaque = estadoActual.map(pokemon => {
              val expGanada = tipo match {
                case Dragon => 80
                case pokemon.especie.tipoPrincipal => 50
                case pokemon.especie.tipoSecundario =>
                  pokemon.genero match {
                    case 'F' => 40
                    case 'M' => 20
                }
              }
              // TODO no sería más cómodo usar un Try y dejar que usarAtaque rompa si no puede usarlo?
              // (esto implicaría que tienen que cambiar el estado Invalido o sacarlo... evalúen que les parece mejor)
              pokemon.usarAtaque(ataque).ganarExperiencia(expGanada)
            })
          efecto(estadoDspDeRealizarAtaque)
          
        case RealizarAtaque(ataque) => estadoActual.flatMap(poke => Invalido(poke,
            "El pokemon quiso ejecutar el ataque " + ataque.toString() + " que es desconocido o no tiene puntos de ataque"))
        
        case LevantarPesas(kilos) => {
          if (estadoActual.pokemon.esDelTipo(Fantasma)) {
            estadoActual.flatMap( poke => Invalido(poke, "Los pokemons del tipo fantasma"))
          } else {
            estadoActual match {
              case estado @ Paralizado(_) => KO(estado.pokemon)
              case estado @ _ => {
                if (kilos > 10 * estado.pokemon.fuerza) {
                  estado.flatMap( poke => Paralizado(poke))
                } else {
                  val experienciaGanada = if (estado.pokemon.esDelTipo(Pelea)) 2 * kilos else kilos
                  estado.map( poke => poke.ganarExperiencia(experienciaGanada))
                }
              }
            }
          }
        }

        case Nadar(tiempo: Int) => {
          // TODO falta el tipo secundario
          if (Agua.leGanaA(estadoActual.pokemon.especie.tipoPrincipal))
            estadoActual.flatMap(pokemon => KO(pokemon))
          else
            estadoActual.map(poke => poke.nadar(tiempo))
        }

        // TODO detalle: es más facil usar "_" para este tipo de funciones
        case UsarPocion() => estadoActual.map(_.usarPocion())

        case ComerCalcio() => {
          estadoActual.map(pokemon => pokemon.comerCalcio())
        }

        case ComerZinc() => {
          estadoActual.map(pokemon => pokemon.comerZinc())
        }

        case UsarAntidoto() => {
          estadoActual match {
            case estado @ Envenenado(_) => estado.flatMap(poke => OK(poke))
            case estado @ _ => estado
          }
        }

        case UsarEther() => {
          estadoActual match {
            case estado @ KO(_) => estado
            case estado => estado.flatMap(poke => OK(poke))
          }
        }

        case Descansar() => {
          estadoActual match {
            case estado @ OK(_) =>
              if( estado.pokemon.energia < estado.pokemon.energiaMaxima / 2){
                estado.flatMap(poke => Dormido(poke.recobrarPuntosAtaque()))
              } else {
                estado.map(poke => poke.recobrarPuntosAtaque())
              }
            case estado => estado.map(poke => poke.recobrarPuntosAtaque())
          }
        }

        case UsarPiedraParaEvolucionar(tipoPiedra: Tipo) =>
            estadoActual.flatMap(poke => {
              if(tipoPiedra.leGanaA(poke.especie.tipoPrincipal) ||
                  tipoPiedra.leGanaA(poke.especie.tipoSecundario)){
                Envenenado(poke)
              } else {
                estadoActual.map(poke => poke.usarPiedra(tipoPiedra))
              }
            }
          )

        case FingirIntercambio() => estadoActual.map(poke => {
          poke.especie.condicionEvolutiva match {
            case Intercambiar() => poke.copy(especie= poke.especie.evolucion)
            // TODO donde valida el peso máximo?
            case _ => if(poke.genero.equals('M')) poke.copy(peso = poke.peso + 1) else poke.copy(peso = poke.peso - 10)
          }
        })
      }

      if (estadoDespuesDeActividad.pokemon.valido()) {
        estadoDespuesDeActividad
      } else {
        Invalido(estadoDespuesDeActividad.pokemon, "Las caracteristicas del pokemon son invalidas")
      }
    }}
  }
}