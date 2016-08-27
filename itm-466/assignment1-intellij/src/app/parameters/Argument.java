package app.parameters;

/**
 * Class used to virtualize Commandline
 * argments.
 *
 * @author Brady Houseknecht
 */
public class Argument {

    String m_str_key;
    String m_str_value;
    String m_str_delimiter = "=";

    /**
     * Default Class constructor.
     */
    public Argument() {

    }

    /**
     * Overloaded constructor, which accepts the
     * keyValuePair having the format: key=value
     *
     * @param keyValuePair string value containing the key and value delimited by an equal character.
     */
    public Argument(String keyValuePair) {
        String[] _argPieces = keyValuePair.split(m_str_delimiter);
        if (null != _argPieces) {
            switch (_argPieces.length) {
                case 1: {
                    this.m_str_key = _argPieces[0];
                    this.m_str_value = "true";
                }
                break;
                case 2: {
                    this.m_str_key = _argPieces[0];
                    this.m_str_value = _argPieces[1];
                } // end:case 2
                break;
            }
        }
    }

    /**
     * @return the key
     */
    public String getKey() {
        return m_str_key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.m_str_key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return m_str_value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.m_str_value = value;
    }

    /**
     * @return the delimiter
     */
    public String getDelimiter() {
        return m_str_delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter) {
        this.m_str_delimiter = delimiter;
    }

}

