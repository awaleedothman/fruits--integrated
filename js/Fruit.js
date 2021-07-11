function Fruit() {}
Fruit.prototype = {
    weight:  0,
    expressType: function() { return "I'm Just A Fruit"},
    expressShape: function() { return "Undefined Shape"},
    expressFlavor: function() { return "Undefined Flavor"},
    checkType: function(obj) { return this.isPrototypeOf(obj)}
}


function OvalShapedFruit() {}
OvalShapedFruit.prototype = {
    __proto__ : Fruit.prototype,
    expressShape: function() { return "I'm Oval Shaped Fruit"}
}

function RoundShapedFruit() {}
RoundShapedFruit.prototype = {
    __proto__ : Fruit.prototype,
    expressShape: function() { return "I'm Round Shaped Fruit"}
}

function TinyFruit() {}
TinyFruit.prototype = {
    __proto__ : Fruit.prototype,
    expressShape: function() { return "I'm Tiny Fruit"}
}

/*
var t = Object.create(TinyFruit.prototype)
console.log(t.expressShape())    
*/

// Oval Shaped Fruits
function Apple() {}
Apple.prototype = {
    __proto__ : OvalShapedFruit.prototype,
    weight: 135,
    expressType: function() { return "I'm Apple"},
    expressFlavor: function() {return "Sweet Flavor"}
}

function Avocado() {}
Avocado.prototype = {
    __proto__ : OvalShapedFruit.prototype,
    weight: 130,
    expressType: function() { return "I'm Avocado"},
    expressFlavor: function() {return "buttery Flavor"}
}

function Pear() {}
Pear.prototype = {
    __proto__ : OvalShapedFruit.prototype,
    weight: 140,
    expressType: function() { return "I'm Pear"},
    expressFlavor: function() {return "Sweet Flavor"}
}

// Round Shaped Fruits
function Citrus() {}
Citrus.prototype = {
    __proto__ : RoundShapedFruit.prototype,
    expressFlavor: function() {return "Sharp Flavor"}
}

function Lemon() {}
Lemon.prototype = {
    __proto__ : Citrus.prototype,
    weight: 55,
    expressType: function() { return "I'm Lemon"}
}

function Orange() {}
Orange.prototype = {
    __proto__ : Citrus.prototype,
    weight: 120,
    expressType: function() { return "I'm Orange"}
}

function Apricot() {}
Apricot.prototype = {
    __proto__ : RoundShapedFruit.prototype,
    weight: 105,
    expressType: function() { return "I'm Apricot"},
    expressFlavor: function() { return "Tart Flavor"}
}

// Tiny Fruits
function Berry() {}
Berry.prototype = {
    __proto__ : TinyFruit.prototype,
    weight: 78
}

function Grapes() {}
Grapes.prototype = {
    __proto__ : TinyFruit.prototype,
    weight: 110,
    expressType: function() { return "I'm Grapes"},
    expressFlavor: function() { return "Sweet Flavor"}
}

// Berries
function Blueberry() {}
Blueberry.prototype = {
    __proto__ : Berry.prototype,
    expressType: function() { return "I'm Blueberry"},
    expressFlavor: function() { return "Sweet with a bit acid flavor"}
}

function Blackberry() {}
Blackberry.prototype = {
    __proto__ : Berry.prototype,
    expressType: function() { return "I'm Blackberry"},
    expressFlavor: function() { return "Tart Flavor"}
}

function Gooseberry() {}
Gooseberry.prototype = {
    __proto__ : Berry.prototype,
    expressType: function() { return "I'm Gooseberry"},
    expressFlavor: function() { return "Savor and Sharp Flavor"}
}

function Elderberry() {}
Elderberry.prototype = {
    __proto__ : Berry.prototype,
    weight: 86,
    expressType: function() { return "I'm Elderberry"},
    expressFlavor: function() { return "Earthy and Tart Flavor"}
}

function Node() {}
Node.prototype = {
    fruit:  null,
    rightChild: null,
    leftChild: null
}

function FruitTree() {}
FruitTree.prototype = {
    root:  null,
    insert: function(fruit, comparator) {
        var newNode = Object.create(Node.prototype)
        newNode.fruit = fruit
        if(this.root === null)
            this.root = newNode
        else
        this.insertNode(this.root, newNode, comparator);
    },
    insertNode: function(node, newNode, comparator)
    {
        switch (true) {
            case comparator(newNode, node) <= 0:
                if(node.leftChild === null)
                    node.leftChild = newNode;
                else
                    this.insertNode(node.leftChild, newNode, comparator); 
                break
            case comparator(newNode, node) > 0:
                if(node.rightChild === null)
                    node.rightChild = newNode;
                else
                    this.insertNode(node.rightChild,newNode, comparator);
                break
        }
    }, iterator: function() { 
        this.inOrderTraversal(this.root)
    }, inOrderTraversal: function(node) {
        if (node == null) 
            return
        this.inOrderTraversal(node.leftChild)
        console.log(node.fruit.expressType() + " of weight " + node.fruit.weight)
        this.inOrderTraversal(node.rightChild)
    },
    filterByType: function(Type) {
        this.filterByTypeTraversal(this.root, Type)    
    },
    filterByTypeTraversal: function(node, Type) {
        if (node == null) 
            return
        this.filterByTypeTraversal(node.leftChild, Type)
        if (Type.prototype.checkType(node.fruit))
            console.log(node.fruit.expressType() + " of weight " + node.fruit.weight)
        this.filterByTypeTraversal(node.rightChild, Type)
    },
    filterByWeight(Weight) {
        this.filterByWeightTraversal(this.root, Weight)
    },
    filterByWeightTraversal: function(node, Weight) {
        if (node == null) 
            return
        if (node.fruit.weight > Weight) {
            this.filterByWeightTraversal(node.leftChild, Weight)
            console.log(node.fruit.expressType() + " of weight " + node.fruit.weight)
        }
        this.filterByWeightTraversal(node.rightChild, Weight)
    },
    removeNode: function(node, searchForNode, comparator)
    {
        if (node === null)
            return null;
        else if(comparator(searchForNode, node) <= 0 && searchForNode !== node)
        {
            node.leftChild = this.removeNode(node.leftChild, searchForNode, comparator);
            return node;
        }
        else if(comparator(searchForNode, node) > 0)
        {
            node.rightChild = this.removeNode(node.rightChild, searchForNode, comparator);
            return node;
        }
        else
        {
            if(node.leftChild === null && node.rightChild === null) {
                node = null;
                return node;
            }
            if(node.leftChild === null) {
                node = node.rightChild;
                return node;
            }
            else if(node.rightChild === null) {
                node = node.leftChild;
                return node;
            }
            var predeccsor = this.findMaxNode(node.leftChild);
            node.leftChild = this.removeNode(node.leftChild, predeccsor, comparator);
            node.fruit = predeccsor.fruit
            return node;
        }
    }, findMaxNode: function(node) {
        if(node.rightChild === null)
            return node;
        else
            return this.findMaxNode(node.rightChild);
    }, findMinNode: function(node) {
        if(node.leftChild === null)
            return node;
        else
            return this.findMinNode(node.leftChild);
    }
    , magnifyByType: function(Type, Weight) {
        this.magnifyByTypeHelper(this.root, Type, Weight)
    }, magnifyByTypeHelper: function(node, Type, Weight) {
        if (node == null) 
            return
        this.magnifyByTypeHelper(node.leftChild, Type, Weight)
        if (Type.prototype.checkType(node.fruit)) {
            if (node.fruit.weight > 0) {
                var tempFruit = node.fruit
                this.root = this.removeNode(this.root, node, weightComparator)
                tempFruit.weight += Weight
                this.insert(tempFruit, weightComparator)
                tempFruit.weight = -tempFruit.weight
            } else
                node.fruit.weight = -node.fruit.weight
        }
        this.magnifyByTypeHelper(node.rightChild, Type, Weight)
    },findHeaviest: function() {
        return this.findMaxNode(this.root)
    },
    findLightest: function() {
        return this.findMinNode(this.root)
    }
}

function weightComparator(node1, node2) {
    return node1.fruit.weight - node2.fruit.weight
}

var apple = Object.create(Apple.prototype)
var avocado = Object.create(Avocado.prototype)
var pear = Object.create(Pear.prototype)
var lemon = Object.create(Lemon.prototype)
var orange = Object.create(Orange.prototype)
var apricot = Object.create(Apricot.prototype)
var grapes = Object.create(Grapes.prototype)
var blackberry = Object.create(Blackberry.prototype)
var blueberry = Object.create(Blueberry.prototype)
var gooseberry = Object.create(Gooseberry.prototype)
var elderberry = Object.create(Elderberry.prototype)
var elderberryAgain = Object.create(Elderberry.prototype)

var fruitTree = Object.create(FruitTree.prototype)
fruitTree.insert(grapes, weightComparator)
fruitTree.insert(gooseberry, weightComparator)
fruitTree.insert(apricot, weightComparator)
fruitTree.insert(orange, weightComparator)
fruitTree.insert(apple, weightComparator)
fruitTree.insert(pear, weightComparator)
fruitTree.insert(lemon, weightComparator)
fruitTree.insert(blackberry, weightComparator)
fruitTree.insert(elderberry, weightComparator)
fruitTree.insert(blueberry, weightComparator)
fruitTree.insert(avocado, weightComparator)
fruitTree.insert(elderberryAgain, weightComparator)

//fruitTree.iterator()

/*console.log("Filter Berry")
fruitTree.filterByType(Berry)
console.log("Filter Oval Shaped")
fruitTree.filterByType(OvalShapedFruit)
console.log("Filter Round Shaped")
fruitTree.filterByType(RoundShapedFruit)
console.log("Filter Citrus")
fruitTree.filterByType(Citrus)
console.log("Filter Apple")
fruitTree.filterByType(Apple)*/

/*console.log("Filter 60")
fruitTree.filterByWeight(60)
console.log("Filter 110")
fruitTree.filterByWeight(110)
console.log("Filter 130")
fruitTree.filterByWeight(130)*/


/*fruitTree.magnifyByType(Berry, 200)
console.log("Magnify Berry")
fruitTree.iterator()
console.log("Magnify Oval Shaped")
fruitTree.magnifyByType(OvalShapedFruit, 200)
fruitTree.iterator()*/

fruitTree.iterator()
console.log("Heaviest")
console.log(fruitTree.findHeaviest().fruit.expressType())
console.log("Lightest")
console.log(fruitTree.findLightest().fruit.expressType())