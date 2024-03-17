import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static String query;

    public static void main(String[] args) {
        //set up communication
        String url = "jdbc:postgresql://localhost:5432/A3-part3";
        String user = "postgres";
        String password = "karate100";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if(connection != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Did Not Connect");
            }

            while(true) {
                //create query
                Statement statement = connection.createStatement();
                //take input from user
                query = generateQuery();
                if (query.toLowerCase() == "quit") break;
                else if (query.toLowerCase() == "continue") continue;

                //if the user is trying to retrieve something, print all the data the query gets
                if(query.toLowerCase().contains("select ")) { // see that if you put select in as a name with a space after this could be an issue, but i am going to assume no one will
                    statement.executeQuery(query);
                    System.out.println("Results:");
                    ResultSet resultSet = statement.getResultSet();
                    while(resultSet.next()) {
                        System.out.print(resultSet.getString("student_id") + '\t');
                        System.out.print(resultSet.getString("first_name") + '\t');
                        System.out.print(resultSet.getString("last_name") + '\t');
                        System.out.print(resultSet.getString("email") + '\t');
                        System.out.println(resultSet.getString("enrollment_date") + '\t');
                    }
                }
                else {
                    statement.executeUpdate(query);
                }

            }



        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static public String generateQuery() {
        //ask the user what they would like to do
        Scanner read = new Scanner(System.in);
        System.out.println("\n1 : Create\n2 : Read\n3 : Update\n4 : Delete\n5 : Custom Query\n6 : Quit");
        String input = read.nextLine().toLowerCase();

        if("1 create".contains(input)) {
            System.out.println("Adding Student...\nProvide first_name");
            String first = read.nextLine();
            System.out.println("Provide last_name");
            String last = read.nextLine();
            System.out.println("Provide email, ");
            String email = read.nextLine();
            System.out.println("Provide  enrollment_dat (In the form: yyyy-mm-dd)");
            String date = read.nextLine();

            return addStudent(first, last, email, date);

        } else if("2 read".contains(input)) {
            return getAllStudents();

        } else if("3 update".contains(input)) {
            System.out.println("Updating Student Email...\nProvide student_id");
            String id = read.nextLine();
            System.out.println("Provide a new email");
            String email = read.nextLine();

            return updateStudentEmail(Integer.parseInt(id), email);

        } else if("4 delete".contains(input)) {
            System.out.println("Deleting Student...\nProvide student_id");
            String id = read.nextLine();

            return deleteStudent(Integer.parseInt(id));

        } else if("5 custom query".contains(input)) {
            System.out.println("Type custom SQL query");
            String q = read.nextLine();

            return q;

        } else if("6 quit".contains(input)) {

            return "quit";

        } // else
        return "continue";

    }

    static private String getAllStudents(){
        return "SELECT * FROM students";
    }
    static private String addStudent(String first_name,String last_name,String email,String enrollment_date) {
        return "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" + first_name
                + "', '" + last_name
                + "', '" + email
                + "', '" + enrollment_date + "');";
    }
    static private String updateStudentEmail(int student_id,String new_email) {
        return "UPDATE students SET email = '" + new_email + "' WHERE student_id = " + student_id;
    }

    static private String deleteStudent(int student_id) {
        return "Delete from students where student_id = " + student_id;
    }


}
