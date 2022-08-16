
public class Assessment {
    
    private String name;
    private String description;
    private Module module;
    private double marks;
    private double totalMarks;
    private double weightage;

    //public Assessment(){} 
    public Assessment(String name, String description, Module module, double marks, double totalMarks, double weightage) throws Exception{
        
        //to check name is alphabet
        
        if(marks<0 && marks>totalMarks){
            throw new Exception("Invalid marks!");
        }
        if(totalMarks<marks){
            throw new Exception("Total marks cannot be lower than the marks!");
        }
        if(weightage>100){
            throw new Exception("The weightage cannot be over 100 percent!");
        }
        this.name = name; 
        this.description = description;
        this.module = module;
        this.marks = marks;
        this.totalMarks = totalMarks;
        this.weightage = weightage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name)throws Exception {
        
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getMarks() {
        return this.marks;
    }

    
    public void setMarks(double marks)throws Exception {
        if(marks<0 && marks>totalMarks){
            throw new Exception("Invalid marks!");
        }
        this.marks = marks;
    }

    public double getTotalMarks() {                   //total mark final
        return this.totalMarks;
    }

    public void setTotalMarks(double totalMarks)throws Exception {
        if(totalMarks<marks){
            throw new Exception("Total marks cannot be lower than the marks!");
        }
        this.totalMarks = totalMarks;
    }

    public double getWeightage() {
        return this.weightage;
    }

    public void setWeightage(double weightage)throws Exception {
        if(weightage>100){
            throw new Exception("The weightage cannot be over 100 percent!");
        }
        this.weightage = weightage;
    }

    public double getWeightageMarks(){
        return marks/totalMarks *weightage;   
    }

    public String toString(){
        return name+" : "+ description +" : "+ module + ":" + marks+" : " + totalMarks+" : " +weightage;
    }

   

}
