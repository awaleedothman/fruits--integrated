package fruits

class BlueBerry(weight: Int) extends Berry(weight){
  override def eat(): Unit = println("Eating BlueBerry..")
  override def display(): Unit = println("I'm very tiny but delicious")
}
