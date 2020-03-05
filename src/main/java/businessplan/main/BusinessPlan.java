package businessplan.main;
import java.util.ArrayList;
import java.util.Hashtable;
//import java.util.Map;
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

import businessplan.main.Section;
import businessplan.exceptions.*;

public class BusinessPlan {
	Section head = null;
	String planType = "";
	String planName =  ""; 
	Hashtable<String, String> hierarchyDict = null;
	public BusinessPlan(){};
	
	public BusinessPlan(String planType, String planName, String[] sectionHierarchy) {
		this.planType = planType; 
		this.planName = planName;
		this.hierarchyDict = new Hashtable<String,String>();
		this.head = new Section("HEAD");
		this.defineStructure(sectionHierarchy);
	};
   	
	private void defineStructure(String[] sectionHierarchy) {
		//initialize the tree structure
      Section current = head;
      if (sectionHierarchy.length != 0){
            hierarchyDict.put(head.sectionType, sectionHierarchy[0]);
      }
		for(int i = 0; i < sectionHierarchy.length; i++)
		{  
         String childSectionType = sectionHierarchy[i];
         
         //Add a child to the current node.
         current.addChild(childSectionType);
         
         //Define the structure in the hierarchyDict
         if (i != (sectionHierarchy.length-1))//If not the last in the array
         {
            hierarchyDict.put(sectionHierarchy[i],sectionHierarchy[i+1]);
            current = current.getChildren().get(0);
         }else {
        	hierarchyDict.put(sectionHierarchy[i], "LEAF");
         }
		}
	};
	
	private boolean isNotLeafSectionType(String sectionType) {
		if (!hierarchyDict.containsKey(sectionType)) {
			return false;
		}
		else if (hierarchyDict.get(sectionType) == "LEAF") {
			return false;
		}else {
			return true;			
		}
	}
	
	public void addChildSectionTo(Section parent) throws InvalidSectionInsertionException{
		// Add a section to the predefined section.
		//Section head = this.head;
		
		//1. Check if parent's sectionType is a valid section type
		if (!this.hierarchyDict.containsKey(parent.sectionType)){
			throw new InvalidSectionInsertionException("Cannot Insert to this node.");
		}else{
			String childSectionType = hierarchyDict.get(parent.sectionType);
			//2. Check if the parent is not a leaf
			boolean nodeCanHaveChildren = isNotLeafSectionType(childSectionType);//Implement here ); 
					if (nodeCanHaveChildren) {
						parent.addChild(childSectionType);
					}else {
						//Raise an Exception
						throw new InvalidSectionInsertionException("Section is a leaf and cannot have a child node.");
					};
			
		};
	};
	
	public void deleteSection(Section node) throws InvalidSectionDeletionException{
		// Delete a section from the predefined section.
		if (this.isNotLeafSectionType(node.sectionType)) {
         Section parent = node.parent;
         if (parent == null){
               throw new InvalidSectionDeletionException("Section is either not part of the tree or does not have a parent.");
         }
        
         boolean nodeIsNotLastChild = (parent.children.size() > 1);    
			if (nodeIsNotLastChild) {
				parent.deleteChild(node);
			}else{
				throw new InvalidSectionDeletionException("BusinessPlan requires at least one of this type of sectio - " + node.sectionType);
			}
		}else {
			throw new InvalidSectionDeletionException("Section's sectionType attribute '" + node.sectionType + "' does not exist in the defined sectionType of this business plan.");
		}
	};
	
	public ArrayList<Section> getChildrenOf(Section node){
		return node.getChildren();
	}
	
   // The methods below are for the XML serializer
   public void setPlanType(String planType){
      this.planType = planType;
   };
   
   public String getPlanType(){
      return this.planType;
   }
   
   public void setPlanName(String planName){
      this.planName = this.planName;
   }
   
   public String getPlanName(){
      return this.planName;
   }
   
   public void setHierarchyDict(Hashtable hierarchyDict){
      this.hierarchyDict = hierarchyDict;
   }
   
   public Hashtable getHierarchyDict(){
      return this.hierarchyDict;
   }
   
   public void setHead(Section head){
    this.head = head;
   }
   
   public Section getHead(){
      return this.head;
   }
};
