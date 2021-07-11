import org.scalatest.FunSuite

class TreeTest extends FunSuite {

  class OrderedInt(val mine: Int) extends Ordered[OrderedInt] {
    override def compare(that: OrderedInt): Int = this.mine - that.mine
  }

  val tree = new Tree[OrderedInt]

    test("TreeTest.insert") {
      tree.insert(new OrderedInt(6))
      assert(tree.getSize === 1)
      tree.insert(new OrderedInt(8))
      assert(tree.getSize === 2)
      tree.insert(null)
      assert(tree.getSize === 2)
      tree.insert(new OrderedInt(8))
      tree.insert(new OrderedInt(50))
      tree.insert(new OrderedInt(6))
      tree.insert(new OrderedInt(90))
      tree.insert(new OrderedInt(9))
      tree.insert(new OrderedInt(3))
    }

  test("TreeTest.first&last") {
    assert(tree.getFirst.get.mine === 3)
    assert(tree.getLast.get.mine === 90)
  }

  test("TreeTest.delete") {
    tree.delete(new OrderedInt(500))
    assert(tree.getSize === 8)
    tree.delete(new OrderedInt(6))
    assert(tree.getSize === 7)
    tree.delete(new OrderedInt(3))
    tree.delete(new OrderedInt(50))
    tree.delete(new OrderedInt(8))
  }
}
