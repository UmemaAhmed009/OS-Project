public class test {
    public static void main(String[] args) {
    //System.out.println("Hello World!");  
    
    short num =32;
    String binaryString = Integer.toBinaryString(num);
    String withLeadingZeros = String.format("%8s", binaryString).replace(' ', '0');
    System.out.println("The binary value is: " + binaryString);
    System.out.println("The leftmost digit is: " + withLeadingZeros.charAt(0));
    }
    
}
