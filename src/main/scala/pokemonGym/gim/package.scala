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
        case a @ Dormido(pok, 0) => OK(pok)
        case a @ Dormido(pok, cont) => Dormido(pok, cont - 1)
      }
      
      val estadoDespuesDeActividad = actividadActual match {
        case RealizarAtaque(ataque @ Ataque(tipo, _, efecto)) if pokemon.conoceAtaque(ataque) && pokemon.puntosDeAtaque(ataque) > 0 =>
          val estadoDspDeRealizarAtaque = (estadoActual.map { poke => poke.usarAtaque(ataque) }).
          map((pokemon) => {
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
      }
      
      estadoDespuesDeActividad.filter((pokemon) => pokemon.valido())
    }}
  }
}