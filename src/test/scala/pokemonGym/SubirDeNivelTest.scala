package pokemonGym

import org.scalatest._

/**
 * @author seb
 */
class SubirDeNivelTest extends FlatSpec with Matchers {

  "Un pokemon" should "no subir de nivel" in {
    val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
    charmander.ganarExperiencia(349).nivel should be (1)
  }
  
  "Un pokemon" should "subir de nivel" in {
	  val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
	  charmander.ganarExperiencia(351).nivel should be (2)
  }
  
  "Un pokemon" should "mejorar velocidad cuando sube de nivel" in {
	  val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
	  val viejaVelocidad = charmander.especie.velocidad
    charmander.subirDeNivel().especie.velocidad should be > viejaVelocidad
  }
  
  "Un pokemon" should "mejorar fuerza cuando sube de nivel" in {
	  val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
	  val viejaFuerza = charmander.especie.fuerza
	  charmander.subirDeNivel().especie.fuerza should be > viejaFuerza
  }
  
  "Un pokemon" should "mejorar peso cuando sube de nivel" in {
	  val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
	  val viejoPeso = charmander.especie.peso
	  charmander.subirDeNivel().especie.peso should be > viejoPeso
  }
  
  "Un pokemon" should "mejorar energia maxima cuando sube de nivel" in {
	  val charmander = Pokemon('F', Especie.Charmander(), OK, 50, null)
	  val viejaEnergiaMaxima = charmander.especie.energiaMaxima
	  charmander.subirDeNivel().especie.energiaMaxima should be > viejaEnergiaMaxima
  }
  
}

