import java.util.*; 
import java.io.*; 
import java.net.*; 
class HttpRequestIndex extends HttpRequest {
    String requestURL, splitURL;
    ArrayList<String> urlContent, splitContent; 

    HttpRequestIndex() {
        super();
        requestURL = ""; 
        urlContent = new ArrayList<String>(); 
        splitContent = new ArrayList<String>(); 
    }
    HttpRequestIndex(String urlIn) {
        requestURL = urlIn; 
        urlContent = new ArrayList<String>();
        splitContent = new ArrayList<String>(); 
    }
    public Boolean readURL() { return readURL(requestURL); }

    public Boolean readURL(String urlIn) {
        Boolean returnValue = super.readURL(urlIn);  
        requestURL = urlIn;
        try { 
            // Get the URL contents from the index link. 
            URL myURL = new URL(requestURL);
            URLConnection myConnection = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
            String inputLine;
                // Read through the URL contents and copy them to a readable string.
                while ((inputLine = in.readLine()) != null) {
                    urlContent.add(inputLine);
                    returnValue = true;

                    //Split to get the URLs
                    String[] splits = inputLine.split("\"");
                    if (splits.length > 1) {
                        if (splits[11].indexOf("http") > -1) {
                            splitContent.add(splits[11]); 
                        //Add java sak command to help test individual links.
                        System.out.println("java sak -HTTPRequest "+ splits[11]); 
                        returnValue = true;
                        }
                    } 
                }
                in.close(); 
            }
            catch (Exception e) {
                returnValue = false;
            } 
            //Get the URLs from the readable string and perform an -HTTPRequest on all of them to get the JSON info
            for (String spl : splitContent) {
                HttpRequest request = new HttpRequest(spl); 
                if (request.readURL()) {
                    System.out.println(request);
                }
                    else { 
                    returnValue = false;
                }
            }

        return returnValue;
    }

    //toString override. 
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
