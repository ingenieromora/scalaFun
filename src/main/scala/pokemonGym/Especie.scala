package pokemonGym

/**
 * @author seb
 */
case class Especie(
    val resistenciaEvolutiva: Int,
    val pesoMaximo: Int,
    val tipoPrincipal: Tipo,
    val tipoSecundario: Tipo,
    val aumentoVelocidad: Int,
    val aumentoPeso: Int,
    val aumentoFuerza: Int,
    val aumentoEnergiaMaxima: Int,
    val condicionEvolutiva: CondicionEvolutiva,
    val evolucion: Especie) {
}

object Especie {
  def Charmander() = {
    Especie(350, 50, Fuego, null, 50, 50, 50, 50, SubirDeNivel(1000000000), Charizard())
  }
  def Charizard() = {
    Especie(400, 100, Fuego, Dragon, 60, 60, 60 , 60, NoEvoluciona(), null)
  }
  def Ratata() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1,Intercambiar(), Raticate())
  }
  def Raticate() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1,NoEvoluciona(), null)
  }
  def Monkey() = {
	  Especie(25, 6, Pelea, null, 3, 4, 5, 6, NoEvoluciona(), null)
  }
  def Gengar() = {
	  Especie(390, 1, Fantasma, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Pikachu() = {
    Especie(390, 1, Electrico, Normal, 45, 45, 50, 60, UsarPiedra(), Raichu())
  }
  def Raichu() = {
    Especie(390, 1, Electrico, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Jigglypuff() = {
    Especie(390, 1, Normal, null, 45, 45, 50, 60, UsarPiedraLunar(), Wigglytuff() )
  }
  def Wigglytuff() = {
    Especie(390, 1, Normal, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Blastoise() = {
    Especie(390, 1, Agua, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
}