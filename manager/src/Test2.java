import java.util.Date;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 
 */

/**
 * @author 华锋
 *
 */
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
	  
            String[] serverlist     = { "localhost:11211" };
//            Integer[] weights       = { new Integer(5), new Integer(2) };   
            int initialConnections  = 10;
            int minSpareConnections = 5;
            int maxSpareConnections = 50;   
            long maxIdleTime        = 1000 * 60 * 30;       // 30 minutes
            long maxBusyTime        = 1000 * 60 * 5;        // 5 minutes
            long maintThreadSleep   = 1000 * 5;                     // 5 seconds
            int     socketTimeOut       = 1000 * 3;                 // 3 seconds to block on reads
            int     socketConnectTO     = 1000 * 3;                 // 3 seconds to block on initial connections.  If 0, then will use blocking connect (default)
            boolean failover        = false;                        // turn off auto-failover in event of server down       
            boolean nagleAlg        = false;                        // turn off Nagle's algorithm on all sockets in pool    
            boolean aliveCheck      = false;                        // disable health check of socket on checkout

            SockIOPool pool = SockIOPool.getInstance();
            pool.setServers( serverlist );
//            pool.setWeights( weights );     
            pool.setInitConn( initialConnections );
            pool.setMinConn( minSpareConnections );
            pool.setMaxConn( maxSpareConnections );
            pool.setMaxIdle( maxIdleTime );
            pool.setMaxBusyTime( maxBusyTime );
            pool.setMaintSleep( maintThreadSleep );
            pool.setSocketTO( socketTimeOut );
            pool.setNagle( nagleAlg );      
            pool.setHashingAlg( SockIOPool.NEW_COMPAT_HASH );
            pool.setAliveCheck( true );
            pool.initialize();      
   
            String key="123456";
            SockIOPool.SockIO sock = SockIOPool.getInstance().getSock( key );
            
            MemCachedClient  mc=new MemCachedClient ();
            
            Map map=mc.stats();
            
            
            mc.set("bbbbbbbbb", "33333333333333333333333",new Date(5000));
           
         System.out.println(mc.get("bbbbbbbbb"));
         
         Thread.sleep(6000);
         System.out.println(mc.get("bbbbbbbbb"));
         System.out.println(mc.keyExists("bbbbbbbbb"));
            System.out.println(map);
            
//            try {
//                    sock.write( "version\r\n" ); 
//                    
//                    sock.flush();   
//                    System.out.println( "Version: " + sock.readLine() );    
//            }
//            catch (IOException ioe) { System.out.println( "io exception thrown" ) };        
//
//            sock.close();   



	}

}
