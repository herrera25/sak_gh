
import java.util.*; 
import java.io.*; 
import java.net.*;


//  ***************************************
//  THIS IS THE SAME AS HTTPREQUESTINDEX

class HttpRequestURLs extends HttpRequest {
    String requestURL, splitURL;
    ArrayList<String> urlContent, splitContent; 

    HttpRequestURLs() {
        super();
        requestURL = ""; 
        urlContent = new ArrayList<String>(); 
        splitContent = new ArrayList<String>(); 
    }
    HttpRequestURLs(String urlIn) {
        requestURL = urlIn; 
        urlContent = new ArrayList<String>();
        splitContent = new ArrayList<String>(); 
    }
    public Boolean readURL() { return readURL(requestURL); }

    public Boolean readURL(String urlIn) {
        Boolean returnValue = super.readURL(urlIn);  
        requestURL = urlIn;
        try { 
            URL myURL = new URL(requestURL);
            URLConnection myConnection = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
            String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    urlContent.add(inputLine);
                    returnValue = true;
                    String[] splits = inputLine.split("\"");
                    if (splits.length > 1) {
                        if (splits[11].indexOf("http") > -1) {
                            splitContent.add(splits[11]); 
                        System.out.println("java sak -HTTPRequest ..."+ splits[11]); 
                        returnValue = true;
                        }
                    } 
                }
                in.close(); 
            }
            catch (Exception e) {
                returnValue = false;
            }
            for (String spl : splitContent) {
                    HttpRequest request = new HttpRequest(spl);
                  if  (request.readURL()) {
                   System.out.println(request);
                }
                   else { 
                    returnValue = false;
                }
            }

        return returnValue;
    }
    
    public String toString() {
        System.out.println("-----------------------------------------------------------------");
        String returnValue = "Index URL: "+requestURL + "\n" 
        + "FULLINDEXCONTENT: " + "\n"; 
        
        for (String s : urlContent) {
            returnValue = returnValue + s + "\n"; 
        }
        System.out.println("-----------------------------------------------------------------");  
        return returnValue;
        
    } 
}
