package pl.waw.rubach.web;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.waw.rubach.points.*;

import static com.vaadin.icons.VaadinIcons.QUESTION_CIRCLE;

class OptionMenu extends MenuBar {


    private VaadinUI ui;

    private CheckBox checkbox1AssumptionWe = new CheckBox("Czy jesteście po partii (ustawiane w głównej aplikacji albo tutaj)? ");
    private CheckBox checkbox1AssumptionThey = new CheckBox("Czy przeciwnicy są  po partii (ustawiane w głównej aplikacji albo tutaj)? ");

    private CheckBox checkboxFitWe = new CheckBox("Czy  macie fit (domyślnie tak - ustawiane tutaj)?");
    private CheckBox checkboxFitThey = new CheckBox("Czy  jest fit u przeciwników (domyślnie nie - ustawiane tutaj)?");

    private CheckBox checkboxDouble = new CheckBox("Czy  była kontra (domyślnie nie - ustawiane tutaj)?");
    private CheckBox checkboxReDouble = new CheckBox("Czy  była rekontra (domyślnie nie - ustawiane tutaj)?");



    private VerticalLayout createLegendDescription() {
        VerticalLayout legendDescription = new VerticalLayout();

        Label legendTitle = new Label("Legenda - opis znaczeń odzywek: ");
        Label legendLabelBlue = new Label("Niebieski - (FD) Forsuje do dogranej, czyli nie można spasować przed końcówką");
        legendLabelBlue.addStyleName("blue");
        Label legendLabelGreen = new Label("Zielony - (F1) Forsuje na jedną kolejkę, czyli jeżeli przeciwnicy się nie odezwą partner nie może spasować");
        legendLabelGreen.addStyleName("green");
        Label legendLabelYellow = new Label("Zółty - (SO) Sing off - sygnał końca licytacji - nie mam już nic wiecej - raczej pasuj ");
        legendLabelYellow.addStyleName("yellow");
        Label legendLabelOrange = new Label("Pomarańczowy - (I) Inwit - zaproszenie do wyższego kontraktu, ktorego jednak nie mogę już zaproponować");
        legendLabelOrange.addStyleName("orange");
        Label legendLabelRose = new Label("Różowy - (BL) Blok - odzwyka mająca na celu zablokowanie przeciwników bo pewnie mogłby im iść dobry kontrakt");
        legendLabelRose.addStyleName("rose");
        Label legendLabelGrey = new Label("Szary - (NF) Nie forsuje - nie zmusza do odpowiedzi (można spasować) ");
        legendLabelGrey.addStyleName("grey");

        Label legendLabelBlueDark = new Label("- kolor niebieski jest ciemniejszy jeżeli odzwyka jest sztuczna... ");
        legendLabelBlueDark.addStyleName("blueD");
        Label legendLabelGreenDark = new Label("- kolor zielony jest ciemniejszy jeżeli odzwyka jest sztuczna...");
        legendLabelGreenDark.addStyleName("greenD");
        Label legendLabelYellowDark = new Label("- kolor żółty jest ciemniejszy jeżeli odzwyka jest sztuczna...");
        legendLabelYellowDark.addStyleName("yellowD");
        Label legendLabelOrangeDark = new Label("- kolor pomarańczowy jest ciemniejszy jeżeli odzwyka jest sztuczna...");
        legendLabelOrangeDark.addStyleName("orangeD");
        Label legendLabelRoseDark = new Label("- kolor różowy jest  ciemniejszy jeżeli odzwyka jest sztuczna...");
        legendLabelRoseDark.addStyleName("roseD");
        Label legendLabelGreyDark = new Label("- kolor  szary jest ciemniejszy jeżeli odzwyka jest sztuczna...");
        legendLabelGreyDark.addStyleName("greyD");
        Label legendLabelRedDark = new Label("Czerwony - (AU) Blok - odzwyka sztuczna automatyczna");
        legendLabelRedDark.addStyleName("redD");


        HorizontalLayout horlBlue = new HorizontalLayout(legendLabelBlue, legendLabelBlueDark);
        HorizontalLayout horlGreen = new HorizontalLayout(legendLabelGreen, legendLabelGreenDark);
        HorizontalLayout horlYellow = new HorizontalLayout(legendLabelYellow, legendLabelYellowDark);
        HorizontalLayout horlOrange = new HorizontalLayout(legendLabelOrange, legendLabelOrangeDark);
        HorizontalLayout horlRose = new HorizontalLayout(legendLabelRose, legendLabelRoseDark);
        HorizontalLayout horlRed = new HorizontalLayout(legendLabelRedDark);
        HorizontalLayout horlGrey = new HorizontalLayout(legendLabelGrey, legendLabelGreyDark);

        legendDescription.addComponent(legendTitle);
        legendDescription.addComponent(horlBlue);
        legendDescription.addComponent(horlGreen);
        legendDescription.addComponent(horlYellow);
        legendDescription.addComponent(horlOrange);
        legendDescription.addComponent(horlRose);
        legendDescription.addComponent(horlRed);
        legendDescription.addComponent(horlGrey);


        //	Label silne = new Label("<b> Silne - wytłuszczone (nie działa)</b>", ContentMode.HTML);
        Label sztuczne = new Label("<font color = #0000A0 > Sztuczne - granatowa czcionka, mocniejszy kolor tła (patrz powyżej) </font >", ContentMode.HTML);

        //legendDescription.addComponent(silne);
        legendDescription.addComponent(sztuczne);


        return legendDescription;
    }

    private VerticalLayout  createDesciption(){
        VerticalLayout description = new VerticalLayout();

        String s ="\n" +
                "Wzięcie lewy w kolorze młodszym (kara itrefle)po 20\n" +
                "Wzięcie lewy w kolorze starszym (kiery i piki) po 30\n" +
                "Wzięcie lewy w grze bez atu pierwsza 40, druga i każda następna po 30\n" +
                "Nadróbki (bez kontry) każda nadróbka jest punktowana jak lewa kontraktowa (20 lub 30)\n" +
                "Nadróbki z kontrą  \tpo 100 przed partią — niezależnie od koloru,  po 200 po partii\n" +
                "Premia za nieudaną kontrę przeciwników 50\n" +
                "Premia za partię Uwzględniane TYLKO w brydżu sportowym 300 przed partią, 500 po partii\n" +
                "Premia za szlemika (wylicytowanie i wzięcie 12 lew) 500 przed partią, 750 po partii\n" +
                "Premia za szlema (wylicytowanie i wzięcie 13 lew) 1000 przed partią, 1500 po partii\n" +
                "Skontrowanie przeciwników  Podwojenie zapisu za ugrane lewy, modyfikacja zapisu za wpadki oraz nadróbki \n" +
                "Wpadki bez kontry przed partią (bez względu na kolor atutowy) po 50 za każdą lewę wpadkową\n" +
                "Wpadki bez kontry po partii  po 100 za każdą lewę wpadkową\n" +
                "Wpadka z kontrą przed partią (bez względu na kolor atutowy)  100 za pierwszą,  po 200 za drugą i trzecią,  po 300 za czwartą i każdą następną\n" +
                "Wpadka z kontrą po partii (bez względu na kolor atutowy) 200 za pierwszą,  po 300 za każdą następną\n" +
                "Rekontra Podwojenie zapisu (z kontrą) za ugrane lewy oraz nadróbki, podwojenie zapisu (z kontrą) za wpadki i za nieudaną kontrę";

        TextArea text = new TextArea("Punktacja w systemie międzynarodowym");
        text.setStyleName("description");
        text.setValue(s);
        text.setWidth("100%");
        text.setHeight("400px");
        description.addComponent(text);

    return description;
    }

    private void actionOpenWindowWithLegend(VaadinUI ui) {
        final Window window = new Window("Legenda - opis typów odzywek");
        window.setWidth("100%");
        //window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(createLegendDescription());
        content.addStyleName("window");
        window.setContent(content);

        ui.addWindow(window);


        // sample.getUI().getUI().addWindow(window);
    }

    private void actionOpenWindowWithDescription(VaadinUI ui) {
        final Window window = new Window("Jak liczyć punkty? ");
        window.setWidth("100%");
        //window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(createDesciption());
        content.addStyleName("window");
        window.setContent(content);

        ui.addWindow(window);


        // sample.getUI().getUI().addWindow(window);
    }

    private void actionCalculetePoints(VaadinUI ui) {
        final Window window = new Window("Okienko do liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja 1.0 - podaj jaki był kontrakt i co ugraliście - działa tylko dla kolorów starszych :)"));

        content.addStyleName("window");

        content.addComponent(checkbox1AssumptionWe);
        content.addComponent(checkbox1AssumptionThey);
        TextField pointsInBothHands = new TextField("Podaj liczbę punktów na obu rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHands);

        //   checkbox1AssumptionWe.setValue(true); //move to create menu?
        checkboxFitWe.setValue(true);
        content.addComponent(checkboxFitWe);

        checkboxFitThey.setValue(false);
        content.addComponent(checkboxFitThey);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze."));


        checkboxDouble.setValue(false);
        content.addComponent(checkboxDouble);

        checkboxReDouble.setValue(false);
        content.addComponent(checkboxReDouble);

        TextField pointsForContract = new TextField("Podaj liczbę zebranych lew:");
        content.addComponent(pointsForContract);

        TextField levelOfContract = new TextField("Podaj wysokość granego kontraktu:");
        content.addComponent(levelOfContract);



        Label resultsLabel = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);
        pointsInBothHands.addValueChangeListener( event -> resultsLabel.setValue(""));
        pointsForContract.addValueChangeListener( event -> resultsLabel.setValue(""));

        //  checkbox1AssumptionWe.addValueChangeListener(event ->
        //         checkboxFitWe.setValue(! checkbox1AssumptionWe.getValue()));

        Button sayHelloButton = new Button("Oblicz punkty i impy! ", clickEvent -> {
            try {
                float foo = Float.parseFloat(pointsInBothHands.getValue());

                int foo2= new PointsForContract(Integer.parseInt(levelOfContract.getValue()), Integer.parseInt(pointsForContract.getValue()), "s", checkboxDouble.getValue(), checkboxReDouble.getValue(), checkbox1AssumptionWe.getValue()).getCalculatedPointsForContract();

                ResultsOfOneGame a = new ResultsOfOneGame(foo, foo2, checkbox1AssumptionWe.getValue(), checkbox1AssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());
                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście "+  foo2 +" punktów za kontrakt  i "+  a.getResults() + " impów.  </B>  <BR> jeżeli liczba punktów jest ujemna to zapisuje się punkty po stronie przeciwników. ");
            } catch (NumberFormatException | InvalidNumberOfPointsException | PointsDiferentLessThenZeroException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowo podana liczba punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>"+message +"</font>");
            }
        });

        content.addComponent(sayHelloButton);
        content.addComponent(resultsLabel);
        window.setContent(content);
        ui.addWindow(window);
    }

    private void actionManualCalculetePoints(VaadinUI ui) {
        final Window window = new Window("Okienko do ręcznego liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna - podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));

        content.addStyleName("window");

        content.addComponent(checkbox1AssumptionWe);
        content.addComponent(checkbox1AssumptionThey);
        TextField pointsInBothHands = new TextField("Podaj liczbę punktów na obu rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHands);

        //   checkbox1AssumptionWe.setValue(true); //move to create menu?
        checkboxFitWe.setValue(true);
        content.addComponent(checkboxFitWe);

        checkboxFitThey.setValue(false);
        content.addComponent(checkboxFitThey);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze."));


        TextField pointsForContract = new TextField("Podaj liczbę punktów uzyskanych przy rozgrywaniu kontraktu:");
        content.addComponent(pointsForContract);





        Label resultsLabel = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);
        pointsInBothHands.addValueChangeListener( event -> resultsLabel.setValue(""));
        pointsForContract.addValueChangeListener( event -> resultsLabel.setValue(""));

        //  checkbox1AssumptionWe.addValueChangeListener(event ->
        //         checkboxFitWe.setValue(! checkbox1AssumptionWe.getValue()));

        Button sayHelloButton = new Button("Oblicz punkty! ", clickEvent -> {
            try {
                float foo = Float.parseFloat(pointsInBothHands.getValue());
                int foo2 = Integer.parseInt(pointsForContract.getValue());
                ResultsOfOneGame a = new ResultsOfOneGame(foo, foo2, checkbox1AssumptionWe.getValue(), checkbox1AssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());
                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + a.getResults() + " impów (punktów).  </B>  <BR> jeżeli liczba punktów jest ujemna to zapisuje się punkty po stronie przeciwników. ");
            } catch (NumberFormatException | InvalidNumberOfPointsException | PointsDiferentLessThenZeroException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowo podana liczba punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>"+message +"</font>");
            }
        });

        content.addComponent(sayHelloButton);
        content.addComponent(resultsLabel);
        window.setContent(content);
        ui.addWindow(window);
    }


    private void actionDisplayPointsTable(VaadinUI ui) {
        final Window window = new Window("Okienko z tabelkami do liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna - tabelki do liczenia punktów dla wariantów podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));

        content.addStyleName("window");

        //   checkbox1AssumptionWe.setValue(true); //move to create menu?
        checkboxFitWe.setValue(true);

        content.addComponent(checkbox1AssumptionWe);
        content.addComponent(checkboxFitWe);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy dowolny kolor jest sfitowany, jeżeli mniej to tylko starszy."));
        //  checkbox1AssumptionWe.addValueChangeListener(event ->
        //         checkboxFitWe.setValue(! checkbox1AssumptionWe.getValue()));

        Button displayExpectedResults = new Button("Wyświetl tabelkę  oczekiwanych punktów dla  danych założeń! ", clickEvent -> {
          TextArea a = new TextArea();
          a.setWidth("100%");
          a.setValue(ExpectedResultsTable.getTableAsString(checkboxFitWe.getValue(), checkbox1AssumptionWe.getValue()));
          content.addComponent(a);
        });

        Button displayImpTable = new Button("Wyświetl tabelkę impów! ", clickEvent -> {
            TextArea b = new TextArea();
            b.setWidth("100%");
            b.setValue(ImpTable.getTableAsString());
            content.addComponent(b);
        });
        content.addComponent(displayImpTable);
        content.addComponent(displayExpectedResults);
        window.setContent(content);
        ui.addWindow(window);
    }

    private MenuBar.Command comandToSetAssumptionNo = (MenuBar.Command) selectedItem -> {
        checkbox1AssumptionWe.setValue(false);
        ui.getAuctionAssumptionLabel().setValue("Założenia: " +(checkbox1AssumptionWe.getValue() ?  "Po parti" :"Przed partią"));
        ui.setAssumption(false);
        ui.refreshBidGrids();
       // if (checkbox1AssumptionWe.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
       // else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command comandToSetAssumptionYes = (MenuBar.Command) selectedItem -> {
        checkbox1AssumptionWe.setValue(true);
        ui.getAuctionAssumptionLabel().setValue("Założenia: " +(checkbox1AssumptionWe.getValue() ?  "Po parti" :"Przed partią"));
        ui.setAssumption(true);
        ui.refreshBidGrids();
    //  if (checkbox1AssumptionWe.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
    //  else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command commandToOpenLegend = (MenuBar.Command) selectedItem -> actionOpenWindowWithLegend(ui);

    private MenuBar.Command commandToOpenDescription = (MenuBar.Command) selectedItem -> actionOpenWindowWithDescription(ui);
    private MenuBar.Command commandToCalculatePoints = (MenuBar.Command) selectedItem -> actionCalculetePoints(ui);
    private MenuBar.Command commandToManualCalculatePoints = (MenuBar.Command) selectedItem -> actionManualCalculetePoints(ui);

    private MenuBar.Command commandToDisplanyPointsTable = (MenuBar.Command) selectedItem -> actionDisplayPointsTable(ui);


    OptionMenu(VaadinUI ui) {
        this.ui = ui;
        checkbox1AssumptionWe.setValue(false);

        // First left top-level item
        this.addItem("Oblicz punkty", null, commandToCalculatePoints);

        this.addItem("Oblicz punkty ręcznie", null, commandToManualCalculatePoints);



        // A top-level menu item that opens a submenu
        MenuBar.MenuItem optionMenuItemsAuctionAssumption = this.addItem("Założenia licytacyjne:", QUESTION_CIRCLE, null);
        //TODO find if it is importatn what oponents are (if yes could be added but dont't think so - should be radioButton or sth to show if it is or not?
        optionMenuItemsAuctionAssumption.addItem("Obie przed partią", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("My przed, oni po", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("Oni przed, my po", null, comandToSetAssumptionYes);
        optionMenuItemsAuctionAssumption.addItem("Obie po partii", null, comandToSetAssumptionYes);

        // Another top-level item
        MenuBar.MenuItem instructionMenuItemBidsTypes = this.addItem("Legenda: ", null);
        instructionMenuItemBidsTypes.addItem("Opis typów odzywek w okienku - kliknij jak chcesz otworzyć. ", commandToOpenLegend);
        instructionMenuItemBidsTypes.addItem("Tabelki  do obliczania punktów.", null, commandToDisplanyPointsTable);
        instructionMenuItemBidsTypes.addItem("Zasady obliczania punktów", null, commandToOpenDescription);


        //  Yet another top-level item with biding system (moved from other menu moved to Vaadin because is to difficult ?)
        //  MenuBar.MenuItem otherBidingSystemMenuItem = this.addItem("Inne systemy licytacyjne: ", null, null);
        //  optionMenuBar.setStyleGenerator( -> getRowColorAndStyle(ItemReference));

        this.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        this.addStyleName("firstmenu");


    }
}
