import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitor extends VoidVisitorAdapter {

    @Override
    public void visit(ConstructorDeclaration constructorDeclaration, Object obj) {

        ArrayList<ConstructorModel> constructorList = new ArrayList<ConstructorModel>();
        String name = null, paramName = null, paramType = null;
        HashMap<String, String> paramHashMap = new HashMap<String, String>();

        // Getting Method Name
        name = String.valueOf(constructorDeclaration.getName());

        // Getting Method Parameter
        List<Parameter> parameterList = constructorDeclaration.getParameters();
        if (parameterList != null) {
            for (Parameter temp : parameterList) {
                paramType = String.valueOf(temp.getType());// Parameter Type
                paramName = String.valueOf(temp.getEnd());// Parameter Name!!!!temp.getId()
                paramHashMap.put(paramName, paramType);
            }
        }

        ConstructorModel tempConstructor = new ConstructorModel();
        tempConstructor.setName(name);
        tempConstructor.setParameter(paramHashMap);
        constructorList.add(tempConstructor);
        StaticClass.classList.get(StaticClass.index).getConstructorList().add(tempConstructor);
    }

}