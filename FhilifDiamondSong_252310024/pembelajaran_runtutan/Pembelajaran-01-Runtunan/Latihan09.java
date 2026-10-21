import java.util.Scanner;

public class Latihan09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Students myBio = new Students();

        System.out.print("Masukkan NPM: ");
        Integer npm = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan nama lengkap: ");
        String fullname = scanner.nextLine();

        System.out.print("Masukkan nama kelas: ");
        String className = scanner.nextLine();

        System.out.print("Masukkan semester: ");
        Integer semester = scanner.nextInt();

        System.out.print("Masukkan GPA: ");
        Float gpa = scanner.nextFloat();

        Integer npmMahasiswa = myBio.getNPM(npm);
        String namaMahasiswa = myBio.getfullname(fullname);
        String kelasMahasiswa = myBio.getClassName(className);
        Integer semesterMahasiswa = myBio.getSemester(semester);
        Float gpaMahasiswa = myBio.getGPA(gpa);

        System.out.println("\n=== Data Mahasiswa ===");
        System.out.println("NPM: " + npmMahasiswa);
        System.out.println("Nama lengkap: " + namaMahasiswa);
        System.out.println("Nama kelas: " + kelasMahasiswa);
        System.out.println("Semester: " + semesterMahasiswa);
        System.out.println("GPA: " + gpaMahasiswa);

        scanner.close();
    }
}

class Students {
    private Integer npm;
    private String fullname;
    private String className;
    private Integer semester;
    private Float gpa;

    public Integer getNPM(Integer value) {
        npm = value;
        return npm;
    }

    public String getfullname(String value) {
        fullname = value;
        return fullname;
    }

    public String getClassName(String value) {
        className = value;
        return className;
    }

    public Integer getSemester(Integer value) {
        semester = value;
        return semester;
    }

    public Float getGPA(Float value) {
        gpa = value;
        return gpa;
    }
}
