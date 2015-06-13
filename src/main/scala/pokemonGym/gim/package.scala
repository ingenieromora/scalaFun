package pokemonGym

/**
 * @author seb
 */
import java.rmi.activation.Activatable
package object gim {
  
  case class ResultadoDeActividad(val pokemon: Pokemon) {
    def map(f: (Pokemon => Pokemon)) = ResultadoDeActividad(f(pokemon))
  }
  
  def ejecutar(pokemon: Pokemon, listaDeActividades: Actividad*): ResultadoDeActividad = {
    listaDeActividades.foldLeft(ResultadoDeActividad(pokemon)) { (resultadoAnterior, actividadActual) => {
      
      resultadoAnterior.map((pokemon) =>
        actividadActual match {
          case RealizarAtaque(ataque @ Ataque(tipo, puntos, efecto)) =>
            if (puntos > 0 && pokemon.conoceAtaque(ataque)) {
              var poke = tipo match {
                case Dragon =>
                  pokemon.ganarExperiencia(80)
                case resultadoAnterior.pokemon.especie.tipoPrincipal =>
                  pokemon.ganarExperiencia(50)
                case resultadoAnterior.pokemon.especie.tipoSecundario =>
                  pokemon.genero match {
                    case 'F' =>
                      pokemon.ganarExperiencia(40)
                    case 'M' =>
                      pokemon.ganarExperiencia(20)
                  }
              }
              poke = efecto(poke)
              poke.usarAtaque(ataque)
              poke
            } else {
              pokemon
            }
          }
        )
    }}
  }
}