package indula;

import dulitha.*;

class CallingAClassinAJar {

    public static void main (String args []) {
        JavaJarFile j = new JavaJarFile();
        j.myInfo();
        System.out.println("loading over");
    }
}