/*
    Program : General Single Linked List Class
    Author: Briggs Richardson
    Date: 4-13-20
 */

class Link {
    public Object data;
    public Link next;

    public Link(Object item) {
        data = item;
        next = null;
    }
}

// General Linked List class (Singly linked)
public class LinkedList {
    /*
        head is a reference to the first Link in the Linked list
        It will originally be assigned to null, because there is no first
        link in a empty linked list
    */
    private Link head;

    /*
        size will simply keep track of how many Links there are in the list
    */
    private int size;

    /*
        The no-parameter constructor will assign head to null and size to
        zero. It will construct an empty linked list
    */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /*
        A constructor in which receives an array of Objects, and converts
        the array of Objects into a Linked List
    */
    public LinkedList (Object[] arr) {
        this(); // Call the no-parameter constructor to construct an empty linked list

        // Only start adding contents to the arraylist if the arr is not null
        if (arr != null) {
            Link curr = head; // Reference to current link. Start at head

            // Iterate through contents of array, for each item, add a link to the linked list
            for (Object item : arr) {
                ++size;

                // If curr is null, we have a empty linked list.
                if (curr == null) {
                    head = new Link(item); // Head always references the first link
                    curr = head;           // curr will then point at head, the current link
                }
                else {
                    // Create the next node and  have the current one point at it
                    curr.next = new Link(item);
                    curr = curr.next;
                }
            }
        }
    }

    /*
        toArray converts the arraylist into an array, and returns the array
    */
     public Object[] toArray() {
         // Handle a empty linked list case
         if (size == 0)
             return null;

         Object[] arr = new Object[size]; // create an array of Objects equal to the size (number of links)
         Link curr = head;
         // Iterate through the linked list, from 0 to size - 1, appending the data into the array
         for (int i = 0; i < size; ++i) {
             arr[i] = curr.data;
             curr = curr.next;
         }
         return arr;
     }

     // getSize returns the number of links in the linked list
    public int getSize() {
         return size;
    }

     /*
         insertAtPos inserts a Object in the linked list at the given position, where
         position starts at 1. Ex. 1 = 1st link, 2 = 2nd link
         You can insert at the end of the linked list, which would be at position == size
         after the size incrementation
         Returns true if insertion is successful, false otherwise
     */
    public boolean insertAtPos(Object item, int pos) {
        // Check if the given position is within the bounds of the linked list
        if (pos < 1 || pos > size + 1)
            return false; // Return false,Insertion not possible. (Exits function at this line)

        ++size; // increment size, +1 after insertion

        // Create the new link to be added to the linked list
        Link newItem = new Link(item);

        // Create a dummy node and have it point at head
        Link dummy = new Link(null);
        dummy.next = head;

        // Set up a reference to keep track of current node when iterating through linked list
        Link curr = dummy;

        // Special case -> head is null
        if (head == null) {
            head = newItem;
        }
        else {
            while (pos > 1) {
                --pos;
                curr = curr.next;
            }
            // Insert the item after the curr node, have new node point at curr was pointing at
            newItem.next = curr.next;
            curr.next = newItem;
        }
        return true;
    }

    /*
        removeAtPos removes a node in the linked list at the given position, where position
        refers to the numbered linked list starting at 1. Example, first link = position 1
        Returns true if removal is successful, false otherwise
     */
    public boolean removeAtPos(int pos) {
        // Check if given position is within bounds of linked list
        if (pos < 1 || pos > size)
            return false;

        // Check if the linked list is empty, if it is -- removal is not possible
        if (head == null)
            return false;

        --size; // If conditions above fail, then a removal will occur guaranteed

        // Special case -> pos is 1, will need to change the head reference
        if (pos == 1) {
            head = head.next;
        }
        else {
            Link curr = head; // Reference link for current node when iterating

            /* We need to iterate through the linked list until we reach the
               link before the deletion point (pos - 1).
               Iteration will require stepping from position 1, up until
               pos - 1
            */
            for (int i = 1; i < pos - 1; ++i) {
                curr = curr.next;
            }
            // Have node before deleted node point at node after the deleted node
            curr.next = curr.next.next;
        }
        return true;
    }

    /*
        Return true if linked list is empty, false otherwise
        Two ways to know this, if head is null or if size is 0
     */
    public boolean isEmpty() {
        return head == null;
    }

    /*
        insertAtFront is a function that inserts a given item in the front (at the head)
        of the linked list. We will use insertAtPos for simplicity
        Returns true if successful, false otherwise
     */
    public boolean insertAtFront(Object item) {
        return insertAtPos(item, 1);
    }

    /*
        insertAtBack is a function that inserts a given item in the back (at the tail)
        of the linked list. We will use insertAtPos for simplicity
        Returns true if successful, false otherwise
     */
    public boolean insertAtBack(Object item) {
        return insertAtPos(item, size + 1);
    }

    /*
        removeAtFront is a function that removes the item in the front (at the head)
        of the linked list. We will use removeAtPos for simplicity
        Returns true if successful, false otherwise
     */
    public boolean removeAtFront() {
        return removeAtPos(1);
    }

    /*
        removeAtBack is a function that removes the item in the back (at the tail)
        of the linked list. We will use removeAtPos for simplicity
        Returns true if successful, false otherwise
     */
    public boolean removeAtBack() {
        return removeAtPos(size);
    }

    /*
        Search is a function that will receive a given Object key and will
        search the linked list for the key. It will return 0 if not found,
        or the position of the first occurrence of the key if found (pos > 0).

        I will use the .equals function for Object comparison [TO-DO]
    */
    public int search(Object key) {
        // Check for an empty list, exit with failed pos
        if (head == null)
            return 0;

        // Otherwise, iterate through the linked list
        Link curr = head;
        int pos = 0;
        while (curr != null) {
            ++pos;
            if (curr.data.equals(key))
                return pos;
            curr = curr.next;
        }
        // If the key was not found while iterating, the while loop
        // ended (reached end of linked list) then we will return 0 (failure)
        return 0;
    }

    /*
        Peek is a function that receives a given position (positions start at 1)
        and simply peeks at the item at that given position. The function returns
        the item that is there
    */
    public Object peek(int pos) {
        // Check if given position is within bounds of the linked list
        if (pos < 1 || pos > size)
            return null;

        // Also check if the linked list is empty
        if (head == null)
            return null;

        // Iterate through the linked list until the position is met
        Link curr = head;
        while (pos > 1) {
            --pos;
            curr = curr.next;
        }
        return curr.data; // return the Link's data
    }

    // Peek at the front of the linked list
    public Object peekFront() {
        return peek(1);
    }

    // Peek at the back of the linked list
    public Object peekBack() {
        return peek(size);
    }

    /*
        remove is a function that receives a Object input and searches the linked list
        for it, and deletes the first occurrence. It returns true if successful, false
        otherwise
     */
    public boolean remove(Object key) {
        // Check if the linked list is empty
        if (head == null)
            return false;

        Link curr = head; // Reference for current node
        Link prev = null; // Reference for node previous to curr
        while (curr != null) {
            if (curr.data.equals(key)) {
                /*
                    Check for special case, prev being null
                    if prev is null, then we are deleting the first Link in the list
                    and must alter head
                 */
                if (prev == null) {
                    head = head.next;
                }
                else {
                    prev.next = curr.next;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false; // Completed iteration and key was not found+deleted
    }

    /*
        removeAll removes the given key from the linked list, and deletes all
        occurrences of that key.
     */
    public void removeAll(Object key) {
        // Check if the linked list is empty
        if (head == null)
            return; // Exit the function

        Link curr = head; // Reference for the current node
        Link prev = null; // Reference for the node prev to curr

        // Iterate through entire linked list
        while (curr != null) {
            if (curr.data.equals(key)) {
                /*
                   Take care of special case (prev == null), if this occurs
                   we are deleting the first link
                */
                if (prev == null) {
                    head = head.next;
                    curr = head; // Simply move curr to point at new first link, prev is still null
                }
                else {
                    prev.next = curr.next;
                    curr = curr.next; // Move the curr reference to the node after deleted node
                }
            }
            else {
                // Normally shift the references to the next pair of links in the list
                prev = curr;
                curr = curr.next;
            }
        }


    }

    /*
        toString returns a string of the contents of the linked list, from head
        to tail (front to back)
    */
    public String toString() {
        String contents = "";

        Link curr = head; // Reference to the current Link, start at the head of linked list
        while (curr != null) {
            contents += curr.data;
            if (curr.next != null)
                contents += " -> ";
            curr = curr.next; // Move curr to the next Link in the list
        }
        return contents;
    }
}
