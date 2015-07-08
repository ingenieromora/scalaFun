package pokemonGym

/**
 * @author leandro.mora
 */
// TODO es preferible un mixin (trait de scala) a un abstract class
abstract class CondicionEvolutiva

case class SubirDeNivel(nivel: Int) extends CondicionEvolutiva

// TODO no usar case clases sin parámetros (son objects)
case class Intercambiar() extends CondicionEvolutiva
case class UsarPiedra() extends CondicionEvolutiva
case class UsarPiedraLunar() extends CondicionEvolutiva
case class NoEvoluciona() extends CondicionEvolutiva