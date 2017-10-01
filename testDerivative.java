/**
 * Michael Chen
 * Version: 1.0
 * Date Created: 2/1/2017 - 2/2/2017
 **/
import java.io.*;
import java.util.*;

public class testDerivative
{
    public static void main(String[] args)
    {
        //function input
        Scanner kbReader = new Scanner (System.in);
        System.out.print("Please input a function: ");
        String inputFunction = kbReader.nextLine();
        Function f = new Function(inputFunction); //creates new object f with function inputFunction
        String derivative = derive(inputFunction); //takes the derivative of the object 
        f.setDerivative(derivative); 
        System.out.println(f); //output 
     
    }
    
    public static String derive(String inputFunction) 
    {  
        String leftovers = inputFunction.trim();
        String expression = ""; //input value for xRule methods
        String derivative = ""; //output value
        int loop = - 2; //loop control variable
        
        do 
        {
            int plus; // plus and minus is used for determining whether to add or subtract the next expression
            int minus;
            int plusB = 2;
            
            if (leftovers.contains("{[")) //product rule, looks for specific product rule syntax
            {
                if (inputFunction.startsWith("{[")) //has to start with product rule 
                    {
                        expression = leftovers.substring(0, inputFunction.indexOf("}") + 1); 
                        leftovers = leftovers.substring(leftovers.indexOf("}") + 1, leftovers.length()); //useless but included for the next update where product rules should work in polynomial functions
                        loop = 4; //same as above
                        if (leftovers.equals("")) //temporary fix to product rule's incompatability with the rest of the program 
                            loop = 3; //ends loop 
                    }
            }
            else if (leftovers.contains("+") && leftovers.contains("-")) //if the polynomial has both subtract and add operators
            {
                plus = leftovers.indexOf("+"); 
                minus = leftovers.indexOf("-");
                loop = 4;
                
                 if (plus < minus) //if the closest expression is added
                 {
                      expression = leftovers.substring(0, leftovers.indexOf("+") - 1); //looks for + sign 
                      leftovers = leftovers.substring(leftovers.indexOf("+") + 1); //stores rest of string in leftovers
                      plusB = 1; //prints +
                 }
                 else if (plus > minus) //is subtracted
                 {
                     expression = leftovers.substring(0, leftovers.indexOf("-") - 1); //looks for - sign
                     leftovers = leftovers.substring(leftovers.indexOf("-") + 1);
                     plusB = 0; //prints -
                 }
            }
            else if (leftovers.contains("+")) //function only contains additions
            {
                loop = 4;
                
                expression = leftovers.substring(0, leftovers.indexOf("+") - 1); 
                leftovers = leftovers.substring(leftovers.indexOf("+") + 1);
                plusB = 1; 
            }
            else if (leftovers.contains("-")) //function only contains subtractions 
            {
                loop = 4;
                
                expression = leftovers.substring(0, leftovers.indexOf("-") - 1);
                leftovers = leftovers.substring(leftovers.indexOf("-") + 1);
                plusB = 0;
            }
            else if (inputFunction.endsWith(leftovers) || inputFunction.equals(leftovers)) //checks for the expression to be the last expression in the function
            {
                loop = 3; //ends loop
                plusB = -1; //doesn't print out any operators
                expression = leftovers.substring(0, leftovers.length());
            }
            else  //error print line
            {
                loop = 3;
                plusB = -1;
                System.out.println("might want to check your expression...");
            }
            String derivativeExp = logic(expression); //derivative of the expression
            //the top two branches of this logic branch was supposed to solve the problem of "x + -sin(x)" when one inputted cos(x) in a polynomial function but is useless for now and is kept for a later patch
            if (expression.contains("cos") && (!inputFunction.startsWith(derivativeExp))) 
                 derivative += derivativeExp.substring(0, derivativeExp.length()); //prints - sign    
            else if (expression.contains("cos"))
            {
                 derivative += derivativeExp.substring(1, derivativeExp.length()); //doesn't print - sign
            }
            else //every other function
                 derivative += derivativeExp;
                 
            if (derivativeExp.equals("n/a")) //ends loop if the derivation failed 
                loop = 3;
                
            //logic statement to print the operator of the next expression
            if (plusB == 1)
                derivative += " + ";
            else if (plusB == 0)
                derivative += " - ";  
            
            loop--; //decrements loop 
        } while (loop != 2); 
        
        return derivative;
    }
    
    public static String logic(String expression) //decides which rule to use
    {
        int base = 0; //default value for base
        String expressionDerivative; //holds derivative of function
        
        if (expression.contains("{[")) //product rule
            expressionDerivative = Derivative.productRule(expression);
        else if (expression.contains("sin")) //sin rule
            expressionDerivative = Derivative.sinRule(expression);
        else if (expression.contains("cos")) //cos rule
            expressionDerivative = Derivative.cosRule(expression);
        else if (expression.contains("tan")) //tan rule
            expressionDerivative = Derivative.tanRule(expression);
        else if (expression.contains("ln")) //ln rule
            expressionDerivative = Derivative.lnRule(expression);
        else if (expression.contains("log")) //log rule
        {
            Scanner kbReader = new Scanner (System.in);
            System.out.print("A logarithm has been detected! Please input the base of the logarithm "); //input statement for base of log 
            base = kbReader.nextInt();
            expressionDerivative = Derivative.logRule(expression, base);
        }
        else if (expression.contains("^")) //power rule
            expressionDerivative = Derivative.powerRule(expression);
        else if (expression.contains("x")) //coefficient rule
            expressionDerivative = Derivative.coefficientRule(expression); 
        else if (expression.contains("0") || expression.contains("1") || expression.contains("2") || expression.contains("3") || expression.contains("4") || expression.contains("5")
              || expression.contains("6") || expression.contains("7") || expression.contains("8") || expression.contains("9")) //constant rule
        {
            expressionDerivative = Derivative.constantRule(expression);               
        }
        else //invalid error function 
        {
            expressionDerivative = "n/a";
            System.out.println("This is not a valid expression.");
        }
        return expressionDerivative;
            
    }
    
}