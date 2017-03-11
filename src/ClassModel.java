import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author Darshil
 *
 */
public class ClassModel {
    private String name;
    private ArrayList<String> extend;
    private ArrayList<String> interfaces;
    private boolean isInterface;
    private boolean isAbstract;
    private ArrayList<FieldModel> fieldList;
    private ArrayList<ConstructorModel> constructorList;
    private ArrayList<MethodModel> methodList;
    private HashMap<String, String> methodCall;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the extend
     */
    public ArrayList<String> getExtend() {
        return extend;
    }

    /**
     * @param extend the extend to set
     */
    public void setExtend(ArrayList<String> extend) {
        this.extend = extend;
    }

    /**
     * @return the interfaces
     */
    public ArrayList<String> getInterfaces() {
        return interfaces;
    }

    /**
     * @param interfaces the interfaces to set
     */
    public void setInterfaces(ArrayList<String> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * @return the isInterface
     */
    public boolean isInterface() {
        return isInterface;
    }

    /**
     * @param isInterface the isInterface to set
     */
    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    /**
     * @return the isAbstract
     */
    public boolean isAbstract() {
        return isAbstract;
    }

    /**
     * @param isAbstract the isAbstract to set
     */
    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    /**
     * @return the fieldList
     */
    public ArrayList<FieldModel> getFieldList() {
        return fieldList;
    }

    /**
     * @param fieldList the fieldList to set
     */
    public void setFieldList(ArrayList<FieldModel> fieldList) {
        this.fieldList = fieldList;
    }

    /**
     * @return the constructorList
     */
    public ArrayList<ConstructorModel> getConstructorList() {
        return constructorList;
    }

    /**
     * @param constructorList the constructorList to set
     */
    public void setConstructorList(ArrayList<ConstructorModel> constructorList) {
        this.constructorList = constructorList;
    }

    /**
     * @return the methodList
     */
    public ArrayList<MethodModel> getMethodList() {
        return methodList;
    }

    /**
     * @param methodList the methodList to set
     */
    public void setMethodList(ArrayList<MethodModel> methodList) {
        this.methodList = methodList;
    }

    public HashMap<String, String> getMethodCall() {
        return methodCall;
    }

    public void setMethodCall(HashMap<String, String> methodCall) {
        this.methodCall = methodCall;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "----------Class Details-------------\n"
                + "ClassModel \n[Class name=" + name +
                ", \nThis class extends =" + extend +
                ", \nThis Class Implements interfaces = " + interfaces +
                ", \nIs this class an Interface=" + isInterface +
                ", \nIs this class an Abstract? =" + isAbstract +
                ", \n\n---------Fields Details---------\nfieldList=" + fieldList +
                ", \n\n---------Method Call Details---------\nmethodCall=" + methodCall +
                ", \n\n-------Constructor Details------\nconstructorList=" + constructorList +
                ", \n\n---------Method Details--------\nmethodList=" + methodList + "]\n" +
                "--------------------------------------------------------------------\n";
    }


}
