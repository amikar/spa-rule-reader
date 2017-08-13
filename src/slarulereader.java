import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.util.*;



public class slarulereader {
    public static void main(String[] args) throws Exception, JSONException, IOException{
    	
    	
    	String jsonitter;
    	StringBuilder app = new StringBuilder();
    	
    	URL appsname = new URL("http://13.56.90.73:8161/monitoring-services/resources/monitoring/getslarules");
    	URLConnection hj = appsname.openConnection();
        BufferedReader ne = new BufferedReader(new InputStreamReader(hj.getInputStream()));
        while ((jsonitter = ne.readLine()) != null) 
        	app.append(jsonitter); 
        	
     	JSONObject jsonrules = new JSONObject(app.toString());
     	
    
    	
   		
    	JSONArray stagesjson = jsonrules.getJSONArray("slaRules");
    	
    	System.out.println(stagesjson);

    	
    	int numberofrules = stagesjson.length();
    	String stagetype = "";
    	String servicetype = "";
    	String desc = "";
    	for (int i = 0; i < numberofrules; ++i) {
   			
     		JSONObject stagenamejson = stagesjson.getJSONObject(i);
    
     		stagetype = stagenamejson.getString("metric");
     		servicetype = stagenamejson.getString("service");
     		if (stagetype.equals("googleglass.temperature") && servicetype.equals("GOOGLE_GLASS"))
     		{
     			desc = stagenamejson.getString("description");
     		}
    	}
    	
 		System.out.println(desc);
 		
 		String[] sla = desc.split("\\{OR\\}");
 		
 		System.out.println("First sla condition : " + sla[0]);
 		System.out.println("Second sla condition : " + sla[1]);


    }
    
		
		
}
		
