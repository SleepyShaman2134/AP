package lab9;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import lab9.CitiesDAO;
import lab9.EntityManagerFactory.Singleton;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException, SQLException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1000);
        Map<String, Integer> cliques=new HashMap<>();
        Integer[] cliquesComplete=new Integer[3000];
        int count=0;
        //String queryContinents = "select * from cities";
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        Database.initializeDatabase();
        Connection connection = Database.createConnection();
       //
       // Connection connection = lab9.Database.createConnection();
        for (int i = 1; i <= 1000; i++)
        {
           int twin=(int)(Math.random()*3000);
           CitiesDAO task = new CitiesDAO(i, "Task"+ i, twin, connection);
            executor.execute(task);
       }
        executor.shutdown();
        for(int i=0; i<3000; i++)
        {
            cliquesComplete[i]=0;
        }
        sleep(1000);
        System.out.println("ALOOO");
        try{
            statement1 = connection.createStatement();
            String queryContinents = "select * from citytwins";
            resultSet1 = statement1.executeQuery(queryContinents);
            while(resultSet1.next())
            {
                cliquesComplete[resultSet1.getInt(7)]++;
                cliques.put(resultSet1.getString(2), resultSet1.getInt(7));
            }
            Singleton single= new Singleton();
            for(int i=0; i<3000; i++)
            {
                if(cliquesComplete[i]>=3)
                {
                    System.out.print("Orasele ");
                    for(Map.Entry<String, Integer> entry: cliques.entrySet())
                        if(entry.getValue()==i)
                        {
                            System.out.print(" " + entry.getKey()+" ");
                        }
                    System.out.println(" sunt foarte unite in relatia " + i +"!");
                }
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
