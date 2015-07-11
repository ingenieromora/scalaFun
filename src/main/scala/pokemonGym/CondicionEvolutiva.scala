package pokemonGym

/**
 * @author leandro.mora
 */
trait CondicionEvolutiva

case class SubirDeNivel(nivel: Int) extends CondicionEvolutiva
object Intercambiar extends CondicionEvolutiva
object UsarPiedra extends CondicionEvolutiva
object UsarPiedraLunar extends CondicionEvolutiva
object NoEvoluciona extends CondicionEvolutiva