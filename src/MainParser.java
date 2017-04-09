
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import net.sourceforge.plantuml.SourceStringReader;

public class MainParser {

    public ClassModel tempClass;

    public static void main(String[] args) throws Exception {

        StaticClass.classList = new ArrayList<ClassModel>();
        StaticClass.index = 0;
        ArrayList<FieldModel> fieldList = new ArrayList<FieldModel>();
        ArrayList<MethodModel> methodList = new ArrayList<MethodModel>();
        ArrayList<ConstructorModel> constructorList = new ArrayList<ConstructorModel>();

        File file = new File("./test5");
        File currentDirectory = null;
        try {
            currentDirectory = file;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Enter Correct Path");
        }

        File fileTemp[] = currentDirectory.listFiles((File pathName) -> pathName.getName().endsWith(".java"));
        CompilationUnit cUnit[] = new CompilationUnit[fileTemp.length];

//         try {
//             // parse all JAVA files
//             for (int index = 0; index < cUnit.length; index++) {
//                 cUnit[index] = JavaParser.parse(fileTemp[index]);
//             }
//         } catch (Exception e) {
//             System.out.println("Exception Occurred :: " + e.getMessage());
//             e.printStackTrace();
//         }

//         for (; StaticClass.index < cUnit.length; StaticClass.index++) {
           
//             new ConstructorVisitor().visit(cUnit[StaticClass.index], null);
//              new ClassVisitor().visit(cUnit[StaticClass.index], null);
//             new FieldVisitor().visit(cUnit[StaticClass.index], null);
//             new MethodVisitor().visit(cUnit[StaticClass.index], null);
//             new MethodCallVisitor().visit(cUnit[StaticClass.index], null);
//         }

        // Creating the UML File

        // UMLData is the String where all the Data would be written
        String umlData = new String();

        // String Object of Relationship between classes
        String relationData = new String();

        // Starting of the UML Diagram
        String start = "@startuml\n";
        start += "skinparam classAttributeIconSize 0\n";

        // Ending of the UML Diagram
        String end = "\n@enduml";

        // Temporary Model Variables
//         FieldModel tempFieldModel = new FieldModel();
//         ConstructorModel tempConstructorModel = new ConstructorModel();
//         MethodModel tempMethodModel = new MethodModel();
//         ClassModel tempClassModel = new ClassModel();

        // Temporary List Variables
//         ArrayList<MethodModel> tempMethodList = new ArrayList<MethodModel>();
//         ArrayList<String> tempImplementsList = new ArrayList<String>();
//         ArrayList<String> tempExtendsList = new ArrayList<String>();
//         ArrayList<FieldModel> tempFieldList = new ArrayList<FieldModel>();
//         ArrayList<ConstructorModel> tempConstructorList = new ArrayList<ConstructorModel>();
        

        // Getting all Class Names in an ArrayList<String>
        ArrayList<String> classNamesList = new ArrayList<String>();
        for (int index = 0; index < StaticClass.classList.size(); index++)
            classNamesList.add(StaticClass.classList.get(index).getName());

        // Multi-Dimensional Variable for Reducing the Redundancy in Relationship
        Integer relationArray[][] = new Integer[classNamesList.size()][classNamesList.size()];
        Integer associationArray[][] = new Integer[classNamesList.size()][classNamesList.size()];

        for (int indexRow = 0; indexRow < associationArray.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < associationArray[0].length; indexColumn++) {
                associationArray[indexRow][indexColumn] = 0;
            }
        }
        
        for (int indexRow = 0; indexRow < relationArray.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < relationArray[0].length; indexColumn++) {
                relationArray[indexRow][indexColumn] = 0;
            }
        }

        for (int indexClass = 0; indexClass < StaticClass.classList.size(); indexClass++) {
            boolean isVariableClass = false;
            boolean isVariablePlural = false;
            int indexForClassFound = 0;

            tempClassModel = StaticClass.classList.get(indexClass);

            boolean isInterface = tempClassModel.isInterface();

            // Setting the interface classes
            if (tempClassModel.isInterface()) {
                umlData += "class " + tempClassModel.getName() + " << interface >> {\n";
            } else {
                umlData += "class " + tempClassModel.getName() + "{\n";
            }

            // Setting Implements
            tempImplementsList = tempClassModel.getInterfaces();

            if (tempImplementsList != null) {
                for (String tempInterface : tempImplementsList)
                    relationData += tempClassModel.getName() + " ..|> " + tempInterface + "\n";
            }

            // Setting Extends
            tempExtendsList = tempClassModel.getExtend();

            if (tempExtendsList != null) {
                for (String tempAbstractClass : tempExtendsList)
                    relationData += tempClassModel.getName() + " --|> " + tempAbstractClass + "\n";
            }

            // Setting Fields
            tempFieldList = StaticClass.classList.get(indexClass).getFieldList();

            if (tempFieldList.size() > 0) {
                for (int indexField = 0; indexField < tempFieldList.size(); indexField++) {
                    isVariablePlural = false;
                    tempFieldModel = tempFieldList.get(indexField);

                    // Setting Relationship between classes if Class Objects as Variables are found
                    // Checking whether One-to-Many OR Many-to-Many Relationship exists
                    if (tempFieldModel.getType().contains("<") && tempFieldModel.getType().contains(">")) {

                        tempFieldModel.setType(tempFieldModel.getType().substring(
                                tempFieldModel.getType().lastIndexOf('<') + 1,
                                tempFieldModel.getType().lastIndexOf('>')));
                        isVariablePlural = true;
                    }

                    System.out.println("Field Details :: " + tempFieldModel.getModifier() + " "
                            + tempFieldModel.getType() + "  " + tempFieldModel.getName());
                    for (int index = 0; index < classNamesList.size(); index++) {
                        if (classNamesList.get(index).equalsIgnoreCase(tempFieldModel.getType().toLowerCase())) {
                            isVariableClass = true;
                            indexForClassFound = index;
                            continue;

                        }
                    }

                    // Differentiating between modifiers
                    if (!isVariableClass) {
                        switch (tempFieldModel.getModifier()) {
                            case "public":
                                umlData += "+" + tempFieldModel.getName() + " : " + tempFieldModel.getType() + "\n";
                                break;
                            case "private":
                                umlData += "-" + tempFieldModel.getName() + " : " + tempFieldModel.getType() + "\n";
                                break;
                        }
                    } else {
                        if (isVariablePlural) {
                            if (!tempFieldModel.getModifier().equals("default")) {
                                relationArray[indexClass][indexForClassFound] = 2;
                                /*
                                 * relationData += "\n" + tempClassModel.getName() +
                                 * " \"1\" - \"*\" " +
                                 * tempFieldModel.getType() + "\n";
                                 */
                            }
                        } else {
                            if (!tempFieldModel.getModifier().equals("default")) {
                                relationArray[indexClass][indexForClassFound] = 1;
                                /*
                                 * relationData += "\n" + tempClassModel.getName() +
                                 * " \"1\" - \"1\" " +
                                 * tempFieldModel.getType() + "\n";
                                 */
                            }
                        }
                    }
                }
            }

            // Setting Constructors
            tempConstructorList = StaticClass.classList.get(indexClass).getConstructorList();

            if (tempConstructorList.size() > 0) {
                for (int indexConstructor = 0; indexConstructor < tempConstructorList.size(); indexConstructor++) {
                    tempConstructorModel = tempConstructorList.get(indexConstructor);

                    // Printing the Constructor name
                    umlData += "+" + tempConstructorModel.getName() + "(";

                    // Printing Parameters
                    HashMap<String, String> tempConstructorParam = tempConstructorModel.getParameter();
                    if (tempConstructorParam != null) {
                        int tempIndex = 0;
                        for (String tempConstructorParamKey : tempConstructorParam.keySet()) {
                            int indexForClassInContructorFound = 0;
                            // This index is for putting "," between 2 Parameters,
                            // if Constructor has more than 1 parameters
                            if (tempIndex > 0)
                                umlData += ",";

                            umlData += tempConstructorParamKey + " : " +
                                    tempConstructorParam.get(tempConstructorParamKey);
                            tempIndex++;
                        }
                    }

                    umlData += ")\n";
                }
            }

            // Setting Methods
            tempMethodList = StaticClass.classList.get(indexClass).getMethodList();

            if (tempMethodList.size() > 0) {
                for (int indexMethod = 0; indexMethod < tempMethodList.size(); indexMethod++) {
                    tempMethodModel = tempMethodList.get(indexMethod);

                    // Printing Method's Modifier
                    switch (tempMethodModel.getModifier()) {
                        case "public":
                            umlData += "+";
                            break;
                        case "private":
                            umlData += "-";
                            break;
                        case "public static":
                            umlData += "+{static} ";
                            break;
                        case "public abstract":
                            umlData += "+{abstract}";
                            break;
                    }
                    ;

                    // Printing Method's name
                    umlData += tempMethodModel.getName() + "(";

                    // Printing Parameters
                    HashMap<String, String> tempMethodParam = tempMethodModel.getParameter();
                    if (tempMethodParam != null) {
                        int tempIndex = 0;
                        for (String tempMethodParamKey : tempMethodParam.keySet()) {

                            // This index is for putting "," between 2 Parameters,
                            // if Constructor has more than 1 parameters
                            if (tempIndex > 0)
                                umlData += ",";

                            umlData += tempMethodParamKey + " : " +
                                    tempMethodParam.get(tempMethodParamKey);

                            // Setting Relationship between classes if Class Object parameters are found
                            for (int index = 0; index < classNamesList.size(); index++) {
                                if (classNamesList.get(index)
                                        .equalsIgnoreCase(tempMethodParam.get(tempMethodParamKey))) {
                                    if (!isInterface)
                                        associationArray[indexClass][index] = 1;
                                }
                            }

                            /*
                             * //Setting Relationship between classes if Class Object parameters are found
                             * for(String tempClassName : classNamesList){
                             * if(tempClassName.equalsIgnoreCase(tempMethodParam.get(tempMethodParamKey))){
                             * if(!isInterface)
                             * relationData += "\n" + tempClassModel.getName() + "..>" + tempClassName + "\n";
                             * }
                             * }
                             */
                            tempIndex++;
                        }
                    }

                    // Ending Method Param
                    umlData += ")";

                    // Method Return Type
                    umlData += " : " + tempMethodModel.getReturnType() + "\n";
                }
            }

            // getting method call like
            // A a:
            // a.getThat(int abc)
            if (tempClassModel.getMethodCall().size() > 0) {
                for (String methodCallKey : tempClassModel.getMethodCall().keySet()) {
                    for (FieldModel tempField : tempClassModel.getFieldList()) {
                        if (tempField.getName().equals(methodCallKey)) {
                            associationArray[indexClass][classNamesList.indexOf(tempField.getType())] = 1;
                            System.out.println("MethodCall Setting :: " + tempClassModel.getName() + " -----> "
                                    + tempField.getType());
                        }
                    }
                }
            }

            // ending the uml diagram
            umlData += "}\n";
        }

        // setting relationship between classes so that redundancy doesn't happen
        for (int indexRow = 0; indexRow < relationArray.length; indexRow++) {
            for (int indexColumn = indexRow + 1; indexColumn < relationArray[0].length; indexColumn++) {
                if (relationArray[indexRow][indexColumn] == 1) {
                    if (relationArray[indexColumn][indexRow] == 1)
                        relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                                " \"1\" - \"1\" " +
                                StaticClass.classList.get(indexColumn).getName() + "\n";
                    else if (relationArray[indexColumn][indexRow] == 2)
                        relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                                " \"*\" - \"1\" " +
                                StaticClass.classList.get(indexColumn).getName() + "\n";
                    else
                        relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                                " - \"1\" " +
                                StaticClass.classList.get(indexColumn).getName() + "\n";
                } else if (relationArray[indexRow][indexColumn] == 2) {
                    relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                            " - \"*\" " +
                            StaticClass.classList.get(indexColumn).getName() + "\n";
                } else {
                    if (relationArray[indexColumn][indexRow] == 1)
                        relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                                " \"1\" - " +
                                StaticClass.classList.get(indexColumn).getName() + "\n";
                    else if (relationArray[indexColumn][indexRow] == 2)
                        relationData += "\n" + StaticClass.classList.get(indexRow).getName() +
                                " \"*\" - " +
                                StaticClass.classList.get(indexColumn).getName() + "\n";
                }
            }
        }

        // setting association
        for (int indexRow = 0; indexRow < associationArray.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < associationArray[0].length; indexColumn++) {
                if (associationArray[indexRow][indexColumn] == 1) {
                    relationData += "\n" + classNamesList.get(indexRow) + "..>" + classNamesList.get(indexColumn)
                            + "\n";
                }
            }
        }

        String diagram = start + relationData + umlData + end;
        System.out.println(diagram);

        // Generating Output

        SourceStringReader stringReader = new SourceStringReader(diagram);

//         try {
//             String destination = stringReader.generateImage(outputStream);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
        
//          OutputStream outputStream = null;

//         try {
//             outputStream = new FileOutputStream(args[1]);

//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
    }

}
