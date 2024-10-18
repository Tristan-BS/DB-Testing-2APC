import java.sql.SQLException;

public class Main {
    protected static String EmployeeNumber;

    public static void main(String[] args) throws SQLException {
        EmployeeNumber = "7844";
        Database db = new Database();


        db.ShowAllTables();
        db.PrintEmployees();
        db.PrintSpecificEmployye(EmployeeNumber);

    }
}