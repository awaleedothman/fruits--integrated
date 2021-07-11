#include <iostream>
#include <string>
#include <stack>

#pragma region fruits
class Fruit {
    int weight;
    public:
        Fruit() : weight(0) { }
        virtual void setWeight(int weight) {this->weight = weight;}
        int getWeight() {return weight;};
        virtual std::string getType(){return "";};
        virtual bool checkType(std::string type){return false;}
};
class TinyFruit :virtual public Fruit {
public:
    virtual std::string getType(){
        return "Tiny";
    }
    virtual bool checkType(std::string type){
        if(type == TinyFruit::getType())
            return true;
        else return false;
    }
};

class RoundShapedFruit : virtual public Fruit {
    public:
        virtual std::string getType(){
        return "Round";
    }
     virtual bool checkType(std::string type){
        if(type == RoundShapedFruit::getType())
            return true;
        else return false;
    }
};

class SourFruit : virtual public Fruit {
public: 
    virtual std::string getType(){
        return "Sour";
    }
     virtual bool checkType(std::string type){
        if(type == SourFruit::getType())
            return true;
        else return false;
    }
};

class SweetFruit : virtual public Fruit {
public:
    virtual std::string getType(){
        return "Sweet";
    }
     virtual bool checkType(std::string type){
        if(type == SweetFruit::getType())
            return true;
        else return false;
    }
};

#pragma region FruitTypes
class Grapes : public TinyFruit,public SweetFruit{
public :
    virtual std::string getType(){
        return "Grape";
    }
     virtual bool checkType(std::string type){
        if(type == Grapes::getType())
            return true;
        else {
            return TinyFruit::checkType(type) || SweetFruit::checkType(type);
        }
    }
};

class Orange :public RoundShapedFruit,public SourFruit{
public:
    virtual std::string getType(){
        return "Orange";
    }
    virtual bool checkType(std::string type){
        if(type == Orange::getType())
            return true;
        else {
            return RoundShapedFruit::checkType(type) || SourFruit::checkType(type);
        }
    }

};
class Berry :public TinyFruit,public SourFruit{
public:
     virtual std::string getType(){
        return "Berry";
    }
    virtual bool checkType(std::string type){
        if(type == Berry::getType())
            return true;
        else {
            return TinyFruit::checkType(type) || SourFruit::checkType(type);
        }
    }
};
class BlueBerry : virtual public Berry{
public:
    virtual std::string getType(){
        return "BlueBerry";
    }
     virtual bool checkType(std::string type){
        if(type == BlueBerry::getType())
            return true;
        else {
            return Berry::checkType(type);
        }
    }
};

class BlackBerry : virtual public Berry{
public:
    virtual std::string getType(){
        return "BlackBerry";
    }
     virtual bool checkType(std::string type){
        if(type == BlackBerry::getType())
            return true;
        else {
            return Berry::checkType(type);
        }
    }
};

#pragma endregion FruitTypes
#pragma endregion fruits


class FruitTree{
    class Node{
        public : 
            Node(Fruit* fruit) : fruit(fruit), left(NULL), right(NULL) { };
            Node* right;
            Node* left;
            Fruit* fruit;
    };
    class TreeIterator{
        std::stack<Node*> stack;
        public : 
            TreeIterator(Node* root){
                Node* ptr = root;
                while (ptr != NULL)
                         stack.push(ptr), ptr = ptr->left;
            }
           
            Node* next(){
                Node* next = stack.top();
                Node* ptr = next->right;
                stack.pop();
                while (ptr != NULL)
                         stack.push(ptr), ptr = ptr->left;
                return next;
            }

            bool hasNext(){
                if(stack.size()>0)
                  return true;
                return false;
            }
    };
    Node* root;
    #pragma region BST Operations

    Node* insert(Node* root,Fruit *fruit ){
            if(root == NULL){
                root = new Node(fruit);
                return root;
            }
            if(fruit->getWeight() < root->fruit->getWeight())
                root->left = insert(root->left,fruit);
            else if(fruit->getWeight() > root->fruit->getWeight())
                root->right = insert(root->right,fruit);
            return root;
        }

     Node* deleteNode(Node* root,Fruit* fruit ){
            if(root == NULL){
                return root;
            }
            if(fruit->getWeight() < root->fruit->getWeight())
                root->left = deleteNode(root->left,fruit);
            else if(fruit->getWeight() > root->fruit->getWeight())
                root->right = deleteNode(root->right,fruit);
            else{
                if(root->left == NULL && root->right == NULL)
                    return NULL;
                else if (root->left == NULL)
                {
                    Node* temp = root->right;
                    delete(root);
                    return temp;
                }
                else if (root->right == NULL)
                {
                    Node* temp = root->left;
                    delete(root);
                    return temp;
                }
                else{
                    Node* min = findMinNode(root->right);
                    root->fruit = min->fruit;
                    root->right = deleteNode(root->right,min->fruit);
                }
            }
            return root;
        }

         Node* findMinNode(Node* root){
             if(root == NULL)
                return NULL;
            Node* ptr = root;
               while(ptr->left!=NULL)
                   ptr = ptr->left; 
            return ptr;
    }

    Node* findMaxNode(Node* root){
             if(root == NULL)
                return NULL;
            Node* ptr = root;
               while(ptr->right!=NULL)
                   ptr = ptr->right; 
            return ptr;
    }
 #pragma endregion BST Operations
  
    void filterByWeight(Node* root, int weight){
        if(root == NULL)
            return;
        if(root->fruit->getWeight() > weight){
            printFruit(root->fruit);
            printTree(root->right);
            filterByWeight(root->left,weight);
        }
        else 
          filterByWeight(root->right,weight);
    }
   
   void printFruit(Fruit* fruit){
                std::cout << "I am " << fruit->getType() 
                << ", my weight is " <<fruit->getWeight() << "\n";     
   }
        void printTree(Node* root){
            TreeIterator itr(root);
            Node* current;
            while (itr.hasNext()){
                 current = itr.next();
                    printFruit(current->fruit);
            }          
        }

    
    public : 
        FruitTree():root(NULL) { };
        void Iterate(){
            printTree(root);       
        }
        void insert(Fruit* fruit){
          root = insert(root, fruit);
        }
        void filterByType(std::string type){
           TreeIterator itr(root);
            Node* current;
            while (itr.hasNext()){
                 current = itr.next();

                 if(current->fruit->checkType(type)){
                   printFruit(current->fruit);
                     } 
            }    
        }
        void filterByWeight(int weight){
            filterByWeight(root,weight);
        }
        Fruit* findHeaviest(){
            if(root == NULL)
                return NULL;
            return findMaxNode(root)->fruit;       
          }  

          Fruit* findLightest(){    
              if(root == NULL)
                return NULL;
            return findMinNode(root)->fruit;      
          }  

          void magnifyByType(std::string type, int value){
            TreeIterator itr(root);
            Node* current;
            std::stack<Fruit*> fruitsToDelete;
            while (itr.hasNext()){
                 current = itr.next();
                  if(current->fruit->checkType(type)){
                     fruitsToDelete.push(current->fruit);
                     } 
            }  
           while(fruitsToDelete.size()>0){
                     root =  deleteNode(root,fruitsToDelete.top());
                     fruitsToDelete.top()->setWeight( fruitsToDelete.top()->getWeight()+value);  
                     insert(fruitsToDelete.top());
                     fruitsToDelete.pop();
                 }
          }
};
int main()
{
     FruitTree ft;
    Grapes one;BlueBerry two;BlackBerry three;Orange four; Orange five;
    one.setWeight(1);
    two.setWeight(2);
    three.setWeight(3);
    four.setWeight(4);
    five.setWeight(5);
    ft.insert(&three);
    ft.insert(&five);
    ft.insert(&two);
    ft.insert(&four);
    ft.insert(&one);

    ft.Iterate();
    std::cout << "\n";

    ft.magnifyByType("Tiny",200);
    ft.Iterate();
    std::cout << "\n";

    ft.filterByType("Sour");
    std::cout << "\n";

    ft.filterByType("Berry");
    std::cout << "\n";

    ft.filterByType("Round");
    std::cout << "\n";

    ft.filterByWeight(100);
    std::cout << "\n";

    std::cout << "Heaviest " << ft.findHeaviest()->getWeight() 
    << " Type : " << ft.findHeaviest()->getType() << "\n";

     std::cout << "Lightest " << ft.findLightest()->getWeight() 
    << " Type : " << ft.findLightest()->getType() << "\n";
    return 0;
}
