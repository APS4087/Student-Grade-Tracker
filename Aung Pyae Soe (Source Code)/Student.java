
import java.util.*;


public class Student {

    private final String name;              //assuming name and id cant be changed
    
    private final String studentID;
    private ArrayList<Module> mod_list = new ArrayList<Module>();

    public Student(String name, String studentID)throws Exception{

        //to check name is alphabet
        String check_name = name.replace(" ", "");
        if(!check_name.matches("[a-zA-Z]+")){
            throw new InputMismatchException();
        }
        
        this.name = name;
        this.studentID = String.valueOf(studentID); //in case student id is all numberic.....converting to String
        
    }

    public String getName() {
        return name;
    }
   

    public String getStudentID() {
        return this.studentID;
    }
    

    public void addModule(Module module){
        this.mod_list.add(module);
    }
    public void removeModule(Module module){
        this.mod_list.remove(module);
    }
    public ArrayList<Module> getMod_list() {
        return mod_list;
    }

           //need method to remove arraylist

    public int getTotalCreditUnits(Student stu){
        int totalCDU = 0;
        ArrayList<Module> stu_mod_list = stu.getMod_list();
        Iterator itr = stu_mod_list.iterator();
        while(itr.hasNext()){
            Module mod = (Module)itr.next();      
            int temp_CDU = mod.getcreditUnit();
            totalCDU += temp_CDU;
        }
        return totalCDU;
    }

    public double getTotalWeightedGradePoints(Student stu){
        double total_WGP=0.0;
        ArrayList<Module> stu_mod_list = stu.getMod_list();
        Iterator itr = stu_mod_list.iterator();
        while(itr.hasNext()){
            Module mod = (Module)itr.next();
            double temp_WGP = mod.getWeightedGradePoint(stu);
            total_WGP += temp_WGP;
        }
        return total_WGP;
    }

    public double getTotalGPA(Student stu){
        return getTotalWeightedGradePoints(stu)/getTotalCreditUnits(stu);
    }
    
}
