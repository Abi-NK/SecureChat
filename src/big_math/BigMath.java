package big_math;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class BigMath {
  final int[] digits; //The number is reperesented as an integer array.
  
  /**
   * This is the intial constructor for this class.
   * 
   * @param digitString the input string that will be turned into a bignumber.
   */
  public BigMath(String digitString) {
    char[] digitsChar = (digitString.toCharArray());
    int[] digitsInt = new int[digitsChar.length];
    
    for (int i = 0; i < digitsInt.length; i++) {
      digitsInt[i] = Character.getNumericValue(digitsChar[i]);
    }
    
    this.digits = digitsInt;
  }
  
  /**
   * Constructor used to form a BigMath number using an integer array.
   * 
   * @param digitsArray the integer array used to make the BigMath
   *     number.
   */
  public BigMath(int[] digitsArray) {
    this.digits = digitsArray;
  }
  
  /**
   * Constructor used to form a random BigMath number using a specified
   * bit length.
   * 
   * @param j the bit length of the BigMath number.
   * @param random the Random class used to make the number random.
   */
  public BigMath(int j, Random random) {
    double a = 2;
    double b = j;
    int digitLength = (int) ((Math.log10(a)) * b) + 1;

    int[] digitsInt = new int[digitLength];

    for (int i = 0; i < digitsInt.length; i++) {
      if (i == 0) {
        digitsInt[i] = random.nextInt(9) + 1;
      } else {
        digitsInt[i] = random.nextInt(10) + 0;
      }
    }

    if (digitsInt[digitsInt.length - 1] % 2 == 0) {
      if (digitsInt[digitsInt.length - 1] == 0) {
        digitsInt[digitsInt.length - 1] = 1;
      } else {
        digitsInt[digitsInt.length - 1] = digitsInt[digitsInt.length - 1] + 1;
      }
    }
    this.digits = digitsInt;
  }

  /**
   * Turns the BigMath number into a string.
   */
  public String toString() {
    return "BigMath." + Arrays.toString(digits);
  }
  
  /**
   * Gets the length of "this" BigMath number.
   * 
   * @return the digit length of the BigMath number.
   */
  public int getLength() {
    return digits.length;
  }

  /**
   * Addition method used to add "this" to another BigMath number.
   * 
   * @param secondBig the other BigMath number to be added to this one.
   * @return the result as a BigMath.
   */
  public BigMath add(BigMath secondBig) {
    if (isNegative(this.digits)) {
      int[] negative = this.digits.clone();
      negative[0] = negative[0] * -1;
      
      return new BigMath(subtractArrays(secondBig.digits.clone(), negative));
    }
    
    //System.out.println("add");
    BigMath newFirstBig = this;
    BigMath newSecondBig = secondBig;

    //Check if both numbers are the same length, if they aren't then
    // pad zeros on the numbers until they are equal lengths.
    if (this.compareTo(secondBig) == -1) {
      newFirstBig = this.makeSameLength(secondBig);
    }

    if (secondBig.compareTo(this) == -1) {
      newSecondBig = secondBig.makeSameLength(this);
    }

    int[] resultArray = new int[newFirstBig.digits.length];
    int carry = 0;
    String result = "";

    for (int i = newFirstBig.digits.length - 1; i >= 0; i--) {
      resultArray[i] = newFirstBig.digits[i] + newSecondBig.digits[i] + carry;

      if (resultArray[i] >= 10) {
        carry = 1;
        resultArray[i] = resultArray[i] - 10;
        result = resultArray[i] + result;

      } else {
        carry = 0;
        result = resultArray[i] + result;
      }
    }

    if (carry == 1) {
      result = 1 + result;
    }

    return new BigMath(result);
  }
  
  /**
   * Subtraction of two BigMath numbers. It performs
   * "this" minus another BigMath number.
   * 
   * @param secondBig the number used to subtract from "this".
   * @return the result after subtracting the second number.
   */
  public BigMath subtract(BigMath secondBig) {
    return new BigMath(subtractArrays(this.digits.clone(), secondBig.digits.clone()));
  }
  
  /**
   * This class is used to subtract two arrays as if they were numbers.
   * 
   * @param a the first number array.
   * @param b the number array used to subtract from a.
   * @return the result as an integer array.
   */
  public int[] subtractArrays(int[] a, int[] b) {
    if (isNegative(a) && isNegative(b)) {
      a[0] = a[0] * -1;
      b[0] = b[0] * -1;
      
      int compareSize = compareToArray(a, b);
      
      if (compareSize == 1) {
        return subtractArrays(a, b);
      } else if (compareSize == 0) {
        int[] zeroArr = {0};
        return zeroArr;
      } else if (compareSize == -1) {
        return subtractArrays(b, a);
      }
      
    } else if (isNegative(a) && !isNegative(b)) {
      a[0] = a[0] * -1;
      int[] negative = new BigMath(a).add(new BigMath(b)).toArray();
      negative[0] = negative[0] * -1;
      return negative;
    } else if (!isNegative(a) && isNegative(b)) {
      b[0] = b[0] * -1;
      return new BigMath(a).add(new BigMath(b)).toArray();
    }
    
    //return negative if the answer is going to be negative.
    if (compareToArray(a, b) == -1) {
      //System.out.println(Arrays.toString(a) + "\n" + Arrays.toString(b));
      int[] negative = subtractArrays(b, a);
      negative[0] = negative[0] * -1;
      return negative;
    }
    
    if (compareToArray(b, a) == -1) {
      b = makeSameLengthArray(b, a);
    }
    
    int[] result = new int[a.length];
    int borrow = 0;
    
    for (int i = a.length - 1; i >= 0; i--) {
      result[i] = a[i] - b[i] - borrow;
      if (result[i] < 0) {
        //borrow 10 from next array slot if the number is below zero.
        borrow = 1;
        result[i] += 10;
      } else {
        borrow = 0;
      }
    }
    
    //remove uneeded zeros from beginning of number.
    while (result[0] == 0 && result.length > 1) {
      int n = result.length - 1;
      int[] newArray = new int[n];
      
      if (result[0] == 0 && result.length > 1) {
        System.arraycopy(result, 1, newArray, 0, n);
        result = newArray;
      }
      
      if (newArray[0] != 0) {
        return newArray;
      }
    }
    return result;
  }
  
  /**
   * Make two BigMath numbers the same length using leading zeros.
   * 
   * @param secondBig the second number being compared.
   * @return the new BigMath number with leading zeros that's the same
   *     length as the other number.
   */
  public BigMath makeSameLength(BigMath secondBig) {
    return new BigMath(makeSameLengthArray(this.digits.clone(), secondBig.digits.clone()));
  }
  
  /**
   * Makes a bigMath number the same length as another if it is smaller by
   * adding zeroes to the beginning of it.
   * 
   * @param a the first number being compared
   * @param b the number it's being compared with.
   * @return the padded number or do nothing if they are the same length.
   */
  public int[] makeSameLengthArray(int[] a, int[] b) {
  
    int compareLength = compareToArray(a, b);
    String firstBigString = "";
    String paddedZeros = "";
    BigMath paddedFirstBig = this;
    
    //if first number is smaller than the second then make it the same
    //    length by adding zeros until they match.
    if (compareLength == -1) {
      int secondBigLength = b.length;
      int firstBigLength = a.length;
      
      //create a string with the zeros needed.
      for (int i = 0; i < (secondBigLength - firstBigLength); i++) {
        paddedZeros += "0";
      }
      
      //create a string of the first number.
      for (int i = 0; i < a.length; i++) {
        firstBigString += a[i];
      }
      paddedFirstBig = new BigMath((paddedZeros) + firstBigString);
    }
    return paddedFirstBig.digits.clone();
  }
  
  /**
   * Compare two BigMath numbers to see which is bigger, smaller
   * or if they are equal.
   * 
   * @param secondBig the number being compared with "this" BigMath
   *     number.
   * @return the integer vaue based on which number is bigger or if
   *     they are the same.
   */
  public int compareTo(BigMath secondBig) {
    return compareToArray(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Compare two numbers represented as arrays.
   * 
   * @param first the number that is being compared with "second".
   * @param second the number that "first" is being compared with.
   * @return value from 1 to -1 to show how both numbers are different or equal.
   */
  public int compareToArray(int[] first, int[] second) {
    //return -1 if the first number is negative
    if (isNegative(first)) {
      return -1;
    }
    
    //return 1 if the second number is negative.
    if (isNegative(second)) {
      return 1;
    }
    
    //if this big number is smaller than the second, then return -1
    if (first.length < second.length) {
      return -1;
    }
    
    //If this big number is larger than the second, then return 1.
    if (first.length > second.length) {
      return 1;
    }
    
    //If numbers are same length then go through each digit.
    for (int i = 0; i < first.length; i++) {
      if (first[i] < second[i]) {
        return -1;
      }
      
      if (first[i] > second[i]) {
        return 1;
      }
    }
    
    //Otherwise the numbers are exactly the same.
    return 0;
  }
  
  /**
   * Checks if a number is even.
   * 
   * @return a boolean value if the number is even.
   */
  public boolean isEven() {
    if (this.digits[this.digits.length - 1] % 2 == 0) {
      return true;
    }
    return false;
  }
  
  /**
   * Multiplies two big numbers.
   * 
   * @param secondBig the second big number to multiply with.
   * @return result when calculated.
   */
  public BigMath multiply(BigMath secondBig) {
    return new BigMath(multiplyNorm(this.digits.clone(), secondBig.digits.clone()));
  }
  
  /**
   * Simple multiplication through addition. Slow implementation
   * of addition.
   * 
   * @param secondBig the number that "this" BigMath number is multiplied with.
   * @return the result as a new BigMath number.
   */
  public BigMath multiplySimple(BigMath secondBig) {
    BigMath firstBig = new BigMath(this.digits.clone());
    BigMath result = new BigMath("0");
    BigMath zero = new BigMath("0");
    BigMath one = new BigMath("1");
    
    while (secondBig.compareTo(zero) != 0) {
      result = result.add(firstBig);
      secondBig = secondBig.subtract(one);
    }
    return result;
  }
  
  /**
   * Long-Multiplication of one number array by another and
   * returns the result. Got help from Matthew Crumley @ silentmatt.com.
   * 
   * @param a the first number in the multiplication equation.
   * @param b the second number in the multiplication equation.
   * @return the answer as a new BigMath number.
   */
  public int[] multiplyNorm(int[] a, int[] b) {
    //check if numbers are negative first.
    if (isNegative(a) && !isNegative(b)) {
      a[0] = a[0] * -1;
      System.out.println(a[0]);
      int[] negative = multiplyNorm(a, b);
      negative[0] = negative[0] * -1;
      return negative;
    } else if (isNegative(b) && !isNegative(a)) {
      b[0] = b[0] * -1;
      int[] negative = multiplyNorm(a, b);
      negative[0] = negative[0] * -1;
      return negative;
    } else if (isNegative(a) && isNegative(b)) {
      a[0] = a[0] * -1;
      b[0] = b[0] * -1;
      return multiplyNorm(a, b);
    }
    
    //return accordingly if one of the numbers is 0 or 1.
    int[] one = {1};
    if (a.length == 1 && a[0] == 0
        || b.length == 1 && b[0] == 0) {
      int[] ret = {0};
      return ret;
    } else if (Arrays.equals(a, one)) {
      return b;
    } else if (Arrays.equals(b, one)) {
      return a;
    }
    
    int carry = 0;
    int shiftCounter = -1;
    
    //result will always be length of both numbers combined. Or it will be
    //the length of both numbers combined minus one. This is useful because it
    //it allows me to create a fixed array for the result.
    int[] result = new int[a.length + b.length];
    
    //iterate through the second number.
    for (int i = b.length - 1; i >= 0; i--) {
      shiftCounter++;
      int number = 0;
      int multCount = result.length - 1;
      
      //iterate through the first number. Multiply each digit of the second
      //number with each digit of the first number, then add up each result for
      //every iteration.
      for (int j = a.length - 1; j >= 0; j--) {
        
        number = result[multCount - shiftCounter] + (b[i]
            * a[j]) + carry;        
        carry = number / 10;
        result[multCount - shiftCounter] = number % 10;
        multCount--;
      }
      
      //If there is still a carry left then add it to the next element of the result
      if (carry > 0) {
        number = result[multCount - shiftCounter] + carry;
        carry = number / 10;
        result[multCount - shiftCounter] = number % 10;
      }
    }
    
    if (result[0] == 0) {
      return Arrays.copyOfRange(result, 1, result.length);
    }
    return result;
  }
  
  /**
   * Karatsuba multiplication return "this" multiplied by another
   * BigMath number.
   * 
   * @param secondBig the second number in the multiplication equation.
   * @return the result as a new BigMath number.
   */
  public BigMath multiplyKarat(BigMath secondBig) {
    return karatsubaMultiply(this, secondBig);
  }
  
  /**
   * Not finished yet.
   * Multiplies two numbers using Karatsuba algorithm.
   * Code taken from introcs.cs.princeton.edu. no author.
   * 
   * @param x the first number to be multiplied.
   * @param y the second number to be multiplied.
   * @return result after multiplying x and y.
   */
  public BigMath karatsubaMultiply(BigMath x, BigMath y) {
      
    //System.out.println("karatsuba");
    // cutoff to brute force
    int n = Math.max(x.bitLength(), y.bitLength());
    
    if (n <= 2000) {
      return x.multiply(y);
    }

    // number of bits divided by 2, rounded up
    n = (n / 2) + (n % 2);

    // x = a + 2^N b,   y = c + 2^N d
    BigMath b = x.shiftRight(n);
    BigMath a = x.subtract(b.shiftLeft(n));
    BigMath d = y.shiftRight(n);
    BigMath c = y.subtract(d.shiftLeft(n));

    // compute sub-expressions

    BigMath ac = karatsubaMultiply(a, c);
    BigMath bd = karatsubaMultiply(b, d);
    BigMath abcd = karatsubaMultiply(a.add(b), c.add(d));

    return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(n)).add(bd.shiftLeft(2 * n));
  }
  
  /**
   * Returns the right-shift of "this" by a specified amount
   * of right-shifts.
   * 
   * @param n the amount of times to shift right.
   * @return the new shifted number as a new BigMath number.
   */
  public BigMath shiftRight(int n) {
    //if in any scenario the specified right shift count is zero
    //then return "this" unchanged.
    if (n == 0) {
      return this;
    }

    int[] digitsArray = this.digits.clone();
    return new BigMath(shiftRight(digitsArray, n));
  }
  
  /**
   * Returns the logical shift right of "this".
   * 
   * @param n the amount of times to shift a number to the right.
   * @return the shifted number as a string.
   */
  public String shiftRight(int[] digits1, int n) {
    //simple division by 2 but round down
    int[] newNumber = digits1;
    String result = "";

    if (n == 0) {
      for (int j = 0; j < digits1.length - 1; j++) {
        result = result + digits1[j];
      }
      return result;
    }
    
    for (int i = 0; i < digits1.length; i++) {
      //If the number is odd then carry the one to the next number otherwise
      // just divide the number by two and add it to the result.
      if (newNumber[i] % 2 != 0 && i < (digits1.length - 1)) {
        newNumber[i] = newNumber[i] / 2;
        result += newNumber[i];
        newNumber[i + 1] += 10;
      } else {
        newNumber[i] = newNumber[i] / 2;
        result += newNumber[i];
      }
    }

    String numberString = "";

    //remove zero at the beginning of number if there is one
    if (newNumber[0] == 0 && newNumber.length > 1) {
      for (int i = 1; i < newNumber.length; i++) {
        numberString += digits1[i];
      }
      result = numberString;
    }
    
    //Recursion: repeat the shifting until n = 0
    if (n - 1 == 0) {
      return result;
    }
    
    String[] resultArray = result.split("(?!^)");
    int[] resultIntArray = new int[resultArray.length];

    for (int i = 0; i < resultArray.length; i++) {
      resultIntArray[i] = Integer.parseInt(resultArray[i]);
    }
    return shiftRight(resultIntArray, n - 1);
  }
  
  /**
   * Returns a new instance of the shifted number. Which is needed because
   * I can do calculations by shifting left without having to change the
   * original number being shifted.
   * 
   * @param n the amount of times the number should be shifted.
   * @return the shifted number.
   */
  public BigMath shiftLeft(int n) {
    //System.out.println("shift left");
    int[] cloneDigits = digits.clone();
    return new BigMath(shiftLeft(cloneDigits, n));
  }
  
  /**
   * Returns the logical shift left of "this".
   * 
   * @param n the amount of times to shift left a number.
   * @return the shifted number.
   */
  public String shiftLeft(int[] digits1, int n) {
    int[] newNumber = digits1;
    String result = "";
    int carry = 0;
    
    if (n == 0) {
      for (int j = 0; j < digits1.length - 1; j++) {
        result = result + digits1[j];
      }
      return result;
    }
      
    for (int i = digits1.length - 1; i >= 0; i--) {
      if (newNumber[i] * 2 > 9) {
        newNumber[i] = (newNumber[i] * 2) % 10;
        newNumber[i] += carry;
        result = newNumber[i] + result;
        carry = 1;
      } else {
        newNumber[i] = newNumber[i] * 2;
        newNumber[i] += carry;
        carry = 0;
        result = newNumber[i] + result;
      }
    }
      
    //Add the carry if there is one remaining after the loop
    if (carry == 1) {
      result = 1 + result;
    }
     
    if (n - 1 == 0) {
      return (result);
    }
      
    String[] resultArray = result.split("(?!^)");
    int[] resultIntArray = new int[resultArray.length];
      
    for (int i = 0; i < resultArray.length; i++) {
      resultIntArray[i] = Integer.parseInt(resultArray[i]);
    }
    
    //Recursion: repeat the left shifting until n = 0
    return shiftLeft(resultIntArray, n - 1);
  }
  
  /**
   * Calculates the bit length of "this" BigMath number.
   * 
   * @return the bitlength of "this".
   */
  public int bitLength() {
    //System.out.println("bitlength");
    int bitLength = 0;
    BigMath count = new BigMath("1");
    BigMath zero = new BigMath("0");
    
    //if the number is zero then bitcount is zero.
    if (this.compareTo(zero) == 0) {
      return 0;
    }
    
    //count the amount of times you have to shift left to get to the number.
    while ((count.shiftLeft(1)).compareTo(this) < 1) {
      //for numbers that are the same.
      if ((count.shiftLeft(1)).compareTo(this) == 0) {
        bitLength++;
        return bitLength + 1;
      }
      count = count.shiftLeft(1);
      bitLength++;
    }
    return bitLength + 1;
  }
 
  /**
   * Slow algorithm to find remainder when dividing two numbers.
   * It works by subtracting until we reach zero, once it can no longer
   * pass zero, it will count the remaining numbers and return the
   * remainder.
   * 
   * @param secondBig the divisor which will divide the divendend.
   * @return the answer as a BigMath number.
   */
  public BigMath modSlow(BigMath secondBig) {
    BigMath first = this;
    BigMath second = secondBig;
    BigMath oldSecond = secondBig;
    BigMath result = new BigMath("0");
    BigMath newBig = new BigMath("0");
    
    while (second.compareTo(first) < 0) {
      
      if ((second.add(oldSecond)).compareTo(first) == 1) {
        //System.out.println("subtract: " + first.subtract(newBig));
        return first.subtract(newBig);
      }
      
      //System.out.println("\nbefore: " + second.toString());
      second = second.add(oldSecond);
      //System.out.println("after: " + second.toString());
      newBig = second;
    }
    
    result = first.subtract(newBig);
    return result;
  }
  
  /**
   * Another slow algorithm to calculate modulo. This method just
   * passes the arrays of the objects into remainder() to make it
   * easier for testing purposes.
   * 
   * @param secondBig the divisor.
   * @return the remainder.
   */
  public BigMath modSlow2(BigMath secondBig) {
    return remainder(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Uses the simple equation ((remainder = a - (a/b)*b)) to calculate
   * the remainder.
   *  
   * @param dividend the number that will be divided by the divisor.
   * @param divisor the number used to divide the 
   * @return the remainder once calculated.
   */
  public BigMath remainder(int[] dividend, int[] divisor) {
    //remainder =  a - (a/b)b
    BigMath firstBig = new BigMath(dividend);
    BigMath secondBig = new BigMath(divisor);
    BigMath remainder = firstBig.subtract((firstBig.divideBy(secondBig)).multiply(secondBig));

    return remainder;
  }
  
  /**
   * This is a slow division algorithm. This method just passes on the
   * BigMath number arrays into divideSlowArray() to make it easier for testing
   * purposes.
   * 
   * @param secondBig the divisor.
   * @return the quotient without remainders.
   */
  public BigMath divideSlow(BigMath secondBig) {
    if (secondBig.compareTo(new BigMath("0")) == 0) {
      return new BigMath("0");
    }
    return divideSlowArray(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Slow division algorthim that works by subtracting the divisor from the
   * dividend until we reach zero while counting each time we subtract and
   * recording it as the quotient.
   * 
   * @param a the dividend that will be subtracted to zero.
   * @param b the divisior that is used to bring the dividend to zero.
   * @return the quotient after counting how many times subtracting was done.
   */
  public BigMath divideSlowArray(int[] a, int[] b) {
    if (a.length == 1 && a[0] == 0) {
      return new BigMath("0");
    }
    int quotient = 0;
    BigMath abig = new BigMath(a);
    BigMath bbig = new BigMath(b);
    
    //repeat until abig is smaller than 1
    //count the amount of subtractions done in quotient.
    while (abig.compareTo(new BigMath("0")) > 0) {
      abig = abig.subtract(bbig);
      quotient++;

      if (abig.compareTo(new BigMath("0")) == 0) {
        quotient++;
      }
    }
    return new BigMath(Integer.toString(quotient - 1));
  }
  
  /**
   * Main mod algorithm that works using long-division. This method
   * just passes the BigMath arrays to divideByArray() to make it
   * easier for testing purposes.
   * 
   * @param secondBig the divisor used in the calculation.
   * @return the remainder as a BigMath number.
   */
  public BigMath mod(BigMath secondBig) {
    if (this.compareTo(secondBig) == -1) {
      return this;
    } else if (this.compareTo(secondBig) == 0) {
      return new BigMath("0");
    }
    
    return modArray(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Divides two numbers using long-division and returns the remainder
   * as a BigMath number. It works by breaking the dividend into smaller
   * numbers and dividing them by the divisor. This makes computational
   * calculations faster.
   * 
   * @param dividend the number that will be divided by the divisor.
   * @param divisor the number that divides the dividend.
   * @return
   */
  public BigMath modArray(int[] dividend, int[] divisor) {
    //using the length of the divisor, get the same length of numbers
    //from the dividend and call it firstBig. The rest of dividend will
    //be used later.
    int[] firstPerm = Arrays.copyOfRange(dividend, 0, divisor.length);
    BigMath firstBig = new BigMath(firstPerm);
    BigMath secondBig = new BigMath(divisor);
    BigMath remainder;
    BigMath result;
    String quotient = "";
    
    //if firstBig is greater than the divisor, subtract the divisor from
    //firstBig to get the intial remainder, then divide firstBig by the
    //divisor to get the intial quotient.
    if (firstBig.compareTo(secondBig) > -1) {
      remainder = firstBig.subtract(secondBig);
      result = firstBig.divideSlow(secondBig);
      quotient = result.toRawString();
    } else {
      remainder = firstBig;
    }
    BigMath remainder2;
    
    //Now using the rest of the numbers from the dividend.
    for (int i = divisor.length; i < dividend.length; i++) {
      //Bring down the next number in the dividend that hasn't be used
      //yet and concatenate it onto the remainder.
      if (remainder.compareTo(new BigMath("0")) == 0) {
        remainder = new BigMath(Integer.toString(dividend[i]));
      } else {
        remainder = new BigMath(remainder.toRawString() + Integer.toString(dividend[i]));
      }
      //Then divide the new remainder by the divisor to get the
      //quotient ((quo)) and add it to the quotient string ((quotient)).
      String quo = remainder.divideSlow(secondBig).toRawString();
      BigMath mul = new BigMath(quo);
      quotient = quotient + (quo);
      
      //Using the calculated quotient ((quo)), multiply it with the
      //divisor to get the next remainder value, then subtract it
      //from the previous remainder value.
      remainder2 = mul.multiply(secondBig);
      remainder = remainder.subtract(remainder2);
    }
    
    return remainder;
  }
  
  /**
   * Returns "this" divided by another BigMath number.
   * 
   * @param secondBig the divisor.
   * @return the result as a new BigMath number.
   */
  public BigMath divideBy(BigMath secondBig) {
    if (this.digits[0] == 0 && this.digits.length == 1) {
      return new BigMath("0");
    }
    return divideByArray(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Performs long division using two arrays.
   * 
   * @param dividend the dividend.
   * @param divisor the divisor.
   * @return the result as a new BigMath number.
   */
  public BigMath divideByArray(int[] dividend, int[] divisor) {
    //using the length of the divisor, get the same length of numbers
    //from the dividend and call it firstBig. The rest of dividend will
    //be used later.
    int[] firstPerm = Arrays.copyOfRange(dividend, 0, divisor.length);
    BigMath firstBig = new BigMath(firstPerm);
    BigMath secondBig = new BigMath(divisor);
    BigMath remainder;
    BigMath result;
    String quotient = "";
    
    //if firstBig is greater than the divisor, subtract the divisor from
    //firstBig to get the intial remainder, then divide firstBig by the
    //divisor to get the intial quotient.
    if (firstBig.compareTo(secondBig) > -1) {
      remainder = firstBig.subtract(secondBig);
      result = firstBig.divideSlow(secondBig);
      quotient = result.toRawString();
    } else {
      remainder = firstBig;
    }
    BigMath remainder2;
    
    //Now using the rest of the numbers from the dividend.
    for (int i = divisor.length; i < dividend.length; i++) {
      //Bring down the next number in the dividend that hasn't be used
      //yet and concatenate it onto the remainder.
      if (remainder.compareTo(new BigMath("0")) == 0) {
        remainder = new BigMath(Integer.toString(dividend[i]));
      } else {
        remainder = new BigMath(remainder.toRawString() + Integer.toString(dividend[i]));
      }
      //Then divide the new remainder by the divisor to get the
      //quotient ((quo)) and add it to the quotient string ((quotient)).
      String quo;
      if (remainder.digits.length > secondBig.digits.length) {
        quo = remainder.divideSlow(secondBig).toRawString();
      } else {
        quo = remainder.divideBy(secondBig).toRawString();
      }
      
      BigMath mul = new BigMath(quo);
      quotient = quotient + (quo);
      
      //Using the calculated quotient ((quo)), multiply it with the
      //divisor to get the next remainder value, then subtract it
      //from the previous remainder value.
      remainder2 = mul.multiply(secondBig);
      remainder = remainder.subtract(remainder2);
    }
    
    return new BigMath(quotient);
  }
  
  /**
   * Another mod algorithm used to calculate "this" mod another
   * BigMath number.
   * 
   * @param secondBig the divisor.
   * @return the result as a new BigMath number.
   */
  public BigMath modFast(BigMath secondBig) {
    BigMath zero = new BigMath("0");
    int compare = secondBig.compareTo(this);
    if (this.compareTo(zero) == 0 || secondBig.compareTo(zero) == 0) {
      return zero;
    } else if (compare >= 0) {
      return zero;
    }
  
    return modFastArrays(this.digits.clone(), secondBig.digits.clone());
  }
  
  /**
   * Performs a logical modulo using long division.
   * 
   * @param dividend the dividend as an integer array.
   * @param divisor the divisor as an integer array.
   * @return the result as a new BigMath number.
   */
  public BigMath modFastArrays(int[] dividend, int[] divisor) {
    int dividendLen = dividend.length;
    int n = divisor.length;
    int[] quotient = new int[1];
    int[] firstPerm = Arrays.copyOfRange(dividend, 0, n);
    int[] firstremainder = new int[dividendLen];
    int[] remainder;
    int[] remainder2;
    boolean y = false;
    
    if (compareToArray(firstPerm, divisor) == 1) {
      firstremainder = subtractArrays(firstPerm, divisor);
    } else {
      firstremainder = Arrays.copyOfRange(dividend, 0, n + 1);
      y = true;
    }
    remainder = new int[firstremainder.length];
    
    for (int i = n; i < dividendLen; i++) {
      int[] remainderCarry = new int[remainder.length + 1];
      
      if (y == false) {
        for (int j = 0; j < remainderCarry.length; j++) {
          if (j == remainderCarry.length - 1) {
            remainderCarry[j] = dividend[i];
          
          } else if (i == n) {
            remainderCarry[j] = firstremainder[j];
          
          } else {
            remainderCarry[j] = remainder[j];
          }
        }
        
        remainder = remainderCarry.clone();
      } else {
        remainder = firstremainder.clone();
        y = false;
      }
      
      if (compareToArray(remainder, divisor) == 1) {
        quotient = divideByArray(remainder, divisor).digits.clone();
        remainder2 = multiplyNorm(quotient, divisor);
        remainder = subtractArrays(remainder, remainder2);
      }
    }
    
    return new BigMath(remainder);
  }
  
  /**
   * Uses BigInteger's mod algorithm to find the answer of a modulo.
   * 
   * @param secondBig the dividend.
   * @return the answer as a new BigMath number.
   */
  public BigMath modBigInt(BigMath secondBig) {
    BigInteger a = new BigInteger(this.toRawString());
    BigInteger b = new BigInteger(secondBig.toRawString());
    BigMath result = new BigMath(a.mod(b).toString());
    
    return result;
  }
  
  /**
   * Modulo algorithm to calculate a BigMath number "mod"
   * another BigMath number. Based on the Knuth's Algorithm D
   * in "The Art Of Computer Programming Volume 2".
   *  
   * @param u the integer array that represents the dividend.
   * @param v the integer array that represents the divisor.
   * @return the result as a new BigMath number.
   */
  public BigMath knuthMod(int[] u, int[] v) {
    int quoHat = 0;
    int[] quotient = new int[u.length - 1];
    //D1: normalise u and v.
    int d = 10 / (v[0] + 1);
    if (d != 1) {
      int[] divArray = {d};
      u = multiplyNorm(u, divArray);
      v = multiplyNorm(v, divArray);
    } else {
      int[] newU = new int[u.length + 1];
      newU[0] = 0;
      
      for (int i = 0; i < u.length; i++) {
        newU[i + 1] = u[i];
      }
      u = newU.clone();
    }
    
    //D2: set the quotient-hat.
    for (int j = 0; j < u.length - 2; j++) {
      int n = v.length - 1;
      //D3: calculate the guess digit.
      if (u[j] == v[0]) {
        quoHat = 9;
      } else {
        quoHat = (((u[j] * 10) + u[j + 1]) / v[0]);
      }
      
      while (v[1] * quoHat > (((((u[j] * 10) + u[j + 1]) - (quoHat * v[0])) * 10) + u[j + 2])) {
        quoHat = quoHat - 1;
      }
      
      //D4: multiply quotient-hat by divisor and subtract answer from dividend.
      int[] quoHatArray = {quoHat};
      u = subtractArrays(u, (multiplyNorm(quoHatArray, v)));
      
      if (isNegative(u)) {
        u = new BigMath(u).add(new BigMath("1000")).digits.clone();
      }
      
      //D5: check if there is a borrow.
      quotient[j] = quoHat;
      if (quoHat < 0) {
        //D6: normalise if there is a negative.
        quotient[j] = quotient[j] - 1;
        u = new BigMath(v).add(new BigMath(u)).digits.clone();
      }
      
      if (quotient[j] > 9) {
        quotient[j - 1] = quotient[j - 1] + 1;
        quotient[j] = quotient[j] - 11;
      }
    }
    return new BigMath(quotient);
  }
  
  /**
   * Calculates BASE (which is 10 in BigMath) to the power of a number.
   * 
   * @param base the base of the power.
   * @param power the exponent.
   * @return the result as an integer array.
   */ 
  public int[] basePow(int base, int power) {
    int[] result = new int[power + 1];
    result[0] = 1;
    for (int i = 1; i < result.length; i++) {
      result[i] = 0;
    }
    return result;
  }

  /**
   * Returns BigMath number as a string. This was needed because the
   * other toString() method returns "BigMath" infront of the number.
   * This one just returns the raw number as a string.
   * 
   * @return the BigMath number as a string.
   */
  public String toRawString() {
    String rawString = "";
    if (this.digits.length == 0) {
      return "";
    }
    
    for (int i = 0; i < this.digits.length;  i++) {
      rawString = rawString + this.digits[i];
    }
    return rawString;
  }
  
  /**
   * Turns BigMath to Java integer.
   * 
   * @return the BigMath number as an integer.
   */
  public int intValue() {
    if (this.digits.length > 9) {
      return -2;
    }
    
    String intValue = "";
    for (int i = 0; i < this.digits.length; i++) {
      intValue = intValue + this.digits[i];
    }
    return Integer.parseInt(intValue);
  }
  
  /**
   * Converts "this" to an integer array.
   * 
   * @return the integer array.
   */
  public int[] toArray() {
    return digits.clone();
  }
  
  /**
   * Checks if an array number is negative.
   * 
   * @param a the array being tested.
   * @return a boolean value depending on a being negative
   *     or positive.
   */
  public boolean isNegative(int[] a) {
    if (a[0] < 0) {
      return true;
    }
    return false;
  }
}
