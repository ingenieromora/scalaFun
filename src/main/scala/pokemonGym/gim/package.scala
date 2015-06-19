package pokemonGym

/**
 * @author seb
 */
package object gim {
  
  def ejecutar(pokemon: Pokemon, listaDeActividades: Actividad*): Estado = {
    listaDeActividades.foldLeft(OK(pokemon) : Estado) { (resultadoAnterior, actividadActual) => {

      val estadoActual =  resultadoAnterior match {
        case a @ OK(pok) => OK(pok)
        case a @ KO(pok) => KO(pok)
        case a @ Paralizado(pok) => Paralizado(pok)
        case a @ Dormido(pok, 0) => OK(pok)
        case a @ Dormido(pok, cont) => Dormido(pok, cont - 1)
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
                  case Especie(_,_,Pelea,_,_,_,_,_) | Especie(_,_,_,Pelea,_,_,_,_) => 2 * kilos
                  case Especie(_,_,Fantasma,_,_,_,_,_) | Especie(_,_,_,Fantasma,_,_,_,_) => 0
                  case _ => kilos
                }
                estado.map( poke => poke.ganarExperiencia(experienciaGanada))
              }
            }
          }
        }

        case UsarPiedra(piedra: String) => {
          val piedraNecesaria = pokemon.especie.piedraEvolutiva.tostring
          if ((piedraNecesaria == piedra) ||
             (piedraNecesaria == "Usar Piedra" && piedra == pokemon.especie.tipoPrincipal.tostring)) {
            //todo: el pokemon evoluciona por usar una piedra especial o porque es del tipo principal del pokemon
            //todo: basta mostrar un mensaje o hay que crear un nuevo objeto evolucion  ?
          } else {
            if(!pokemon.especie.tipoPrincipal.gana_tipo(piedra) || !pokemon.especie.tipoSecundario.gana_tipo(piedra))
            //todo: el tipo de la piedra le gana al tipo de la especie
            //todo: cambiar estado del pokemon a envenenado
          }
        }
      }
      
      estadoDespuesDeActividad.filter((pokemon) => pokemon.valido())
    }}
  }
}