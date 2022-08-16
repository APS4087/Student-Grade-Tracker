import java.util.*;

//blah

//=====enum for module details=====
enum Module_enum{
    BSP("Business Stats with Python","ITSD001"),
    PS("Problem Solving","ITSD002"),
    CN("Communication and Networks","ITSD003"),
    PF("Programming Fundamentals","ITSD004");

    private String mod_description;
    private String mod_code;
    
    private Module_enum(String mod_description, String mod_code){
        this.mod_description= mod_description;
        this.mod_code = mod_code;
    }
    public String getMod_description() {
        return mod_description;
    }
    public String getMod_code() {
        return mod_code;
    }
}

//=====enum for assesement details=====
enum Assessment_enum{
    CA1("Quiz 1","CA1"),
    CA2("Common Test","CA2"),
    CA3("Indivitual Assignment","CA3"),
    CA4("Quiz 2","CA4");

    private String ass_description;
    private String ass_name;

    private Assessment_enum(String ass_description, String ass_name){
        this.ass_description = ass_description;
        this.ass_name = ass_name;
    }
    public String getAss_description() {
        return ass_description;
    }
    public String getAss_name() {
        return ass_name;
    }
}

public class GradeTracker{

    private static ArrayList<Student> stu_list = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    private static char option;
    public static void main(String[] args) throws Exception{      
        
        
        do{
            try{          
            showOption();
            System.out.print("Enter your option number: ");
            option = scan.next().charAt(0);
            scan.nextLine();

            switch(option){
                case '1':
                    //create new student
                        Student student = CreateStudent();
                        if(student ==null){
                            System.out.println("Student already exist");

                        }else{
                            addStudent(student); 
                            System.out.println("Student has been added!");
                        }                                   
                    break;
                case '2':
                    //delete student
                    System.out.print("Enter the id of the student you want to remove: ");
                    String id = scan.nextLine();
                    removeStudent(id);
                    
                    break;
                case '3':
                    //add module
                    System.out.print("Enter the id of student you want to add module: ");
                    String add_id_mod = scan.nextLine();
                    //checking if student is in list
                        
                    if(checkStudent_created(add_id_mod)==null){           
                        System.out.println("No Student found");
                    }
                    else{
                        Student add_stu =checkStudent_created(add_id_mod);    //return the student where found                        
                        System.out.print("Enter the number of modules the student took: ");
                        int num_mod = scan.nextInt();       
                        for(int i =0; i<num_mod;i++){                          
                            Module addModule =addModule(askModuleChoice());
                            if(addModule == null){
                                System.out.println("invalid module");
                                break;
                            }else{ 
                                add_stu.addModule(addModule);
                            }
                        } 
                    }    
                    break;
                case '4':
                    //delete module
                    System.out.print("Enter the id of student you want to delete module: ");
                    String del_id_mod= scan.nextLine();
                    Student del_stu = checkStudent_created(del_id_mod);
                    if(checkStudent_created(del_id_mod)==null){
                        System.out.println("No Student found");
                        
                    }
                    else{
                        ArrayList<Module> mod_list = del_stu.getMod_list();   //getting the module list for the chosen student
                        System.out.println();
                        System.out.println("These are the modules availble for "+del_stu.getName());
                        int counter =0;
                        for (Iterator iter = mod_list.iterator(); iter.hasNext();) {
                            counter +=1;
                            System.out.println(counter+". "+iter.next());
                        }
                        System.out.println();
                        System.out.print("Please enter the name of the module you want to delete as follows(BSP,PS,CN,PF): "); //currently cant find bug for not accepting lower case
                        String del_modName = scan.nextLine();
                        
                        remove_modList(mod_list, del_modName);
                    }
                  
                    break;
                case '5':
                    //add assessment
                    System.out.print("Enter the id of student you want to add assessment: ");
                    String add_id_ass = scan.nextLine();
                    //checking if student is in list
                    Student add_stu_ass =checkStudent_created(add_id_ass);    //return the student where found      
                    if(checkStudent_created(add_id_ass)==null){
                        System.out.println("No Student found");
                    }
                    else{
                        ArrayList<Module> mod_list_ass = add_stu_ass.getMod_list();   //getting the module list for the chosen student to add assessment
                        System.out.println();
                        System.out.println("These are the module availble for "+add_stu_ass.getName());
                        int counter =0;
                        for (Iterator iter = mod_list_ass.iterator(); iter.hasNext();) {
                            counter +=1;
                            System.out.println(counter+". "+iter.next());
                        }
                        System.out.println();
                        System.out.print("Enter the name of module to add assessment as follows(BSP,PS,CN,PF): ");
                        String mod_to_addAssessment = scan.nextLine();

                        if(checkModule(mod_list_ass, mod_to_addAssessment)==null){
                            System.out.println("No module found!");
                        }
                        else{
                            Module mod_addass = checkModule(mod_list_ass, mod_to_addAssessment);           //return mod
                            System.out.print("Enter the number of assignments the student took: ");
                            int num_ass = scan.nextInt();
                            for(int i = 0; i<num_ass; i++){
                                Assessment addassessment = addAssessment(askAssignmentChoice(), mod_addass);
                                if(addassessment ==null){
                                    System.out.println("Invalid asssessment!");
                                    break;
                                }else{  
                                mod_addass.addAssessment(addassessment);  //add assigment into mod class arraylist
                                }          
                            }
                        }
                    }
                    break;
                case '6':
                    //remove assessment
                    System.out.print("Enter the id of student you want to remove assessment: ");
                    String remove_id_ass = scan.nextLine();
                    //checking if student is in list
                    Student remove_stu_ass =checkStudent_created(remove_id_ass);    //return the student where found      
                    if(checkStudent_created(remove_id_ass)==null){
                        System.out.println("No Student found");
                    }
                    else{
                        ArrayList<Module> mod_list_ass = remove_stu_ass.getMod_list();   //getting the module list for the chosen student to remove assessment
                        System.out.println();
                        System.out.println("These are the module availble for "+remove_stu_ass.getName());
                        int counter =0;
                        for (Iterator iter = mod_list_ass.iterator(); iter.hasNext();) {
                            counter +=1;
                            System.out.println(counter+". "+iter.next());
                        }
                        System.out.println();
                        System.out.print("Enter the name of module to remove assessment as follows(BSP,PS,CN,PF): ");
                        String mod_to_removeAssessment = scan.nextLine();

                        if(checkModule(mod_list_ass, mod_to_removeAssessment)==null){
                            System.out.println("No module found!");
                        }
                        else{
                            Module mod_removeAss = checkModule(mod_list_ass, mod_to_removeAssessment);
                            ArrayList<Assessment> ass_modHas = mod_removeAss.getAss_list();
                            System.out.println();
                            System.out.println("These are the assessment availble for "+mod_removeAss.getName());
                            int counting =0;
                            for (Iterator iter = ass_modHas.iterator(); iter.hasNext();) {
                                counting +=1;
                                System.out.println(counting+". "+iter.next());
                            }
                            System.out.println();
                            System.out.print("Enter the name of assessment to remove assessment as follows(CA1,CA2,CA3,CA4): ");
                            String ass_toRemove = scan.nextLine();

                            if(checkAss(ass_modHas,ass_toRemove)==null){
                                System.out.println("No assessment found");

                            }
                            else{
                                remove_asslist(ass_modHas, ass_toRemove);
                                //remove_stu_ass.getMod_list().get(0);                               
                            }
                        }
                    }
                    break;
                case '7':
                    //calculate
                    System.out.print("Enter the id of student you want to do calculations: ");
                    String calculate_stu_id = scan.nextLine();
                    //checking if student is in list
                    Student calculate_stu =checkStudent_created(calculate_stu_id);    //return the student where found      
                    if(checkStudent_created(calculate_stu_id)==null){
                        System.out.println("No Student found");
                    }
                    else{
                        System.out.println("What do you want to calculate?");
                        System.out.println("1. The student's overall GPA");
                        System.out.println("2. The marks the student scored for the module");
                        System.out.println();
                        System.out.println("Enter your option number: ");
                        char cal_choice = scan.next().charAt(0);
                        scan.nextLine();
                        calculate(cal_choice, calculate_stu_id, calculate_stu);
                    }
                    break;
                case '8':
                    //show students
                    showStudents();
                    break;
                case '9':
                    //search student id
                    System.out.print("Enter the name of the student you want the id of: ");
                    String name = scan.nextLine();
                    System.out.println();
                    searchStudentID(name);
                    break;
                case '0':
                    //exit
                    System.out.println("Thanks for using the program!");
                    System.exit(0);               //terminate without error
                    break;
                }
            }catch(NullPointerException e1){
                System.out.println("Oops something went wrong. There seems to be a null value");
                continue;
            }catch(InputMismatchException e2){
                System.out.println("Oops invalid input caught!");
                continue;
            }catch(ClassNotFoundException e3){
                System.out.println("Oops cannot locate class!");
                continue;
            }catch(ArrayIndexOutOfBoundsException e4){
                System.out.println("Oops invalid input to array!");
                continue;
            }catch(ArithmeticException e5){
                System.out.println("Oops calculation error occur!");
                continue;
            }catch (Exception e6){
                System.out.println("Oops something went wrong!!!");
                continue;
            }
       
        }while(option !=0);
    
}

    private static void showOption(){
        
        System.out.println();
        System.out.println("1. Create new Students");
        System.out.println("2. Delete new Students");
        System.out.println("3. Add module to Student");
        System.out.println("4. Remove module from student");
        System.out.println("5. Add assessment to student's module");
        System.out.println("6. Delete assessment from student's module");
        System.out.println("7. Calculate a Student's GPA");
        System.out.println("8. Show student created");
        System.out.println("9. Search student id by Name");
        System.out.println("0. Exit");
        System.out.println();

    }
    private static Student CreateStudent() throws Exception{
        String stu_name;
        String stu_ID;

        System.out.print("Enter the Student Name: ");
        stu_name = scan.nextLine();
        System.out.print("Enter "+stu_name+" ID:");
        stu_ID = scan.nextLine();

        if(checkStudent_created(stu_ID)==null){
            Student stu = new Student(stu_name, stu_ID);
            return stu;
        }
        else{
            return null;
        }
        
    
    }
    private static char askModuleChoice(){
        //asking module depending on the number of modules taken      
        System.out.println();
        System.out.println("These are the Modules available.");
        System.out.println();
        int count =0;
        for (Module_enum mods : Module_enum.values()) {
            count +=1;
            System.out.println(count+". "+mods+" : "+ mods.getMod_description()); 
        }      
        System.out.print("Enter the number of your choice of the module above: ");
            char choice_mod = scan.next().charAt(0);
            scan.nextLine();
        return choice_mod;

    }
    private static char askAssignmentChoice(){
        //asking the assignment
        System.out.println();
        System.out.println("These are the assignments availble");
        System.out.println();
        int count = 0;
        for (Assessment_enum ass : Assessment_enum.values()){
            count+=1;
            System.out.println(count+". "+ass+" : "+ ass.getAss_description()); 
        }
        System.out.print("Enter the number of you choice of assessment above: ");
        char choice_ass = scan.next().charAt(0);
        scan.nextLine();
        return choice_ass;
    }
    
    private static Module addModule(char choice_mod  ) throws Exception{
        System.out.print("Enter the credit unit for this module: ");
        int cd_unit = scan.nextInt();
        if(choice_mod =='1'){
            //bsp
            Module mod = new Module("BSP", Module_enum.BSP.getMod_code(), Module_enum.BSP.getMod_description(), cd_unit);
            return mod;    
        }
        else if(choice_mod=='2'){   
            //PS
            Module mod = new Module("PS", Module_enum.PS.getMod_code(), Module_enum.PS.getMod_description(), cd_unit);
            return mod;
        }
        else if(choice_mod =='3'){
                //CN
            Module mod = new Module("CN", Module_enum.CN.getMod_code(), Module_enum.CN.getMod_description(), cd_unit);
            return mod;
        }
        else if(choice_mod =='4'){
                //pf
            Module mod = new Module("PF", Module_enum.PF.getMod_code(), Module_enum.PF.getMod_description(), cd_unit);
            return mod;
        }
        else{
            System.out.println("No module availble");
            return null;
        }
            
    }
    private static Assessment addAssessment(char choice_ass,Module mod) throws Exception{

        System.out.println();
        System.out.println("Enter the marks achieved for this assessment: ");
        double marks = scan.nextDouble();
        scan.nextLine();
        System.out.println("Enter the total marks given for this assessment: ");
        double totalMarks = scan.nextDouble();
        scan.nextLine();
        System.out.println("Enter the weightage for this assessment: ");
        double weightage = scan.nextDouble();
        scan.nextLine();

        if(choice_ass =='1'){
            //ca1
            Assessment ass = new Assessment(Assessment_enum.CA1.getAss_name(),Assessment_enum.CA1.getAss_description(), mod, marks, totalMarks, weightage);
            return ass;  
        }
        else if(choice_ass=='2'){   
            //ca2
            Assessment ass = new Assessment(Assessment_enum.CA2.getAss_name(),Assessment_enum.CA2.getAss_description(), mod, marks, totalMarks, weightage);
            return ass;
        }
        else if(choice_ass =='3'){
            //ca3
            Assessment ass = new Assessment(Assessment_enum.CA3.getAss_name(),Assessment_enum.CA3.getAss_description(), mod, marks, totalMarks, weightage);
            return ass;
        }
        else if(choice_ass =='4'){
            //ca4
            Assessment ass = new Assessment(Assessment_enum.CA4.getAss_name(),Assessment_enum.CA4.getAss_description(), mod, marks, totalMarks, weightage);
            return ass;
        }
        else{
            System.out.println("No assessment available");
            return null;
        }
    }
    
    private static void addStudent(Student stu){
        stu_list.add(stu);
    }

    private static void showStudents(){
        System.out.println();
        System.out.println("=====Student recorded=====");
        System.out.println();
        for(Student stu : stu_list){
            System.out.println(stu.getName()+" : "+stu.getStudentID());
        }
    }
    private static void removeStudent(String id){
        //create iterator to avoid error because modifying the Collection and then trying to use the same iterator
        if(checkStudent_created(id)==null){
            System.out.println("No student found!");
        }
        else{
            Iterator itr = stu_list.iterator();
            while(itr.hasNext()){
                Student stu = (Student)itr.next();
                if (id.equals(stu.getStudentID())){
                    itr.remove();
                    System.out.println("Student with " +id+" has been removed!");               
                }
            }
        }
    }

    private static void remove_modList(ArrayList<Module> mod_list, String del_modName){
        
        if(checkModule(mod_list, del_modName)==null){
            System.out.println("No module found!");
        }
        else{
            Iterator itr = mod_list.iterator();
            while(itr.hasNext()){
                Module mod = (Module)itr.next();
                if (del_modName.toUpperCase().equals(mod.getName().toUpperCase())){
                    itr.remove();
                    System.out.println("The module "+del_modName+ " has been deleted!");
                    //System.out.println();
                }
                
            }
        }

    }
    private static void remove_asslist(ArrayList<Assessment> ass_list, String del_assName){
        
        if(checkAss(ass_list, del_assName)==null){
            System.out.println("No assessment found!");
        }
        else{
            Iterator itr = ass_list.iterator();
            while(itr.hasNext()){
                Assessment ass = (Assessment)itr.next();
                if (del_assName.toUpperCase().equals(ass.getName().toUpperCase())){
                    itr.remove();
                    System.out.println("The assessment "+del_assName+ " has been deleted!");
                    //System.out.println();
                }
                
            }
        }

    }   
    private static void searchStudentID(String name){
        for (Student stu : stu_list){
            if(stu.getName().toUpperCase().equals(name.toUpperCase())){           //in case user enter a mix of upper and lower
                System.out.println(name +"'s id is "+stu.getStudentID());
            }else{
                System.out.println("Student not found");
            }
        }
    } 
    private static Student checkStudent_created(String id){
        Iterator itr = stu_list.iterator();
        while(itr.hasNext()){
            Student stu = (Student)itr.next();
            if(id.equals(stu.getStudentID())){
                return stu;
            }
        }return null;
    }
    private static Module checkModule(ArrayList<Module> mod_list ,String mod_name){
        Iterator itr = mod_list.iterator();
        while(itr.hasNext()){
            Module mod = (Module)itr.next();
            if(mod_name.equals(mod.getName())){
                return mod;
            }
        }return null;
    }
    private static Assessment checkAss(ArrayList<Assessment> ass_list ,String ass_name){
        Iterator itr = ass_list.iterator();
        while(itr.hasNext()){
            Assessment ass = (Assessment)itr.next();
            if(ass_name.equals(ass.getName())){
                return ass;
            }
        }return null;
    }

    private static void calculate(char cal_choice, String calculate_stu_id , Student calculate_stu){

        switch(cal_choice){
            case '1':
                //overall gpa
                
                double total_gpa =calculate_stu.getTotalGPA(calculate_stu);
                System.out.println("Total GPA for "+calculate_stu.getName()+" is "+total_gpa);
                break;
            case '2':
                //total marks student scored for module
                ArrayList <Module> mod_list = calculate_stu.getMod_list();
                System.out.println();
                System.out.println("These are the module availble for "+calculate_stu.getName());
                int counter =0;
                for (Iterator iter = mod_list.iterator(); iter.hasNext();) {
                    counter +=1;
                    System.out.println(counter+". "+iter.next());
                }
                System.out.println();
                System.out.print("Enter the name of module you want to calculate total marks as follows(BSP,PS,CN,PF): ");
                String modName_to_calculate = scan.nextLine();
                Module mod_to_calculate = checkModule(mod_list, modName_to_calculate);

                double total_ScoredMarks = 0.0;
                ArrayList <Assessment> ass_list = mod_to_calculate.getAss_list();
                Iterator itr1 = ass_list.iterator();

                while(itr1.hasNext()){
                    Assessment ass = (Assessment)itr1.next();
                    double temp_total =ass.getWeightageMarks();
                    total_ScoredMarks +=temp_total;
                }

                String total_grade = mod_to_calculate.getOverallGrade(total_ScoredMarks);
                System.out.println("The total Marks for "+mod_to_calculate.getName()+" is "+total_ScoredMarks);
                System.out.println("The total Grade for "+mod_to_calculate.getName()+" is "+total_grade);
                break;
                
               default:
                System.out.print("Invalid input!");
                break;
        }
    }
}