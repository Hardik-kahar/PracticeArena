package sdet;

public class NumericTask {

    public static void main(String[] args) {

        System.out.print(isPrime(49));
    }

    public static boolean isPrime(int number){
        if(number <= 1) return false;
        if(number == 2  || number == 3) return true;
        if(number % 2 ==0 || number % 3 ==0) return false;

        for(int i = 5 ; i * i <= number; i =i+6){

            if(number % i ==0 || number % (i + 2) == 0){
                return false;
            }
        }
        return true;
    }


}
