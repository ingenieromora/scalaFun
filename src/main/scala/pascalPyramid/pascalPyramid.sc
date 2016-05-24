object session {

  /*
    Write a function that computes the elements of Pascal’s triangle by means of a recursive process
        1
       1 1
      1 2 1
     1 3 3 1
    1 4 6 4 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || r == c || r == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  pascal(0,2)
  pascal(1,2)
  pascal(1,3)
  pascal(2,4)

}