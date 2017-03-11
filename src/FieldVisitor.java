
import java.util.ArrayList;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * 
 */

/**
 * @author Darshil
 *
 */
public class FieldVisitor extends VoidVisitorAdapter {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Object obj) {

        try {
            ArrayList<FieldModel> fieldList = new ArrayList<FieldModel>();
            String name, modifier = null, type = null;

            // Getting Variable Name
            name = String.valueOf(fieldDeclaration.getVariables().get(0));

            // Getting Modifier
            /*
             * !!!!!!!!!!!!!!!
             * int mod = fieldDeclaration.getModifiers();
             * switch (mod) {
             * case 1:
             * modifier = "public";
             * break;
             * case 2:
             * modifier = "private";
             * break;
             * case 4:
             * modifier = "protected";
             * break;
             * case 0:
             * modifier = "default";
             * break;
             * case 3:
             * modifier = null;
             * break;
             * default:
             * break;
             * }
             */
            // Getting Variable Type
            type = String.valueOf(fieldDeclaration.getElementType());// !!!!!!getType()

            // Setting all FieldModel values
            if (modifier != null) {
                FieldModel tempField = new FieldModel();
                if (name.contains("="))
                    tempField.setName(name.substring(0, name.lastIndexOf('=') - 1));
                else
                    tempField.setName(name);
                tempField.setModifier(modifier);
                tempField.setType(type);
                fieldList.add(tempField);
                // System.out.println(fieldList+ "\n");
                // System.out.println("--" + StaticClass.index + "--");
                // System.out.println("Class = " + StaticClass.classList.get(StaticClass.index));

                StaticClass.classList.get(StaticClass.index).getFieldList().add(tempField);
                // System.out.println(StaticClass.classList.get(StaticClass.index).getFieldList().get(StaticClass.index));
                // System.out.println("Breaking Point::");
            }
        } catch (Exception e) {
            System.out.println("Exception Caught::" + e.getMessage());
            e.printStackTrace();
        }
    }

}