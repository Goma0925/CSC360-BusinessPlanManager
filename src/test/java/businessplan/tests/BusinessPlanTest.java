package businessplan.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import businessplan.exceptions.InvalidSectionDeletionException;
import businessplan.exceptions.InvalidSectionInsertionException;
import businessplan.main.*;

class BusinessPlanTest {

	@Test
	void test() {
		
		String xmloutputFilePath = System.getProperty("user.dir") + "/src/test/java/businessplan/outputs/xmloutput.xml";
		System.out.println(xmloutputFilePath);
		
		//create a new bussinessPlan
		String[] hierarchy = {"Vision","Mission","Objective","Strategy","Action"};
		BusinessPlan newPlan = new BusinessPlan("VMOSA", "Plan B", hierarchy);

		BusinessPlan[] bPlans = {newPlan};
		
		for (int i=0; i<bPlans.length; i++) {
			//Get the node
			Section head = bPlans[i].getHead();
			
			//Test content modification methods of Section
			try {
				bPlans[i].deleteSection(head);			
				fail("The head section should not be deleted");
			}catch(InvalidSectionDeletionException e) {
				System.out.println("SUCCESS: Business plan did not allow the invalid head deletion.");
			}

			
			//Test editing the Vision Section
			ArrayList<Section> children = bPlans[i].getChildrenOf(head);
			Section v1 = children.get(0);
			v1.setContent("Vision 1");
			if (v1.getContent() != "Vision 1") {
				fail("Section does not return a proper content");
			}
			//Test addding a new section
			Section mission1 = bPlans[i].getChildrenOf(head).get(0);
			Section obj1 = bPlans[i].getChildrenOf(mission1).get(0);
			
			//Add obj2 under mission1
			try {
				bPlans[i].addChildSectionTo(mission1);	
			}catch (InvalidSectionInsertionException e) {
				System.out.println(e.getMessage());
			}
			
			Section obj2 = bPlans[i].getChildrenOf(mission1).get(1);
			
			//Test trying to add a child to a leaf
			//Get the leaf & trying adding a child to the leaf
			Section action1 = bPlans[i].getChildrenOf(obj1).get(0).getChildren().get(0);
			try {
				bPlans[i].addChildSectionTo(action1);	
				fail("BusinessPlan shold not allow addition to the leaf node.");
			} catch(InvalidSectionInsertionException e) {
				System.out.println("SUCCESS: Business plan did not allow the invalid addition.");
			}
			
			
			//Test trying to change the height of the tree.
			try {
				bPlans[i].deleteSection(obj1);// This should work	
			}catch (Exception e){
				fail("BusinessPlan's deleteSection() raised an exeption");
			}
			
			try {
				bPlans[i].deleteSection(obj2);// This should work
				fail("BusinessPlan should not allow delition the last node of each section type.");
			}catch(InvalidSectionDeletionException e){
				System.out.println("SUCCESS: Business plan did not allow the invalid deletion.");
			};
		}
		
        System.out.println("Starting tests for XML methods...");
        //Create a business plan first
        String[] hierarchy2 = {"Vision","Mission","Objective"};
		BusinessPlan planV = new BusinessPlan("VMOSA-Short", "Plan V", hierarchy2);
        System.out.println("Plan instantiated");
        //Fill in the data in the business plan
        try{
           Section head = planV.getHead();
           planV.addChildSectionTo(head);
           Section v1 = planV.getChildrenOf(head).get(0);
           v1.setSectionName("V1");
           planV.addChildSectionTo(head);
           Section v2 = planV.getChildrenOf(head).get(1);
           v2.setSectionName("V2");
           Section m1 = planV.getChildrenOf(v1).get(0);
           m1.setSectionName("M1");
           Section obj1 = planV.getChildrenOf(m1).get(0);
           obj1.setSectionName("Obj1");
        }catch(Exception e){
        	System.out.println("Initialization error");
           fail(e.getMessage());
        };
        
		BusinessPlanSerializer serializer = new BusinessPlanSerializer();
        File storageFile = new File(xmloutputFilePath);
        try {
            serializer.encodeToXML(planV, storageFile);
        }catch (Exception e) {
        	fail("BusinessPlanSerializer failed to encode a BusinessPlan instance into XML.");
        };
        //BusinessPlan restoredPlan = serializer.decodeFromXML(storageFile);
		
        
        System.out.println("SUPER SUCCESS: We passed all the tests!");
	}

}
