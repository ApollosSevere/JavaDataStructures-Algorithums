package Stacks;

import LinkedList.Singly.Example_LinkedList;

public class Tester {
    public static void main(String[] args) {
        LinkedStack<Integer> cart = new LinkedStack<>();
        cart.push(6);
        cart.push(255);
        cart.push(21);
        cart.push(21);
        cart.push(21);
        cart.push(21);
        cart.pop();
        cart.pop();

        System.out.println(cart.show());

        Example_LinkedList cart2 = new Example_LinkedList();
        cart2.insertFirst(100);
        cart2.insertFirst(200);
        cart2.insertFirst(350);
        cart2.display();
    }
}
