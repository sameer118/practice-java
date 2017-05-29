package com.practice.java.datastructure;


public class MyLinkedList {
	Node first;
	Node last;

	public static class Node {
		Object data;
		Node next;
		Node(Object data) {
			this.data = data;
			this.next = null;
		}
		Node() {
			this.next = null;
		}
	}
	int SIZE = 0;
	public MyLinkedList() {	}

	MyLinkedList(Node first) {
		this.first = first;
		Node currentNode = this.first;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		this.last = currentNode;
	}

	public void add(Object o) {
		if (first == null) {
			SIZE++;
			first = new Node(o);
			last = first;
		} else {
			Node newNode = new Node(o);
			Node currentNode = first;
			// here it's important to check current.next for not null value
			// because
			// at the last node current will become null and so there will not
			// be
			// any reference left to assign new node to.
			while (currentNode.next != null) {
				SIZE++;
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
			last = newNode;
		}
	}

	
	/**
	 * This method will identify the middle element of the list in single pass
	 * with minor auxiliar space and without any extra DS.
	 */
	public Node findMiddleOfTheListInSinglePass() {
		Node current = this.first;
		Node middle = this.first;
		int middleElement = 1, currentElement = 1, prevMiddleElelemnt = 1;
		middle = current;
		while (current != null) {
			int devider = currentElement / 2;
			int modulo = currentElement % 2;
			middleElement = devider + modulo;
			if (middleElement != prevMiddleElelemnt) {
				prevMiddleElelemnt = middleElement;
				middle = middle.next;
			}
			currentElement++;
			current = current.next;
		}
		return middle;
	}

	public Node reverse(Node head, int n) {
		Node current = head;
		Node nextNode = null;
		Node prev = null;
		int counter = 0;

		while (current != null && counter != n) {
			nextNode = current.next;
			current.next = prev;
			prev = current;
			current = nextNode;
			counter++;
		}
		return prev;
	}

	public Node reverseAgain(){
	
		Node prev, next=null;
		Node current = first;
		
		while(current!=null){
			prev=current;
			current=current.next;
			next=current;
			next.next=prev;
		}		
		return next;
	}
	
	public void reverse() {
		Node previous = null, newFirst = null, temp = null;
		Node current = first;
		// newFirst = current;
		// current = current.next;
		// newFirst.next = null;
		while (current != null) {
			// previous = newFirst;
			// temp = current;
			// current = current.next;
			// temp.next = previous;
			// newFirst = temp;
			previous = current;
			// #1. this step is important in the sequence here because
			// current(memory address) is still being referred by previous in
			// last step.
			// so any operation you do on current or previous will update both
			// references
			current = current.next;
			temp = newFirst;
			// here if you din't hv step #1 then newFirst will hold reference of
			// current (through previous)
			// and so once you do newfirst.next = temp (which is null,
			// temp=newFirst=null) then current.next
			// also becomes null and so the chain ends on very first element.
			newFirst = previous;
			newFirst.next = temp;
		}
		first = newFirst;
	}

	/**
	 * Reverse every 3 node in linked list. For example 1 2 3 4 5 6 7 8 9 => 3 2
	 * 1 6 5 4 9 8 7
	 * 
	 * @return
	 */
	public void reverseKthNodesOfList(int k) {

		int count = 1;

		Node current = first;
		Node temp = null;
		Node previous = null;
		Node newFirst = null;
		MyLinkedList listOfSubLists = new MyLinkedList();

		while (current != null) {

			previous = current;
			current = current.next;
			temp = newFirst;
			newFirst = previous;
			newFirst.next = temp;

			if (count == k || current == null) { // if count is 3 or list ended
													// before count reached to 3
				count = 0;
				listOfSubLists.add(new MyLinkedList(newFirst));
				newFirst = null;
			}
			++count;
		}

		MyLinkedList reversedList = mergeSubLists(listOfSubLists);

		// System.out.println("merged list is =>"+reversedList.toString());
		first = reversedList.first;
	}

	private MyLinkedList mergeSubLists(MyLinkedList listOfSubLists) {
		Node current;
		current = listOfSubLists.first;
		MyLinkedList reversedList = (MyLinkedList) current.data;
		MyLinkedList previousList = null;
		MyLinkedList tempList = null;

		while (current.next != null) {
			previousList = reversedList;
			current = current.next;
			tempList = (MyLinkedList) current.data;
			previousList.last.next = tempList.first;
			reversedList = previousList;
			reversedList.last = tempList.last;
		}

		return reversedList;
	}

	public MyLinkedList mergeAlternateNodes(MyLinkedList anotherList) {

		Node temp1 = null;
		Node temp2 = null;

		Node current = null;
		Node newNode = null;

		Node current1 = this.first;
		Node current2 = anotherList.first;

		if (this.SIZE > anotherList.SIZE) {
			current = current1;
			newNode = current1;
		} else {
			current = current2;
			newNode = current2;
		}

		while (current != null) {
			temp1 = newNode;

			if (this.SIZE > anotherList.SIZE) {
				temp2 = current2;
			} else {
				temp2 = current1;
			}

			current1 = current1.next;
			current2 = current2.next;
			current = current.next;
			// System.out.println(current1.data);
			temp1.next = temp2;

			if (temp2 != null) {
				temp2.next = null;
			} else
				break;

			newNode = temp1;
		}
		return null;
	}

	public String toString() {
		String listAsString = "";
		Node current = this.first;
		while (current != null) {
			listAsString = listAsString + current.data + ",";
			current = current.next;
		}

		return listAsString;
	}

	public static void main(String args[]) {

		MyLinkedList myList = new MyLinkedList();
		myList.add(10);
		myList.add(11);
		myList.add(11);
		myList.add(10);
	}
}
