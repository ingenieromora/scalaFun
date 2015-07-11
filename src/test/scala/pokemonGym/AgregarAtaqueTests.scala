package pokemonGym

import org.scalatest.Matchers
import org.scalatest.FlatSpec

/**
 * @author eugenio
 */
class AgregarAtaqueTests extends FlatSpec with Matchers {

  "Un pokemon" should "agregar ataque al ser de tipo Normal" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4)), 55)
    val ataque = Ataque(Normal,100)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), AprenderAtaque(ataque)).pokemon
    val ataquesEsperados = List((ataque, 100), (FuriaDragon,4))
    charizarDespuesDeActividad.ataques should be (ataquesEsperados)
  }
  
  "Un pokemon" should "agregar ataque al ser de su tipo" in {
	  val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4)), 55)
		val ataque = Ataque(Fuego,100)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), AprenderAtaque(ataque)).pokemon
		val ataquesEsperados = List((ataque, 100), (FuriaDragon,4))
		charizarDespuesDeActividad.ataques should be (ataquesEsperados)
  }
  
  "Un pokemon" should "no agregar ataque al no ser de su tipo" in {
	  val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Charizard, List((FuriaDragon, 4)), 55)
    val ataque = Ataque(Agua,100)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), AprenderAtaque(ataque)).pokemon
		val ataquesEsperados = List((FuriaDragon,4))
		charizarDespuesDeActividad.ataques should be (ataquesEsperados)
  }

}