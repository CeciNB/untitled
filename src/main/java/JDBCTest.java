import java.sql.*;
import java.util.Map;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();
        //Opret forbindelse til MySQL database
        Connection forbindelseTilMySQL = connectionManager.getConnection();
        //Opret statement
        Statement mitStatement = forbindelseTilMySQL.createStatement();
        //Opret Queury
        String getAllDepartments = "select * from dept";
        //Eksikver q
        ResultSet alleDepartments = mitStatement.executeQuery(getAllDepartments);
        ResultSetMetaData alleDepartmentsMetaData = alleDepartments.getMetaData();
        int columnCount = alleDepartmentsMetaData.getColumnCount();
        //int columnTypes = alleDepartmentsMetaData.getColumnType();

        for(int i = 1; i <= columnCount; i++) {
            System.out.print(alleDepartmentsMetaData.getColumnName(i) + " ");
        }
        System.out.println(" ");
        for(int i = 1; i <= columnCount; i++) {
            System.out.print(alleDepartmentsMetaData.getColumnTypeName(i) + " ");
        }
        System.out.println(" ");
        while(alleDepartments.next()) {
            System.out.print(alleDepartments.getInt("deptno")+ " ");
            System.out.print(alleDepartments.getString("dname")+ " ");
            System.out.println(alleDepartments.getString("loc"));
        }
        System.out.println();

        while(alleDepartments.next()) {
            System.out.print(alleDepartments.getInt("deptno") + "\t");

        }
        Employee employee = new Employee();

        System.out.println("Opgave 4 del 1" + "\n" + employee.getAllEmployees());

        Department department = new Department();

        System.out.println("Opgave 4 del 2" + "\n" + department.getAllDepartments());

    }
}
