import java.util.*;

/*
  using enum to assign grades to gpa
 */


 enum Grades{

    APLUS("A+",90,100,4), 
    A("A",80,90,4.0), 
    BPLUS("B+",75,80,3.5), 
    B("B",70,75,3.0), 
    CPLUS("C+",65,70,2.5), 
    C("C",60,65,2.0),  
    DPLUS("D+",55,60,1.5), 
    D("D",50,55,1.0),
    F("F",0,50,0.0); 
    

    private String grade;
    private double gradeMin;
    private double gradeMax;
    private double GPA;

    private Grades(String grade, double gradeMin, double gradeMax, double GPA) {
        this.grade = grade;
        this.gradeMin = gradeMin;
        this.gradeMax = gradeMax;
        this.GPA = GPA;
    }
    public String getStringGrade(){
        return grade;
    }
    public double getGradeMin() {
        return gradeMin;
    }

    public double getGradeMax() {
        return gradeMax;
    }
    public double getGPA(){
        return GPA;
    }
    
 }

public class Module {
    
    private String name;
    private String moduleCode;
    private String description;
    private int creditUnit;

    private ArrayList<Assessment> ass_list = new ArrayList<Assessment>();

    //public Module(){}
    public Module(String name, String moduleCode, String description, int creditUnit)throws Exception{
        
        this.name = name;
        this.moduleCode = moduleCode;
        this.description = description;
        this.creditUnit = creditUnit;
        
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getModuleCode(){
        return moduleCode;
    }
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }
    public String getdescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public int getcreditUnit(){
        return creditUnit;
    }
    public void setCreditUnit(int creditUnit){
        this.creditUnit = creditUnit;
    }
    public void addAssessment(Assessment ass){
        this.ass_list.add(ass);
    }
    public ArrayList<Assessment> getAss_list() {
        return ass_list;
    }
    
    /*public void getAss_list() {
        for(Assessment ass : ass_list){
            System.out.println(ass.getName()+" "+ass.getDescription()+" "+ass.getWeightageMarks());
        }
    }
    */
    

    // ADD TO STRING METHOD
    
    public double getOverallMarks(Student stu){
        double total_ScoredMarks = 0.0;
        //looping through each Assignment list to get the marks
        ArrayList<Module> stu_mod_list = stu.getMod_list();
        Iterator itr = stu_mod_list.iterator();
        while(itr.hasNext()){
            Module mod = (Module)itr.next();      
            ArrayList <Assessment> ass_list = mod.ass_list;
            Iterator itr1 = ass_list.iterator();
            while(itr1.hasNext()){
                Assessment ass = (Assessment)itr1.next();
                double temp_total =ass.getWeightageMarks();
                total_ScoredMarks +=temp_total;
            }
        }
        return total_ScoredMarks;
    }
    
    public double getOverallTotalMarks(Student stu){
        double poss_totalMarks =0.0;
        ArrayList<Module> stu_mod_list = stu.getMod_list();
        Iterator itr = stu_mod_list.iterator();
        while(itr.hasNext()){
            Module mod = (Module)itr.next();      
            ArrayList <Assessment> ass_list = mod.ass_list;
            Iterator itr1 = ass_list.iterator();
            while(itr1.hasNext()){
                Assessment ass = (Assessment)itr1.next();
                double temp_total =ass.getWeightage();
                poss_totalMarks +=temp_total;
            }
        }
        return poss_totalMarks;
    }
    

    //for all modules
    
    public String getOverallGrade(Student stu){
        double marks = getOverallMarks(stu)/getOverallTotalMarks(stu) *100;
        for (Grades g : Grades.values()) {
            if (marks >= g.getGradeMin() && marks <= g.getGradeMax()) {
                return g.getStringGrade();        //retrun A,A+
            }                                                                
        }
        return "invalid grades!";       
    }
    
    //for each module
    public String getOverallGrade(double marks){
        
        for (Grades g : Grades.values()) {
            if (marks >= g.getGradeMin() && marks <= g.getGradeMax()) {
                return g.getStringGrade();        //retrun A,A+
            }                                                                
        }
        return "invalid grades!";       
    }
    public static double getGradePoint(String gradeGPA){
        for(Grades g : Grades.values()){
            if(gradeGPA.equals(g.getStringGrade())){
                return g.getGPA();
            }
        }
        return 0.0;
    }

    public double getWeightedGradePoint(Student stu){
        
        double gpa = getGradePoint(getOverallGrade(stu));       
        return gpa*creditUnit; 
    }

    public String toString(){
        return name+" : "+ moduleCode +" : "+ description + " : " + creditUnit +" ";    
    }
}
