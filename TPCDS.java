import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class TPCDS
{
	LinkedList<String> queries = new LinkedList<String>();
	Connection conn;

	public TPCDS()
	{
		try
		{
			// Getting database properties from db.properties
			Properties prop = new Properties();
			InputStream db = new FileInputStream("db.properties");
			prop.load(db);
			String db_type     = prop.getProperty("db_type");
			String db_hostname = prop.getProperty("db_hostname");
			String db_username = prop.getProperty("db_username");
			String db_password = prop.getProperty("db_password");
			String db_database = prop.getProperty("db_database");

			// Load the MySQL JDBC driver
			if (db_type.equals("mysql"))
			{
				Class.forName("com.mysql.jdbc.Driver");
				String jdbc_url = "jdbc:mysql://" + db_hostname + "/" + db_database + "?user=" + db_username + "&password=" + db_password;
				conn = DriverManager.getConnection(jdbc_url);
			}
			else if (db_type.equals("postgre"))
			{
				Class.forName("org.postgresql.Driver");
				String jdbc_url = "jdbc:postgresql://hostname/database?user=username&password=password&ssl=true"";
				conn = DriverManager.getConnection(jdbc_url);
			}
			else if (db_type.equals("redshift))
			{
				Class.forName("org.postgresql.Driver");
				String jdbc_url = "jdbc:postgresql://hostname/database?user=username&password=password&ssl=true"";
				conn = DriverManager.getConnection(jdbc_url);
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadQueries()
	{
		for (int i=1; i<=99; i++)
		{
			try
			{
				String filename = String.format("queries/%02d.sql", i);
				String sql = new String(Files.readAllBytes(Paths.get(filename)));
				queries.add(sql);
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void runQuery(int n)
	{
		try
		{
			String sql = queries.get(n-1);
			System.out.println(sql);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		TPCDS tpcds = new TPCDS();
		tpcds.loadQueries();
		tpcds.runQuery(Integer.parseInt(args[0]));
	}
}
