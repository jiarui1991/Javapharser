import java.util.HashMap;

/**
 * @author Darshil
 *
 */
public class ConstructorModel {

    private String name;
    private HashMap<String, String> parameter;

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
        // return "ConstructorModel [name=" + name + ", parameter=" + parameter + "]";

        return "ConstructorModel [" + name + "(" + parameter + ")";
    }

}