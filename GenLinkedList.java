import java.io.*;
import java.util.NoSuchElementException;

public class GenLinkedList<T> {

		//data members
		public Node<T> head;
		public Node<T> tail;
		int length = 0;
		
		//class to hold data for nodes
		private static class Node<T> {
			
			//data members
			T data;					//stores value of node
			Node<T> next;			//stores address of next node
			
			//constructor
			Node(T data){
				
				this.data = data;
				this.next = null;
			}
		}
		
		//constructor
		GenLinkedList(){
			
			this.head = null;
			this.tail = null;
		}
			
		//add node to front method
		public void addFront(T data) {
			
			//new node created with data passed
			Node<T> newNode = new Node<>(data);
			
			//if the head is empty
			if (head == null) {
				
				//place the new node at head
				head = newNode;
				tail = head;
				
			} else {
				
				//move the original head and place new node at head
				newNode.next = head;
				head = newNode;
				
				//increase length of list
				length++;
			}
			
		}
		//add node to end method
		public void addEnd(T data) {
			
			//new node created with data passed
			Node<T> newNode = new Node<>(data);
			
			//if the list is empty
			if (head == null) {
				
				//place the node at the end
				head = newNode;
				tail = head;
			
			//if the list is not empty
			} else {
				
				//create a temporary node for later use
				Node<T> temp = new Node<>(data);
				
				tail.next = temp;
				tail = tail.next;
			}
			//increase length of list
			length++;
		}
		//remove node from front method
		public void removeFront() {
			
			//new node with a null value
			Node<T> temp = new Node<>(null);
			
			temp.next = head;
			
			//if the list is empty
			if(head == null) {
				
				throw new NoSuchElementException();
				
			//if list is not empty
			} else if (head == tail){
				
				head = null;
				tail = null;
				
			} else {
				
				//move the node to next position 
				Node<T> hold = head;
				head = head.next;
				hold.next = null;
				
			}
			length--;
		}
		//remove node from end method
		public void removeEnd() {
			
			//if the list is empty
			if(head == null) {
				
				throw new NoSuchElementException();
			
			//if the list is not empty
			} else if (head == tail){
				
				head = null;
				tail = null;
				
			} else {
				
				//create temporary node
				Node<T> beforeLast = head;
				
				//iterate through the list
				while(beforeLast.next.next != null) {
					
					//find the second to last node
					beforeLast = beforeLast.next;
				}
				//change the last node to null
				beforeLast.next = null;
			}
		}
		//set data to certain node
		public void setNode(T data, int position) {
			
			//determine position given is within the list size
			if(position > length + 1) {
				
				//send error message to user
				System.out.println("Given position is out of bounds.");
				return;
			
			//if position is within size of list
			} else {
				
				//create a temporary node
				Node<T> temp = head;
				int count = 0;
				
				//create node with new info
				Node<T> updatedNode = new Node<>(data);
			
				//iterate through the list
				while(temp != null) {
				
					//if the position and index are the same
					if(count == position) {
					
						//change the old data to updated data
						temp.data = updatedNode.data;
					}
					
					temp = temp.next;
					count++;
				
				}
			}
		}
		//get method, returns data at specific method
		public void getNode(int position) {
			
			//determine position given is within the list size
			if(position > length + 1 || position < 0) {
						
				throw new ArrayIndexOutOfBoundsException("Index " + position + "; size " + length);
					
			//if position is within size of list
			} else {
			
				//create a temporary node
				Node<T> temp = head;
			
				//iterate through the list until the index is found
				for(int i = 0; position < i; i++) {
					
					temp = temp.next;
				}
				
				//return the data at given position
				System.out.println(" " + temp.data);
				return;
			}
			
		}
		//swap nodes, gets two indexes and swaps them
		public void swapNode(int position1, int position2) {
			
			//make sure both indexes are within length of the list
			Node<T> t = head;
			int length = 0;
			
			while(t.next != null) {
				t = t.next;
				length++;
			}
			//if the list is empty
			if(head == null) {
				
				throw new NoSuchElementException();
			}
			//if one of the positions is greater than the length
			if(position1 > length + 1 || position2 > length + 1) {
				
				throw new ArrayIndexOutOfBoundsException("Index " + position1 + position2 + "; size " + length);
				
			} 
			//if both positions are the same
			else if(position1 == position2) {
				System.out.println(" The values are the same. No changes made.");
				return;
				
			} else {
			
				//search for position 1
				Node<T> p1 = head;
				Node<T> previous1 = null;
				int count = 0;
			
			
				while(p1 != null && count != position1) {
					previous1 = p1;
					p1 = p1.next;
					count++;
				}
			
				//search for position 2
				Node<T> p2 = head;
				Node<T> previous2 = null;
				int counting = 0;
			
				while(p2 != null && counting != position2) {
					previous2 = p2;
					p2 = p2.next;
					counting++;
				}
			
				//if position 1 is not the head
				if (previous1 != null) {
				
					previous1.next = p2;
				
				} else {
				
					//make position 2 the head
					head = p2;
				}
			
				//if position 2 is not the head
				if (previous2 != null) {
	        	
					previous2.next = p1;
				} else {
	        	
					//make position 1 the head
					head = p1;
				}
				//switch the pointers to the correct node
				Node<T> temp = p1.next;
				p1.next = p2.next;
				p2.next = temp;
			}
			
		}
			
		//shift method, shifts position by certain amount
		public void shiftNode(int shift) {
			
			//if the number is 0
			if(shift == 0) {
				return;
			}
			//if the number is positive, will shift in clockwise direction
			else if (shift > 0) {
				
				int count = 0;
				Node<T> temp = head;
				
				//the temporary node will point to the last node
				while(temp.next != null) {
					
					temp = temp.next;
					count++;
				}
				//if the number given is larger than the length of the list
				if(shift > count) {
					
					//divide the number by the length of the list to get the number to shift
					shift = shift % count;
				}
				shift = length - shift;
				
				Node<T> hold = head;
				int counting = 1;
				
				//node will point to the correct node
				while(counting < shift && hold != null) {
					
					hold = hold.next;
					counting++;
				}
				
				//if the shift number is greater than the number of nodes in the list, do not do anything
				if(hold == null) {
					return;
				}
				
				//update the nodes to point to the correct node
				//will be a circular list - the last node will now point to the beginning of the list
				Node<T> node = hold;
				temp.next = head;
				head = node.next;
				node.next = null;
				
			}
			//if the number is negative, will shift counter-clockwise direction
			else if(shift < 0) {
				
				int count = 1;
				Node<T> temp = head;
				
				//make the number positive
				shift = 0 - shift;
				
				//the node will point to the correct node
				while(count < shift && temp != null) {
				
					//shift through the list
					temp = temp.next;
					count++;
				}
				
				//if the shift number is greater than the number of nodes in the list, do not do anything
				if(temp == null) {
					return;
				}
				
				Node<T> node = temp;
			
				//the temporary node will point to the last node
				while(temp.next != null) {
				
					temp = temp.next;
				}
			
				//update the nodes to point to the correct node
				//will be a circular list - the last node will now point to the beginning of the list
				temp.next = head;
				head = node.next;
				node.next = null;
			}
			
		}
		//removes matching items in linked list
		public void removeMatching(T data) {
			
			//create temporary nodes
			Node<T> hold = head;
			Node<T> previous = new Node<>(null);
			
			//if the beginning of the list has the item
			while(hold != null && hold.data == data) {
				
				//change the head to next node
				head = hold.next;
				hold = head;
			}
			
			//if the data is found in other nodes other than the beginning
			while(hold != null) {
				
				//search through the list for the nodes to be removed based on matching data
				while(hold != null && hold.data != data) {
					
					//rearrange nodes to remove node with matching data
					previous = hold;
					hold = hold.next;
				}
				//if the data is not find in the linked list
				if(hold == null) {
					
					return;
				}
				
				//remove the node from the list
				previous.next = hold.next;
				
				hold = previous.next;
			}
			
			
		}
		//erase method, removes nodes based on position and amount
		public void eraseNode(int position, int amount) {
			
			//create temporary nodes
			Node<T> temp = head;
			Node<T> hold = new Node<>(null);
			int count = 0;
			
			//if the list is empty, do not do anything
			if(head == null) {
			
				return;
			}
			//if the first element needs to be removed
			else if(position == 0) {
				
				hold = temp.next;
				
				//start from the given position, until the amount is reached
				for(count = 0; count <= amount && hold != null; count++) {
					
					Node<T> node = hold;
					hold = hold.next;
					
				}
				//rearrange to nodes to point to correct nodes
				temp.next = hold;
				temp = hold;
				
			//if an element other than the first needs to be removed
			} else {
				
				temp = head;
				//iterate through list until the position is found
				while(count < position) {
					
					temp = temp.next;
					count++;
				}
				//if the position is found
				if(count == position) {
						
					hold = temp.next;
						
					//start from the given position, until the amount is reached
					for(count = 0; count <= amount && hold != null; count++) {
							
						Node<T> node = hold;
						hold = hold.next;
							
					}
					//rearrange to nodes to point to correct nodes
					temp.next = hold;
					temp = hold;
				}
				//if position is greater than the length of the list
				if (position > count + 2) {
					System.out.println("The position and amount are out of bounds for list. Please re-enter new values");
				}
			}
			
		}
		//adds list method, receives a generic list and position and enters into the list
		public void insertList(GenLinkedList<T> list, int position) {
			
			
			Node<T> temp = head;
			int count = 0;
			
			//iterate through the list until the position is found
			while(count < position) {
				
				temp = temp.next;
				count++;
			}
			
			//if position is found
			if(count == position) {
					
				//join the new list to the original linked list
				Node<T> hold = temp.next;
				Node<T> head2 = list.head;
				temp.next = head2;
					
				//go through the entire second list until the end
				while(head2.next != null) {
						
					head2 = head2.next;
				}
					
				//join the end of the list to the linked list
				head2.next = hold;
				return;
					
			//if the position is greater than the length of the list
			} else if (position > count + 2) {
					
				System.out.println("The position and amount are out of bounds for list. Please re-enter new values");
			}
		}
		@Override
		//method to display linked list
		public String toString()
		{

			String str = " ";

			Node<T> temp = head;

			if (temp == null) {

				return str;
			}
			while (temp.next != null) {
					str = str + String.valueOf(temp.data) + " ";
					temp = temp.next;
			}

			str = str + String.valueOf(temp.data);
			return str;
		}
		
		//main method to test the methods above
		public static void main(String[] args) {
			
			        //create a linked list to test methods above
					GenLinkedList<Integer> linkedlist = new GenLinkedList<>();
					
					//add elements to list to test methods
					linkedlist.addEnd(20);
					linkedlist.addEnd(13);
					linkedlist.addEnd(2);
					linkedlist.addEnd(4);
					linkedlist.addEnd(25);
					linkedlist.addEnd(10);
					linkedlist.addEnd(12);
					linkedlist.addEnd(3);
					linkedlist.addEnd(10);
					linkedlist.addEnd(11);
					linkedlist.addEnd(6);
					
					//display the original linked list before any methods are used
					System.out.println(linkedlist);
					
					//test the add to front method
					//pass info to method to add to front
					linkedlist.addFront(12);
					System.out.println(linkedlist);
					
					//test the add to end method
					linkedlist.addEnd(30);
					System.out.println(linkedlist);
					
					//test the remove front method
					linkedlist.removeFront();
					System.out.println(linkedlist);
					
					//test the remove end method
					linkedlist.removeEnd();
					System.out.println(linkedlist);
					
					//test the set node method 
					linkedlist.setNode(15, 4);
					System.out.println(linkedlist);
					
					//test the get node method
					linkedlist.getNode(6);
					
					//test the swap method
					linkedlist.swapNode(3, 7);
					System.out.println(linkedlist);
					
					//test the shift method
					linkedlist.shiftNode(-5);
					System.out.println(linkedlist);
					
					//test the remove matching data method
					int key = 10;
					linkedlist.removeMatching(key);
					System.out.println(linkedlist);
					
					//test the erase method
					int position = 5;
					int amount = 3;
					linkedlist.eraseNode(position, amount);
					System.out.println(linkedlist);
					
					//test the add list method
					GenLinkedList<Integer> list = new GenLinkedList<>();
					list.addEnd(8);
					list.addEnd(7);
					list.addEnd(22);
					list.addEnd(13);
					list.addEnd(9);
					list.addEnd(18);
					linkedlist.insertList(list, 4);
					System.out.println(linkedlist);

			}
}
