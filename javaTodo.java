import java.sql.*;
import java.util.*;


public class javatodo {
    static String changeDB = "0";
    // Doesn't work directly check README.md pre-requests to get it started.
    static String DBurl = "jdbc:derby:D:\\Work\\Java\\Projects\\JavaTodo\\Database";
    static String DBclose = "jdbc:derby:D:\\Work\\Java\\Projects\\JavaTodo\\Database;shutdown=true";
    static String callfrom = "0";
    static void todo_screen() {
        System.out.println("--------------------");
        System.out.println("       JavaTodo     ");
        System.out.println("--------------------");
        System.out.println(" ");
        System.out.println("Tasks");
        System.out.println(" ");
        try {
            Connection connect = DriverManager.getConnection(DBurl);
            Statement stmnt = connect.createStatement();
            String query = "0";
            if (changeDB == "db1" | changeDB == "0") {
                query = "select * from todo";
            }
            if (changeDB == "db2") {
                query = "select * from todo2";
            }
            ResultSet rs = stmnt.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("task"));
            }
            DriverManager.getConnection(DBclose);
        } catch (Exception e) {
        }
        System.out.println(" ");
        System.out.println("a: Task - Add, r: No. - Remove, exit - Exit");
        System.out.println(" ");
        System.out.print("> ");
        Scanner input = new Scanner(System.in);
        String enter = input.nextLine();
        if (enter.startsWith("a:")) {
            String task = enter.replaceFirst("a:", "");
            add_task(task);
        }
        if (enter.startsWith("r:")) {
            String task = enter.replaceFirst("r:", "");
            String reTask = task.replaceAll("\\s", "");
            int id = Integer.parseInt(reTask);
            try {
                Connection connect = DriverManager.getConnection(DBurl);
                Statement stmnt = connect.createStatement();
                if (changeDB == "db1" | changeDB == "0") {
                    String query = "delete from todo where id = " + id ;
                    String query0 = "delete from todo2";
                    String query1 = "alter table todo2 alter column id restart with 1";
                    String query2 = "insert into todo2(task) select (task) from todo";
                    String query3 = "delete from todo";
                    stmnt.executeUpdate(query);
                    stmnt.execute(query0);
                    stmnt.execute(query1);
                    stmnt.execute(query2);
                    stmnt.execute(query3);
                    changeDB = "db2";
                    DriverManager.getConnection(DBclose);
                }
                if (changeDB == "db2") {
                    String query = "delete from todo2 where id = " + id ;
                    String query0 = "delete from todo";
                    String query1 = "alter table todo alter column id restart with 1";
                    String query2 = "insert into todo(task) select (task) from todo2";
                    String query3 = "delete from todo2";
                    stmnt.executeUpdate(query);
                    stmnt.execute(query0);
                    stmnt.execute(query1);
                    stmnt.execute(query2);
                    stmnt.execute(query3);
                    changeDB = "db1";
                    DriverManager.getConnection(DBclose);
                }
            } catch (Exception e) {
            }
            todo_screen();
        }
        input.close();
    }

    static void add_task(String task) {
        try {
            Connection connect = DriverManager.getConnection(DBurl);
            Statement stmnt = connect.createStatement();
            String query = "0";
            if (changeDB == "db1" | changeDB == "0") {
                query = "insert into todo (task) values ('" + task + "')";
            }
            if (changeDB == "db2") {
                query = "insert into todo2 (task) values ('" + task + "')";
            }
            stmnt.execute(query);
            // System.out.println("Task saved in database");
            DriverManager.getConnection(DBclose);
        } catch (Exception e) {
        }
        if (callfrom == "gui") {
            System.out.println("Got from gui");
        } else {
            todo_screen();
        }
    }

    public static void main(String[] args) {
        todo_screen();
    }
}
