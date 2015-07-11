package pokemonGym

/**
 * @author leandro.mora
 */
// TODO es preferible un mixin (trait de scala) a un abstract class
abstract class CondicionEvolutiva

case class SubirDeNivel(nivel: Int) extends CondicionEvolutiva

object Intercambiar extends CondicionEvolutiva
object UsarPiedra extends CondicionEvolutiva
object UsarPiedraLunar extends CondicionEvolutiva
object NoEvoluciona extends CondicionEvolutiva