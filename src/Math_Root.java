

class Math_Root extends Math_Panel
{
    private double value;

    Math_Root(Collection collection)
    {
        super("Bestimmen einer Nullstelle");


        Menu_dropdown dropdown = new Menu_dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown);

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            value = collection.root(dropdown.getSelectedIndex());
            System.out.println(value);
            if (value == 0.0 && collection.valueAt_Horner(dropdown.getSelectedIndex(),"0") != 0.0)
                this.output("Keine Nullstelle gefunden!");
            else
                this.output(String.valueOf(Math.round(value*1000.)/1000.));
        }));
        this.add_output();
    }
}
