package app.mysql;

/**
 * Class used to virtualize a single column of a
 * MySql Table column.
 *
 * @author Brady Houseknecht
 */
public class MySqlColumn {
    private String
            m_str_columnName,
            m_str_dataType;
    private int
            m_int_index;

    /**
     * Enumeration defining the types of columns
     * supported.
     * <p/>
     * NOTE - At the outset, its simply integer and string.
     */
    public enum ColumnType {
        INTEGER,
        STRING,
        DECIMAL
    }

    /**
     * Default class constructor.
     */
    public MySqlColumn() {
        this.m_str_columnName = "";
        this.m_str_dataType = "";
        this.m_int_index = 0;
    } // end:constructor

    /**
     * Overloaded constructor that can be used
     * to assign the attributes of the class
     * during initialization.
     *
     * @param columnName string value equal to the name of the result set column
     * @param dataType   string value equal to the data type of the result set column
     * @param index      int value equal to the sequential, zero-based index of the result set column when going from left to right
     */
    public MySqlColumn(String columnName, String dataType,
                       int index) {
        this.m_str_columnName = columnName;
        this.m_str_dataType = dataType;
        this.m_int_index = index;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return this.m_str_columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.m_str_columnName = columnName;
    }

    /**
     * @return the dataType
     */
    public String getDataType() {
        return this.m_str_dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(String dataType) {
        this.m_str_dataType = dataType;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return this.m_int_index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.m_int_index = index;
    }

    /**
     * Override of the class to string in order
     * to return the color and piece type attributes.
     */
    @Override
    public String toString() {
        return "[ index=\"" + this.m_int_index + "\" " +
                "name=\"" + this.m_str_columnName + "\" " +
                "type=\"" + this.m_str_dataType + "\" ]";
    }

    /**
     * Returns a string literal using the format --
     * <p/>
     * "`&lt;Column Name&gt;` lt;Column Type&gt; NULL"
     *
     * @return string
     */
    public String toSql() {

        ColumnType _enumSwitchValue = ColumnType.valueOf(this.m_str_dataType.toUpperCase());
        switch (_enumSwitchValue) {
            case INTEGER:
                return "`" + this.getColumnName() + "` " + "INT NULL";
            case STRING:
                return "`" + this.getColumnName() + "` " + "VARCHAR(255) NULL";
            case DECIMAL:
                return "`" + this.getColumnName() + "` " + "DOUBLE NULL";
            default:
                throw new IllegalArgumentException("Only \"Integer\" or \"String\" column types are (currently) supported.");
        }
    }

}