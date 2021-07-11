package fruits

class OvalFruit(weight: Int) extends Fruit(weight){
  override def eat(): Unit = println("Eating Oval Fruit..")
}
