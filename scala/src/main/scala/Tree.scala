import scala.collection.mutable.ArrayBuffer

class Tree[A <: Ordered[A]] {

  case class Node(var data: A,
                  var leftChild: Option[Node] = None,
                  var rightChild: Option[Node] = None,
                  var parent: Option[Node] = None)

  private var root: Option[Node] = None
  private var size: Int = 0

  def getSize: Int = size

  def insert(data: A): Unit = {
    if (data == null) return

    size += 1
    root match {
      case None => root = Some(Node(data))
      case Some(_) =>
        var currentNode: Option[Node] = None
        var nextNode = root
        var biggerThan: Boolean = true

        /*get node place*/
        do {

          currentNode = nextNode
          biggerThan = data > currentNode.get.data

          if (biggerThan) nextNode = currentNode.get.rightChild
          else nextNode = currentNode.get.leftChild

        } while (nextNode.isDefined)

        /*insert node*/
        val newNode: Node = Node(data = data, parent = currentNode)
        if (biggerThan) currentNode.get.rightChild = Some(newNode)
        else currentNode.get.leftChild = Some(newNode)
    }
  }

  def delete(data: A): Option[A] = {
    if (data == null) return None

    size match {
      case 0 => None /*no items to delete*/
      case 1 if root.get.data == data => /*delete root*/
        val old = root
        root = None
        size -= 1
        Some(old.get.data)
      case _ =>
        search(data) match {
          /*get node to be deleted*/
          case None => None /*node not found*/
          case Some(node) =>

            if (node.leftChild.isDefined)
              node.data = dropPredecessor(node).get.data

            else if (node.rightChild.isDefined)
              node.data = dropSuccessor(node).get.data

            else drop(node)

            size -= 1
            Some(node.data)
        }

    }
  }

  def getFirst: Option[A] = size match {
    case 0 => None
    case 1 => Some(root.get.data)
    case _ => root.get.leftChild match {
      case None => Some(root.get.data)
      case Some(_) =>
        var myNode = root
        while (myNode.get.leftChild.isDefined) myNode = myNode.get.leftChild
        Some(myNode.get.data)
    }
  }

  def getLast: Option[A] = size match {
    case 0 => None
    case 1 => Some(root.get.data)
    case _ => root.get.rightChild match {
      case None => Some(root.get.data)
      case Some(_) =>
        var myNode = root
        while (myNode.get.rightChild.isDefined) myNode = myNode.get.rightChild
        Some(myNode.get.data)
    }
  }

  def getElements: ArrayBuffer[A] = {
    val list = new ArrayBuffer[A]()
    if (size == 0) return list

    inOrder(root, list)
    list
  }

  private def inOrder(node: Option[Node], list: ArrayBuffer[A]): Unit = {
    val left = node.get.leftChild
    val right = node.get.rightChild

    if (left.isDefined) inOrder(left, list)
    list.addOne(node.get.data)
    if (right.isDefined) inOrder(right, list)
  }

  private def search(data: A): Option[Node] = {
    if (data == null) return None

    var currentNode: Option[Node] = None
    var nextNode = root

    /*get node place*/
    do {
      currentNode = nextNode
      val currentData: A = currentNode.get.data

      if (data > currentData) nextNode = currentNode.get.rightChild
      else if (data < currentData) nextNode = currentNode.get.leftChild
      else return currentNode

    } while (nextNode.isDefined)

    None
  }

  private def dropPredecessor(node: Node): Option[Node] = {
    var myNode: Option[Node] = node.leftChild
    myNode.get.rightChild match {
      case None =>
        myNode.get.parent.get.leftChild = None
        myNode
      case Some(_) =>
        while (myNode.get.rightChild.isDefined) myNode = myNode.get.rightChild
        myNode.get.parent.get.rightChild = None
        myNode
    }
  }

  private def dropSuccessor(node: Node): Option[Node] = {
    var myNode: Option[Node] = node.rightChild
    myNode.get.leftChild match {
      case None =>
        myNode.get.parent.get.rightChild = None
        myNode
      case Some(_) =>
        while (myNode.get.leftChild.isDefined) myNode = myNode.get.leftChild
        myNode.get.parent.get.leftChild = None
        myNode
    }
  }

  private def drop(node: Node): Unit = {
    val parent = node.parent.get
    if (node == parent.leftChild.get) parent.leftChild = None
    if (node == parent.rightChild.get) parent.rightChild = None
  }

}
