package pokemonGym

/**
 * @author seb
 */
case class Ataque(val tipo: Tipo, val maximoPuntosAtaque: Int, val efectoColateral:(Estado => Estado) = {(estado: Estado) => estado})

object Mordida extends Ataque(Normal, 30)
object Enfocarse extends Ataque(Normal, 25, (estado: Estado) => estado.map((poke) => poke.aumentarVelocidad(1)))
object Reposar extends Ataque(Normal, 20, (estado: Estado) => estado.flatMap((poke) => Dormido(poke.aumentarEnergiaAlMaximo())))
object FuriaDragon extends Ataque(Dragon, 10)
object Ascuas extends Ataque(Fuego, 25)
