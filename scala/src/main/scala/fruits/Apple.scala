package fruits

class Apple(weight: Int) extends OvalFruit(weight){
  override def eat(): Unit = println("Eating Apple..")
}
