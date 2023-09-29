// Justin Lugo
// COP 3503, Fall 2021

import java.io.*;
import java.util.*;

// Node structure for a BST, modified for generics.
class Node<T>
{
	T data;
	Node<T> left, right;

	Node(T data)
	{
		this.data = data;
	}
}

// For this assignment, we will be modifying the given BST code from holding just integers to being
// able to hold "AnyType" of Comparable objects. We will be able to do this by "extending" the input
// within the function to generics.
public class GenericBST<T extends Comparable<T>>
{
	private Node<T> root;

	// At the beginning we've got our functions for inserting.
	public void insert(T data)
	{
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> root, T data)
	{
		// First we check if our root is null, in which case we'll create a new one.
		if (root == null)
		{
			return new Node<T>(data);
		}
		// For our else-if branches of our loops, we have to change the line to implement the compareTo
		// method, or else the program will fail. As for what it will do, it will compare the values
		// within the nodes to determine which subtree we go down.
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = insert(root.right, data);
		}

		return root;
	}

	// We move on to our deletion functions, which act similarly to the insertion ones but instead of
	// inserting data, we move through the GenericBST to find our node we want to delete.
	public void delete(T data)
	{
		root = delete(root, data);
	}

	private Node<T> delete(Node<T> root, T data)
	{
		if (root == null)
		{
			return null;
		}

		// Compare values within nodes to determine which subtree we go down.
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}

		// If we get this far, this means that we've found the node that will be deleted,
		else
		{
			if (root.left == null && root.right == null)
			{
				return null;
			}
			else if (root.left == null)
			{
				return root.right;
			}
			else if (root.right == null)
			{
				return root.left;
			}

			// Should the node we get to have two children, we take the largest node (by going down the
			// left subtree) and delete the data within the left child.
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private T findMax(Node<T> root)
	{
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// If the data is inside the GenericBST, we return true, otherwise return false.
	public boolean contains(T data)
	{
		return contains(root, data);
	}

	private boolean contains(Node<T> root, T data)
	{
		// If the root is null, we return false as our value has not been found (or the tree is empty).
		if (root == null)
		{
			return false;
		}

		// Compare values within nodes to determine which subtree we go down.
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			return contains(root.right, data);
		}
		else
		{
			return true;
		}
	}

	// These are our functions to recursively print out the GenericBST in In-Order (linearly from
	// smallest to largest).
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	private void inorder(Node<T> root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	// Next we've got our functions to print out our GenericBST in Pre-Order (Start at root then make
	// way down to left and then right children).
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	private void preorder(Node<T> root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	// And finally, we've got our functions to print out our GenericBST in Post-Order (start at
	// children, from left subtree to right subtree all the way up to the root node).
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	private void postorder(Node<T> root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	// This function returns the difficulty I felt with this programming assignment.
	public static double difficultyRating()
	{
		return 2.0;
	}

	// This function returns how long I spent on this programming assignment.
	public static double hoursSpent()
	{
		return 2.0;
	}
}
