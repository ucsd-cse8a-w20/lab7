public class Lab7 {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    String myString1 = "hello";
    double myNumber1 = CSE8ALib.parseDouble(myString1);

    int myNumber2 = divide(1.0, 3.0);
    
    double myNumber3 = subtract(3.0, 4.0);

    int[] myArray = {100, 27, 43, 64};
    int myInteger1 = myArray[4];

    int myInteger2 = 6 / 0;

  }

  // Takes two doubles and returns their sum as a double
  public static int add(double a, double b) {
    return a + b;
  }

  // Takes two doubles and returns their difference as a double
  public static double subtract(double a, double b) {
    return a - b;
  }

  // Takes two doubles and returns their quotient as a double
  public static double divide(double a, double b) {
    return a / b
  }

}
