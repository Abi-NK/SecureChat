package des;

public class KeyGenDES {
  
  private String[] mainKey = new String[16];
  
  /**
   * The constructor used to set up the DES
   * key in this class.
   * 
   * @param key the DES key represented as a HEX string.
   */
  public KeyGenDES(String key) {
    this.mainKey = key.split("(?!^)");
  }
  
  /**
   * Generates the key as a 64bit binary value.
   * 
   * @return the 64bit binary key.
   */
  public String generateKey() {
    
    String[] K = this.mainKey.clone();
    String[] binK = new String[K.length];
    int iInt;
    
    for (int i = 0; i < K.length; i++) {
      iInt = Integer.parseInt(K[i], 16);
      binK[i] = Integer.toBinaryString(iInt);

      while (binK[i].length() < 4) {
        binK[i] = "0" + binK[i];
      }
    }
    
    String binKStr = String.join("", binK);
    //System.out.println("binKStr: " + binKStr);
    return binKStr;
  }
  
  /**
   * Generates the permuted key K+ using the permutation
   * table "pc1".
   * 
   * @return the 56bit permuted K+.
   */
  public String generateKeyPlus() {
    
    String binKey = generateKey();
    String[] binKeySplit = binKey.split("(?!^)");
    String[] binKeyPlus = new String[56];
    
    int[] pc1 = {57, 49, 41, 33, 25, 17, 9,
                  1, 58, 50, 42, 34, 26, 18,
                 10, 2, 59, 51, 43, 35, 27,
                 19, 11, 3, 60, 52, 44, 36,
                 63, 55, 47, 39, 31, 23, 15,
                 7, 62, 54, 46, 38, 30, 22,
                 14, 6, 61, 53, 45, 37, 29,
                 21, 13, 5, 28, 20, 12, 4};
                 
    for (int i = 0; i < pc1.length; i++) {
      binKeyPlus[i] = binKeySplit[pc1[i] - 1];
      //System.out.print(binKeyPlus[i]);
    }
    
    String binKStr = String.join("", binKeyPlus);
    //System.out.println("binKStr: " + binKStr);
    return binKStr;
  }
  
  /**
   * Generates the subkeys using K+ by applying the permutation table
   * to each pair of CnDn where n is 1 to 16.
   * 
   * @return the set of subKeys as a String array.
   */
  public String[] generateSubKeys() {
    
    String binKeyPlus = generateKeyPlus();
    String c0 = binKeyPlus.substring(0, binKeyPlus.length() / 2);
    String d0 = binKeyPlus.substring(binKeyPlus.length() / 2);
    
    String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12,
            c13, c14, c15, c16;
    
    String d1, d2, d3, d4, d5, d6, d7, d8 ,d9, d10, d11, d12,
            d13, d14 ,d15 ,d16;
            
    int[] pc2 = {14, 17, 11, 24, 1, 5,
                 3, 28, 15, 6, 21, 10,
                 23, 19, 12, 4, 26, 8,
                 16, 7, 27, 20, 13, 2,
                 41, 52, 31, 37, 47, 55,
                 30, 40, 51, 45, 33, 48,
                 44, 49, 39, 56, 34, 53,
                 46, 42, 50, 36, 29, 32};
    
    c1 = c0.substring(1) + c0.substring(0, 1);
    d1 = d0.substring(1) + d0.substring(0, 1);
    
    c2 = c1.substring(1) + c1.substring(0, 1);
    d2 = d1.substring(1) + d1.substring(0, 1);
    
    c3 = c2.substring(2) + c2.substring(0, 2);
    d3 = d2.substring(2) + d2.substring(0, 2);
    
    c4 = c3.substring(2) + c3.substring(0, 2);
    d4 = d3.substring(2) + d3.substring(0, 2);
    
    c5 = c4.substring(2) + c4.substring(0, 2);
    d5 = d4.substring(2) + d4.substring(0, 2);
    
    c6 = c5.substring(2) + c5.substring(0, 2);
    d6 = d5.substring(2) + d5.substring(0, 2);
    
    c7 = c6.substring(2) + c6.substring(0, 2);
    d7 = d6.substring(2) + d6.substring(0, 2);
    
    c8 = c7.substring(2) + c7.substring(0, 2);
    d8 = d7.substring(2) + d7.substring(0, 2);
    
    c9 = c8.substring(1) + c8.substring(0, 1);
    d9 = d8.substring(1) + d8.substring(0, 1);
    
    c10 = c9.substring(2) + c9.substring(0, 2);
    d10 = d9.substring(2) + d9.substring(0, 2);
    
    c11 = c10.substring(2) + c10.substring(0, 2);
    d11 = d10.substring(2) + d10.substring(0, 2);
    
    c12 = c11.substring(2) + c11.substring(0, 2);
    d12 = d11.substring(2) + d11.substring(0, 2);
    
    c13 = c12.substring(2) + c12.substring(0, 2);
    d13 = d12.substring(2) + d12.substring(0, 2);
    
    c14 = c13.substring(2) + c13.substring(0, 2);
    d14 = d13.substring(2) + d13.substring(0, 2);
    
    c15 = c14.substring(2) + c14.substring(0, 2);
    d15 = d14.substring(2) + d14.substring(0, 2);
    
    c16 = c15.substring(1) + c15.substring(0, 1);
    d16 = d15.substring(1) + d15.substring(0, 1);
    
    //Start generating the sub-keys.
    String[] c = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12,
        c13, c14, c15, c16};
            
    String[] d = {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12,
        d13, d14, d15, d16};
            
    String[] subKeys = new String[16];
            
    for (int i = 0; i < c.length; i++) {
      String combination = c[i] + d[i];
      String[] combSplit = combination.split("(?!^)");
      String[] key = new String[48];
      String result = "";
      
      for (int j = 0; j < pc2.length; j++) {
        key[j] = combSplit[pc2[j] - 1];
        result = result + key[j];
      }
      subKeys[i] = result;
    }
    return subKeys;
  }
  
}
