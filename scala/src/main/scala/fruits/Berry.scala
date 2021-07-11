package fruits

class Berry(weight: Int) extends TinyFruit(weight){
  override def eat(): Unit = println("Eating Berry..")
}
