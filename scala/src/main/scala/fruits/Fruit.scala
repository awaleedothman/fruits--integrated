package fruits

class Fruit(var weight: Int) extends Ordered[Fruit]{
  override def compare(that: Fruit): Int = this.weight - that.weight

  def eat(): Unit = println("Eating Fruit..")
  def display(): Unit = println("I'm a general shaped fruit..")
}
