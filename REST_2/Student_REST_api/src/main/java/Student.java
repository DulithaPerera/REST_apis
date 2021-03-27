public class Student {
    private int indexNo;
    private String password;
    private String name, grade;

    public Student(int indexNo, String password, String name, String grade){
        this.indexNo = indexNo;
        this.password = password;
        this.name = name;
        this.grade = grade;
    }

    public Student(){}

    public void setName(String name){
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getIndexNo(){
        return indexNo;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getGrade(){
        return grade;
    }
}
