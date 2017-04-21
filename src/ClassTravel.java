

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassTravel extends VoidVisitorAdapter{

    private ClassOrInterfaceDeclaration cd;
    private CompilationUnit cu;
    public String class_name; 
    // private StringBuilder classString = new StringBuilder();
    public StringBuilder classString = new StringBuilder();
    private ArrayList<String> field_array = new ArrayList();
    private ArrayList<String> method_array = new ArrayList();
    

    public ClassTravel(CompilationUnit cu) {
        this.cu = cu;
    }

    public ArrayList<String> getField_Array(){
    	return field_array;
    }
    
    public String toString() {
        return classString.toString();
    }

    public String toClassNameString() {
        return class_name;
    }
    
    public ClassOrInterfaceDeclaration parseClassOrInterface() {

        List<Node> nodes = this.cu.getChildNodes();
        if (nodes != null) {
            for (Node n : nodes) {
                if (n instanceof ClassOrInterfaceDeclaration) {
                    // System.out.println("--->" + (ClassOrInterfaceDeclaration) n);
                    return (ClassOrInterfaceDeclaration) n;
                }
            }
        }

        return null;
    }
    
    
    public ClassOrInterfaceDeclaration parseClassOrInterface(CompilationUnit cu) {
        List<Node> nodes = cu.getChildNodes();
        if (nodes != null) {
            for (Node n : nodes) {
                if (n instanceof ClassOrInterfaceDeclaration) {
                    // System.out.println("--->" + (ClassOrInterfaceDeclaration) n);
                    return (ClassOrInterfaceDeclaration) n;
                }
            }
        }

        return null;
    }

    // method public, private intermediate code

    public String getModifierS(String mod) {
        if (mod.equals("[PRIVATE]")) return "-";
        if (mod.equals("[PUBLIC]")||mod.equals("[PUBLIC, STATIC]")) return "+";
        return "~";
    }

    @Override
    public void visit(VariableDeclarationExpr n, Object arg) {
    	List<VariableDeclarator> myVars = n.getVariables();
    	  for (VariableDeclarator vars : myVars) {
    		  System.out.println("Variable Name: "+vars.getNameAsString());
    	  }
    	
    	
    }


    public void parseAll(HashMap<String,ClassTravel> class_map) {
        cd = parseClassOrInterface(cu);

        class_name = cd.getNameAsString();
        // 1. member and field
       if (cd.isInterface()) {
    	    //yuml inteface cannot intercode like class. 
            classString.append("[interface::").append(cd.getName());
        } else {
            classString.append("[").append(cd.getName());
        }

        List<Node> childrenNodes = cd.getChildNodes();

     
        parseField(childrenNodes);

        parseMethod(childrenNodes,class_map);
        classString.append("]");
        
       // System.out.println(classString);

    }
    
    public void setClassName(CompilationUnit cu) {
    	cd = parseClassOrInterface(cu);
    	 this.class_name = cd.getNameAsString();
   	
    }
    
    
    
    public void parseField(List<Node> childrenNodes) {
    	   boolean flag =false;
    	   int index = classString.length();
    	   for (Node childNode : childrenNodes) {
               if (childNode instanceof FieldDeclaration) {
                   FieldDeclaration fd = (FieldDeclaration) childNode;
                   // System.out.println(fd);
                   // Private and Public Attributes (ignore package and protected scope)
                   String mod = String.valueOf(fd.getModifiers());
                   if (mod.equals("[PRIVATE]")
                           || mod.equals("[PUBLIC]")) {
                       flag =true;
                       String fieldname = fd.getVariables().get(0).getNameAsString();
                       field_array.add(String.valueOf(fd.getElementType()));
                       classString.append(getModifierS(mod)).append(fieldname + ": " + fd.getElementType() + ";");
                   }

               }
           }
    	   if (flag)
    		 classString.insert(index, '|');
   
    }
    
    public void parseMethod(List<Node> childrenNodes, HashMap<String,ClassTravel> class_map) {
    	boolean flag1 =false;
    	boolean refer1 =false;
    	boolean flag2 =false;
    	boolean refer2 =false;
    	
    	int index = classString.length();
    	
        for (Node childNode : childrenNodes) {
            if (childNode instanceof MethodDeclaration) {
            
                MethodDeclaration md = (MethodDeclaration) childNode;
               
                // Private and Public Attributes (ignore package and protected scope)
               String method_mod = String.valueOf(md.getModifiers());
               
               List<Parameter> paraList =   md.getParameters();
               
               List<Node> pparaList =   md.getChildNodes();
               for (Node p : pparaList)
                    System.out.println("ppp:   "+p);
           
                if (method_mod.equals("[PUBLIC]")||method_mod.equals("[PUBLIC, STATIC]")) {
                    flag1 =true;
                    String methodname = String.valueOf(md.getName());
                   
                    method_array.add(methodname);
                    
                    classString.append(getModifierS(method_mod)).append(methodname);
                  //get reference method argument 
                    for (Parameter p : paraList) {
                 	   
             	       Type ptype = p.getType();
             	        
             	       if (ptype instanceof ReferenceType) {
             		
             	          Type subType = ptype.getElementType();
                          if ((subType instanceof ClassOrInterfaceType && class_map.containsKey(subType.toString()))||method_mod.equals("[PUBLIC, STATIC]")) {
                            String depName = ((ClassOrInterfaceType) subType).getNameAsString();
                            String pname = p.getNameAsString();
                            classString.append("("+pname+":"+depName+")");
                            field_array.add(depName);
                            refer1=true;
                           
                          }
                       }
                
                   }
                   if (refer1)
                       classString.append(":"+md.getType()+";"); 
                   else
                	   classString.append("(): "+md.getType()+";");
                    
                }
            }
            
            if (childNode instanceof ConstructorDeclaration) {
            	ConstructorDeclaration cond = (ConstructorDeclaration) childNode;
            	String method_mod = String.valueOf(cond.getModifiers());
                
                List<Parameter> paraList =   cond.getParameters();
               
            
                 if (method_mod.equals("[PUBLIC]")) {
                     flag2 =true;
                     String methodname = String.valueOf(cond.getName());
                     method_array.add(methodname);
                     
                     classString.append(getModifierS(method_mod)).append(methodname);
                   //get reference method argument 
                     for (Parameter p : paraList) {
                  	   
              	       Type ptype = p.getType();
              	        
              	       if (ptype instanceof ReferenceType) {
              		
              	          Type subType = ptype.getElementType();
                           if (subType instanceof ClassOrInterfaceType && class_map.containsKey(subType.toString())) {
                             String depName = ((ClassOrInterfaceType) subType).getNameAsString();
                             String pname = p.getNameAsString();
                             classString.append("("+pname+":"+depName+")");
                             field_array.add(depName);
                             refer2=true;
                            
                           }
                        }
                     }
                     if (refer2)
                         classString.append(";"); 
                     else
                  	   classString.append("();");
                 }
       	
            }
            
        }
        if (classString.charAt(classString.length()-1) == ';')
        	classString.deleteCharAt(classString.length()-1);
        if (flag1||flag2)
        	classString.insert(index, '|');
        
    }
    
    

}
