package diffie_hellman;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;
import big_math.ModularExp;
import big_math.ModularExpBigMath;

public class DiffieHellman {
  BigMath mainG; //the primitive root of p.
  BigMath mainP; //the public modulus p.
  BigMath secretNumber = new BigMath(1024, new Random());  //the secret number.
  ModularExpBigMath mdx = new ModularExpBigMath();
  
  /**
   * Constructor used to form the Diffie-Hellman class using
   * pre-generated prime modulus and its primitive root.
   */
  public DiffieHellman() {
    this.mainG = new BigMath("2");
    this.mainP = new BigMath("179769313486231590770839156793787453197860"
            + "296048756011706444423684197180216158519368947833795864925"
            + "541502180565485980503646440548199239100050792877003355816"
            + "639229553136239076508735759914822574862575007425302077447"
            + "712589550957937778424442426617334727629299387668709205606"
            + "050270810842907692932019128194467627007");;
  }
  
  /**
   * Constructor used to test if Diffie-Hellman works with smaller numbers.
   * 
   * @param g the primitive root of p.
   * @param p the prime modulus.
   */
  public DiffieHellman(BigMath g, BigMath p) {
    this.mainG = g;
    this.mainP = p;
    this.secretNumber = new BigMath("15");
  }
  
  /**
   * The first step of Diffie-Hellman calculates
   * g^(secretnumber) mod p using modular exponentiation.
   * 
   * @return the result once calcualted.
   */
  public BigMath stepOne() {
    return mdx.modPower(mainG, secretNumber, mainP);
  }
  
  /**
   * Calculates step two of Diffie-Hellman by doing
   * gp^(secretNumber) mod p, it does this using the
   * modulare exponentiation algorithm.
   *  
   * @param gp the result of step-one.
   * @return the shared secret key as hexadecimal once calculated.
   */
  public String stepTwo(BigMath gp) {
    //BigInteger is used to convert the key from decimal to hexadecimal.
    return ((new BigInteger(mdx.modPower(gp, secretNumber,
            mainP).toRawString()).toString(16))).substring(0, 16);
  }
  
  /**
   * Calculates step two of Diffie-Hellman by doing
   * gp^(secretNumber) mod p, it does this using the
   * modulare exponentiation algorithm.
   * 
   * @param gp the result from step-one.
   * @return the shared secret key as a BigMath number.
   */
  public BigMath stepTwoTest(BigMath gp) {
    return (mdx.modPower(gp, secretNumber, mainP));
  }

}
