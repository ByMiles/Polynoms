

class Math_Subtraction extends Math_Panel {

    private GUI gui;
    private Polynom result;
    private Menu_dropdown[] dropdown;



    Math_Subtraction(Collection collection, GUI gui) {
        super("Subtraktion zweier Polynome");
        this.gui = gui;

        dropdown = new Menu_dropdown[2];

        dropdown[0] = new Menu_dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown[0]);

        this.add_stuff(2, new Menu_Label("-"));

        dropdown[1] = new Menu_dropdown(collection.getAsArray());
        this.add_stuff(3, dropdown[1]);

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
           result = collection.subtraction(dropdown[0].getSelectedIndex(), dropdown[1].getSelectedIndex());
           output(result.toString());
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            collection.addPolynom(result);
            this.gui.action(6);
        }));
    }
}

