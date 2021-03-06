public class LinkedListDeque<Item> implements Deque<Item> {

    private IntNode sentinel;
    private int size;

    private class IntNode {
        private Item item;
        private IntNode prev;
        private IntNode next;

        public IntNode(IntNode p, Item i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(Item item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        sentinel.prev.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode curr = sentinel;
        while (curr.next != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(" ");
    }

    @Override
    public Item removeFirst() {
        IntNode old = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return old.item;
    }

    @Override
    public Item removeLast() {
        IntNode old = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return old.item;
    }

    @Override
    public Item get(int index) {
        IntNode curr = sentinel;
        while (index > 0) {
            curr = curr.next;
            index -= 1;
        }
        return curr.next.item;
    }
    //Try with helper method
    public Item getRecursive(int index) {
        return getRecursivehelper(index, sentinel);
    }

    private Item getRecursivehelper(int x, IntNode current) {
        if (x == 0) {
            return current.next.item;
        } else {
            return getRecursivehelper(x - 1, current.next);
        }
    }

}


