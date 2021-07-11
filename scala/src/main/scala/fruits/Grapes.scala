package fruits

class Grapes(weight: Int) extends TinyFruit(weight){
  override def eat(): Unit = println("Eating Grapes..")
}
