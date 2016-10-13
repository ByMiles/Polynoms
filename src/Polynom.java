
class Polynom
{
    private int[] values;
    private int[] remainder;
    private int length;

    Polynom(int length)
    {
        this.length = length;
        values = new int[length];

        for (int i = 0; i < values.length; i++)
        {
            values[i] = 0;
        }

        remainder = new int[]{0, 0};
    }

    int getLength()
    {
        return this.length;
    }

    int getValue(int place)
    {
        return this.values[place];
    }

    void setValue(int place, int value)
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
            text += String.valueOf(Math.abs(values[0])) + "</html>";

        if(text.equals("<html>"))
            text = "0";
        return text;
    }

    void setRemainder(int above, int under)
    {
        remainder[0] = above;
        remainder[1] = under;
    }

    int[] getRemainder()
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
}
