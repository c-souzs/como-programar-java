package chapterEight;

public class EmployeeE {
    public static int count = 0;
    private final String firstName;
    private final String lastName;
    private final DateE birthDate;
    private final DateE hireDate;

    public EmployeeE(String firstName, String lastName, DateE birthDate, DateE hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;

        count++;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        return  String.format("%s, %s Hired: %s Birthday: %s", lastName, firstName, hireDate, birthDate);
    }
}
