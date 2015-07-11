package pokemonGym

import org.scalatest._

/**
 * @author seb
 */
class SubirDeNivelTest extends FlatSpec with Matchers {

  "Un pokemon" should "subir de nivel" in {
    val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
    val charmanderEntrenado = charmander.ganarExperiencia(349)
    charmander.getNivel should be (1)
    charmanderEntrenado.getNivel should be (2)
  }
  
  "Un pokemon" should "no subir de nivel" in {
	  val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
    val charmanderEntrenado = charmander.ganarExperiencia(348)
    charmander.getNivel should be (1)
    charmanderEntrenado.getNivel should be (1)
  }
  
  "Un pokemon" should "mejorar velocidad cuando sube de nivel" in {
	  val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
	  val viejaVelocidad = charmander.velocidad
    charmander.subirDeNivel(1).velocidad should be > viejaVelocidad
  }
  
  "Un pokemon" should "mejorar fuerza cuando sube de nivel" in {
	  val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
	  val viejaFuerza = charmander.fuerza
	  charmander.subirDeNivel(1).fuerza should be > viejaFuerza
  }
  
  "Un pokemon" should "mejorar peso cuando sube de nivel" in {
	  val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
	  val viejoPeso = charmander.peso
	  charmander.subirDeNivel(1).peso should be > viejoPeso
  }
  
  "Un pokemon" should "mejorar energia maxima cuando sube de nivel" in {
	  val charmander = Pokemon(30, 30, 2, 4, 3, 'F', Charmander, Nil, 1)
	  val viejaEnergiaMaxima = charmander.energiaMaxima
	  charmander.subirDeNivel(1).energiaMaxima should be > viejaEnergiaMaxima
  }
  
}

