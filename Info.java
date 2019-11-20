/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file is liscensed under the BSD-3-Clause
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 *****************************************************************************/

class Info extends HttpRequest implements Runnable {
    private String firstName;
    private String lastName;
    private String preferredName;
    private String email;


   Info(String urlIn) {
        super(urlIn);

        firstName = "";
        lastName = "";
        preferredName = "";
        email = "";

  
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("preferredName")) {
                    preferredName = subString[3];
                        }
                if (subString[1].equals("email")) {
                    email = subString[3];
                        }
                    } 
                    
                }
            }
    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
         return email.matches(regex);
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
            return; 
        }

        if (firstName.length() < 2 || firstName.length() > 16 ){
            System.out.println("    **Failed**: First Name (\"firstName\") has to be between 2 to 16\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } else if (preferredName.length() != 0 && (preferredName.length() < 2 || preferredName.length() > 16 )) {   
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } 
        else if (! isValid(email) ) {   
            System.out.println("Validation falid: Make sure email is in proper format");
            System.out.println(this);          
        } 
        else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
    }

    public String toString() {
  
        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "PreferredName: " + preferredName+ "\n";
        returnString = returnString + "email; " + email+ "\n";
        returnString = returnString + super.toString();

        return returnString;
    }

    public void run() {
        Load();
        Validate();

    }
    
}