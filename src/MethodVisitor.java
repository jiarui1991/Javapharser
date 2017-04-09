import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter {

    @Override
    public void visit(MethodDeclaration methodDeclaration, Object obj) {

//         try {

//             ArrayList<MethodModel> methodList = new ArrayList<MethodModel>();
//             String name = null, modifier = null, returnType = null, paramName = null, paramType = null;
//             HashMap<String, String> paramHashMap = new HashMap<String, String>();

//             // Getting Method Name
//             name = String.valueOf(methodDeclaration.getName());

//             // Getting Method Modifier
//             int mod = MethodDeclaration.class.getModifiers();// !!!!!!!!! methodDeclaration.getModifiers

//             switch (mod) {
//                 case 1:
//                     modifier = "public";
//                     break;
//                 case 1025:
//                     modifier = "public abstract";
//                     break;
//                 /*
//                  * !!!!!!!!!!!!!!!!
//                  * case 9:
//                  * modifier = "public static";
//                  * String arr[] = methodDeclaration.getBody().getStmts().get(0).toString().split(" ");
//                  * FieldModel tempField = new FieldModel();
//                  * tempField.setType(arr[0]);
//                  * tempField.setModifier("default");
//                  * tempField.setName(arr[1]);
//                  * StaticClass.classList.get(StaticClass.index).getFieldList().add(tempField);
//                  * break;
//                  */
//                 case 0:
//                 case 2:
//                 case 3:
//                 case 4:
//                     modifier = null;
//                     break;
//                 default:
//                     break;
//             }

//             // Getting Method Type
//             returnType = String.valueOf(methodDeclaration.getType());

//             // Getting Method Parameter
//             List<Parameter> parameterList = methodDeclaration.getParameters();
//             if (parameterList != null) {
//                 for (Parameter temp : parameterList) {
//                     paramType = String.valueOf(temp.getType());// Parameter Type
//                     paramName = String.valueOf(temp.getEnd());// Parameter Name!!! tem.getId();
//                     paramHashMap.put(paramName, paramType);
//                 }
//             }

//             if (name != null && modifier != null) {

//                 ArrayList<FieldModel> fieldList = new ArrayList<FieldModel>();
//                 fieldList = StaticClass.classList.get(StaticClass.index).getFieldList();
//                 boolean getSetMethodFound = false;

//                 // Checking whether the method is getter or setter. If it is either of them, then
//                 // Method is not added to the Method ArrayList
//                 for (int index = 0; index < fieldList.size(); index++) {
//                     FieldModel tempField = fieldList.get(index);
//                     if (tempField.getModifier().equalsIgnoreCase("private")
//                             || tempField.getModifier().equalsIgnoreCase("public")) {
//                         if (modifier.equalsIgnoreCase("public")) {
//                             if (name.equalsIgnoreCase("get".concat(tempField.getName())) ||
//                                     name.equalsIgnoreCase("set".concat(tempField.getName()))) {
//                                 // Don't add the method to the method list
//                                 // because it is a getter or setter method of the variable.
//                                 // Instead change that variable's modifier to "public"
//                                 tempField.setModifier("public");
//                                 fieldList.set(index, tempField);
//                                 // System.out.println(tempField.getModifier());
//                                 StaticClass.classList.get(StaticClass.index).setFieldList(fieldList);

//                                 // Set the boolean value to true so that that value can be checked
//                                 // in adding to the method list
//                                 getSetMethodFound = true;
//                             }
//                         }
//                     }
//                 }
//                 if (!getSetMethodFound) {
//                     MethodModel tempMethod = new MethodModel();
//                     tempMethod.setName(name);
//                     tempMethod.setModifier(modifier);
//                     tempMethod.setReturnType(returnType);
//                     tempMethod.setParameter(paramHashMap);
//                     methodList.add(tempMethod);

//                     StaticClass.classList.get(StaticClass.index).getMethodList().add(tempMethod);
//                 }

//             }
//         } catch (Exception e) {
//             System.out.println("Exception Caught:: " + e.getMessage());
//             e.printStackTrace();
//         }
    }

}
