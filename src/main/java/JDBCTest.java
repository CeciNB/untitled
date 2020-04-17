import java.sql.*;
import java.util.Map;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {
       //opgave1();
       //opgave2();
       opgave3();

    }
    public static void opgave3() {
        DatabaseConnectionManager dbconnectionManager = DatabaseConnectionManager.getInstance();
        Connection conn = dbconnectionManager.getConnection();
        Connection conn2 = dbconnectionManager.getConnection();
        System.out.println("connection 1: " +conn + " connection 2: " + conn2);
        if(conn == conn2){
            System.out.println("De er ens");
        }
    }

    public static void opgave2() {
        DatabaseConnectionManager dbconnectionManager = DatabaseConnectionManager.getInstance();
        Connection conn = dbconnectionManager.getConnection();
        Employee employee = new Employee();
        System.out.println("Opgave 4 del 1" + "\n" + employee.getAllEmployees());
        Department department = new Department();
        System.out.println("Opgave 4 del 2" + "\n" + department.getAllDepartments());
        EmployeeDAO employeeDAO = new EmployeeDAO(conn);
        DepartmentDAO departmentDAO = new DepartmentDAO(conn);
        System.out.println("Opgave 2 JDBC-2 del 1" + employeeDAO.getAllEmployees());
        System.out.println("Opgave 2 JDBC-2 del 2" + employeeDAO.getSingleEmpById(7934));
        EmployeeDTO employeeDTO = new EmployeeDTO(99999,"ENAME","MANAGER",0, new Date(1981-06-10),3000,0,10);
        //employeeDAO.addEmployee(employeeDTO);
        System.out.println("Opgave 3 JDBC-2 emNO 9999" + employeeDAO.getAllEmployees());
        employeeDTO.setEname("KARSTEN");
        employeeDAO.editEmployee(employeeDTO.getEmpno(),employeeDTO);
        System.out.println("Opgave 4 JDBC-2 rettet navn til PELLE" + employeeDAO.getAllEmployees());
        employeeDAO.deleteEmployee(employeeDTO.getEmpno());
        System.out.println("Opgave 4 JDBC-2 slet KARSTEN" + employeeDAO.getAllEmployees());

    }

    public static void opgave1() throws SQLException {
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        Connection forbindelseTilMySQL = connectionManager.getConnection();
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
    }

}
