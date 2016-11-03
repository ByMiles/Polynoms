
class Polynom
{
    private double[] values;
    private double[] remainder;
    private int length;

    Polynom(int length)
    {
        this.length = length;
        values = new double[length];

        for (int i = 0; i < values.length; i++)
        {
            values[i] = 0;
        }

        remainder = new double[]{0, 0};
    }

    int getLength()
    {
        return this.length;
    }

    double getValue(int place)
    {
        return this.values[place];
    }

    void setValue(int place, double value)
    {
        this.values[place] = value;
    }

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
                text += String.valueOf(Math.abs(values[i])) + "x<sup>" + i + "</sup>";
        }
        if(length > 1)
        {
            if(values[1] < 0)
                text += " - ";
            if(!text.equals("<html>") && values[1] > 0)
                text += " + ";
            if(values[1] != 0)
                text += String.valueOf(Math.abs(values[1])) + "x";
        }

        if(values[0] < 0)
            text += " - ";
        if(!text.equals("<html>") && values[0] > 0)
            text += " + ";
        if(values[0] != 0)
            text += String.valueOf(Math.abs(values[0]));

        text += "</html>";

        if(text.equals("<html></html>"))
            text = "<html>0</html>";
        return text;
    }

    void setRemainder(double above, int under)
    {
        remainder[0] = above;
        remainder[1] = under;
    }

    double[] getRemainder()
    {
        return remainder;
    }

    String remainderToString()
    {
        String text = remainder[0] + " / (x ";

        if(remainder[0] == 0)
            return "0";

        if(remainder[1] < 0)
            text += "- " + Math.abs(remainder[1]);
        else
            text += "+ " + remainder[1];

        text += ")";

        return text;
    }

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
