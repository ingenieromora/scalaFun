package countingChange

/**
 * Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations. For example, there are 3 ways to give change for 4 if you have coins with denomiation 1 and 2: 1+1+1+1, 1+1+2, 2+2.
 * Do this exercise by implementing the countChange function in Main.scala. This function takes an amount to change, and a list of unique denominations for the coins. Its signature is as follows:
 * Created by leandromora on 5/21/15.
 */
class CountChangeManager {
  def countChange(money: Int, coins: List[Int]): Int = {
    return findChange(money, coins, 0)
  }

  def findChange(money:Int, coins: List[Int], accumulated: Int): Int = {
    if(money == 0)
      return 1
    if(money < coins.head)
      findChange(money, coins.tail, accumulated)
    else {
      val accum = findChange(money - coins.head, coins, accumulated)
      if(!coins.tail.isEmpty)
        return accum + findChange(money, coins.tail, accum)
      return accum
    }
  }

}
