
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class umlparser {
    public static void main(String[] args) throws Exception {

        // original path and outputpath
        String path = args[0];
        String output_filename = args[1];
         int index = 4;
        // String path = "./test" + index;
        // String output_filename = index + ".png";
        
        drawUml obj = new drawUml(path, output_filename);

        obj.draw();
        

        File file = new File("t1.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write(obj.toString());
            if (bw != null)
            	bw.close();
            if (fw != null)
            	fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime runtime = Runtime.getRuntime();
        Process pr = runtime.exec("/usr/local/bin/yuml -i t1.txt -o " + output_filename);

       

    }

}
