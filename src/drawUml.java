package pack2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class drawUml {

    private final String path;
    private final String output;
    private HashMap<String, ClassTravel> class_map = new HashMap();
    // private ClassOrInterfaceDeclaration currentCID = null;
    private StringBuilder relationSB = new StringBuilder();
    private Map<String, Relationship> relationship_map = new HashMap();
    private StringBuilder IntermediateCode = new StringBuilder();
    private HashMap<String, StringBuilder> classcode = new HashMap();


    public drawUml(String path, String output) {
        this.path = path;
        this.output = output;
    }

    public void draw() {
        // read source file
        File file = new File(path);
        File currentDirectory = null;
        try {
            currentDirectory = file;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Enter Correct Path");
        }

        File files[] = currentDirectory.listFiles(
                (File pathName) -> pathName.getName().endsWith(".java"));
        
        // fetch class
        
        CompilationUnit cUnit[] = new CompilationUnit[files.length];

        // parse all class
        for (int index = 0; index < cUnit.length; index++) {
            try {
                cUnit[index] = JavaParser.parse(files[index]);
                ClassTravel c = new ClassTravel(cUnit[index]);
                c.parseAll();
                // System.out.println("-->" + cUnit[index]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
    
    
}
