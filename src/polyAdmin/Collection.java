package polyAdmin;

import java.util.ArrayList;

/**
 * @author Miles Lorenz (S0556515)
 * @version 1.0 <br>
 * This class is the backbone of the program.
 * Polynomials are saved in the ArrayList collection.
 * Operations with this saved polynomials are collected here.<br>
 * <b>Note:</b> Polynomial values can only be set by the class Polynomial.
 * @see polyAdmin.Polynomial
 */
public class Collection {

    private ArrayList<Polynomial> collection;

    /**
     * ArrayList collection is initialized
     */
    public Collection() {
        this.collection = new ArrayList<>();
    }

    /**
     * adds a polynomial at the end of the collection.
     *
     * @param polynomial polynomial which is to be added to collection
     */
    public void addPolynom(Polynomial polynomial) {
        this.collection.add(polynomial);
    }

    /**
     * adds a polynomial at a specific position of the collection.
     *
     * @param position   position where the polynomial is to be added
     * @param polynomial polynomial which is to be added to collection
     */
    public void addPolynom(int position, Polynomial polynomial) {
        this.collection.add(position, polynomial);
    }

    /**
     * creates polynominal of the class Polynomial from a given double-array
     *
     * @param poly_array given double-array containing the factors. The Position in the array gives the value of the power
     * @return a {@link Polynomial#optimize() optimized} (= no empty fields at the end) polynomial of the class Polynomial
     */
    public Polynomial createPolynom(double[] poly_array) {
        Polynomial polynomial = new Polynomial(poly_array.length);

        for (int i = 0; i < poly_array.length; i++) {
            polynomial.setValue(i, poly_array[i]);
        }

        // blank fields behind the last field != 0 gets "cut" by the optimize-method
        polynomial.optimize();
        return polynomial;
    }

    /**
     * removes a polynomial from the array-list collection
     *
     * @param position index of the polynomial that gets deleted
     */
    public void deletePolynom(int position) {
        collection.remove(position);
    }

    /**
     * returns a polynomial from the array-list collection
     *
     * @param position index of the polynomial thats get returned
     * @return Polynomial
     */
    public Polynomial getPolynom(int position) {
        return collection.get(position);
    }

    /**
     * returns a polynomial from the array-list as a displayable String
     * (for more info about the returned String at {@link polyAdmin.Polynomial#toString() Polynomial.toString()} method
     *
     * @param position index of the polynomial
     * @return displayable String
     */
    public String getText(int position) {
        return collection.get(position).toString();
    }

    /**
     * returns the number of the saved polynomials in the array-list collection
     *
     * @return int
     */
    public int getLength() {
        return collection.size();
    }

    /**
     * returns the array-list collection as an array.
     * The array is generated fresh by call and reference is not stored.
     *
     * @return Polynomial[]
     */
    public Polynomial[] getAsArray() {
        Polynomial[] list = new Polynomial[collection.size()];

        for (int i = 0; i < collection.size(); i++) {
            list[i] = collection.get(i);
        }
        return list;
    }

    /**
     * calculates and returns the y-value belonging to a handed over x-value from a polynomial of the array-list collection
     * the used mathematical method is pluging in the x-value in the polynomial
     *
     * @param position index of the polynomial in the array-list collection
     * @param x_value  x-value, which is plugged in. Is the x_value not parsable to Double x_value is handled as 0.
     * @return Double y-value
     */
    public double valueAt(int position, String x_value) {
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

    /**
     * calculates and returns the y-value belonging to a handed over x-value from a polynomial of the array-list collection
     * the used mathematical method is the horner-scheme:
     * (polynomial) / (x - a)
     *
     * @param position index of the polynomial in the array-list collection
     * @param a_value  a-value of the term (x-a) Is the a_value not parsable to Double the result is given as the y for = 0.
     * @return Double y-value
     */
    public double valueAt_Horner(int position, String a_value) {

        // number is the double-equivalent to a_value
        double number;
        // value stores the result
        double value;

        // parsing "," to "." to avoid NumberFormatExceptions
        for (int i = 0; i < a_value.length(); i++) {
            if (a_value.charAt(i) == ',')
                a_value = a_value.substring(0, i) + "." + a_value.substring(i + 1);
        }

        // parsing String to Double. Exceptions breaks the method giving back the y-value for x = 0
        try {
            number = Double.parseDouble(a_value);
        } catch (NumberFormatException e) {
            return collection.get(position).getValue(0);
        }

        // finding the first field which is not blank
        int start = 0;
        for (int i = collection.get(position).getLength() - 1; i >= 0; i--) {
            if (collection.get(position).getValue(i) != 0) {
                start = i;
                break;
            }
        }

        // horner-algorithm: ((first factor * (-a) + second factor) * (-a) + third factor)...
        value = collection.get(position).getValue(start);
        start--;

        for (int i = start; i >= 0; i--) {
            value = value * number + collection.get(position).getValue(i);
        }
        // result (value) equals the remainder and the y-value
        return value;
    }

    /**
     * calculates and returns the sum of two polynomials from the array-list collecion.
     * polynomials can be added with them self
     *
     * @param position_1 index in the array-list collection of the first polynomial
     * @param position_2 index in the array-list collection in the second polynomial
     * @return Polynomial
     */
    public Polynomial addition(int position_1, int position_2) {
        Polynomial polynomial;
        if (collection.get(position_1).getLength() > collection.get(position_2).getLength())
            polynomial = new Polynomial(collection.get(position_1).getLength());
        else
            polynomial = new Polynomial(collection.get(position_2).getLength());

        for (int i = 0; i < polynomial.getLength(); i++) {
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

            polynomial.setValue(i, a + b);
        }

        return polynomial;
    }

    /**
     * calculates and returns the difference of two polynomials from the array-list collecion.
     * polynomials can be added with them self
     *
     * @param position_1 index in the array-list collection of the first polynomial
     * @param position_2 index in the array-list collection in the second polynomial
     * @return Polynomial
     */
    public Polynomial subtraction(int position_1, int position_2) {
        Polynomial polynomial;
        if (collection.get(position_1).getLength() > collection.get(position_2).getLength())
            polynomial = new Polynomial(collection.get(position_1).getLength());
        else
            polynomial = new Polynomial(collection.get(position_2).getLength());

        for (int i = 0; i < polynomial.getLength(); i++) {
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

            polynomial.setValue(i, a - b);
        }

        return polynomial;
    }

    /**
     * calculates and returns the product of two polynomials from the array-list collecion.
     * polynomials can be added with them self
     *
     * @param position_1 index in the array-list collection of the first polynomial
     * @param position_2 index in the array-list collection in the second polynomial
     * @return Polynomial
     */
    public Polynomial multiplication(int position_1, int position_2) {

        // "polynomial" stores the final result. The length is the sum of the highest power of each factor-polynomial
        Polynomial polynomial = new Polynomial(collection.get(position_1).getLength() + collection.get(position_2).getLength() - 1);

        // "fractures" stores the interim results. The length equals the length of the first factor-polynomial,

        Polynomial[] fractures = new Polynomial[collection.get(position_1).getLength()];

        // each field of the first factor-polynomial is multiplied with the hole second factor-polynomial
        // by using the method "multi_fractions"
        // the result is stored in "fractures"
        for (int i = 0; i < collection.get(position_1).getLength(); i++) {
            fractures[i] = multi_fractions(collection.get(position_1).getValue(i), i, position_2);
        }

        // the final result is calculated by the method "multi_addition", which adds all interim results
        for (Polynomial fracture : fractures) {
            polynomial = multi_addition(polynomial, fracture);
        }
        return polynomial;
    }

    /**
     * sub-method of {@link polyAdmin.Collection#multiplication(int, int) multiplication}.
     * the calculation looks like:<br>
     * multiplicator * x ^ power * (polynomial[= collection.get(position_2)])
     *
     * @param multiplicator the factor in front of "x"
     * @param power         the power of "x"
     * @param position_2    the intex of the polyinomial which gets multiplicated
     * @return Polynomial
     */
    private Polynomial multi_fractions(double multiplicator, int power, int position_2) {
        Polynomial fraction = new Polynomial(power + collection.get(position_2).getLength());

        for (int i = 0; i < collection.get(position_2).getLength(); i++) {
            if (collection.get(position_2).getValue(i) == 0)
                fraction.setValue(i + power, 0);
            else
                fraction.setValue(i + power, collection.get(position_2).getValue(i) * multiplicator);
        }

        return fraction;
    }

    /**
     * sub-method of {@link polyAdmin.Collection#multiplication(int, int) multiplication}.
     * equals the method{@link polyAdmin.Collection#addition(int, int) addition}, but with not stored but
     * handed over polynomials
     *
     * @param polynomial_1 polynomial 1
     * @param polynomial_2 polynomial 2
     * @return Polynomial
     */
    private Polynomial multi_addition(Polynomial polynomial_1, Polynomial polynomial_2) {
        Polynomial polynomial;
        if (polynomial_1.getLength() > polynomial_2.getLength())
            polynomial = new Polynomial(polynomial_1.getLength());
        else
            polynomial = new Polynomial(polynomial_2.getLength());

        for (int i = 0; i < polynomial.getLength(); i++) {
            double a, b;
            try {
                a = polynomial_1.getValue(i);
            } catch (Exception e) {
                a = 0;
            }
            try {
                b = polynomial_2.getValue(i);
            } catch (Exception e) {
                b = 0;
            }

            polynomial.setValue(i, a + b);
        }

        return polynomial;
    }

    /**
     * calculates a division using the horner-scheme and returns the result as an polynomial of the class Polynomial
     *
     * @param position index
     * @param a_value  a-value of the term (x-a) Is the a_value not parsable to Double the result is given as the y for = 0.
     * @return Polynomial
     */
    public Polynomial divisionHorner(int position, String a_value) {

        // under equals the parsed value of a_value ("under the fraction bar")
        double under;

        // value stores the result
        double value;

        // parsing "," to "." to avoid NumberFormatExceptions
        for (int i = 0; i < a_value.length(); i++) {
            if (a_value.charAt(i) == ',')
                a_value = a_value.substring(0, i) + "." + a_value.substring(i + 1);
        }
        // parsing a_value to double
        try {
            // -- = +
            if(a_value.length() > 1) {
                if (a_value.substring(0, 2).equals("--"))
                    a_value = a_value.substring(2);
            }
            under = Double.parseDouble(a_value);
        } catch (NumberFormatException e) {
            if (a_value.equals(""))
                under = 0;
            else
                return new Polynomial(1);
        }

        // making a = -a

        under = under * -1;

        int start = 0;

        // finding the first not empty field to start with
        for (int i = collection.get(position).getLength() - 1; i >= 0; i--) {
            if (collection.get(position).getValue(i) != 0) {
                start = i;
                break;
            }
        }
        Polynomial polynomial = new Polynomial(start);
        if (start == 0) {
            polynomial = new Polynomial(1);
            polynomial.setValue(0, 0);
            polynomial.setRemainder(collection.get(position).getValue(0), under * -1);
            return polynomial;
        }

        // horner-algorithm: ((first factor * (-a) + second factor) * (-a) + third factor)...
        value = collection.get(position).getValue(start);
        polynomial.setValue(polynomial.getLength() - 1, value);
        start--;

        for (int i = start; i > 0; i--) {
            // caused by the power-reduction the value is stored one field "deeper"
            value = value * under + collection.get(position).getValue(i);
            polynomial.setValue(i - 1, value);

        }

        // the last value equals the remainder
        value = value * under + collection.get(position).getValue(0);

        // remainder is stored in the corresponding polynomial
        polynomial.setRemainder(value, under * -1);
        return polynomial;
    }

    /**
     * finds and returns a root of a polynomial from the array-list collection<br>
     * the algorithm uses iteration over the area -10`000 - 10`000<br>
     * with {@link polyAdmin.Collection#valueAt_Horner(int, String) valueAt_Horner}
     * a root is approximated
     * @param position index of the polynomial in the array-list collection
     * @return Polynomial
     */
    public double root(int position) {
        double i = 0.0;          // stores the current x-value
        double steps = 1.0;      // stores the distance between the previous and the current x-value
        double test;             // stores the to the current x-value belonging y-value
        double previous = 0.0;   // stores the previous y-value
        int counter = 0;         // counts the failed runs
        boolean closer = false;  // stores if the distance to 0 increased or decreased
        boolean forward = true;  // stores if i increases or decreases


        // break is counter >= 10000
        while (true) {
            test = valueAt_Horner(position, String.valueOf(i));  // returns the y-value
            if (Math.round(test * 1000.) / 1000. == 0) {
                return i; // root is found
            }

            if (Math.abs(previous) > Math.abs(test)) {  // => current y-value is closer to 0 then previous
                if (closer) {                           // => previous was also closer than the one before
                    if (forward)
                        i += steps;                     // => i is increased by steps, => next run
                    else
                        i -= steps;                     // => i is decreased by steps, => next run
                } else {
                    closer = true;                      // => previous was not closer than the one before
                    counter = 0;                        // => probably we are coming closer
                    if (forward)
                        i += steps;                     // => i is increased by steps, => next run
                    else
                        i -= steps;                     // => i is decreased by steps, => next run
                }
            } else {                                    // => current y-value is not closer to 0 than previous
                if (closer) {                           // => previous was also closer than the one before
                    closer = false;
                    forward = !forward;                 // => probably we run over 0, so we change the direction
                    steps = steps / 2;                  // => we half the step-size, for going back
                    if (forward)
                        i += steps;                     // => i is increased by steps, => next run
                    else
                        i -= steps;                     // => i is decreased by steps, => next run
                } else {
                    counter++;                          // => previous was not closer than the one before
                    if (counter >= 10000) {             // => we made 10'000 steps without finding
                        forward = false;                // => all values rested, try find one by starting
                        counter = 0;                    //    other direction
                        i = 0.;
                        steps = 1.0;
                        break;
                    }
                    if (forward)
                        i += steps;
                    else
                        i -= steps;
                }
            }
            previous = test;
        }
        while (true) {
            test = valueAt_Horner(position, String.valueOf(i));
            if (Math.round(test * 1000.) / 1000. == 0) {
                return i;
            }
            if (Math.abs(previous) > Math.abs(test)) {
                if (closer) {
                    if (forward)
                        i += steps;
                    else
                        i -= steps;
                } else {
                    closer = true;
                    counter = 0;
                    if (forward)
                        i += steps;
                    else
                        i -= steps;
                }
            } else {
                if (closer) {
                    closer = false;
                    forward = !forward;
                    steps = steps / 2;
                    if (forward)
                        i += steps;
                    else
                        i -= steps;
                } else {
                    counter++;
                    if (counter >= 10000)              // => no root found
                        return 0;                      // => Math_Root checks for remainder with divisionHorner()
                    if (forward)                       // if remainder = 0 => root at x = 0, else => no root found
                        i += steps;
                    else
                        i -= steps;
                }
            }
            previous = test;
        }
    }

    /**
     * calculates and returns a derivative from a polynomial from the array-list collection
     * @param position index of the polynomial in the array-list collection
     * @param value grade of the derivative
     * @return Polynomial
     */
    public Polynomial derivative(int position, String value) {
        Polynomial polynomial = collection.get(position);
        Polynomial derivated;

        int i;

        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            i = 0;
        }

        if (i >= polynomial.getLength()) {
            i = 0;
            polynomial = new Polynomial(1);
            polynomial.setValue(0, 0);
        }

        if (i < 0)
            i = 0;

        // i is the grad of derivation
        // each derivation is a derivation of the previous derivation till i == 0
        while (i > 0) {

            // derivation is one grade smaller than the polynomial
            derivated = new Polynomial(polynomial.getLength() - 1);

            // power gets decreased by 1 and the factor gets multiplied with the value of the old power
            // 2x^3 => 3*2x^2 = 6x^2
            for (int j = derivated.getLength(); j > 0; j--) {
                derivated.setValue(j - 1, polynomial.getValue(j) * j);
            }
            polynomial = derivated;
            i--;
        }

        return polynomial;
    }


}