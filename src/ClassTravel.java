package pack2;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class ClassTravel {

    private ClassOrInterfaceDeclaration cd;
    private CompilationUnit cu;
    private StringBuilder classString = new StringBuilder();
    private ArrayList<String> field_array = new ArrayList();
    private ArrayList<String> method_array = new ArrayList();

    public ClassTravel(CompilationUnit cu) {
        this.cu = cu;
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
        if (mod.equals("[PUBLIC]")) return "+";
        return "~";
    }



    public void parseAll() {
        cd = parseClassOrInterface(cu);


        // 1. member and field
       if (cd.isInterface()) {
            classString.append("[<<interface>>;").append(cd.getName()).append("|");
        } else {
            classString.append("[").append(cd.getName()).append("|");
        }

        List<Node> childrenNodes = cd.getChildNodes();

        for (Node childNode : childrenNodes) {
            if (childNode instanceof FieldDeclaration) {
                FieldDeclaration fd = (FieldDeclaration) childNode;
                // System.out.println(fd);
                // Private and Public Attributes (ignore package and protected scope)
                String mod = String.valueOf(fd.getModifiers());
                if (mod.equals("[PRIVATE]")
                        || mod.equals("[PUBLIC]")) {

                    String fieldname = fd.getVariables().get(0).getNameAsString();
                    field_array.add(fieldname);
                    classString.append(getModifierS(mod)).append(fieldname + ":" + fd.getElementType() + ";");
                }

            }
        }
        classString.append("|");

        // 2. method
        for (Node childNode : childrenNodes) {
            if (childNode instanceof MethodDeclaration) {
                MethodDeclaration md = (MethodDeclaration) childNode;
                // System.out.println(md);
                // Private and Public Attributes (ignore package and protected scope)
               String method_mod = String.valueOf(md.getModifiers());
                if (method_mod.equals("[PUBLIC]")) {
                    

                    // getModifier(md.getModifiers())).append(md.getName()).append("("
                    String methodname = String.valueOf(md.getName());
                    classString.append(getModifierS(method_mod)).append(methodname + ":" + ";");
                }

            }
        }

        System.out.println(classString);

    }

}
