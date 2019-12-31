
class Essai{


    public static void main(String[] args) 
    { 
        AVLTree tree = new AVLTree(); 

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 9); 
        tree.root = tree.insert(tree.root, 5); 
        tree.root = tree.insert(tree.root, 10); 
        tree.root = tree.insert(tree.root, 0); 
        tree.root = tree.insert(tree.root, 6); 
        tree.root = tree.insert(tree.root, 11); 
        tree.root = tree.insert(tree.root, -1); 
        tree.root = tree.insert(tree.root, 1); 
        tree.root = tree.insert(tree.root, 2); 

        /* The constructed AVL Tree would be 
        9 
        / \ 
        1 10 
        / \ \ 
        0 5 11 
        / / \ 
        -1 2 6 
        */
       System.out.println("Preorder traversal of "+ 
                            "constructed tree is : "); 
        tree.preOrder(tree.root); 

        tree.root = tree.deleteNode(tree.root, 10); 

        /* The AVL Tree after deletion of 10 
        1 
        / \ 
        0 9 
        /    / \ 
        -1 5 11 
        / \ 
        2 6 
        */
       System.out.println(""); 
        System.out.println("Preorder traversal after "+ 
                        "deletion of 10 :"); 
        tree.preOrder(tree.root); 
   }
}
    