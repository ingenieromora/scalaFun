package pokemonGym

/**
 * @author seb
 */
package object gim {
  
  def dameLaMejorRutina(estadoInicial: Estado,
      // TODO el criterio podría ser un Ordering[Estado]
                        funcionCriterio: (Estado => Int),
                        listaDeRutinas: Rutina*) : Option[String] = {
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
      None
    } else {
      val mejorEstado = listaDeEstadosOrdenadosSegunCriterio.last
      val indiceDelMejorEstado = listaDeEstadosDeRutinas.indexOf(mejorEstado)
      Some(listaDeRutinas(indiceDelMejorEstado).nombre)
    }
  }
  
  def ejecutar(estadoInicial : Estado, rutina: Rutina): Estado = {
    ejecutar(estadoInicial, rutina.actividades.toSeq : _ *)
  }
  
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