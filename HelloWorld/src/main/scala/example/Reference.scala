package example

class Reference[T] {

  // why is this private?
  // ans: because it's defined as a var
  // and should not be modified
  private var contents:T = _

  def set(input: T): Unit = { contents = input}

  def get(): T = contents


}
