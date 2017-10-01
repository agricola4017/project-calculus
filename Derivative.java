/**
 Derivative Class
 * Available functions :
 *  1. Constants
 *      Available subfunctions: decimals, integers, negatives 
 *      exceptions: negatives as primary expression (ex: f = "-1" will not work whereas f = "0 - 1" will )
 *  2. Basic Variables
 *      Available subfunctions: coefficients, powers, decimals, coeff + power combined
 *      exceptions: negative powers
 *  3. Basic Trigonometry 
 *      Available subfunctions: cos(), sin(), tan(), decimals, powers, coefficients, coeff + power combined
 *      exceptions: negative powers, exponential trig functions (ex: sin^2(x) will not work)
 *  4. Basic Logarithms
 *      Available subfunctions: ln(), log(), coefficients (limited -- does not simplify)
 *      !Does not work! : exponents, powers,
 *  5. Operators
 *      Available subfunctions: +, -, * 
 *      note: does not simplify values such as 2 + 0
 *      note: see *(multiplication) below
 *  6. Products
 *      Available subfunctions: unsimplified product rule, should work with all the functions listed above 
 *      exceptions: operators
 *          !Does not work! : combined in an equation, powers, coefficients
 *          example: "{[x + 1][x - 4]}" will work whereas "{[x + 1][x - 4]} + 5" will not
 *      
 *  Rules of Function input 
 *  1. Exponents should be written using the carat ("^") symbol
 *      example: "x^2" 
 *      note exceptions: 1st and zeroth power exponents should be writted as "x" and "1" for clarity
 *  2. Trigonometric and logarithmic functions should use parentheses to highlight the "x" value
 *      examples: "tan(x)" and "2ln(x)"
 *      note special case: non-natural logarithms have a later input for the base, do NOT include the base inside the function input 
 *  3. Fractions, percents and ratios should be converted to decimals prior to function input
 *      examples: "(1/2)" will not work whereas "0.5" will
 *  4. Expressions should be separated with space, operator, space 
 *      examples: "x+x" will not work whereas "x + x" will
 *      examples: "x - x"
 *  5. Negatives should be entered as an operator between two expressions, binary negative operators do not work
 *      examples: "-4" will not work whereas "0 - 4" will 
 *      examples: "1 - 6x" and "6x^2 - 5cos(x)" 
 *      examples: "-sin(x)" will not work
 *  6. Multiplication (product rule) between two variables should be enclosed by sq. brackets[] instead of parentheses() and the entire operation must be enclosed by brackets {} instead of a multiplication operator *
 *      examples: "(x + 5)(x-1)" will not work -- 
 *      examples: "{[x + 3][sin(x)]}" and "{[x^2][2 + ln(x)]}" will work
 *      
 *  Next Patch
 *      * chain rule
 *      * inverse trig
 *      * quotient rule
 *      * product rule in polynomial functions
 *      * negatives by themselves
 *      * fix cos rule 
 *      * simplified log derivatives
 *      * negatives in general
 *      * rounded decimal values (maybe input for desired number of sig figs)
 */ 

public class Derivative
{
    //instance variables -------------------------
    
    private static String derivative; //holds the derivative
    private static String coefficient; //holds coefficient values
    private static String power; //holds exponent values 
    private static double coefficientValue; //floating pt version of coefficient
    private static double powerValue; //floating pt version of exponent value
    private static String x; //holds the "x" value of the function -- such as sin(20) -- x = 20
    private static int coefficientValueInt; //integer version of coefficient
    private static int powerValueInt; //integer version of exponent value
    
   /**
    * Power rule 
    */
   
   public static String powerRule(String f) //function as parameter
   {
       //gets the coefficient
       coefficient = f.substring(0, f.indexOf("^") - 1); //finds and separates the coefficient of the function
       if (coefficient.equals("") || coefficient.equals(" ")) //if there is no coefficient, the coefficient = 1
            coefficientValue = 1; 
       else //else, parseDouble() will find the coefficient for us 
            coefficientValue = Double.parseDouble(coefficient); 
           
       //gets the exponent     
       power = f.substring(f.indexOf("^") + 1); //finds and seperates the exponent of the function
       powerValue = Double.parseDouble(power); 
       coefficientValue *= powerValue; //power rule step 1 
       
       //syntax for integer coefficient and finds the derived coefficient
       coefficientValueInt = (int) coefficientValue; 
       if (coefficientValueInt == coefficientValue) 
            derivative = "" + coefficientValueInt;
       else //if decimals are needed
            derivative = "" + coefficientValue;
       powerValue--;
       
       //finds the derived exponent
       if (powerValue == 1) //exception , if exponent = 1
            derivative += "x"; 
       else 
       {
            powerValueInt = (int) powerValue; //syntax fixes for integer exponent
            if (powerValueInt == powerValue)
                derivative += "x^" + powerValueInt;
            else //if decimals are needed
                derivative += "x^" + powerValue;  
       }
       return derivative;
   }
   
   /**
    * Constant rule
    */
   
   public static String constantRule(String f)
   {
       derivative = "0"; //rule 1 of constant rule
       return derivative;
   }
   
   /**
    * Coefficient rule
    */
   
   public static String coefficientRule(String f)
   {
       coefficient = f.substring(0, f.indexOf("x")); //finds and separates the coefficient
       if (coefficient.equals("") || coefficient.equals(" ")) //exception: if there is no coefficient
            coefficient = "1";
       //syntax for integer coefficient correction and finds the derived coefficient
       coefficientValueInt = (int) Double.parseDouble(coefficient);
       if (coefficientValueInt == Double.parseDouble(coefficient))
            derivative = "" + coefficientValueInt;
       else
            derivative = coefficient;
       return derivative;
   }
   
   /**
    * trig rules
    */
   
   // Sine rule
   
   public static String sinRule(String f) 
   {
       f = f.trim(); 
  
       int locationSin = f.indexOf("sin"); //looks for coefficients
       if (locationSin > 0) 
            coefficientValue = Double.parseDouble(f.substring(0, locationSin));
       else 
            coefficientValue = 1;
            
       x = f.substring(f.indexOf("(") + 1, f.indexOf(")")); //stores the "x" value of sin(x)
       double coefficientValueTemp, coefficientValue2;       
       if (x.contains("x")) //makes sure the function isn't a constant
            {
                if (x.contains("^")) //power inside a trig function
                    {
                        coefficientValueTemp =  coefficientValue; //temporarily stores coefficient -- prevents errors with static variables
                        String coefficient2 =  powerRule(x); //calls power rule on the x value (a step of trig rule)
                        coefficientValue = coefficientValueTemp; 
                        coefficientValue2 = Double.parseDouble(coefficient2.substring(0, coefficient2.indexOf("x"))); 
                        coefficientValue *= coefficientValue2; //finds the final derived coefficient
                        
                        //syntax corrections for integer coefficient and stores coefficient 
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1)
                            derivative = "";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "" + coefficientValueInt;
                        else 
                            derivative = "" + coefficientValue;
                        derivative += coefficient2.substring(x.indexOf(".") + 2) + "cos(" + x + ")"; //sin -> cos 
                    }
                else //coefficient inside a trig function
                    {
                        coefficientValueTemp =  coefficientValue;
                        String coefficient2 =  coefficientRule(x); 
                        coefficientValue = coefficientValueTemp;
                        coefficientValue2 = Double.parseDouble(coefficient2);
                        coefficientValue *= coefficientValue2;
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1)
                            derivative = "";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "" + coefficientValueInt;
                        else 
                            derivative = "" + coefficientValue;
                        derivative += "cos(" + x + ")";    
                    }
            }
       else 
            derivative = "0";
           
       return derivative;    
   }
   
   // Cosine rule -- works basically the same way as sin()
   
   public static String cosRule(String f) 
   {
       f = f.trim();
       int locationCos = f.indexOf("cos");
       if (locationCos > 0)
            coefficientValue = Double.parseDouble(f.substring(0, locationCos));
       else 
            coefficientValue = 1;
       x = f.substring(f.indexOf("(") + 1, f.indexOf(")"));
       double coefficientValueTemp, coefficientValue2;
       if (x.contains("x")) 
            {
                if (x.contains("^")) 
                    {
                        coefficientValueTemp =  coefficientValue;
                        String coefficient2 =  powerRule(x);  
                        coefficientValue = coefficientValueTemp;
                        coefficientValue2 = Double.parseDouble(coefficient2.substring(0, coefficient2.indexOf("x")));
                        coefficientValue *= coefficientValue2;
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1) //only difference is that cos() has a - coefficient when derived
                            derivative = "-";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "-" + coefficientValueInt;
                        else 
                            derivative = "-" + coefficientValue;
                        derivative += coefficient2.substring(x.indexOf(".") + 2) + "sin(" + x + ")";  //cos -> -sin
                    }
                else
                    {
                        coefficientValueTemp =  coefficientValue;
                        String coefficient2 =  coefficientRule(x); 
                        coefficientValue = coefficientValueTemp;
                        coefficientValue2 = Double.parseDouble(coefficient2);
                        coefficientValue *= coefficientValue2;
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1)
                            derivative = "-";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "-" + coefficientValueInt;
                        else 
                            derivative = "-" + coefficientValue;
                        derivative +=  "sin(" + x + ")";    
                    }
            }
       else 
            derivative = "0";
       return derivative;    
   }
   
   // Tangent rule -- works the same way as sin and cos
   
   public static String tanRule(String f) 
   {
       f = f.trim();
       int locationTan = f.indexOf("tan");
       if (locationTan > 0)
            coefficientValue = Double.parseDouble(f.substring(0, locationTan));
       else 
            coefficientValue = 1;
       x = f.substring(f.indexOf("(") + 1, f.indexOf(")"));
       double coefficientValueTemp, coefficientValue2;
       if (x.contains("x")) 
            {
                if (x.contains("^")) 
                    {
                        coefficientValueTemp =  coefficientValue;
                        String coefficient2 =  powerRule(x);   
                        coefficientValue = coefficientValueTemp;
                        coefficientValue2 = Double.parseDouble(coefficient2.substring(0, coefficient2.indexOf("x")));
                        coefficientValue *= coefficientValue2;
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1)
                            derivative = "";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "" + coefficientValueInt;
                        else 
                            derivative = "" + coefficientValue;
                        derivative += coefficient2.substring(x.indexOf(".") + 2) + "(sec(" + x + "))^2"; //tan -> sec^2
                    }
                else
                    {
                        coefficientValueTemp =  coefficientValue;
                        String coefficient2 =  coefficientRule(x); 
                        coefficientValue = coefficientValueTemp;
                        coefficientValue2 = Double.parseDouble(coefficient2);
                        coefficientValue *= coefficientValue2;
                        coefficientValueInt = (int) coefficientValue;
                        if (coefficientValue == 1)
                            derivative = "";
                        else if (coefficientValueInt == coefficientValue)
                            derivative = "" + coefficientValueInt;
                        else 
                            derivative = "" + coefficientValue;
                        derivative += "(sec(" + x + "))^2";    
                    }
            }
       else 
            derivative = "0";
       return derivative;    
   }
   
   /** 
    * natural logarithm and logarithm
    */
   
   // natural log -- works similarly to trig functions
   
   public static String lnRule(String f)
   {
       f = f.trim();
       
       int locationLn = f.indexOf("ln");
       if (locationLn > 0)
            coefficientValue = Double.parseDouble(f.substring(0, locationLn));
       else 
            coefficientValue = 1;
       x = f.substring(f.indexOf("(") + 1, f.indexOf(")"));
       double coefficient2Value;
       if (x.contains("x")) 
            {
                String coefficient2 = x.substring(0, x.indexOf("x")); 
                if (coefficient2.equals(""))
                    coefficient2Value = 1;
                else
                    coefficient2Value = Double.parseDouble(coefficient2);
                coefficientValue *= coefficient2Value;
                coefficientValueInt = (int) coefficientValue;
                if (coefficientValueInt == coefficientValue)         
                    derivative = "(" + coefficientValueInt + "/" + x + ")"; //ln(x) -> 1/x 
                else 
                    derivative = "(" + coefficientValue + "/" + x + ")";
            }
       else
            derivative = "0";
       return derivative;   
   }
   
   // non-natural logarithms -- works similarly to trig functions
   
   public static String logRule(String f, int base) //two parameters, one for the base of the log
   {
       f = f.trim();
       int locationLog = f.indexOf("log");
       if (locationLog > 0)
            coefficientValue = Double.parseDouble(f.substring(0, locationLog));
       else 
            coefficientValue = 1;
       x = f.substring(f.indexOf("(") + 1, f.indexOf(")"));
       double coefficient2Value;
       if (x.contains("x")) 
            {
                String coefficient2 = x.substring(0, x.indexOf("x")); 
                if (coefficient2.equals(""))
                    coefficient2Value = 1;
                else 
                    coefficient2Value = Double.parseDouble(coefficient2);
                coefficientValue *= coefficient2Value;
                coefficientValueInt = (int) coefficientValue;
                if (coefficientValueInt == coefficientValue)
                    derivative = "(" + coefficientValueInt + "/" + x + "ln" + base + ")";
                else 
                    derivative = "(" + coefficientValue + "/" + x + "ln" + base + ")";
            }
       else
            derivative = "0";
       return derivative;   
   }
   
   /**
    * product rule
    */
   
   public static String productRule(String f) //{[x+1][x+2]}
   {
       f = f.trim();
       
       //separates f into two expressions (factors in multiplication)
       String expression1 = f.substring(2, f.indexOf("]")); 
       String leftovers = f.substring(f.indexOf("]") + 1);
       String expression2 = leftovers.substring(1, leftovers.indexOf("}") - 1);
       String derivative1 = testDerivative.derive(expression1);
       String derivative2 = testDerivative.derive(expression2);
       String derivative = "(" + derivative1 + ")(" + expression2 + ") + (" + derivative2 + ")(" + expression1 + ")"; //(x)(y) -> x'y + xy'
       return derivative;   
   }
   
   /**
    * quotient rule 
    */
   
   public static String quotientRule(String f) //{[x+1]/[x+2]} //work in progress
   {
       f = f.trim();
       
       //separates f into two expressions (factors in multiplication)
       String expression1 = f.substring(2, f.indexOf("]"));
       String leftovers = f.substring(f.indexOf("]") + 1);
       String expression2 = f.substring(2, f.indexOf("]"));
       String derivative1 = testDerivative.derive(expression1);
       String derivative2 = testDerivative.derive(expression2);
       String derivative = null; //(x)/(y) -> 
       return derivative;
   }
   

}
