import java.util.ArrayList;

/**
 * <h1>Collection</h1>
 * <p>
 * This class is the backbone of the program.
 * Polynomials are saved in the ArrayList collection.
 * Operations with this saved polynomials are collected here.<p>
 * <b>Note:</b> Polynomial values can only be set by the class Polynomial.
 */
class Collection {

    private ArrayList<Polynom> collection;

    /**
     * ArrayList collection is initialized
     */
    Collection() {
        this.collection = new ArrayList<>();
    }

    /**
     * adds a polynomial at the end of the collection.
     *
     * @param polynom polynomial which is to be added to collection
     */
    void addPolynom(Polynom polynom) {
        this.collection.add(polynom);
    }

    /**
     * adds a polynomial at a specific position of the collection.
     *
     * @param position position where the polynomial is to be added
     * @param polynom  polynomial which is to be added to collection
     */
    void addPolynom(int position, Polynom polynom) { this.collection.add(position, polynom);}

    Polynom createPolynom(double[] poly_array) {
        Polynom polynom = new Polynom(poly_array.length);

        for (int i = 0; i < poly_array.length; i++) {
            polynom.setValue(i, poly_array[i]);
        }

        polynom.optimize();
        return polynom;
    }

    void deletePolynom(int position) {
        collection.remove(position);
    }

    Polynom getPolynom(int position) {
        return collection.get(position);
    }

    String getText(int position) {
        return collection.get(position).toString();
    }

    int getLength() {
        return collection.size();
    }

    Polynom[] getAsArray() {
        Polynom[] list = new Polynom[collection.size()];

        for (int i = 0; i < collection.size(); i++) {
            list[i] = collection.get(i);
        }
        return list;
    }

    double valueAt(int position, String x_value) {
        double x;
        double ram;
        double value = collection.get(position).getValue(0);

        for (int i = 0; i < x_value.length(); i++) {
            if (x_value.charAt(i) == ',')
                x_value = x_value.substring(0, i) + "." + x_value.substring(i + 1);
        }

        try {
            x = Double.parseDouble(x_value);
        } catch (NumberFormatException e) {
            return value;
        }

        if (collection.get(position).getLength() > 1) {
            value += collection.get(position).getValue(1) * x;

            for (int i = 2; i < collection.get(position).getLength(); i++) {
                ram = x;
                for (int j = 1; j < i; j++) {
                    ram *= x;
                }
                ram *= collection.get(position).getValue(i);
                value += ram;
            }
        }

        return value;
    }

    double valueAt_Horner(int position, String x_value) {
        double number;
        double value;

        for (int i = 0; i < x_value.length(); i++) {
            if (x_value.charAt(i) == ',')
                x_value = x_value.substring(0, i) + "." + x_value.substring(i + 1);
        }

        try {
            number = Double.parseDouble(x_value);
        } catch (NumberFormatException e) {
            return collection.get(position).getValue(0);
        }

        int start = 0;

        for (int i = collection.get(position).getLength() - 1; i >= 0; i--) {
            if (collection.get(position).getValue(i) != 0) {
                start = i;
                break;
            }
        }

        value = collection.get(position).getValue(start);
        start--;

        for (int i = start; i >= 0; i--) {
            value = value * number + collection.get(position).getValue(i);
        }
        return value;

    }

    Polynom addition(int position_1, int position_2) {
        Polynom polynom;
        if (collection.get(position_1).getLength() > collection.get(position_2).getLength())
            polynom = new Polynom(collection.get(position_1).getLength());
        else
            polynom = new Polynom(collection.get(position_2).getLength());

        for (int i = 0; i < polynom.getLength(); i++) {
            double a, b;
            try {
                a = collection.get(position_1).getValue(i);
            } catch (Exception e) {
                a = 0;
            }
            try {
                b = collection.get(position_2).getValue(i);
            } catch (Exception e) {
                b = 0;
            }

            polynom.setValue(i, a + b);
        }

        return polynom;
    }

    Polynom subtraction(int position_1, int position_2) {
        Polynom polynom;
        if (collection.get(position_1).getLength() > collection.get(position_2).getLength())
            polynom = new Polynom(collection.get(position_1).getLength());
        else
            polynom = new Polynom(collection.get(position_2).getLength());

        for (int i = 0; i < polynom.getLength(); i++) {
            double a, b;
            try {
                a = collection.get(position_1).getValue(i);
            } catch (Exception e) {
                a = 0;
            }
            try {
                b = collection.get(position_2).getValue(i);
            } catch (Exception e) {
                b = 0;
            }

            polynom.setValue(i, a - b);
        }

        return polynom;
    }

    Polynom multiplication(int position_1, int position_2) {
        Polynom polynom = new Polynom(collection.get(position_1).getLength() + collection.get(position_2).getLength() - 1);
        Polynom[] fractures = new Polynom[collection.get(position_1).getLength()];

        System.out.println(polynom.getLength());

        for (int i = 0; i < collection.get(position_1).getLength(); i++) {
            fractures[i] = multi_fractions(collection.get(position_1).getValue(i), i, position_2);
        }

        for (Polynom fracture : fractures) {
            polynom = multi_addition(polynom, fracture);
        }
        return polynom;
    }

    private Polynom multi_fractions(double multiplicator, int power, int position_2) {
        Polynom fraction = new Polynom(power + collection.get(position_2).getLength());

        for (int i = 0; i < collection.get(position_2).getLength(); i++) {
            if (collection.get(position_2).getValue(i) == 0)
                fraction.setValue(i + power, 0);
            else
                fraction.setValue(i + power, collection.get(position_2).getValue(i) * multiplicator);
        }

        return fraction;
    }

    private Polynom multi_addition(Polynom polynom_1, Polynom polynom_2) {
        Polynom polynom;
        if (polynom_1.getLength() > polynom_2.getLength())
            polynom = new Polynom(polynom_1.getLength());
        else
            polynom = new Polynom(polynom_2.getLength());

        for (int i = 0; i < polynom.getLength(); i++) {
            double a, b;
            try {
                a = polynom_1.getValue(i);
            } catch (Exception e) {
                a = 0;
            }
            try {
                b = polynom_2.getValue(i);
            } catch (Exception e) {
                b = 0;
            }

            polynom.setValue(i, a + b);
        }

        return polynom;
    }

    double root(int position) {
        double zero = 0.0;
        double i = 0.0;
        double steps = 1.0;
        double test;
        double previous = 0.0;
        int counter = 0;
        boolean closer = false;
        boolean forward = true;

        while(true)
        {
            test = valueAt_Horner(position, String.valueOf(i));
            System.out.println(i + " : " + test + " -> ");
            if(Math.round(test*1000.)/1000. == 0)
            {
                return i;
            }
            if(Math.abs(previous) > Math.abs(test))
            {
                if(closer)
                {
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
                else
                {
                    closer = true;
                    counter = 0;
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
            }
            else
            {
                if(closer)
                {
                    closer = false;
                    forward = !forward;
                    steps = steps/2;
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
                else
                {
                    counter++;
                    if(counter >= 10000)
                    {
                        forward = false;
                        counter = 0;
                        i = 0.;
                        steps = 1.0;
                        break;
                    }
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
            }
            previous = test;
        }
        while(true)
        {
            test = valueAt_Horner(position, String.valueOf(i));
            System.out.print(i + " : " + test + " -> ");
            if(Math.round(test*1000.)/1000. == 0)
            {
                return i;
            }
            if(Math.abs(previous) > Math.abs(test))
            {
                if(closer)
                {
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
                else
                {
                    closer = true;
                    counter = 0;
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
            }
            else
            {
                if(closer)
                {
                    closer = false;
                    forward = !forward;
                    steps = steps/2;
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
                else
                {
                    counter++;
                    if(counter >= 10000)
                        return zero;
                    if(forward)
                        i+=steps;
                    else
                        i-=steps;
                }
            }
            previous = test;
        }
    }

    Polynom derivative(int position, String value)
    {
        Polynom polynom = collection.get(position);
        System.out.println(polynom.toString());
        System.out.println(polynom.getLength()-1);
        Polynom derivated;

        int i;

        try
        {
            i = Integer.parseInt(value);
        }
        catch(NumberFormatException e){i = 0;}

        if(i >= polynom.getLength())
        {
            i = 0;
            polynom = new Polynom(1);
            polynom.setValue(0, 0);
        }

        if(i < 0)
            i = 0;

        while(i > 0)
        {
            derivated = new Polynom(polynom.getLength()-1);
            for (int j = derivated.getLength(); j > 0; j--)
            {
                derivated.setValue(j-1, polynom.getValue(j)*j);
            }
            polynom = derivated;
            i--;
        }

        return polynom;
    }

    Polynom divisionHorner(int position, String valueS)
    {
        int under;
        double value;

        try
        {
            under = Integer.parseInt(valueS);
        }
        catch (NumberFormatException e)
        {
            if(valueS.equals(""))
                under = 0;
            else
                return new Polynom(1);
        }


        under = under*-1;

        int start = 0;

        for (int i = collection.get(position).getLength() - 1; i >= 0; i--) {
            if (collection.get(position).getValue(i) != 0) {
                start = i;
                break;
            }
        }
        Polynom polynom = new Polynom(start);
        if(start == 0)
        {
            polynom = new Polynom(1);
            polynom.setValue(0, 0);
            polynom.setRemainder(collection.get(position).getValue(0), under*-1);
            return polynom;
        }
        value = collection.get(position).getValue(start);
        polynom.setValue(polynom.getLength()-1, value);
        start--;

        for (int i = start; i > 0; i--)
        {
            value = value * under + collection.get(position).getValue(i);
            polynom.setValue(i-1, value);

        }
        value = value * under + collection.get(position).getValue(0);
        polynom.setRemainder(value, under*-1);
        System.out.println(polynom.getRemainder()[0] + " " + polynom.getRemainder()[1] + " => " + polynom.remainderToString());

        return polynom;
    }

}