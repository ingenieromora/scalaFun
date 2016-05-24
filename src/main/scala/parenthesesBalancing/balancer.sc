
object session {

  def balance(chars: List[Char]): Boolean = {
    lookForCharacter(chars, '(', ')')
  }

  def lookForCharacter(chars: List[Char], admited_char: Char, notAllowedChar: Char): Boolean = {
    if (chars.isEmpty)
      return true

    if (chars.head.equals(notAllowedChar))
      return false

    if (chars.head.equals(admited_char))
      lookForCharacter(chars.tail.reverse, notAllowedChar, admited_char)
    lookForCharacter(chars.tail, admited_char, notAllowedChar)
  }

  val test1 : String = "(if (zero? x) max (/ 1 x))"
  balance(test1.toList)

  val test2 : String = ""
  balance(test2.toList)


  test1.toList.head.equals('(')

  val parenthesisList : List[Char] = test1.toList
  parenthesisList.reverse
}