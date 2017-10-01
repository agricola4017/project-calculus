/** 
 Function Class
 */

public class Function
{
    private String function; //f(x) = ?
    private String derivative; //f'(x) = ?
    
 //----------------------Constructors------------------------------
 
    /** 
     * Default Constructor
     */
    
    public Function()
    {
        function = ""; 
            //empty default function
    }
    
    /** 
     * Normal Constructor
     */
    
    public Function(String f)
    {
        function = f;
    }
 //----------------------Mutators------------------------------
    
    /** 
     * setFunction: Changes the function 
     * setDerivative: Changes the derivative
     */
    
    public void setFunction(String f)
    {
        function = f;
    }
    
    public void setDerivative(String d)
    {
        derivative = d;
    }
 
 //----------------------Accessors------------------------------
 
    /**
     * getFunction: returns the function 
     * getDerivative: returns the derivative
     */
    
    public String getFunction()
    {
        return function;
    }
    
    public String getDerivative()
    {
        derivative = testDerivative.derive(function);
        return derivative;
    }
    
  //---------------------Override-----------------------------
  
  /**
   * Overrides the normal String method for toString
   */
  
  public String toString()
  {
      String output;
      output = "The original function was f(x) = " + function;
      output += "\nThe derivative of this function is f'(x) = " + derivative;
      return output;
  }
}