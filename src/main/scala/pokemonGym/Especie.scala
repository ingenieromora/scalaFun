package pokemonGym

/**
 * @author seb
 */
case class Especie(val resistenciaEvolutiva: Int,
    var energiaMaxima: Int,
    var peso: Int,
    var fuerza: Int,
    var velocidad: Int,
    val pesoMaximo: Int,
    val tipoPrincipal: Tipo,
    val tipoSecundario: Tipo,
    val aumentoVelocidad: Int,
    val aumentoPeso: Int,
    val aumentoFuerza: Int,
    val aumentoEnergiaMaxima: Int) {
}

object Especie {
  def Charmander() = {
    Especie(350, 50, 50, 50, 50, 50, Dragon, Volador, 50, 50, 50, 50)
  }
}