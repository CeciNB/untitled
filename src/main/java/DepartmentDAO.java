import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO { //DAO Data Access Object
    private Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }

    public DepartmentDTO getSingleDeptById(int id){
        DepartmentDTO objectToReturn = new DepartmentDTO();
        try {
            PreparedStatement getSingleStatement = connection.prepareStatement("select * from dept where deptno = ?");
            getSingleStatement.setInt(1,id);
            ResultSet singleDepartment = getSingleStatement.executeQuery();
            while (singleDepartment.next()){
                objectToReturn.setDeptno(singleDepartment.getInt("deptno"));
                objectToReturn.setDname(singleDepartment.getString("dname"));
                objectToReturn.setLoc(singleDepartment.getString("loc"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return objectToReturn;
    }
    public void addDepartment (DepartmentDTO dep){
        try{
            PreparedStatement insertEmp = connection.prepareStatement("INSERT INTO dept (depNO,DNAME,LOC) VALUES (?,?,?);");
            insertEmp.setInt(1,dep.getDeptno());
            insertEmp.setString(2,dep.getDname());
            insertEmp.setString(3,dep.getLoc());

            int rowsInserted = insertEmp.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new department was inserted successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void editDepartment (int id, DepartmentDTO dep){
        try{
            PreparedStatement updateDept = connection.prepareStatement("UPDATE dept SET DNAME =?, LOC=? WHERE DepNO = ?;");
            updateDept.setString(1,dep.getDname());
            updateDept.setString(2,dep.getLoc());
            updateDept.setInt(3,id);


            int rowsInserted = updateDept.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(rowsInserted + " existing department was updated successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteDepartment (int id){
        try{
            PreparedStatement deleteDep = connection.prepareStatement("DELETE FROM dept WHERE deptno = ?;");
            deleteDep.setInt(1,id);

            int rowsInserted = deleteDep.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A department was deleted successfully!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
