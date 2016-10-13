

class Math_Division extends Math_Panel
{
    private GUI gui;
    private Polynom result;

    private Menu_Input input;

    Math_Division(Collection collection, GUI gui)
    {
        super("Division durch (x-a)");
        this.gui = gui;

       Menu_dropdown dropdown = new Menu_dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown);

        this.add_stuff(2, new Menu_Label("/"));

        this.add_stuff(3, new Menu_Label("( x  "));

        Menu_dropdown plusminus = new Menu_dropdown(new String[]{" + ", " - "});
        this.add_stuff(3, plusminus);

        input = new Menu_Input();
        this.add_stuff(3, input);

        this.add_stuff(3, new Menu_Label(" )"));

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            String text = "";
            if(plusminus.getSelectedIndex() == 1)
            {
                text += "-";
            }

            text += input.getText();
            result = collection.divisionHorner(dropdown.getSelectedIndex(), text);

            System.out.println(result.remainderToString());
            System.out.println(result.toString() + " + " + result.remainderToString());
            text = (result.toString().substring(0, result.toString().length()- 7) + " + " + result.remainderToString() + "</html>");
            this.output(text);
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            collection.addPolynom(result);
            this.gui.action(8);
        }));
    }
}
