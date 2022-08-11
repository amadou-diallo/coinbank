
package business;

/**
 *
 * @author amadou
 */
public class Bank {
    
    private double quart = 0.0, dime = 0.0 , nickel = 0.0, pennie = 0.0;
    private String errmsg = "";
    private double dollars;
    private double sub,add;
   
    
    
    public Bank(double qt, double dm, double nk, double pn ) {
        this.quart = qt;
        this.dime = dm;
        this.nickel= nk;
        this.pennie = pn;
        this.dollars = 0.0;
        this.sub = 0.0;
        this.add = 0.0;
       
       
        if (isValid()) {
            CalcTotal();
            
            
            
        }
        
        
    }//end of Constructor
    
    private boolean isValid() {
        this.errmsg = "";
    if (this.quart < 0) {
        this.errmsg = "Illegal input: Quarters must be positive.";
    }
    if (this.dime < 0) {
        this.errmsg += " Illegal input: Dimes must be positive.";
    }
    if (this.nickel < 0) {
        this.errmsg += " Illegal input: Nickels must be positive.";
    }
    if (this.pennie < 0) {
        this.errmsg += " Illegal input: Pennies must be positive.";
    }
    if (this.errmsg.isEmpty()) {
        return true;
    }
    
    return false;
   
    } // end of Valid
        

    private void CalcTotal() {
        
        double totcents = (this.quart * .25) + (this.dime * .10) + (this.nickel * .05) + (this.pennie * .01);
       
        this.dollars = totcents/ 1.0;
        
     
    }
    
    
    
    public double getSubCoint(double ctype) {
        
       
        while (ctype > 0) {
           ctype = ctype -1;
           
           
           this.sub = ctype;
           
           
           
        
        }
      return this.sub;  
    }
    
    public double getAddCoint(double ctype) {
        double c;
          
       
        while (ctype > 0) {
             ctype ++;
             c = ctype;
        
            
        
        this.add = c;
     
            
        }
        
        
     return this.add;   
    }

    
    public String getErrorMsg() {
        return this.errmsg;
        
    }
    
    public double getResult() {
        return this.dollars;
    }
    
    
    
    
    
    
}
