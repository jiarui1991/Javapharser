
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;


import javafx.util.Pair;

public class drawUml {

    private final String path;
    private final String[] re_buffer = {"^-","^-.-","-.->","<>-1","+->","++-1>"};
    
    private final String output;
    private HashMap<String, ClassTravel> class_map = new HashMap();
    // private ClassOrInterfaceDeclaration currentCID = null;
    private StringBuilder relationSB = new StringBuilder();
//    private Map<String, Relationship> relationship_map = new HashMap();
    private StringBuilder IntermediateCode = new StringBuilder();
    private HashMap<String, StringBuilder> classcode = new HashMap();
    private HashMap<Pair<ClassOrInterfaceDeclaration,ClassOrInterfaceDeclaration>,Relationship> relation_map = new HashMap();

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

        // parse all class get class name
        for (int index = 0; index < cUnit.length; index++) {
            try {
                cUnit[index] = JavaParser.parse(files[index]);
                ClassTravel c = new ClassTravel(cUnit[index]);
                c.setClassName(cUnit[index]);
                String class_name = c.toClassNameString();
                class_map.put(class_name,c);    
                //c.parseAll();
                //add dependency parameter to classString
                
                
                // System.out.println("-->" + cUnit[index]);
               // IntermediateCode.append(c.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
         
        //parse each class completely
        
        for (int index = 0; index < cUnit.length; index++) {
            try {
                cUnit[index] = JavaParser.parse(files[index]);
                ClassTravel c = new ClassTravel(cUnit[index]);
              
                c.parseAll(class_map);
                class_map.put(c.toClassNameString(),c); 
                
                System.out.println(c.toString()+"....."+c.getField_Array());
                //add dependency 
                
                //check extends
                List<ClassOrInterfaceType> extendsList = c.parseClassOrInterface(cUnit[index]).getExtendedTypes();
             
                if (extendsList != null) {
                    for (ClassOrInterfaceType classType : extendsList) {
                        String name = classType.getNameAsString();
                        if (class_map.containsKey(name)) {
                           
                          relation_map.put(new Pair(classType,c.parseClassOrInterface(cUnit[index])),
                                   new Relationship(class_map.get(name).parseClassOrInterface(), c.parseClassOrInterface(cUnit[index]),re_buffer[0]));
                        }
                    }
                }
                
              //check inherentence
                List<ClassOrInterfaceType> inherenceList = c.parseClassOrInterface(cUnit[index]).getImplementedTypes();
             
                if (inherenceList != null) {
                    for (ClassOrInterfaceType classType2 : inherenceList) {
                        String name2 = classType2.getNameAsString();
                        if (class_map.containsKey(name2)) {
                           
                          relation_map.put(new Pair(classType2,c.parseClassOrInterface(cUnit[index])),
                                   new Relationship(class_map.get(name2).parseClassOrInterface(), c.parseClassOrInterface(cUnit[index]),re_buffer[1]));
                        }
                    }
                }
                
                //check dependency
                List<ClassOrInterfaceDeclaration> dependencyList = new ArrayList<ClassOrInterfaceDeclaration>();
                for (int i=0;i<c.getField_Array().size();i++) {
                	if (class_map.containsKey(c.getField_Array().get(i)) && class_map.get(c.getField_Array().get(i)).parseClassOrInterface().isInterface())
                        dependencyList.add(class_map.get(c.getField_Array().get(i)).parseClassOrInterface());
                    //System.out.println("field---->"+c.getField_Array().get(i));
                }
                
                if (dependencyList != null) {
                    for (ClassOrInterfaceDeclaration class_de : dependencyList) {
                        String name_de = class_de.getNameAsString();
                        if (class_map.containsKey(name_de)) {
                           
                          relation_map.put(new Pair(c.parseClassOrInterface(cUnit[index]),class_de),
                                   new Relationship(c.parseClassOrInterface(cUnit[index]),class_map.get(name_de).parseClassOrInterface(), re_buffer[2]));
                        }
                    }
                }
    
               // IntermediateCode.append(c.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
      
        Set<Map.Entry<Pair<ClassOrInterfaceDeclaration,ClassOrInterfaceDeclaration>,Relationship>> entrySet = relation_map.entrySet();
        for (Map.Entry<Pair<ClassOrInterfaceDeclaration,ClassOrInterfaceDeclaration>,Relationship> entry: entrySet) {
        	ClassTravel class_a = class_map.get(entry.getValue().getA().getNameAsString());
        	ClassTravel class_b = class_map.get(entry.getValue().getB().getNameAsString());
        	
        	IntermediateCode.append(class_a.toString()+entry.getValue().toString()+class_b.toString()+",");
            
        }
        
        
        //System.out.println(IntermediateCode);
    }
    
    
    
    
    
    public String toString() {
        return IntermediateCode.toString();
    }
    
}
