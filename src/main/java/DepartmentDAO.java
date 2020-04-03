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

}
