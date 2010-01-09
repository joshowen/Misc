
public class convert {
	public static void main(String[] args){
		System.out.println(convert(Integer.parseInt(args[0])));
	}
	
//	Function to calculate string from integer 124
//	one hundred and twenty four
// Max int is 2^31 - 1 = 2,147,483,648
	private static String convert(int i){
		//Create mutable string object
		StringBuffer sb = new StringBuffer();

		//Check for zero
		if (i==0) return "zero";
		//check for negative
		if(i<0){
			sb.append("negative ");
			i = i * -1;
		}
		
		int billion = i/1000000000;
		int million = (i%1000000000)/1000000;
		int thousand = (i%1000000)/1000;
		int hundred = ((i%1000)/100)*100 + (i%100);
		
		if (billion>0) sb.append(getTxt(billion) + "billion ");
		if (million>0) sb.append(getTxt(million) + "million ");
		if (thousand>0) sb.append(getTxt(thousand) + "thousand ");
		if (hundred>0) sb.append(getTxt(hundred));
		
		return sb.toString();
		
	}
	
	//Turns text into an integer
	private static String getTxt(int i){
		StringBuffer sb = new StringBuffer();
		String[] tens = new String[10];
		String[] teens = new String[10];
		String[] ones = new String[10];
		
		tens[2] = "twenty";
		tens[3] = "thirty";
		tens[4] = "fourty";
		tens[5] = "fifty";
		tens[6] = "sixty";
		tens[7] = "seventy";
		tens[8] = "eighty";
		tens[9] = "ninety";
		
		teens[0] = "ten";
		teens[1] = "eleven";
		teens[2] = "twelve";
		teens[3] = "thirteen";
		teens[5] = "fifteen";
		teens[6] = "sixteen";
		teens[7] = "seventeen";
		teens[8] = "eighteen";
		teens[9] = "nineteen";
		ones[1] = "one";
		ones[2] = "two";
		ones[3] = "three";
		ones[4] = "four";
		ones[5] = "five";
		ones[6] = "six";
		ones[7] = "seven";
		ones[8] = "eight";
		ones[9] = "nine";
		
		if(i>=100){
			sb.append(ones[i/100] + " hundred and ");
		}
		int ten = (i%100)/10;
		int one = i%10;
		if (ten >= 2){
			sb.append(tens[ten] + " ");
			if (one > 0) sb.append(ones[one] + " ");
		}else{
			if (ten == 1) sb.append(teens[one] + " ");
			if (one > 0) sb.append(ones[one] + " ");
		}
		
		return sb.toString();
	}
}
