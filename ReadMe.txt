open pgadmin, make a new database and open the query tool
click open file, and open the data.sql file provided, then exicute it

next open intelliJ IDEA, creat a maven project  and add this to pom: 
<dependencies>

        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
        </dependency>

    </dependencies>
Add the provided Main to java under main
next open the Main file of the source code:
change the url port to match your pgadmin port, and the database name
change the user and password as well
then run

