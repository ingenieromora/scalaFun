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
    val aumentoEnergiaMaxima: Int) {
}

object Especie {
  def Charmander() = {
    Especie(350, 50, Fuego, null, 50, 50, 50, 50)
  }
  def Charizard() = {
    Especie(400, 100, Fuego, Dragon, 60, 60, 60 , 60)
  }
  def Ratata() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1)
  }
  def Monkey() = {
	  Especie(25, 6, Pelea, null, 3, 4, 5, 6)
  }
  def Gengar() = {
	  Especie(390, 1, Fantasma, null, 45, 45, 50, 60)
  }
  def Magikarp() = {
	  Especie(20, 20, Agua, null, 30, 30, 30, 5)
  }
  def Poliwhirl() = {
	  Especie(20, 20, Psiquico, Agua, 30, 30, 30, 5)
  }
  def Deeglet() = {
    Especie(10, 10, Tierra, null, 10, 10, 10, 5)
  }
  
}