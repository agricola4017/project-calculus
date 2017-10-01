import java.io.*;
import java.util.*;

public class testKinematics
{
   public static void main(String[] args)
   {
       
   double iV = -1, fV = -1, aV = -1, t = -1, a = -1, d = -1; //default class variables 
   int checkI, checkF, checkT, checkA, checkD; //check input formulas
   String yesNoI, yesNoF, yesNoT, yesNoA, yesNoD; //check input storing
   
   Scanner kbReader = new Scanner (System.in);
   Kinematics system = new Kinematics();
   System.out.println("1. Initial velocity\n2. Final velocity\n3. Average velocity\n4. Average acceleration\n5. Displacement\n6. Time"); //prints table
   System.out.print("Which variable would you like to solve for? "); //chooses which value user wants 
   int choice = kbReader.nextInt();

   //asks user for given values -----------------
   
   System.out.print("Do you know the Initial velocity? (y/n) "); 
   yesNoI = kbReader.next();
   if (yesNoI.equals("y"))
   {
       System.out.print("Please enter the initial velocity ");
       iV = kbReader.nextDouble();
       system.setInitialVelocity(iV);
       checkI = 1;
   }
   else 
       checkI = 0;
       
   System.out.print("Do you know the Final velocity? (y/n) ");
   yesNoF = kbReader.next();
   if (yesNoF.equals("y"))
   {
       System.out.print("Please enter the final velocity ");
       fV = kbReader.nextDouble();
       system.setFinalVelocity(fV);
       checkF = 1;
   }
   else 
       checkF = 0;
       
   System.out.print("Do you know the Average acceleration? (y/n) ");
   yesNoA = kbReader.next();
   if (yesNoA.equals("y"))
   {
       System.out.print("Please enter the average acceleration  ");
       a = kbReader.nextDouble();
       system.setAvgAcceleration(a);
       checkA = 1;
   }
   else
       checkA = 0;
       
   System.out.print("Do you know the Displacement? (y/n) ");
   yesNoD = kbReader.next();
   if (yesNoD.equals("y"))
   {
       System.out.print("Please enter the displacement  ");
       d = kbReader.nextDouble();
       system.setDisplacement(d);
       checkD = 1;
   }
   else
       checkD = 0;
       
   System.out.print("Do you know the time? (y/n) ");
   yesNoT = kbReader.next();
   if (yesNoT.equals("y"))
   {
       System.out.print("Please enter the average time  ");
       t = kbReader.nextDouble();
       system.setTime(t);
       checkT = 1;
   }
   else
       checkT = 0;
   
   //calculates average velocity
   if (checkI == 1 && checkF == 1) //if iV and fV were given 
       aV = Kinematics.averageVelocity(iV, fV);
   else if (checkD == 1 && checkT ==1) //if d and t were given 
       aV = Kinematics.averageVelocity(d, t, 2);  
       
   //calcs acceleraction
   if ((aV >= 0 && checkT == 1)) //if average velocity is already calculated and time is given
       a = Kinematics.averageAcceleration(aV, t);
   else if ((aV >= 0 && checkA == 1)) //calculates time if acceleration is given and average velocity has been calculated
       t = Kinematics.time(a, aV); 
   
   if ((aV >= 0 && checkI == 1)) //if average velocity has been already calculated and initial velocity is given
       fV = Kinematics.finalVelocity(aV, iV); //calculates final velocity
   else if ((aV >= 0 && checkF == 1)) //if average velocity has been already calculated and final velocity is given
       iV = Kinematics.initialVelocity(aV, fV); //calculates initial velocity
    
   if (iV >= 0 && t >=0 && a >= 0) //if initial velocity, time and acceleration have all been calculated 
       d = Kinematics.displacement(iV, t, a); //calculates displacement
   
   if (iV >= 0 && a >= 0 && d >= 0) //if initial velocity, acceleration and displacement were already calculated
       fV = Kinematics.finalVelocity(iV, a, d); //calculates final velocity
   else if (fV >= 0 && a >=0 && d >=0) //if final velocity, acceleration and displacement were already calculated
       iV = Kinematics.initialVelocity(fV, a, d); //calculates initial velocity
   aV = Kinematics.averageVelocity(iV, fV); //calculates average velocity again (checks)
   
   t = Kinematics.time(a, aV); //calculates time again (checks)
   
   Kinematics.round();
   
   //output decision tree
   
   if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6)
       System.out.print("The value you are looking for is ");
   if (choice == 1)
       System.out.println(Kinematics.getInitialVelocity());
   else if (choice == 2)
       System.out.println(Kinematics.getFinalVelocity());
   else if (choice == 3)
       System.out.println(Kinematics.getAvgVelocity());
   else if (choice == 4)
       System.out.println(Kinematics.getAvgAcceleration());
   else if (choice == 5)
       System.out.println(Kinematics.getDisplacement());
   else if (choice == 6)
       System.out.println(Kinematics.getTime());
   else 
       System.out.println("Your input value was invalid, please try again.");
       
       
   
   }
}
