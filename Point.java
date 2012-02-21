
public class Point
{
	private double x,y;
	private int hashCode = 0;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
	
	public String toString(){
		return x + "," + y;
	}
	
	@Override
	public int hashCode(){
		 final int multiplier = 17;
	        if (hashCode == 0) {
	            int code = 133;
	            code = (int) (multiplier * code + x);
	            code = (int) (multiplier * code + y);
	            hashCode = code;
	        }
	        return hashCode;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.x == ((Point) o).getX() && this.y == ((Point) o).getY()){
			return true;
		}
		else{
			return false;
		}
			
	}
}

