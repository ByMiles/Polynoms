package polyAdmin;

/**
 * @author Miles Lorenz (S0556515)
 * @version 1.0 <br>
 * This class stores all information about one polynomial.<br>
 * Attribute values[] stores the factors in an array, the power corresponds to the index
 * Attribute remainder[] stores the possible remainder after horner-division.
 */
public class Polynomial
{
    private double[] values;
    private double[] remainder;
    private int length;

    /**
     * new empty polynomial with lengtth length
     * @param length length of the new polynomial
     */
    public Polynomial(int length)
    {
        this.length = length;
        values = new double[length];

        for (int i = 0; i < values.length; i++)
        {
            values[i] = 0;
        }

        remainder = new double[]{0, 0};
    }

    /**
     * returns the length of the polynomial, the array values
     * @return int length
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * returns the factor at the index position of the array values
     * @param position index of the factor in the array values
     * @return double value[position]
     */
    public double getValue(int position)
    {
        return this.values[position];
    }

    /**
     * sets the factor at the index postion of the array values
     * @param position index of the factor in the array values
     * @param value new value of the factor
     */
    void setValue(int position, double value)
    {
        this.values[position] = value;
    }

    /**
     * creates instant and returns a displayable String using html
     * factors are rounded to two decimal places
     * @return String polynomial
     */
    public String toString()
    {
        String text = "<html>";

        for (int i = length-1; i > 1; i--)
        {
            if(values[i] < 0)
                text += " - ";
            if(!text.equals("<html>") && values[i] != 0)
            {
                if(values[i] > 0)
                    text += " + ";
            }
            if(values[i] != 0)
                text += String.valueOf(Math.abs(roundValue(values[i]))) + "x<sup>" + i + "</sup>";
        }
        if(length > 1)
        {
            if(values[1] < 0)
                text += " - ";
            if(!text.equals("<html>") && values[1] > 0)
                text += " + ";
            if(values[1] != 0)
                text += String.valueOf(Math.abs(roundValue(values[1]))) + "x";
        }

        if(values[0] < 0)
            text += " - ";
        if(!text.equals("<html>") && values[0] > 0)
            text += " + ";
        if(values[0] != 0)
            text += String.valueOf(Math.abs(roundValue(values[0])));

        text += "</html>";

        if(text.equals("<html></html>"))
            text = "<html>0</html>";
        return text;
    }

    private double roundValue(double value)
    {
        value =  ((double)((int)(value*100))/100.);

        return value;
    }

    void setRemainder(double above, double under)
    {
        remainder[0] = above;
        remainder[1] = under;
    }

    public double[] getRemainder()
    {
        return remainder;
    }

    public String remainderToString()
    {
        String text = roundValue(remainder[0]) + " / (x ";

        if(remainder[0] == 0)
            return "0";

        if(remainder[1] < 0)
            text += "- " + Math.abs(roundValue(remainder[1]));
        else
            text += "+ " + roundValue(remainder[1]);

        text += ")";

        return text;
    }

    /**
     * rebuilds the array values with no backlog
     */
    void optimize()
    {
        if(values[values.length-1] != 0)
        {
            return;
        }

        int start = values.length;

        while(true)
        {
            if(values[start-1] != 0.)
                break;
            start--;
        }

        double[] copy = new double[start];

        for (int i = 0; i < copy.length ; i++)
        {
            copy[i] = values[i];
        }
        values = copy;
        length = values.length;
    }
}
