package poly.util;

public class EncryptTest {
   
   public static void main(String[] args) throws Exception {
      
      System.out.println("############################################");
      System.out.println("해시 암호화 알고리즘");
      //암호화 문자열
      String str = "암호화할 문자열";
      
      //복호화가 불가능ㅎ나 해시암호화 알고리즘 실행
      String hashEnc = EncryptUtil.encHashSHA256(str);
      
      //해시 암호화 알고리즘 결과 출력
      System.out.println("hashEnc : " + hashEnc);
      
      System.out.println("############################################");
      
      String enc = EncryptUtil.encAES128CBC(str);
      
      System.out.println("enc : " + enc);
      
      String dec = EncryptUtil.decAES128CBC(enc);
      
      System.out.println("dec : " + dec);
      
      System.out.println("############################################");
   }

}