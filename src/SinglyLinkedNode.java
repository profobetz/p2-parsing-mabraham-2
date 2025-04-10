/*
No matter what type of linked list I'm working with, nodes work generally the same way:
    * They have some payload of actual data.
    * They have a reference to the next thing in the list.

 If I want this node to work for any possible type of object in the "contents", we've
 learned a tool that will allow us to do that...

*/

public class SinglyLinkedNode<T> {
    
    private T contents;//<- this represents the actual item in the list
    private SinglyLinkedNode<T> next; //<- this points to the next item, which holds the same type as this item

    public SinglyLinkedNode( T contents ) {
        this.contents = contents;
        this.next = null;
    }

    public SinglyLinkedNode( T contents, SinglyLinkedNode<T> successor ) {
        this.contents = contents;
        this.next = successor;
    }

    public T getContents() {
        return contents;
    }

    public void setNext(SinglyLinkedNode<T> new_next) {
        this.next = new_next;
    }

    public SinglyLinkedNode<T> getNext() {
        return this.next;
    }
}