package fruits

class BlackBerry(weight: Int) extends Berry(weight){
  override def eat(): Unit = println("Eating BlackBerry..")
}
