/**
 * Kinematics Class
 */
public class Kinematics 
{
  private static double initialVelocity; // meters per second
  private static double finalVelocity; // meters per second
  private static double avgVelocity;
  private static double avgAcceleration; // meters per second per second
  private static double displacement; //meters
  private static double time; //seconds
  
  /**
   * Constructors
   */
  public Kinematics() //default values
  {
      initialVelocity = -1;
      finalVelocity = -1;
      avgAcceleration = -1;
      displacement = -1;
      time = -1;
  }
  
  public Kinematics(double vi, double vf, double a, double d, double t)
  {
      initialVelocity = vi;
      finalVelocity = vf;
      avgAcceleration = a;
      displacement = d;
      time = t;
  }
  
  /**
   * Mutators
   */
  
  public static void setInitialVelocity(double vi)
  {
      initialVelocity = vi;
  }
  public static void setFinalVelocity(double vf)
  {
      finalVelocity = vf;
  }
  public static void setAvgAcceleration(double a)
  {
      avgAcceleration = a;
  }
  public static void setDisplacement(double d)
  {
      displacement = d;
  }
  public static void setTime(double t)
  {
      time = t;
  }
  
  /**
   * Accessors
   */
  
  public static double getInitialVelocity()
  {
      return initialVelocity;
  }
  
  public static double getFinalVelocity()
  {
      return finalVelocity;
  }
  public static double getAvgVelocity()
  {
      return avgVelocity;
  }
  public static double getAvgAcceleration()
  {
      return avgAcceleration;
  }
  public static double getDisplacement()
  {
      return displacement;
  }
  public static double getTime()
  {
      return time;
  }
  
  /**
   * Methods
   */
  
  public static double averageVelocity(double vi, double vf) //uses v = (vi + vf) / 2
  {
      avgVelocity = vi + vf;
      avgVelocity /= 2;
      //setAvgVelocity(avgVelocity);
      return avgVelocity;
  }
  
  public static double averageVelocity(double d, double t, int x) //uses v = d / t
  {
      avgVelocity = d/t;
      //setAvgVelocity(avgVelocity);
      return avgVelocity;
  }
  
  public static double averageAcceleration(double aV, double t) //uses a = v / t
  {
      avgAcceleration = aV/t;
      return avgAcceleration;
  }
  
  public static double time(double a, double aV) //uses a = v / t
  {
      time = aV/a;
      return time;
  }
  
  public static double finalVelocity(double aV, double vi) //uses v = (vi + vf) / 2
  {
      finalVelocity = aV * 2; 
      finalVelocity += initialVelocity;
      return finalVelocity;
  }
  
  public static double initialVelocity(double aV, double vf) //uses v = (vi + vf) / 2
  {
      initialVelocity = aV * 2;
      initialVelocity = finalVelocity = initialVelocity;
      return initialVelocity;
  }
  
  public static double displacement(double vi, double t, double a) //uses d = vi * t + .5 * a * t^2
  {
      displacement = vi * t;
      double simplify = .5 * a * t * t;
      displacement += simplify;
      return displacement;
  }
  
  public static double finalVelocity(double vi, double a, double d) //uses vf^2 = vi^2 + 2 * a * d
  {
      finalVelocity = vi * vi + (2 * a * d);
      finalVelocity = Math.sqrt(finalVelocity);
      return finalVelocity;
  }
  
  public static double initialVelocity(double vf, double a, double d) //uses vf^2 = vi^2 + 2 * a * d
  {
      initialVelocity = vf * vf - (2 * a * d);
      initialVelocity = Math.sqrt(initialVelocity);
      return initialVelocity;
  }
  
  public static void round() //rounds to two decimal places 
  {
      initialVelocity =  (int) (initialVelocity * 100 + 0.5)/ 100.0;
      avgVelocity =  (int) (avgVelocity * 100 + 0.5)/ 100.0;
      finalVelocity =  (int) (finalVelocity * 100 + 0.5)/ 100.0;
      avgAcceleration =  (int) (avgAcceleration * 100 + 0.5)/ 100.0;
      displacement =  (int) (displacement * 100 + 0.5)/ 100.0;
      time =  (int) (time * 100 + 0.5)/ 100.0;
      
  }
  /**
   * Prints equations
   */
  public static void equations() 
  {
      System.out.println("Average Velocity = (final velocity - initial velocity) / 2"); // no acceleration 
      System.out.println("Average Velocity = displacement / time");  //no initial velocity 
      System.out.println("Average acceleration = average velocity / time"); //no displacement
      System.out.println("Displacement = initial velocity x time + (1/2) x average acceleration x time^2"); //no final velocity
      System.out.println("Final velocity ^ 2 = initial velocity ^ 2 + 2 x average acceleration x displacement"); // no time 
  }
  
}
