import fruits.{Apple, Avocado, Berry, BlackBerry, BlueBerry, Fruit, Grapes, OvalFruit, TinyFruit}
import org.scalatest.FunSuite

class FruitTreeTest extends FunSuite {

  val fruitTree = new FruitTree
  test("FruitTreeTest.add") {
    fruitTree.add(new Apple(5))
    fruitTree.add(new OvalFruit(10))
    fruitTree.add(new Fruit(7))
    fruitTree.add(new Avocado(9))
    fruitTree.add(new Berry(2))
    fruitTree.add(new BlackBerry(6))
    fruitTree.add(new Grapes(9))
    fruitTree.add(new BlueBerry(5))
    fruitTree.add(new TinyFruit(2))
  }

  test("FruitTreeTest.iterate") {
    println("\nIterate")
    fruitTree.iterate()
  }

  test("FruitTreeTest.eat") {
    println("\nEat")
    fruitTree.getFruits.foreach(_.eat())
  }

  test("FruitTreeTest.display") {
    println("\nDisplay")
    fruitTree.getFruits.foreach(_.display())
  }

  test("FruitTreeTest.filterByWeight") {
    println("\nFilter By Weight")
    fruitTree.filterByWeight(6)
  }

  test("FruitTreeTest.filterByType") {
    println("\nFilter By Type")
    fruitTree.filterByType[TinyFruit]()
  }

  test("FruitTreeTest.magnify") {
    println("\nMagnify")
    fruitTree.magnifyByType[Berry](1)
    fruitTree.filterByType[Berry]()

    println("\nIterate")
    fruitTree.iterate()
  }

  test("FruitTreeTest.heaviest") {
    println("\nHeaviest")
    fruitTree.findHeaviest() match {
      case Some(fruit) => println(fruit.weight)
    }
  }

  test("FruitTreeTest.lightest") {
    println("\nLightest")
    fruitTree.findLightest() match {
      case Some(fruit) => println(fruit.weight)
    }
  }

}
