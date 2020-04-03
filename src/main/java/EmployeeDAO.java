import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO { //DAO Data Access Object
    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<EmployeeDTO> getAllEmployees(){
        EmployeeDTO employeeDTO = null;
        ArrayList<EmployeeDTO> empList = new ArrayList<>();
        try{
            PreparedStatement getAllStatement = connection.prepareStatement("select * from emp");
            ResultSet allEmployees = getAllStatement.executeQuery();
            ResultSetMetaData allEmployeesMetaData = allEmployees.getMetaData();
            int columnCount = allEmployeesMetaData.getColumnCount();
            while (allEmployees.next()){
                empList.add(new EmployeeDTO(
                        allEmployees.getInt("empno"),
                        allEmployees.getString("ename"),
                        allEmployees.getString("job"),
                        allEmployees.getInt("mgr"),
                        allEmployees.getDate("hiredate"),
                        allEmployees.getInt("sal"),
                        allEmployees.getInt("comm"),
                        allEmployees.getInt("deptno")
                        ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return empList;
    }

    public EmployeeDTO getSingleEmpById(int id){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            PreparedStatement getSingleStatement = connection.prepareStatement("select * from emp where empno = ?");
            getSingleStatement.setInt(1, id);
            ResultSet singleEmployee = getSingleStatement.executeQuery();
            while (singleEmployee.next()){
                employeeDTO.setEmpno(singleEmployee.getInt("empno"));
                employeeDTO.setEname(singleEmployee.getString("ename"));
                employeeDTO.setJob(singleEmployee.getString("job"));
                employeeDTO.setMgr(singleEmployee.getInt("mgr"));
                employeeDTO.setHiredate(singleEmployee.getDate("hiredate"));
                employeeDTO.setSal(singleEmployee.getInt("sal"));
                employeeDTO.setComm(singleEmployee.getInt("comm"));
                employeeDTO.setDeptno(singleEmployee.getInt("deptno"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return employeeDTO;
    }

    public void addEmployee (EmployeeDTO emp){
        try{
            PreparedStatement insertEmp = connection.prepareStatement("INSERT INTO emp (empNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,deptno) VALUES (?,?,?,?,?,?,?,?);");
            insertEmp.setInt(1,emp.getEmpno());
            insertEmp.setString(2,emp.getEname());
            insertEmp.setString(3,emp.getJob());
            insertEmp.setInt(4,emp.getMgr());
            insertEmp.setDate(5,emp.getHiredate());
            insertEmp.setInt(6,emp.getSal());
            insertEmp.setInt(7,emp.getComm());
            insertEmp.setInt(8,emp.getDeptno());

            int rowsInserted = insertEmp.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void editEmployee (int id, EmployeeDTO emp){
        try{
            PreparedStatement updateEmp = connection.prepareStatement("UPDATE emp SET ENAME =?,JOB=?,MGR=?,HIREDATE=?,SAL=?,COMM=?,deptno=? WHERE empNO = ?;");
            //insertEmp.setInt(1,emp.getEmpno());
            updateEmp.setString(1,emp.getEname());
            updateEmp.setString(2,emp.getJob());
            updateEmp.setInt(3,emp.getMgr());
            updateEmp.setDate(4,emp.getHiredate());
            updateEmp.setInt(5,emp.getSal());
            updateEmp.setInt(6,emp.getComm());
            updateEmp.setInt(7,emp.getDeptno());
            updateEmp.setInt(8,id);

            int rowsInserted = updateEmp.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A existing user was updated successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteEmployee (int id){
        try{
            PreparedStatement deleteEmp = connection.prepareStatement("DELETE FROM emp WHERE empNO = ?;");
            deleteEmp.setInt(1,id);

            int rowsInserted = deleteEmp.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
