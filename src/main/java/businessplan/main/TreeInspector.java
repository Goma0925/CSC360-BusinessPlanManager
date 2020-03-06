package businessplan.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import businessplan.main.*;

public class TreeInspector {
	public TreeInspector() {};
	public boolean isSameTrees(BusinessPlan a, BusinessPlan b){
		LinkedList<Section> aList = this.getAllNodes(a.getHead());
		LinkedList<Section> bList = this.getAllNodes(b.getHead());
		System.out.println("------------Comparing the trees-------------");
		System.out.println(a);
		System.out.println(b);
		System.out.println("--------------------------------------------");

		if (a == null || b == null) {
			System.out.println("In TreeInspector.isSameTreesTrees: Trees not defined!");
		}
			
		if (aList.size() != bList.size()){
			return false;
		}
		Section sectA;
		Section sectB;
		while (!aList.isEmpty() || !bList.isEmpty()) {
			System.out.println("-----------------Node Info------------------");
			sectA = aList.pop();
			sectB = bList.pop();
			System.out.println("Node data:");
			System.out.print("Tree A node:");
			System.out.print(sectA.getSectionType() + ", ");
			System.out.print(sectA.getSectionName() + ", ");
			System.out.print(sectA.getContent() + "\n");
			System.out.print("Tree B node:");
			System.out.print(sectB.getSectionType() + ", ");
			System.out.print(sectB.getSectionName() + ", ");
			System.out.print(sectB.getContent() + "\n");
			if (!isSameSections(sectA, sectB)){
				System.out.println("NODES DID NOT MATCH.");
				return false;
			}
			System.out.println("--------------------------------------------");
		}
		System.out.println("ALL NODES MATCHED");
		return true;
	};
	
	private boolean isSameSections(Section a, Section b) {
		return a.getContent().equals(a.getContent()) &&
				a.getSectionType().equals(b.getSectionType()) &&
				a.getSectionName().equals(b.getSectionName());
	}
	
	private LinkedList<Section> getAllNodes(Section root) {
		Stack<Section> stack = new Stack<Section>();
		stack.push(root);
		LinkedList<Section> allNodes = new LinkedList<Section>();
		boolean isDone = false;
		Section current;
		ArrayList<Section> children = new ArrayList<Section>();
		while (!isDone) {
			current = stack.pop();
			children.clear();
			children = current.getChildren();
			for (int i=0; i<children.size(); i++) {
				stack.push(children.get(i));
			};
			//Action
			allNodes.push(current);
			if (stack.size() == 0) {
				isDone = true;
			}
		};
		return allNodes;
	}
}
