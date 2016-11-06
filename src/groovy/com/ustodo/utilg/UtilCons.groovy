package com.ustodo.utilg

import com.ustodo.utilg.O;

class UtilCons {

	public static String gets(String prompt)
	{
		O.o prompt
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		String input = br.readLine()
		return input
	}

	public static String getPicklist(String prompt, List l)
	{
		O.o prompt
		String rtn;
		while (true)
		{
			try {
				l.eachWithIndex { it, i -> 
					O.o (i + ". " + it)
				}
				int num = UtilCons.geti(prompt)
				if (num >= 0 && num <= (l.size()-1))
				{
					String dbname = l[num];
					O.o "==> you entered " + num + " which is item " + l[num]
					rtn =  l[num];
					break;
				}
				else
				{
					O.o("out of range, retry")
				}
			} catch ( Exception e ) {
				O.o ("retry", e)
			}

		}
		return rtn
	}

	public static int geti(String prompt)
	{
		O.o prompt
		int rtn;
		while (true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
			String input = br.readLine()
			try {
				//			  O.o("user entered: " + input)
				//			  O.oc("user entered: ", input)
				//rtn = Integer.parseInt(input, 10);
				rtn = Integer.valueOf(input, 10);
				//O.oc "rtn", rtn
				break;
			} catch ( Exception e ) {
				O.o ("enter a number", e)
			}

		}
		return rtn
	}


	public static boolean getYtrueNFalse(String prompt)
	{
		O.o prompt + "('y' or 'n' only)"
		int rtn;
		while (true)
		{
			try {
				//O.o("user entered: " + input)
				//O.oc("user entered: ", input)
				//rtn = Integer.parseInt(input, 10);
				while (true) {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
					String input = br.readLine()
					if (input.toLowerCase().equals("y"))
						return true
					else if (input.toLowerCase().equals("n"))
						return false
					else
						O.o "enter y or n";
				}
			} catch ( Exception e ) {
				O.o ("enter a number", e)
			}

		}
		return rtn
	}

	public static String getRegEx(String prompt)
	{
		return UtilRegEx.convertSearchToRegEx(prompt);
		
	}

}
