import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

public class Employee /*implements Comparator */{
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
    private Map<Integer, Employee> empMap;

    public Employee(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(){
        empMap = new HashMap();
    }

    public int getEmpno() {
        return empno;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public Map getAllEmployees(){
        try {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            //Opret forbindelse til MySQL database
            Connection forbindelseTilMySQL = connectionManager.getConnection();
            //Opret statement
            Statement mitStatement = forbindelseTilMySQL.createStatement();
            //Opret Queury
            String getAllEmps = "select * from emp";
            //Eksikver q
            ResultSet allEmps = mitStatement.executeQuery(getAllEmps);
            ResultSetMetaData allEmpsMetaData = allEmps.getMetaData();
            int columnCount = allEmpsMetaData.getColumnCount();
            Employee emp = null;
            while (allEmps.next()) {
                emp = new Employee(allEmps.getInt(1),allEmps.getString(2),allEmps.getString(3), allEmps.getInt(4), allEmps.getDate(5),allEmps.getInt(6),allEmps.getInt(7),allEmps.getInt(8));
                empMap.put(emp.getEmpno(), emp);
            }
        } catch (Exception e){

        }
        return empMap;
    }

    @Override
    public String toString() {
        return "ID{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}' + "\n";
    }
    /*
    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) empMap.get(keyA).getHiredate();
        Comparable valueB = (Comparable) empMap.get(keyB).getHiredate();
        return valueB.compareTo(valueA);


    }
    public Map sortByValue(Map unsortedMap) {
        Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    public void printSorted(){
        Map sortedMap = sortByValue(empMap);
        System.out.println(sortedMap);
    }*/
}
