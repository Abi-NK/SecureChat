import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big_math.BigMath;

class BigMathTest {

  @BeforeEach
  void setUp() throws Exception {
    BigMath bigMath = new BigMath("123");
  }
  
  @Test //Test 1
  void testBigMathContructor() {
    //Setup test
    BigMath d = new BigMath("135066410865995223349603216278805969938881475605667027524485"
            + "14385152651060"
            + "48595338339402871505719094417982072821644715513736804197039641917430464965"
            + "89274256239341020864383202110372958725762358509643110564073501508187510676"
            + "59462920556368552947521350085287941637732853390610975054433499981115005697"
            + "7236890927563");
  }
  
  @Test
  void testAdd() {
    
    BigMath first = new BigMath("11");
    BigMath second = new BigMath("22");
    BigMath result = new BigMath("33");
    assertEquals("Add two  numbers", result.toString(), (first.add(second)).toString());
    
    BigMath first1 = new BigMath("9");
    BigMath second1 = new BigMath("2");
    BigMath result1 = new BigMath("11");
    assertEquals("Add two  numbers", result1.toString(), (first1.add(second1)).toString());
    
    BigMath first2 = new BigMath("99");
    BigMath second2 = new BigMath("1282");
    BigMath result2 = new BigMath("1381");
    assertEquals("Add two  numbers", result2.toString(), (first2.add(second2)).toString());
    
    BigMath first3 = new BigMath("1282");
    BigMath second3 = new BigMath("99");
    BigMath result3 = new BigMath("1381");
    assertEquals("Add two  numbers", result3.toString(), (first3.add(second3)).toString());
    
    BigMath first4 = new BigMath("99999999999999999999999");
    BigMath second4 = new BigMath("99999999999999999999999999999999999999");
    BigMath result4 = new BigMath("100000000000000099999999999999999999998");
    assertEquals("Add two  numbers", result4.toString(), (first4.add(second4)).toString());


    BigMath one = new BigMath("55324234234234234444444234234233333333333333333333333333"
        + "3333333444444444444444444444444444444444443423424131242342498273942798462");
    BigMath two = new BigMath("69902834092384092384092834027498233333333333333333333333"
        + "3333333333333333333333333333333333333333333333333333742938749238749798327");
    BigMath resulta = new BigMath("1252270683266183268285370682617315666666666666666666"
        + "666666666666777777777777777777777777777777777776756757464985281247512692596789");

    assertEquals("Add two large numbers", resulta.toString(), (one.add(two)).toString());

    
    BigMath one2 = new BigMath("5532423423423423444444423423424131242342498273942798462");
    BigMath two2 = new BigMath("6990283409238409238409283402749823742938749238749798327");
    BigMath resultb = new BigMath("12522706832661832682853706826173954985281247512692596789");
    
    assertEquals("Add two large numbers", resultb.toString(), (one2.add(two2)).toString());
    
    //Test with a 1024 bit number.
    BigMath one3 = new BigMath("5532423423423423444444423423423333333333333333333333333333"
        + "333334444444444444444444444444444444444434234241312423424982739427984625532"
        + "423423423423444444423423423333333333333333333333333333333334444444444444444"
        + "444444444444444444434234241312423424982739427984621111111111111111111111111"
        + "11111111111111111111111111111");
    BigMath two3 = new BigMath("699028340923840923840928340274982333333333333333333333"
        + "33333333333333333333333333333333333333333333333333333374293874923874979"
        + "83275532423423423423444444423423423333333333333333333333333333333334444"
        + "44444444444444444444444444444443423424131242342498273942798462111111111"
        + "111111111111111111111111111111111111111111111");
    BigMath resultc = new BigMath("125227068326618326828537068261731566666666666666"
        + "66666666666666667777777777777777777777777777777777767567574649852812"
        + "47512692596790106484684684684688888884684684666666666666666666666666"
        + "66666666688888888888888888888888888888888888684684826248468499654788"
        + "5596924222222222222222222222222222222222222222222222222222222");
     
    assertEquals("Add two large numbers", resultc.toString(), (one3.add(two3)).toString());
    
    BigMath one4 = new BigMath("12");
    BigMath two4 = new BigMath("3");
    BigMath resultd = new BigMath("15");
    
    assertEquals("Add two large numbers", resultd.toString(), (one4.add(two4)).toString());
  }
  
  @Test //Test 3
  void testBigSubtract() {
    BigMath one = new BigMath("55555555555555555555555555555555555555555555555555555555");
    BigMath two = new BigMath("16111111111111111111111111111111111111111111111111111111");
    BigMath result = new BigMath("39444444444444444444444444444444444444444444444444444444");
    
    assertEquals("Subtract two numbers", result.toString(), (one.subtract(two)).toString());
    
    BigMath one1 = new BigMath("10");
    BigMath two1 = new BigMath("10");
    BigMath result1 = new BigMath("0");
    
    assertEquals("Subtract two numbers", result1.toString(), (one1.subtract(two1)).toString());
    
    BigMath one2 = new BigMath("100");
    BigMath two2 = new BigMath("99");
    BigMath result2 = new BigMath("1");
    
    assertEquals("Subtract two numbers", result2.toString(), (one2.subtract(two2)).toString());
    
    BigMath one3 = new BigMath("21");
    BigMath two3 = new BigMath("121");
    int[] negative = {-1, 0, 0};
    BigMath result3 = new BigMath(negative);
    
    assertEquals("Subtract two numbers", result3.toString(), (one3.subtract(two3)).toString());
  }
  
  @Test //Test 4
  void testSubtractDifferentLength() {
    BigMath one = new BigMath("5555");
    BigMath two = new BigMath("161");
    BigMath result = new BigMath("5394");
    
    
    assertEquals("Subtract two numbers of different lengths: ",
        result.toString(), (one.subtract(two)).toString());
    one = one.subtract(two);
    
    BigMath one1 = new BigMath("55555555555555555555");
    BigMath two1 = new BigMath("16112312312312");
    BigMath result1 = new BigMath("55555539443243243243");
    
    assertEquals("Subtract two numbers of different lengths: ",
        result1.toString(), (one1.subtract(two1)).toString());
    
    BigMath one2 = new BigMath("45655616516565465");
    BigMath two2 = new BigMath("89716516548");
    BigMath result2 = new BigMath("45655526800048917");
    
    assertEquals("Subtract two numbers of different lengths: ",
        result2.toString(), (one2.subtract(two2)).toString());
  }
  
  @Test //Test 5
  void testMakeSameLength() {
    BigMath one = new BigMath("123");
    BigMath two = new BigMath("123453423424204923874983274982749827349823749");
    BigMath result = new BigMath("000000000000000000000000000000000000000000123");
    
    assertEquals("pad zeroes onto firt number", result.toString(),
        (one.makeSameLength(two)).toString());
    
    BigMath one1 = new BigMath("12345");
    BigMath two1 = new BigMath("123");
    BigMath result1 = new BigMath("00123");
    
    assertEquals("pad zeroes onto firt number",
        result1.toString(), (two1.makeSameLength(one1)).toString());
  }
  
  @Test //Test 6
  void testIsEven() {

    BigMath even = new BigMath("12345678");
    assertEquals("Subtract two numbers", true, even.isEven());

    BigMath odd = new BigMath("12345673");
    assertEquals("Subtract two numbers", false, odd.isEven());
  }
  
  @Test //Test 7
  void testShiftRight() {
    BigMath test1 = new BigMath("1254");
    BigMath result1 = new BigMath("627");
    
    assertEquals("shiftright the number 1254", result1.toString(), test1.shiftRight(1).toString());
    //System.out.println("test1 vs result1 after: " + test1.toString() + " " + result1.toString());
    
    BigMath test2 = new BigMath("19");
    BigMath result2 = new BigMath("9");
    
    assertEquals("shiftright the number 19", result2.toString(), test2.shiftRight(1).toString());
    
    BigMath test3 = new BigMath("99999");
    BigMath result3 = new BigMath("49999");
    
    assertEquals("shiftright the number 99999", result3.toString(), test3.shiftRight(1).toString());
    
    BigMath test4 = new BigMath("1111");
    BigMath result4 = new BigMath("555");
    
    assertEquals("shiftright the number 1111", result4.toString(), test4.shiftRight(1).toString());
    
    //Test shifting twice.
    BigMath test5 = new BigMath("15");
    BigMath result5 = new BigMath("3");
    
    assertEquals("shiftright the number 15 twice",
        result5.toString(), test5.shiftRight(2).toString());
    
    //Test shifting a very large number and shift it 12 times.
    BigMath test6 = new BigMath("11111111111111111111111");
    BigMath result6 = new BigMath("2712673611111111111");
    
    assertEquals("shiftRight a large number 12 times",
        result6.toString(), test6.shiftRight(12).toString());
    
    BigMath test7 = new BigMath("0");
    BigMath result7 = new BigMath("0");
    
    assertEquals("shiftRight a large number 12 times",
        result7.toString(), test7.shiftRight(0).toString());
    
  }
  
  @Test //Test 8
  void testShiftLeft() {

    BigMath test1 = new BigMath("123");
    BigMath result1 = new BigMath("246");
    
    //System.out.println("test1 vs result1 before: " + test1.toString() + " " + result1.toString());
    assertEquals("shiftLeft the number 123", result1.toString(), test1.shiftLeft(1).toString());
    //System.out.println("test1 vs result1 after: " + test1.toString() + " " + result1.toString());
    
    BigMath test2 = new BigMath("27");
    BigMath result2 = new BigMath("54");
    
    assertEquals("shiftLeft the number 27", result2.toString(), test2.shiftLeft(1).toString());
    
    //Test a number where there are carries in the calculation.
    BigMath test3 = new BigMath("97");
    BigMath result3 = new BigMath("194");

    assertEquals("shiftLeft the number 97", result3.toString(), test3.shiftLeft(1).toString());
    
    //Test on large number. 
    BigMath test4 = new BigMath("999999999999");
    BigMath result4 = new BigMath("1999999999998");
    
    assertEquals("shiftLeft the number 999999999999",
        result4.toString(), test4.shiftLeft(1).toString());
    
    //Test on multiple shiftLeft
    BigMath test5 = new BigMath("56416684843531849");
    BigMath result5 = new BigMath("451333478748254792");
    
    assertEquals("shiftLeft the number 99999999999 100 times",
        result5.toString(), test5.shiftLeft(3).toString());
    
    //test on a very large number
    BigMath test6 = new BigMath("9999999999999999999999999999999999999999999999999999999"
        + "9999999999999999999999999999999999999999999999999999999999999999999999999"
        + "9999999999999999999999");
    BigMath result6 = new BigMath("126765060022822940149670320537599999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999999"
        + "9999999999999999999998732349399771770598503296794624");
    
    assertEquals("shiftLeft the number 100 times",
        result6.toString(), test6.shiftLeft(100).toString());
  }
  
  @Test //Test 9
  void testBitLength() {
    
    BigMath test1 = new BigMath("9");
    assertEquals("get bitlength of 1", 4, test1.bitLength());
    
    BigMath test2 = new BigMath("12344555");
    assertEquals("get bitlength of 12344555", 24, test2.bitLength());
    
    BigMath test3 = new BigMath("128");
    assertEquals("get bitlength of 128", 8, test3.bitLength());
    
    BigMath test4 = new BigMath("14");
    assertEquals("get bitlength of 14", 4, test4.bitLength());
    
    BigMath test5 = new BigMath("1232131231231343253465475567342324234322445455875685685686435436");
    assertEquals("get bitlength of large number", 210, test5.bitLength());
    
    BigMath test6 = new BigMath("1");
    assertEquals("get bitlength of 1", 1, test6.bitLength());
    
    BigMath test7 = new BigMath("0");
    assertEquals("get bitlength of 0", 0, test7.bitLength());
  }
  
  @Test //Test10
  void testCompareTo() {
    
    BigMath first = new BigMath("64");
    BigMath second = new BigMath("128");
    assertEquals("test that one is smaller than 22", -1, first.compareTo(second));
    
    BigMath second2 = new BigMath("2");
    assertEquals("test that one is smaller than 2", 1, first.compareTo(second2));
    
    
    BigMath first3 = new BigMath("99999");
    BigMath second3 = new BigMath("99998");
    assertEquals("test first number is larger than second", 1, first3.compareTo(second3));
    
    BigMath first4 = new BigMath("123456789");
    BigMath second4 = new BigMath("123456789");
    assertEquals("test first number is same as second", 0, first4.compareTo(second4));
  }
  
  @Test
  void testMultiply() {
    
    BigMath one = new BigMath("12");
    BigMath two = new BigMath("10");
    BigMath result = new BigMath("120");
    
    assertEquals("multiply two numbers", result.toString(), (one.multiplySimple(two)).toString());
    
    BigMath one1 = new BigMath("50009");
    BigMath two1 = new BigMath("1000");
    BigMath result1 = new BigMath("50009000");
    
    assertEquals("multiply two numbers", result1.toString(),
        (one1.multiplySimple(two1)).toString());
    
    BigMath one2 = new BigMath("999999999");
    BigMath two2 = new BigMath("999");
    BigMath result2 = new BigMath("998999999001");
    
    assertEquals("multiply two numbers", result2.toString(),
        (one2.multiplySimple(two2)).toString());
    
    //stress test
    BigMath one3 = new BigMath("3786360491481466345690815408587416698562760765790070916725762416"
        + "419695398644344371191458457145396161707340121543311007245694568550109106270002547"
        + "023343361893811146210502357407148196060763924359448977023164516987780775793310729"
        + "945650336071819989690584392532934160287043646271926314735003510307832999669556720"
        + "200987090589325665021818236471772605739516259059729523832401590748498543250217850"
        + "294003612012317003034083988811061974324767550579565603675060033129692661761165529"
        + "011148632438754129461102352440802319892402038510993517478387054704093020461460935"
        + "539340517877086138137308034970489005325401411980430919299989247768041370671943945"
        + "063050048847822358076516923847935256732043872234032263482756319815544760862876912"
        + "504903093662088212668973343397527807508797639477947643262551038177889015799005623"
        + "922371037908558711699127723115322624666730257918763676515605512440728393370968092"
        + "150267948742153458108727136552755708851773409376671449234706418649189058360908748"
        + "813364471548273033251661194901786855874456914899454377388108937522594236600467980"
        + "520307278028868356836579668221452079036590264746396470016909513770155530531892291"
        + "448824152338866668529129804687453131704493587872448816274460632784566242118244732"
        + "785856384605450786194173898497981311437067174462677202040841635433654091422680253"
        + "331905377174806209215157555206178416996041490672504303870776353235106241388190381"
        + "288821605675805987083362405345647576714987467828787941656883910381860087218430995"
        + "745240349299949310671913175098265164986924033597774174773203013248488893544974899"
        + "316789310461203674989501991655455844013291318651459926326958634098513223299718067"
        + "387235841032017824478569992195391686839796116269568901600009981633393951375299013"
        + "659264898304702228891995972736860272050113104422374565979121523467865866988377043"
        + "838336683883864451461290083730400428052037719438491527503546432906948298277754297"
        + "369647462799288970917678225192109292454411719373625347398066821140393469029386952"
        + "445990340147841716253225952593441870043714527437415523855093559699820847914174432"
        + "577400574937286658201734822966982503844663416534361935847897738838351884457579545"
        + "746363650430203509704041840137626382191282817812442887454688802332580307013827893"
        + "202946391291102762572660992194850880116382476330463195927295552564042661098705115"
        + "5927572");
    BigMath two3 = new BigMath("10");
    BigMath result3 = new BigMath("378636049148146634569081540858741669856276076579007091672"
        + "57624164196953986443443711914584571453961617073401215433110072456945685501091"
        + "06270002547023343361893811146210502357407148196060763924359448977023164516987"
        + "78077579331072994565033607181998969058439253293416028704364627192631473500351"
        + "03078329996695567202009870905893256650218182364717726057395162590597295238324"
        + "01590748498543250217850294003612012317003034083988811061974324767550579565603"
        + "67506003312969266176116552901114863243875412946110235244080231989240203851099"
        + "35174783870547040930204614609355393405178770861381373080349704890053254014119"
        + "80430919299989247768041370671943945063050048847822358076516923847935256732043"
        + "87223403226348275631981554476086287691250490309366208821266897334339752780750"
        + "87976394779476432625510381778890157990056239223710379085587116991277231153226"
        + "24666730257918763676515605512440728393370968092150267948742153458108727136552"
        + "75570885177340937667144923470641864918905836090874881336447154827303325166119"
        + "49017868558744569148994543773881089375225942366004679805203072780288683568365"
        + "79668221452079036590264746396470016909513770155530531892291448824152338866668"
        + "52912980468745313170449358787244881627446063278456624211824473278585638460545"
        + "07861941738984979813114370671744626772020408416354336540914226802533319053771"
        + "74806209215157555206178416996041490672504303870776353235106241388190381288821"
        + "60567580598708336240534564757671498746782878794165688391038186008721843099574"
        + "52403492999493106719131750982651649869240335977741747732030132484888935449748"
        + "99316789310461203674989501991655455844013291318651459926326958634098513223299"
        + "71806738723584103201782447856999219539168683979611626956890160000998163339395"
        + "13752990136592648983047022288919959727368602720501131044223745659791215234678"
        + "65866988377043838336683883864451461290083730400428052037719438491527503546432"
        + "90694829827775429736964746279928897091767822519210929245441171937362534739806"
        + "68211403934690293869524459903401478417162532259525934418700437145274374155238"
        + "55093559699820847914174432577400574937286658201734822966982503844663416534361"
        + "93584789773883835188445757954574636365043020350970404184013762638219128281781"
        + "24428874546888023325803070138278932029463912911027625726609921948508801163824"
        + "7633046319592729555256404266109870511559275720");
    
    assertEquals("multiply two numbers", result3.toString(), (one3.multiply(two3)).toString());
    
    int[] negative = {-1, 2};
    int[] answer = {-1, 2, 0};
    BigMath one4 = new BigMath(negative);
    BigMath two4 = new BigMath("10");
    BigMath result4 = new BigMath(answer);

  }
  
  @Test //Test 12
  void testMultiplyNorm() {
  
    BigMath one = new BigMath("123");
    BigMath two = new BigMath("53");
    BigMath result = new BigMath("6519");
    assertEquals("multiply two numbers", result.toString(), (one.multiply(two)).toString());
    
    BigMath one1 = new BigMath("100");
    BigMath two1 = new BigMath("100");
    BigMath result1 = new BigMath("10000");
    assertEquals("multiply two numbers", result1.toString(), (one1.multiply(two1)).toString());
    
    
    BigMath one2 = new BigMath("99");
    BigMath two2 = new BigMath("99");
    BigMath result2 = new BigMath("9801");
    assertEquals("multiply two numbers", result2.toString(), (one2.multiply(two2)).toString());
    
    BigMath one3 = new BigMath("0");
    BigMath two3 = new BigMath("111");
    BigMath result3 = new BigMath("0");
    assertEquals("multiply two numbers", result3.toString(), (one3.multiply(two3)).toString());
    
    
    //Multiplication stress test: I used bigInteger to be certain that result4
    //is the right answer when multiplying
    //the two large numbers.
    BigMath one4 = new BigMath("37863604914814663456908154085874166985627607657900709167257"
        + "6241641969539864434437119145845714539616170734012154331100724569456855010910"
        + "6270002547023343361893811146210502357407148196060763924359448977023164516987"
        + "7807757933107299456503360718199896905843925329341602870436462719263147350035"
        + "1030783299966955672020098709058932566502181823647177260573951625905972952383"
        + "2401590748498543250217850294003612012317003034083988811061974324767550579565"
        + "6036750600331296926617611655290111486324387541294611023524408023198924020385"
        + "1099351747838705470409302046146093553934051787708613813730803497048900532540"
        + "1411980430919299989247768041370671943945063050048847822358076516923847935256"
        + "7320438722340322634827563198155447608628769125049030936620882126689733433975"
        + "2780750879763947794764326255103817788901579900562392237103790855871169912772"
        + "3115322624666730257918763676515605512440728393370968092150267948742153458108"
        + "7271365527557088517734093766714492347064186491890583609087488133644715482730"
        + "3325166119490178685587445691489945437738810893752259423660046798052030727802"
        + "8868356836579668221452079036590264746396470016909513770155530531892291448824"
        + "1523388666685291298046874531317044935878724488162744606327845662421182447327"
        + "8585638460545078619417389849798131143706717446267720204084163543365409142268"
        + "0253331905377174806209215157555206178416996041490672504303870776353235106241"
        + "3881903812888216056758059870833624053456475767149874678287879416568839103818"
        + "6008721843099574524034929994931067191317509826516498692403359777417477320301"
        + "3248488893544974899316789310461203674989501991655455844013291318651459926326"
        + "9586340985132232997180673872358410320178244785699921953916868397961162695689"
        + "0160000998163339395137529901365926489830470222889199597273686027205011310442"
        + "2374565979121523467865866988377043838336683883864451461290083730400428052037"
        + "7194384915275035464329069482982777542973696474627992889709176782251921092924"
        + "5441171937362534739806682114039346902938695244599034014784171625322595259344"
        + "1870043714527437415523855093559699820847914174432577400574937286658201734822"
        + "9669825038446634165343619358478977388383518844575795457463636504302035097040"
        + "4184013762638219128281781244288745468880233258030701382789320294639129110276"
        + "25726609921948508801163824763304631959272955525640426610987051155927572");

    BigMath two4 = new BigMath("9999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "999999999999999999999999999999999999999999999999999999999999999999999999"
        + "9999999999999999999999999999999999999999999999999999999999999999");
    //for comparison a 1024 bit number shown below.
    BigMath lengthOf1024BitNumber = new BigMath("10000000000000000000000000000001000000"
        + "000000000000000000000000100000000000000000000000000000010000000000000000"
        + "000000000000001000000000000000000000000000000100000000000000000000000000"
        + "000010000000000000000000000000000001000000000000000000000000000000100000"
        + "000000000000000000000000010000000000000000000000000000001000000000000000"
        + "000000000000000");
    BigMath result4 = new BigMath("378636049148146634569081540858741669856276076579007091672576"
        + "241641969539864434437119145845714539616170734012154331100724"
        + "569456855010910627000254702334336189381114621050235740714819"
        + "606076392435944897702316451698778077579331072994565033607181"
        + "998969058439253293416028704364627192631473500351030783299966"
        + "955672020098709058932566502181823647177260573951625905972952"
        + "383240159074849854325021785029400361201231700303408398881106"
        + "197432476755057956560367506003312969266176116552901114863243"
        + "875412946110235244080231989240203851099351747838705470409302"
        + "046146093550147691296227147468039988088461483833977380432253"
        + "021013273162360384441668550050135113546427636839645944352263"
        + "250214665958692654853117242005629434531132724393880104279807"
        + "008801673070836570415393331773856599430806983550461793087843"
        + "251243828742402546519398322937009625728665260336151938022281"
        + "568534651890994523871852246507483550004976637743573205133197"
        + "396215841361344939346918646380220424014624902224078557878302"
        + "363166016241350841351939599119983770631456815745077049728223"
        + "248275027608043922569625450446033363765564783634214386180639"
        + "419935553981229490441476212535312143006745107198916177847587"
        + "579056764882251149369539476840256774119333393574162976650920"
        + "509121536697143362685345977564098880223961126452175443221286"
        + "630860506048605937120521564374083093114006957881139894056341"
        + "598029072541371278629699588253100910479417183032844465276083"
        + "669510276645993994983141270438065772317422946035740773100946"
        + "116415307793442175358339544100449845049457696941128228418959"
        + "392576035524882949216898427269851218580643075742250646130023"
        + "941022927623398396095395429331015051942938381887004434980330"
        + "368820408954792772213962702113186734629469105871890570985191"
        + "034697397360025782769757495103685813578388736849809452890355"
        + "793907614000730314232253597639494489888463834691941198577365"
        + "603162930308415498658931963037362924450600623527749588724722"
        + "718674771781028538424941570395584127428498418491732750525297"
        + "262889257515671609438524048607607791515399062714568833359692"
        + "298290924974236844442117493947068144318630400655186558263100"
        + "476209693138640187704691244798984518399135296750899646863051"
        + "364895126390892695111178292801323147145775522169162165870794"
        + "860350771495816069136914884570426303103215519110038237651412"
        + "925641354256242745001211565809330383724027263139727949886895"
        + "577625434020878476532134133011622956161663316116135548538709"
        + "916269599571947962280561508472496453567093051701722245702630"
        + "352537200711029082321774807890707545588280626374652601933178"
        + "859606530970613047554009659852158283746774047406558129956285"
        + "472562584476144906440300179152085825567422599425062713341798"
        + "265177033017496155336583465638064152102261161648115542420454"
        + "253636349569796490295958159862373617808717182187557112545311"
        + "197667419692986172106797053608708897237427339007805149119883"
        + "6175236695368040727044474359573389012948844072428");
    assertEquals("multiply two numbers", result4.toString(), (one4.multiply(two4)).toString());
  }
  
  @Test //Test 13
  void testModuloSlow() {
    
    BigMath one = new BigMath("15");
    BigMath two = new BigMath("6");
    BigMath result = new BigMath("3");
    assertEquals("mod two numbers", result.toString(), (one.modSlow(two)).toString());
    
    BigMath one1 = new BigMath("20");
    BigMath two1 = new BigMath("5");
    BigMath result1 = new BigMath("0");
    assertEquals("mod two numbers", result1.toString(), (one1.modSlow(two1)).toString());
    
    BigMath one2 = new BigMath("100");
    BigMath two2 = new BigMath("23");
    BigMath result2 = new BigMath("8");
    assertEquals("mod two numbers", result2.toString(), (one2.modSlow(two2)).toString());
    
    BigMath one3 = new BigMath("1000");
    BigMath two3 = new BigMath("82");
    BigMath result3 = new BigMath("16");
    assertEquals("mod two numbers", result3.toString(), (one3.modSlow(two3)).toString());
    
    BigMath one4 = new BigMath("455246697");
    BigMath two4 = new BigMath("234");
    BigMath result4 = new BigMath("165");
  }
  
  @Test //Test 14
  void testRemainder() {
    int[] a = {2, 6, 5, 4, 2, 0};
    int[] b = {2, 7, 6};
    BigMath remainder = new BigMath("272");
  }
  
  @Test //Test 15
  void testDivideSlowArray() {
    int[] a = {8, 9};
    int[] b = {2, 7, 6};
    BigMath quotient = new BigMath("0");
    
    assertEquals("division test",
        quotient.toString(), (quotient.divideSlowArray(a, b)).toString());
            
    int[] a1 = {8, 9, 4};
    int[] b1 = {2, 7, 6};
    BigMath quotient1 = new BigMath("3");
    
    assertEquals("division test",
        quotient1.toString(), (quotient1.divideSlowArray(a1, b1)).toString());

    BigMath one = new BigMath("894");
    BigMath two = new BigMath("276");
    
    assertEquals("division test", quotient1.toString(), one.divideSlow(two).toString());
  }
  
  @Test //Test 16
  void testDivideSlow() {
    BigMath one = new BigMath("229304823094802938402984029384029840923"
        + "840928340928409238409283409283402");
    BigMath two = new BigMath("2");
    BigMath quotient = new BigMath("0");
    
    assertEquals("division test", quotient.toString(), one.mod(two).toString());
  }
  
  @Test //Test 17
  void testModArray() {
    int[] a = {1, 5};
    int[] b = {5};
    BigMath quotient = new BigMath("0");
    
    assertEquals("get the remainder of two numbers",
            quotient.toString(), quotient.modArray(a, b).toString());
  }
  
  @Test //Test 18: testing dividing in divideBy()
  void testDivideBy() {
    
    BigMath one = new BigMath("2002");
    BigMath two = new BigMath("2");
    BigMath quotient = new BigMath("1001");
    
    assertEquals("division test", quotient.toString(), one.divideBy(two).toString());
    
    BigMath one1 = new BigMath("1100");
    BigMath two1 = new BigMath("276");
    BigMath quotient1 = new BigMath("3");
    
    assertEquals("division test", quotient1.toString(), one1.divideBy(two1).toString());
  }
  
  @Test
  void testRandomConstructor() {
    BigMath one = new BigMath(512, new Random());
  }
  
  @Test
  void testFastMod() {
    int[] a = {3, 6, 5, 4, 2, 0};
    int[] b = {2, 7, 6};
    BigMath remainder = new BigMath("272");
    assertEquals("fast mod test", remainder.toString(), remainder.modFastArrays(a, b).toString());
    
    int[] a1 = {2, 0, 0, 2};
    int[] b1 = {2};
    BigMath remainder1 = new BigMath("0");
    assertEquals("fast mod test", remainder1.toString(),
        remainder1.modFastArrays(a1, b1).toString());
    
    BigMath a2 = new BigMath("565452");
    BigMath b2 = new BigMath("5523");
    BigMath remainder2 = new BigMath("2106");
    assertEquals("fast mod test", remainder2.toString(), a2.modFast(b2).toString());
    
    BigMath a3 = new BigMath("9999");
    BigMath b3 = new BigMath("71");
    BigMath remainder3 = new BigMath("59");
    assertEquals("fast mod test", remainder3.toString(), a3.modFast(b3).toString());
    
    BigMath a4 = new BigMath("11653311309769376041851259004217081287054786833409532388114"
        + "87039785507554718580979923496621207789827639107286616115305879374153941781"
        + "95506326992370501806650142618135054337726497001249166204049244932844196572"
        + "56411715844307846678551497603696787262063438074461289715623476359003709098"
        + "6568004825824057551011927760");
    BigMath b4 = new BigMath("44381925929359683985166237692614625003261105052996382615"
        + "102872195115936132895381337089225280694214583197850591983870346755366271"
        + "143120335401629865048461206");
    BigMath remainder4 = new BigMath("22324372487041487968102253780200978146320075234038"
        + "9333596036207685382615597739947738137285037660480283120101985175877323607"
        + "50174454346764182407565691503476");
    assertEquals("fast mod test", remainder4.toString(), a4.modFast(b4).toString());
    
    BigMath a5 = new BigMath("11111");
    BigMath b5 = new BigMath("9");
    BigMath remainder5 = new BigMath("5");
    assertEquals("fast mod test", remainder5.toString(), a5.modFast(b5).toString());
    
  }
  
  @Test
  void testKnuthMod() {
    int[] a = {4, 4, 3, 2};
    int[] b = {1, 3};
    BigMath answer = new BigMath("340");
    assertEquals("knuth mod.", answer.toRawString(), answer.knuthMod(a, b).toRawString());
    
  }
  
  @Test
  void testBasePow() {
    int base = 10;
    int power = 12;
    int[] result = {1, 0,0,0,0,0,0,0,0,0,0,0,0};
    
    assertEquals("base to the power.", new BigMath(result).toRawString(),
        new BigMath(new BigMath("0").basePow(base, power)).toRawString());
  }
  
  @Test
  void testNegative() {
    int[] a = {-1, 2};
    int[] b = {1, 3};
    int[] result = {-2, 5};
    BigMath answer = new BigMath(result);
    assertEquals("knuth mod.", answer.toRawString(),
        new BigMath(answer.subtractArrays(a, b)).toRawString());
    
  }
}
