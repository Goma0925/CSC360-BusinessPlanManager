package businessplan.main;

import java.util.ArrayList;
import businessplan.exceptions.InvalidSectionDeletionException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Section {
	protected String sectionType;
	protected String sectionName = "";
	protected Section parent = null;
	protected String content = "";
	protected ArrayList<Section> children = new ArrayList<Section>();
	public Section() {};
	
	public Section(String sectionType) {
		//COMPLETED
		this.sectionType = sectionType;
	}
		
	protected Section addChild(String childsectionType){
		//COMPLETED
		Section child = new Section(childsectionType);
		child.parent = this;
		this.children.add(child);
		return child;
	}
	
	protected void deleteChild(Section node) throws InvalidSectionDeletionException {
		//COMPLETED
		node.parent.children.remove(node);
	}
   
   //Attribute getters & setters
   public String getContent() {
		//COMPLETED
		return this.content;
	}
   
	public void setContent(String content) {
		//COMPLETED
		this.content = content;
	}
   
   public String getSectionType(){
      return this.sectionType;
   }
   
   public void setSectionType(String sectionType){
      this.sectionType = sectionType;
   }
   
   public String getSectionName(){
      return this.sectionName;
   }
	
	public void setSectionName(String name) {
		//COMPLETED
		this.sectionName = name;
	}
   
   public void setParent(Section parent){
      this.parent = parent;
   }
	
   public Section getParent(){
      return this.parent;
   } 
   
   public void setChildren(ArrayList<Section> children){
      this.children = children;
   }
   
   public ArrayList<Section> getChildren(){
      return children;
   }
}
