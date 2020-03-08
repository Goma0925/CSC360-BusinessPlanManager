# CSC360-BusinessPlanManager
Our program consists of two concrete classes: BusinessPlan Manager and Section.
To define a new business plan, call the business plan constructor with a planType, a planName and a list ordered by the section 
heirarchy of your plan. 
Ex: 
	BusinessPlan BP = BusinessPlan("VMOSA", "Computer Science", ["Vision","Mission","Objectives","Strategies","Plan of Action"])
	
We create individual business plan classes because creating them will limit the flexibility of dynamically creating a new business plan temlate. So, we concluded that,  once a business plan template is created(Such as VMOSA above), the hierachy structure particular to the plan is stored in the XML file, and a user can restore it back from the file. Essentially, we move the role to store the hierarchy of sections from each class to each file. 
	
<b>The demonstration of the usage and test are in /src/test/java/businessplan/tests/BusinessPlanTest.java</b>
 
