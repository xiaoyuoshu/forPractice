import java.lang.reflect.Array;
import java.util.*;

import javax.xml.soap.Node;;

class BTNode{
	int data;
	BTNode lchild,rchild;
	public BTNode(int data) {
		this.data=data;
		this.lchild=null;
		this.rchild=null;
	}
}

public class BinarySortTree {
	public static void main(String[] args){
		int[] data = new int[10];
		for (int i = 0; i < 10; i++) {
			data[i] = (int)(Math.random()*1000);
			System.out.print(data[i] + ",");
		}
		BinarySortTree binarySortTree = new BinarySortTree(data);
		System.out.print("\n");
		binarySortTree.inOrder();
		System.out.print("\nsearch:");
		Scanner scanner = new Scanner(System.in);
		int search = scanner.nextInt();
		if(binarySortTree.searchData(search))
			System.out.println("true");
		else
			System.out.println("false");
		System.out.print("\ndelete:");
		int delete = scanner.nextInt();
		binarySortTree.deleteData(delete);
		binarySortTree.inOrder();
		int[] arr = binarySortTree.inOrderForArray();
		int wait = scanner.nextInt();
		for (int i : arr) {
			System.out.print(i + ",");
		}
		System.out.println("###"+binarySortTree.getMinimumDifference());
	}
	
	BTNode root;
	//��ͨ���췽��
	public BinarySortTree() {
		root=null;
	}
	//ֱ�ӽ����Ĺ��췽��
	public BinarySortTree(int[] data){
		for(int i=0;i<data.length;i++){
			insertData(data[i]);
		}
	}
	//ɾ��������
	public void deinit(){
		this.root=null;
	}
	//��������
	public boolean insertData(int data){
		BTNode node = new BTNode(data);
		if(this.root == null){
			this.root = node;
			return true;
		}
		else{
			BTNode current = root;
			BTNode parent;
			while(true){
				parent = current;
				if(data < current.data){
					current = current.lchild;
					if(current == null){
						parent.lchild = node;
						return true;
					}
				}
				else if(data > current.data){
					current = current.rchild;
					if(current == null){
						parent.rchild = node;
						return true;
					}
				}
				else{
					return false;
				}
			}
		}
	}
	//ɾ������
	public boolean deleteData(int data){
		BTNode current = this.root;
		BTNode parent = this.root;
		//�ҵ�Ҫɾ���ڵ㼰��ǰ��
		while(current != null){
			if(data == current.data) break;
			parent = current;
			if(data < current.data) current = current.lchild;
			else current = current.rchild;
		}
		//δ�ҵ���Ӧ���ݣ�����false
		if(current == null) return false;
		//���ڵ�ΪҶ�ӽ��ʱ��ֱ��ɾ����
		if(current.lchild == null && current.rchild == null){
			if(parent.lchild == current) parent.lchild = null;
			else parent.rchild = null;
			return true;
		}
		//���ڵ������ӣ����Һ���ʱ
		else if(current.lchild != null && current.rchild == null){
			if(parent.lchild == current) parent.lchild = current.lchild;
			else parent.rchild = current.lchild;
			return true;
		}
		//���ڵ����Һ��ӣ�������ʱ
		else if(current.lchild == null && current.rchild != null){
			if(parent.lchild == current) parent.lchild = current.rchild;
			else parent.rchild = current.rchild;
			return true;
		}
		//���ڵ������Һ��ӣ���������ʱ���ҵ��Һ�������С�Ľڵ㣬������
		else{
			BTNode findNewNode = current.rchild;
			BTNode preNewNode = current;
			//�ҵ���������С�ڵ�������ǰ��
			while(findNewNode.lchild != null){
				preNewNode = findNewNode;
				findNewNode = findNewNode.lchild;
			}
			//��ǰ���ڵ�Ϊ��ɾ���ڵ�ʱ
			if(preNewNode == current){
				current.data = findNewNode.data;
				current.rchild = findNewNode.rchild;
			}
			
			else{
				preNewNode.lchild = findNewNode.rchild;
				current.data = findNewNode.data;
			}
			return true;
		}
	}
	
	public boolean searchData(BTNode current , int data) {
		if(current == null) return false;
		if(data < current.data) return searchData(current.lchild , data);
		else if(data > current.data) return searchData(current.rchild, data);
		else return true;
	}
	
	public boolean searchData(int data) {
		if(this.root == null) return false;
		if(data < this.root.data) return searchData(this.root , data);
		else if(data > this.root.data) return searchData(this.root.rchild, data);
		else return true;
	}
	
	public void preOrder(BTNode Root){
		if(Root != null){
			System.out.print(Root.data + ",");
			preOrder(Root.lchild);
			preOrder(Root.rchild);
		}
	}
	
	public void preOrder() {
		this.preOrder(this.root);
	}
	
	public void inOrder(BTNode Root){
		if(Root != null){
			inOrder(Root.lchild);
			System.out.print(Root.data + ",");
			inOrder(Root.rchild);
		}
	}
	
	public void inOrder() {
		this.inOrder(this.root);
	}
	
	public int[] inOrderForArray(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		Stack<BTNode> stack = new Stack<BTNode>();
	    BTNode current = root;
	    while(current!=null || !stack.empty()){
	        while(current!=null){
	            stack.add(current);
	            current = current.lchild;
	        }
	        current = stack.pop();
	        list.add(current.data);
	        current = current.rchild;
	    }
	    int[] arr = new int[list.size()];
	    int j = 0;
	    for (int i : list) {
			arr[j] = i;
			j++;
		}
	    return arr;
	}
	
	public void postOrder(BTNode Root){
		if(Root != null){
			postOrder(Root.lchild);
			postOrder(Root.rchild);
			System.out.print(Root.data + ",");
		}
	}
	
	public void postOrder() {
		this.preOrder(this.root);
	}
	
	public boolean isSameTree(BTNode p, BTNode q) {
        if(p == null && q == null) return true;
        else if(p == null && q != null) return false;
        else if(p != null && q == null) return false;
        else{
            if(p.data != q.data) return false;
            else return isSameTree(p.lchild , q.lchild) && isSameTree(p.rchild , q.rchild);
        }
    }
	
	public boolean isSameTree(BTNode another) {
		return isSameTree(this.root , another);
	}
	
	public boolean isValidBST() {
        if(this.root==null)   return true;
        Integer max = null , min = null;
        return isValidBST(this.root.lchild , this.root.data , min) && isValidBST(this.root.rchild , max , this.root.data); 
    }
    public boolean isValidBST(BTNode root, Integer max, Integer min){
       if(root==null)   return true;
        if(max != null && root.data >= max) return false;
        if(min != null && root.data <= min) return false;
        return isValidBST(root.lchild , root.data , min) && isValidBST(root.rchild , max , root.data);  
    }
	
public int getMinimumDifference() {
        int[] arr = this.inOrderForArray();
        int tag = Integer.MAX_VALUE;
        for(int i = arr.length - 2;i >= 0;i--){
        	if(tag > arr[i+1] - arr[i]) tag =arr[i+1] -arr[i];
        }
        return tag;
    }
    
}
