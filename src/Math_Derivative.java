
class Math_Derivative extends Math_Panel
{
    private GUI gui;

    private Menu_Input field;

    private Polynom result;

    Math_Derivative(Collection collection, GUI gui)
    {
        super("Bestimmen einer Ableitung");
        this.gui = gui;

        Menu_dropdown dropdown = new Menu_dropdown(collection.getAsArray());

        this.add_stuff(1, dropdown);

        field = new Menu_Input();
        this.add_stuff(3, field);

        this.add_stuff(3, new Menu_Label(". Ableitung"));

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            result = collection.derivative(dropdown.getSelectedIndex(), field.getText());
            this.output(result.toString());
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            collection.addPolynom(result);
            this.gui.action(10);
        }));
    }
}
