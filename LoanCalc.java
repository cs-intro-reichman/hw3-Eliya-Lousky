// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
		public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		double r= rate/100.0;
		for (int i =0; i < n; i++){
		balance =  (balance - payment) * (1 + r);
	}
		// Replace the following statement with your code
		return balance;
	}
	

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code
		double N = loan / n;
		iterationCounter = 0;
		while (endBalance(loan, rate, n, N)>0){
		N = N + epsilon;
		iterationCounter ++ ;
	}
		return N;
}
    
    
  
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
         double l  = loan / n;   
  		 double h = loan;      
		
		  iterationCounter = 0;  

  		  double fL = endBalance(loan, rate, n, l);
   		  double fH = endBalance(loan, rate, n, h);


		  double mid = 0.0;

	
  		  while (h - l > epsilon) {
     	   mid = (l + h) / 2.0;
     	   double fMid = endBalance(loan, rate, n, mid);

     
        if (fL * fMid > 0) {
            l = mid;
            fL = fMid;
        } else {
            h = mid;
            fH = fMid;
        }

        iterationCounter++;
    }

    return (l + h) / 2.0;
    }
}