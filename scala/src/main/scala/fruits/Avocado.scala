package fruits

class Avocado(weight: Int) extends OvalFruit(weight){
  override def eat(): Unit = println("Eating Avocado..")
}
