public class People {
    private String name, job, password;
    private int age;

    public People (String name, String job, int age, String password) {
        this.name = name;
        this.job = job;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getAge() {
        return age;
    }

    public  String getPassword() { return password; }
}
