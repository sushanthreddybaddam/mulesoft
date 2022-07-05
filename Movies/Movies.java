package myapplication;
import java.sql.Connection;  
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.SQLException;  
import java.sql.Statement;
   
public class Myclass1 {  
      
    public static void connect() {  
        Connection conn = null;  
        try {  
            
            String url = "jdbc:sqlite:D:\\SQLite\\sqlite-tools-win32-x86-3390000/mydb.db";  
            
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        } 
        
    }  
    public static void createNewDatabase() {  
   
        String url = "jdbc:sqlite:D:\\SQLite\\sqlite-tools-win32-x86-3390000/mydb.db";  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public static void createNewTable(String name) {  
        // SQLite connection string  
        String url = "jdbc:sqlite:D:\\SQLite\\sqlite-tools-win32-x86-3390000/mydb.db";  
          
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS"+"name"+" (\n"  
                + " relyear integer,\n"  
                + " moviename text NOT NULL,\n"  
                + " hero text\n"  
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public static void insert(String tbname,int relyear,String movname, String hname) {  
        String sql = "INSERT INTO "+tbname+"(relyear,moviename, hero) VALUES("+relyear+","+movname+","+hname+")";  
   
        try{  
            Connection conn = Myclass1.connect();  
            PreparedStatement pstmt;  
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, relyear);  
            pstmt.setString(2, movname);
            pstmt.setString(3, hname);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
     
    public static void selectAll(String mname){  
        String sql = "SELECT * FROM "+mname+";";  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
             
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
     
    public static  void main(String[] args) {  
        connect(); 
        createNewDatabase();
        createNewTable("Movies");
        insert("Movies",2022,"sarkaruvaripata","maheshbabu");
        selectAll("Movies");
        
        
    }  
}  