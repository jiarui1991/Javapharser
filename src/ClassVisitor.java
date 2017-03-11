import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter {

    @Override
    public void visit(ClassOrInterfaceDeclaration classDeclaration, Object obj) {

        String name = null;
        ArrayList<String> extend = new ArrayList<String>();
        ArrayList<String> interfaces = new ArrayList<String>();
        boolean isInterface = false;
        boolean isAbstract = false;

        // Getting Class Name
        name = String.valueOf(classDeclaration.getName());

        // Getting Extended Base Classes
        List<ClassOrInterfaceType> extended = classDeclaration.getExtendedTypes();
        if (extended != null) {
            for (ClassOrInterfaceType temp : extended) {
                extend.add(String.valueOf(temp.getName()));
            }
        }

        // Getting Implemented Case Classes
        List<ClassOrInterfaceType> iFace = classDeclaration.getImplementedTypes();
        if (iFace != null) {
            for (ClassOrInterfaceType temp : iFace) {
                interfaces.add(String.valueOf(temp.getName()));
            }
        }

        // Checking if Class is Interface or not
        isInterface = classDeclaration.isInterface();

        // Checking if Class is Abstract or not---No method for this
        // classDeclaration.is

        // Setting the Fields
        // Setting the Methods

        ClassModel tempClass = new ClassModel();
        tempClass.setName(name);
        tempClass.setExtend(extend);
        tempClass.setInterfaces(interfaces);
        tempClass.setInterface(isInterface);
        tempClass.setAbstract(false);
        tempClass.setFieldList(new ArrayList<FieldModel>());
        tempClass.setMethodList(new ArrayList<MethodModel>());
        tempClass.setConstructorList(new ArrayList<ConstructorModel>());
        tempClass.setMethodCall(new HashMap<String, String>());

        StaticClass.classList.add(tempClass);
    }

}