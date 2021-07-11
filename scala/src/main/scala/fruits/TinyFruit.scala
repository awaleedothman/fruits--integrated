package fruits

class TinyFruit(weight: Int) extends Fruit(weight){
  override def eat(): Unit = println("Eating TinyFruit..")
  override def display(): Unit = println("I'm very tiny..")
}
