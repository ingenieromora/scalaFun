package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class ComerTests extends FlatSpec with Matchers {

  "Un pokemon" should "come calcio y aumenta en 5 su velocidad" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4)), 55)
    val velocidadAntes = charizard.velocidad
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), ComerCalcio).pokemon
    charizarDespuesDeActividad.velocidad should be (velocidadAntes + 5)
  }

  "Un pokemon" should "come zinc y aumenta en 2 Maximos Puntos en Ataque en todos sus ataques" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4), (Ascuas, 8)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), ComerZinc).pokemon
    val ataquesEsperados = List((FuriaDragon,6), (Ascuas, 10))
    charizarDespuesDeActividad.ataques should be (ataquesEsperados)
  }
 
  "Un pokemon" should "come hierro y aumenta en 5 su fuerza" in {
	  val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4), (Ascuas, 8)), 55)
			  val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), ComerHierro).pokemon
			  charizarDespuesDeActividad.fuerza should be (40)
  }
}
