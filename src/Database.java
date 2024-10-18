// 2a APC ITL12 - Eibiswald
// Tristan Birnstingl

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    private final String DatabaseName;
    private final String DatabaseType;
    private final String DatabaseHost;
    private final String DatabasePort;
    private final String DatabaseUser;
    private final String DatabasePassword;

    public Database() {
        this.DatabaseName = "itl12_dbtesting";
        this.DatabaseType = "mysql";
        this.DatabaseHost = "localhost";
        this.DatabasePort = "3306";
        this.DatabaseUser = "root";
        this.DatabasePassword = "";
    }

    // Connection to Database
    protected Connection ConnectToDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:" + DatabaseType + "://" + DatabaseHost + ":" + DatabasePort + "/" + DatabaseName;
            connection = DriverManager.getConnection(url, DatabaseUser, DatabasePassword);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage() + "\n\n");
            return null;
        }
    }

    // Print all Employees
    protected void PrintEmployees() throws SQLException {
        Connection connection = ConnectToDatabase();

        // Get all entries from Database and Table "emp"
        try {
            String Query = "SELECT * FROM emp";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);

            // Gett Employee Data
            while (resultSet.next()) {
                int empNo = resultSet.getInt("EMPNO");
                String eName = resultSet.getString("ENAME");
                String job = resultSet.getString("JOB");
                int mgr = resultSet.getInt("MGR");
                String hireDate = resultSet.getString("HIREDATE");
                double sal = resultSet.getDouble("SAL");
                double comm = resultSet.getDouble("COMM");
                int deptNo = resultSet.getInt("DEPTNO");

                System.out.println("\n" + "EMPNO: " + empNo + ", ENAME: " + eName + ", JOB: " + job + ", MGR: " + mgr + ", HIREDATE: " + hireDate + ", SAL: " + sal + ", COMM: " + comm + ", DEPTNO: " + deptNo);
            }

        } finally {
            System.out.println("\n" + "All employees printed!" + "\n\n");
        }
    }


    // Print a Employee with specific Employee Number
    protected void PrintSpecificEmployye(String employeeNumber) throws SQLException {
        Connection connection = ConnectToDatabase();
        try {
            String Query = "SELECT * FROM emp WHERE EMPNO = " + employeeNumber;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);

            if (resultSet.next()) {
                // Get Employee with specific EMPNO
                int empNo = resultSet.getInt("EMPNO");
                String eName = resultSet.getString("ENAME");
                String job = resultSet.getString("JOB");
                int mgr = resultSet.getInt("MGR");
                String hireDate = resultSet.getString("HIREDATE");
                double sal = resultSet.getDouble("SAL");
                double comm = resultSet.getDouble("COMM");
                int deptNo = resultSet.getInt("DEPTNO");

                System.out.println("\n" + "EMPNO: " + empNo + ", ENAME: " + eName + ", JOB: " + job + ", MGR: " + mgr + ", HIREDATE: " + hireDate + ", SAL: " + sal + ", COMM: " + comm + ", DEPTNO: " + deptNo);
            } else {
                System.out.println("\n" + "Employee with EMPNO " + employeeNumber + " not found!" + "\n\n");
            }
        } finally {
            System.out.println("\n" + "Specific employee printed!" + "\n\n");
        }
    }

    // Show All Tables in Database
    protected void ShowAllTables() throws SQLException {
        Connection connection = ConnectToDatabase();
        try {
            String Query = "SHOW TABLES";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);

            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_" + DatabaseName);
                System.out.println("Table: " + tableName);
            }
        } finally {
            System.out.println("\n" + "All tables printed!" + "\n\n");
        }
    }
}
