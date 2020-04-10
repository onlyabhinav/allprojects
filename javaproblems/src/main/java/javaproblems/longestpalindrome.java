package javaproblems;

public class longestpalindrome {
	
	
	private static String[] strings = {
											"saqfr",
											"abcdeedcba",
											"xyzxyzxyzxyzqzyxzyxzyxzyx",
											"abcfcba",
											"xyzxyzxyzxyzzqzyxzyxzyxzyx",
											"xyzxyzxyzzyxzyxzyx",
											"abba",
											"abdseocmde",
											"djsjs"
												};
	
	
	public static void main(String[] a) {
		
		String lastLongestLengthString = "";
		
		int counter = 0;
		for (String s:strings) {
			System.out.format("\n-----------------------------------------------------------------");
			
			counter++;
			int sLength = s.length();
			Boolean isPalindrome = Boolean.FALSE;
			
			if(s.length()==1)
			{
				isPalindrome = Boolean.TRUE;	
			}

			System.out.format("\n S= %s",s);
			
			String s1,s2;
			
			if(s.length()%2==0) {
				
				int mid = s.length()/2;
				
				s1 = s.substring(0,mid);
				s2 = new StringBuilder(s.substring(mid,s.length())).reverse().toString();
				
				System.out.format("\n S1 = %s, S2 = %s",s1,s2);
				
			}else {
				
				int mid = s.length()/2;
				
				s1 = s.substring(0,mid);
				s2 = new StringBuilder(s.substring(mid+1,s.length())).reverse().toString();
				
				System.out.format("\n S1 = %s, S2 = %s",s1,s2);
			}
			
			if(s1.equals(s2)) {
				System.out.format("\n\n\n ** PALINDROME: %s (%d) **\n\n\n",s,s.length());
				isPalindrome=Boolean.TRUE;
			}
			
			
			if(isPalindrome && sLength > lastLongestLengthString.length() )
			{
				lastLongestLengthString=s;
			}
			
			
			System.out.format("\n counter: %5d, currentL: %5d, LongestL: %5d, Longest String: %20s", counter,sLength,lastLongestLengthString.length(),lastLongestLengthString);
			
			
		}
		
	}

}
