/**
 * 
 */

/**
 * @author Darshil
 *
 */
public class FieldModel {

    private String name;
    private String type;
    private String modifier;

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // return "FieldModel [Field name=" + name +
        // ", \nField type=" + type +
        // ", \nField modifier=" + modifier + "]";

        return "FieldModel [" + modifier + " " + type + " " + name + "]\n";
    }

}