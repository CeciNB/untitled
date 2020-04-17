import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Department {
    int deptno;
    String dname;
    String loc;

    Set<Department> depSet;

    public Department(){
        depSet = new HashSet<>();
    }

    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Set getAllDepartments(){
        try {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            //Opret forbindelse til MySQL database
            Connection forbindelseTilMySQL = connectionManager.getConnection();
            //Opret statement
            Statement mitStatement = forbindelseTilMySQL.createStatement();
            //Opret Queury
            String getAllDeps = "select * from dept";
            //Eksikver q
            ResultSet allDeps = mitStatement.executeQuery(getAllDeps);
            ResultSetMetaData allDepsMetaData = allDeps.getMetaData();
            int columnCount = allDepsMetaData.getColumnCount();
            Department dep = null;
            while (allDeps.next()) {
                dep = new Department(allDeps.getInt(1),allDeps.getString(2),allDeps.getString(3));
                depSet.add(dep);
            }
        } catch (Exception e){

        }
        return depSet;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", depSet=" + depSet +
                '}' + "\n";
    }
}
