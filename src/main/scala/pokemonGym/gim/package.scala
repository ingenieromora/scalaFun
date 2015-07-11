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

      val estadoDespuesDeActividad = actividadActual.realizarActividad(resultadoAnterior)

      if (estadoDespuesDeActividad.pokemon.valido()) {
        estadoDespuesDeActividad
      } else {
        Invalido(estadoDespuesDeActividad.pokemon, "Las caracteristicas del pokemon son invalidas")
      }
    }}
  }
}