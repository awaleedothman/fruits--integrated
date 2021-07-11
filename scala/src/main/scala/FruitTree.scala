import fruits.Fruit

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class FruitTree {
  private val tree: Tree[Fruit] = new Tree[Fruit]

  def add(fruit: Fruit): Unit = tree.insert(fruit)

  def iterate(): Unit =
    tree
      .getElements
      .foreach(printOrdered())

  def filterByWeight(weight: Int): Unit = {
    tree
      .getElements
      .filter(_.weight >= weight)
      .foreach(printOrdered())
  }

  def filterByType[T <: Fruit : ClassTag](): Unit = {
    tree
      .getElements
      .collect { case x: T => x.asInstanceOf[Fruit] }
      .foreach(printOrdered())
  }

  def magnifyByType[T <: Fruit : ClassTag](weightInc: Int): Unit = {
    val list =
      tree
        .getElements
        .collect { case x: T => x.asInstanceOf[Fruit] }

    list.foreach(e => tree.delete(e))
    list.foreach(_.weight += weightInc)
    list.foreach(e => tree.insert(e))
  }

  def findHeaviest(): Option[Fruit] = tree.getLast

  def findLightest(): Option[Fruit] = tree.getFirst

  def getFruits: ArrayBuffer[Fruit] = tree.getElements

  private def printOrdered(): Fruit => Unit = {
    var index: Int = 1
    fruit => {
      val s = fruit.toString
      println(index + ")\tweight= " + fruit.weight + "\ttype: " +
        s.substring(s.indexOf('.') + 1, s.indexOf('@')))

      index += 1
    }
  }
}
