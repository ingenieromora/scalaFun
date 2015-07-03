package pokemonGym

/**
 * @author seb
 */
package object gim {
  
  def ejecutar(pokemon: Pokemon, estadoInicial : Estado, listaDeActividades: Actividad*): Estado = {
    listaDeActividades.foldLeft(estadoInicial) { (resultadoAnterior, actividadActual) => {

      val estadoActual =  resultadoAnterior match {
        case a @ OK(pok) => OK(pok)
        case a @ KO(pok) => KO(pok)
        case a @ Paralizado(pok) => Paralizado(pok)
        case a @ Dormido(pok, 0) => OK(pok)
        case a @ Dormido(pok, cont) => Dormido(pok, cont - 1)
        case a @ Envenenado(pok) => Envenenado(pok)
      }
      
      val estadoDespuesDeActividad = actividadActual match {
        case RealizarAtaque(ataque @ Ataque(tipo, _, efecto)) if pokemon.conoceAtaque(ataque) && pokemon.puntosDeAtaque(ataque) > 0 =>
          val estadoDspDeRealizarAtaque = estadoActual
            .map( poke => poke.usarAtaque(ataque) )
            .map(pokemon => {
              tipo match {
                case Dragon =>
                  pokemon.ganarExperiencia(80)
                case pokemon.especie.tipoPrincipal =>
                  pokemon.ganarExperiencia(50)
                case pokemon.especie.tipoSecundario =>
                  pokemon.genero match {
                    case 'F' =>
                      pokemon.ganarExperiencia(40)
                    case 'M' =>
                      pokemon.ganarExperiencia(20)
                }
              }
            })
          efecto(estadoDspDeRealizarAtaque)
          
        case RealizarAtaque(_) => estadoActual
        
        case LevantarPesas(kilos) => {
          estadoActual match {
            case estado @ Paralizado(_) => estado.flatMap( poke => KO(poke) )
            case estado @ _ => {
              if (kilos > 10 * estado.pokemon.fuerza) {
                estado.flatMap( poke => Paralizado(poke))
              } else {
                val experienciaGanada = estado.pokemon.especie match {
                  case Especie(_,_,Pelea,_,_,_,_,_,_,_) | Especie(_,_,_,Pelea,_,_,_,_,_,_) => 2 * kilos
                  case Especie(_,_,Fantasma,_,_,_,_,_,_,_) | Especie(_,_,_,Fantasma,_,_,_,_,_,_) => 0
                  case _ => kilos
                }
                estado.map( poke => poke.ganarExperiencia(experienciaGanada))
              }
            }
          }
        }

        case UsarPocion() => {
          estadoActual.map(pokemon => pokemon.usarPocion())
        }

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
              }else{
                estado.map(poke => poke.recobrarPuntosAtaque())
              }
            case estado => estado.map(poke => poke.recobrarPuntosAtaque())
          }
        }

        case UsarPiedraParaEvolucionar(tipoPiedra: Tipo) =>
          val nuevoEstado: Estado = estadoActual.map(poke => poke.usarPiedra(tipoPiedra))
          nuevoEstado
            .flatMap(poke => {
                if(tipoPiedra.leGanaA(poke.especie.tipoPrincipal) ||
                    tipoPiedra.leGanaA(poke.especie.tipoSecundario))
                  Envenenado(poke)
                else nuevoEstado
            }
          )
      }

      estadoDespuesDeActividad.filter((pokemon) => pokemon.valido())
    }}
  }
}