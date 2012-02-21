import java.util.List;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

//Here's an example of what this may look like, it doesn't have to be like this

public class JavaScriptPointsWriter {
	public static void writeJSPoints(List<IntersectionI> list, String filename){
		if(filename != "points1.js" && filename != "points2.js" && filename != "points3.js")
			throw new IllegalArgumentException("The filename must either be points1.js, points2.js, or points3.js");
		FileWriter out;
		try{
			 out = new FileWriter(new File(filename));
			 
			 String n = "";
			 if(filename == "points1.js"){
				n = "points1";
			 }
			 else if(filename == "points2.js"){
				 n = "points2";
			 }
			 else if(filename == "points3.js"){
				 n = "points3";
			 }
			 
			 //write heading to js file
			 out.write("//Javascript file by Lisa Jiang\n");
			 out.write("//data structure for shortest path\n");
			 out.write("var " + n + "= new Array();\n");
			 out.write("\n");
			 out.write("//data\n");
			 //write the points
			 int count = 0;
			for(int i = 0; i< list.size()*2; i+=2){
				out.write(n + "[" + i + "]=" + list.get(count).getLocation().getX() + ";\n");
				out.write(n + "[" + (i+1) + "]=" + list.get(count).getLocation().getY() + ";\n");
				count++;
			}
			
			out.close(); 
		}catch(IOException e){
			throw new RuntimeException("cannot write file: "+filename,e);
		}
	}
}
