package pokemonGym

/**
 * @author seb
 */
// TODO detalle: en las case clases los parametros por default son "val"
case class Especie(
    resistenciaEvolutiva: Int,
    pesoMaximo: Int,
    tipoPrincipal: Tipo,
    tipoSecundario: Option[Tipo],
    aumentoVelocidad: Int,
    aumentoPeso: Int,
    aumentoFuerza: Int,
    aumentoEnergiaMaxima: Int,
    condicionEvolutiva: CondicionEvolutiva,
    evolucion: Option[Especie]) {
}

object Charmander extends Especie(350, 50, Fuego, None, 50, 50, 50, 50, SubirDeNivel(1000000000), Some(Charizard))
object Charizard extends Especie(400, 100, Fuego, Some(Dragon), 60, 60, 60 , 60, NoEvoluciona, None)
object Ratata extends Especie(1, 1, Normal, None, 1, 1, 1, 1,Intercambiar, Some(Raticate))
object Raticate extends Especie(1, 1, Normal, None, 1, 1, 1, 1,NoEvoluciona, None)
object Monkey extends Especie(25, 6, Pelea, None, 3, 4, 5, 6, NoEvoluciona, None)
object Gengar extends Especie(390, 1, Fantasma, None, 45, 45, 50, 60, NoEvoluciona, None)
object Pikachu extends Especie(390, 1, Electrico, Some(Normal), 45, 45, 50, 60, UsarPiedra, Some(Raichu))
object Raichu extends Especie(390, 1, Electrico, None, 45, 45, 50, 60, NoEvoluciona, None)
object Jigglypuff extends Especie(390, 1, Normal, None, 45, 45, 50, 60, UsarPiedraLunar, Some(Wigglytuff))
object Wigglytuff extends Especie(390, 1, Normal, None, 45, 45, 50, 60, NoEvoluciona, None)
object Blastoise extends Especie(390, 1, Agua, None, 45, 45, 50, 60, NoEvoluciona, None)
