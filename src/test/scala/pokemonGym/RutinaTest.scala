package pokemonGym

import org.scalatest.FlatSpec
import org.scalatest.Matchers


/**
 * @author seb
 */
class RutinaTest extends FlatSpec with Matchers {
  
  "Un poke fantasma" should "quedar invalido despues rutina con pesas 1" in {
      val gengar = Pokemon(300, 300, 1, 100, 200, 'F', Gengar, Nil, 60)
      val gengarDespuesDeActividad = gim.ejecutar(OK(gengar), RutinaConPesas1)
      gengarDespuesDeActividad match {
        case Invalido(_, _) => 
        case _ => fail("El estado deberia ser invalido")
    }
  }
  
  "Un poke fantasma" should "quedar invalido despues rutina con pesas 2" in {
    val gengar = Pokemon(300, 300, 1, 100, 200, 'F', Gengar, Nil, 60)
    val gengarDespuesDeActividad = gim.ejecutar(OK(gengar), RutinaConPesas2)
    gengarDespuesDeActividad match {
      case Invalido(_, _) => 
      case _ => fail("El estado deberia ser invalido")
    }
  }
  
  "Un poke fantasma" should "quedar invalido despues rutina con pesas 3" in {
    val gengar = Pokemon(300, 300, 1, 100, 200, 'F', Gengar, Nil, 60)
    val gengarDespuesDeActividad = gim.ejecutar(OK(gengar), RutinaConPesas3)
    gengarDespuesDeActividad match {
      case Invalido(_, _) => 
      case _ => fail("El estado deberia ser invalido")
    }
  }
  
  "Un pokemon" should "quedar dormido despues rutina con descansar" in {
    val ratata = Pokemon(1, 5, 1, 1, 1, 'F', Ratata, List((Reposar, 400)), 1)
    val ratataDespuesDeActividad = gim.ejecutar(OK(ratata), RutinaConDescansar)
    ratataDespuesDeActividad match {
      case Dormido(_, _) => 
      case _ => fail("El estado deberia ser dormido")
    }
  }
  
  "Un pokemon" should "quedar invalido despues rutina con ataque desconocido" in {
    val ratata = Pokemon(1, 1, 1, 1, 1, 'F', Ratata, List((Reposar, 400)), 1)
    val ratataDespuesDeActividad = gim.ejecutar(OK(ratata), RutinaAtaqueFuriaDragon)
    ratataDespuesDeActividad match {
      case Invalido(_, _) => 
      case _ => fail("El estado deberia ser invalido")
    }
  }
  
  "un criterio segun experiencia" should "obtener la mejor rutina de la lista" in {
    val criterioSegunExperiencia = (estado : Estado) => {
      estado.pokemon.experiencia
    }
    val listaDeRutinas = List(RutinaAtaqueFuriaDragon, RutinaConDescansar, RutinaConPesas1)
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4)), 55)
    val nombreMejorRutina = gim.dameLaMejorRutina(OK(charizard), criterioSegunExperiencia, listaDeRutinas.toSeq : _ *)
    nombreMejorRutina.get should equal("rutina con furia dragon")
  }
  
  "un criterio segun fuerza" should "obtener la mejor rutina de la lista" in {
    val criterioSegunExperiencia = (estado : Estado) => {
      estado.pokemon.fuerza
    }
    val listaDeRutinas = List(RutinaAtaqueFuriaDragon, RutinaConDescansar, RutinaConPesas1)
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, Nil, 55)
    val nombreMejorRutina = gim.dameLaMejorRutina(OK(charizard), criterioSegunExperiencia, listaDeRutinas.toSeq : _ *)
    nombreMejorRutina.get should equal("rutina con pesas 1")
  }
  
  "un criterio cualquiera" should "obtener None si todas las rutinas son invalidas" in {
    val criterioSegunExperiencia = (estado : Estado) => {
      estado.pokemon.fuerza
    }
    val listaDeRutinas = List(RutinaAtaqueFuriaDragon, RutinaConPesas2, RutinaConPesas1)
    val gengar = Pokemon(300, 300, 1, 100, 200, 'F', Gengar, Nil, 60)
    val nombreMejorRutina = gim.dameLaMejorRutina(OK(gengar), criterioSegunExperiencia, listaDeRutinas.toSeq : _ *)
    if (!nombreMejorRutina.isEmpty) fail("No deberia devolver ninguna rutina")
  }
  
}