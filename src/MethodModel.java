import java.util.HashMap;

/**
 * @author Darshil
 *
 */
public class MethodModel {

    private String name;
    private String modifier;
    private String returnType;
    private HashMap<String, String> parameter; // <Name>,<Type>

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param mame the mame to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * @return the returnType
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * @param returnType the returnType to set
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * @return the parameter
     */
    public HashMap<String, String> getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(HashMap<String, String> parameter) {
        this.parameter = parameter;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // return "MethodModel [name=" + name + ", modifier=" + modifier + ", returnType=" + returnType + ",
        // parameter="
        // + parameter + "]";

        return "MethodModel [" + modifier + " " + returnType + " " + name + "( " + parameter + " )";
    }

}
