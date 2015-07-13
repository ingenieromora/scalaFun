package pokemonGym

/**
 * @author leandro.mora
 */
trait CondicionEvolutiva

// TODO otra cosa que se me escapó, subir de nivel no está usado en ningún lado
case class SubirDeNivel(nivel: Int) extends CondicionEvolutiva
object Intercambiar extends CondicionEvolutiva
object UsarPiedra extends CondicionEvolutiva
object UsarPiedraLunar extends CondicionEvolutiva
// TODO se me pasó en la corrección anterior, pero NoEvoluciona no era necesario (podria usarse un Option en el uso de la cond evolutiva)
object NoEvoluciona extends CondicionEvolutiva