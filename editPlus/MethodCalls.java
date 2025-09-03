class MethodCalls 
{
	static String name = "Mahesh";
	String place = "Nashik";
	public static void main(String[] args) 
	{
		MethodCalls s = new MethodCalls();
		MethodCalls s1 = new MethodCalls();
		s1.name = "Santosh";
		System.out.println("Your name is ="+s.name);
		System.out.println("Your name is ="+s1.name);
		s.Counter();
		//s.area(10, 3);
		//s.knowYourCapital("London");
		//System.out.println("Area of the Triangle="+s.area(10,4));
	}
	
	public void Counter(){
	    for (int i=1;i<=10 ;i++ )
	    {
			System.out.println(i);
	    }
		
	}
	
	public int area(int h, int b){
		int res =(int) (h*b)*1/2;
		return res;
		//System.out.println(res);
	}
	
	public  void knowYourCapital(String country){
	    switch(country){
			case "India":
				System.out.println("Delhi");
			break;
			case "America":
				System.out.println("New york");
			break;
			case "London":
				System.out.println("Paris");
			break;
			default: 
				System.out.println("Invalid country name!");
	    }
	}
}
