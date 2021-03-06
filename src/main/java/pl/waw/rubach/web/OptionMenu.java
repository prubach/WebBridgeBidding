package pl.waw.rubach.web;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.waw.rubach.points.duplicateBridgeImps.*;
import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfGamesInRubber;
import pl.waw.rubach.points.rubberBridge.RubberScorring;

import static com.vaadin.icons.VaadinIcons.QUESTION_CIRCLE;
import static java.lang.Math.abs;
import static pl.waw.rubach.points.duplicateBridgeImps.Assumption.fillAssumptionThey;
import static pl.waw.rubach.points.duplicateBridgeImps.Assumption.fillAssumptionWe;
import static pl.waw.rubach.points.duplicateBridgeImps.AbstractImpTable.impDeclination;

class OptionMenu extends MenuBar {


    private final VaadinUI ui;
    private final CheckBox checkboxAssumptionWe = new CheckBox();
    private final CheckBox checkboxAssumptionThey = new CheckBox();
    private final CheckBox checkboxWe = new CheckBox(" My rozgrywamy - domyślnie");
    private int gameID = 0;
    private String descriptionOf4play;
    private CheckBox checkboxFitWe, checkboxFitThey, checkboxDouble, checkboxReDouble;
    private Label fitLabel, resultsLabel;
    private RadioButtonGroup<String> suitGroup, doubleRedoubleGroup;
    private TextField pointsForContractField, contractLevelField, numberOfTricksField, pointsInBothHandsField, colorOfContractField, impsField;


    OptionMenu(VaadinUI ui) {
        this.ui = ui;
        checkboxAssumptionWe.setValue(false);
        checkboxAssumptionThey.setValue(false);

        // First left top-level items to calculate points
        Command commandToCalculatePoints = (Command) selectedItem -> actionOpenWindow(ui, "Okienko do liczenia punktów za serię rozdań.", actionCalculetePoints());
        this.addItem("Oblicz punkty (nowe) ", null, commandToCalculatePoints);
        MenuBar.MenuItem optionMenuItemsCalculatePointsOld = this.addItem("Oblicz punkty (inne opcje):", null);
        Command commandToCalculatePointsRubberScorring = (Command) selectedItem -> actionOpenWindow(ui, "Okienko do liczenia punktów za jeden kontrakt.", actionCalculetePointsRubberScorring());
        optionMenuItemsCalculatePointsOld.addItem("Oblicz punkty - zapis robrowy", null, commandToCalculatePointsRubberScorring);
        Command commandToCalculatePointsOneDeal = (Command) selectedItem -> actionOpenWindow(ui, "Okienko do liczenia punktów za jeden kontrakt.", actionCalculetePointsOneDeal());
        optionMenuItemsCalculatePointsOld.addItem("Oblicz punkty jednego rozdania", null, commandToCalculatePointsOneDeal);

        Command commandToPredictWhichContractShouldBePlayed = (Command) selectedItem -> actionOpenWindow(ui, "Okienko do przeiwdywania wyników.", actionPredictsOneDealResult());
        optionMenuItemsCalculatePointsOld.addItem("Przewidywanki", null, commandToPredictWhichContractShouldBePlayed);
        Command commandToOldCalculatePoints = (Command) selectedItem -> actionOpenWindow(ui, "Stare okienko do ręcznego liczenia punktów.", actionManualCalculetePoints());
        optionMenuItemsCalculatePointsOld.addItem("Oblicz punkty ręcznie (stare):", null, commandToOldCalculatePoints);


        // A top-level menu item that opens a submenu - assumption
        MenuBar.MenuItem optionMenuItemsAuctionAssumption = this.addItem("Założenia licytacyjne:", QUESTION_CIRCLE, null);

        optionMenuItemsAuctionAssumption.addItem("Obie przed partią", null, (Command) selectedItem -> setAssumptionToGrid(false, false));
        optionMenuItemsAuctionAssumption.addItem("My przed, oni po", null, (Command) selectedItem -> setAssumptionToGrid(false, true));
        optionMenuItemsAuctionAssumption.addItem("My po, oni przed", null, (Command) selectedItem -> setAssumptionToGrid(true, false));
        optionMenuItemsAuctionAssumption.addItem("Obie po partii", null, (Command) selectedItem -> setAssumptionToGrid(true, true));

        // A top-level menu item that opens a submenu - description
        MenuBar.MenuItem instructionMenuItemBidsTypes = this.addItem("Legenda: ", null);
        Command commandToOpenLegend = (Command) selectedItem -> actionOpenWindow(ui, "Legenda - opis typów odzywek:", createLegendDescription());
        instructionMenuItemBidsTypes.addItem("Opis typów odzywek w okienku: ", commandToOpenLegend);
        Command commandToDisplayPointsTable = (Command) selectedItem -> actionOpenWindow(ui, "Tableki do liczenia punktów.", createPointsTable());
        instructionMenuItemBidsTypes.addItem("Tabelki  do obliczania punktów:", null, commandToDisplayPointsTable);
        Command commandToOpenDescription = (Command) selectedItem -> actionOpenWindow(ui, "Jak liczyć punkty? ", createDesciption());
        instructionMenuItemBidsTypes.addItem("Zasady obliczania punktów", null, commandToOpenDescription);
        Command commandToOpenDescriptionOfBiding = (Command) selectedItem -> actionOpenWindow(ui, "Dodatkowe informacje ", createDesciption2());
        instructionMenuItemBidsTypes.addItem("Wisty i zrzutki", null, commandToOpenDescriptionOfBiding);


        //  Yet another top-level item with biding system (moved from other menu moved to Vaadin because is to difficult ?)
        //  MenuBar.MenuItem otherBidingSystemMenuItem = this.addItem("Inne systemy licytacyjne: ", null, null);
        //  optionMenuBar.setStyleGenerator( -> getRowColorAndStyle(ItemReference));

        this.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        this.addStyleName("firstmenu");


    }

    private void setAssumptionToGrid(boolean assWe, boolean assThey) {
        checkboxAssumptionWe.setValue(assWe);
        checkboxAssumptionThey.setValue(assThey);
        ui.getAuctionAssumptionLabel().setValue("Założenia: " + (assWe ? "Po parti" : "Przed partią"));
        ui.setAssumption(assWe);
        ui.refreshBidGrids();

        //  if (checkboxAssumptionWe.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
        //  else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");

    }

    private void actionOpenWindow(VaadinUI ui, String title, FormLayout vL) {
        final Window window = new Window(title);
        window.setWidth("100%");
        //window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        vL.addStyleName("window");
        content.addComponent(vL);
        content.addStyleName("window");
        window.setContent(content);

        ui.addWindow(window);
        // sample.getUI().getUI().addWindow(window);
    }

    private void actionOpenWindow(VaadinUI ui, String title, VerticalLayout vL) {
        final Window window = new Window(title);
        window.setWidth("100%");
        //window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        vL.addStyleName("window");
        content.addComponent(vL);
        content.addStyleName("window");
        window.setContent(content);

        ui.addWindow(window);
        // sample.getUI().getUI().addWindow(window);
    }

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

    private VerticalLayout createDesciption() {
        VerticalLayout description = new VerticalLayout();

        String s = "\n" +
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


    private VerticalLayout createDesciption2() {
        VerticalLayout description = new VerticalLayout();

        String s = " \n Wist ODMIENNY (z figur naturalnie z blotek odwrotnie): " +
                "\n z sekwensu zawsze wistujemy kartą najstarszą, chyba że nasze figury są secowe (tzn. mamy tylko dwie karty w danym kolorze i jest to sekwens " +
                "\n- wtedy wistujemy figurą młodszą) albo chcemy poprosić partnera o odblokowanie (wtedy też wistujemy kartą młodszą),"+
                "\n wyjątek od tej zasady stanowi wyjście z dwóch honorów sec lub w kolor licytowany przez partnera – w takich przypadkach z pary honorów lub z sekwensu wychodzimy najstarszą kartą"+
                "\n z blotek wistujemy kartą drugą od góry,  " +
                "\n z drugiego honoru wistujemy honorem,  " +
                "\n spod trzeciego honoru wistujemy środkową kartą (nawet gdy również jest ona honorem)," +
                "\n spod czwartej lub dłuższej figury wistujemy czwartą najstarszą kartą (czwarta najlepsza),   " +
                "\n spod czwartej lub dłuższej dziesiątki postępujemy tak, jak przy wiście z blotek,  czyli wychodzimy drugą kartą od góry. " +
                "\n\n\n Przykłady:  "+
                "\n  Wist z blotek: " +
                "\n \txx       - blotka niższa z dwóch, "+
                "\n \txxx      - blotka środkowa, później blotka niższa z trzech ,"+
                "\n \txxxx     - druga od góry, później wyższa (wskazanie parzystości),"+
                "\n \txxxxx    - druga od góry, później niższa (wskazanie nieparzystości),"+
                "\n  Wist spod figury "+
                "\n \tFx       - wistujemy figurą, z 10x wistujemy 10,"+
                "\n \tFxx      - karta środkowa z trzech,"+
                "\n \tFxxx(xx) - czwarta najlepsza (z czterech lub wiecej),"+
                "\n Wist figurami z sekwensów"+
                "\n  \tA:   AKx (żąda ilościówki),"+
                "\n  \tK:   AK, KDx, KD(w sytuacjach, gdy nie może to zmylić partnera),KDW lub AKsec,"+
                "\n  \tD:   DWx, ADWx, DW (wyklucza króla!) "+
                "\n  \tW:   W10x, W10, AW10x, KW10x(xx), (wyklucza damę!)"+
                "\n  \t10:  A109x, K109x, D109x, 10x(wyklucza waleta!)"+
                "\n  \t9:   109x(x,x), "+
                "" +
                "\n\n\n Zrzutki odwrotne: "+


                "\n          Sygnał ilościowy"+
                "\n  niższa - wyższa - wskazuje na parzystą liczbę kart w kolorze,"+
                "\n  wyższa - niższa - wskazuje nieparzystą liczbę kart w kolorze,"+
                "\n        Sygnał jakościowy"+
                "\n  niska blotka (niższa - wyższa)  - zachęca do kontynuowania wistu (marka)"+
                "\n  wysoka blotka (wyższa - niższa) - zniechęca do kontynuowania wistu (demarka)"+
                " \n\n czyli konkretnie: "+
                "\n  -do koloru partnera dokładamy markę – jakościówka (mała karta lub młodsza – starsza zachęca [demarka to duża lub starsza młodsza]; " +
                "\n \t \t W sytuacji, gdy lewa zostanie zabita na ręce przed nami i nie mamy karty starszej, użyteczna może być ilościówka." +
                "\n  -do koloru rozygrywającego ilościówkę (dołożenie w danym kolorze młodsza starsza – parzysta liczba kart w tym kolorze; starsza – młodsza -nieparzystą);" +
                "\n - Lavintal do koloru innego (mała – mam figurę – wychodź w ten kolor ; duża – nie mam figury – nie chcę tego koloru)" +
                "\n -do koloru atutowego LAVINTHAL; (wyższa-młodsza= wartości w starszych)    do atu rozgrywającego 962 mam coś najstarszym kolorze / 269 mam coś w najmłodszym kolorze, 692 mam coś w środkowym kolorze" +
                "\n -zrzutki nie do koloru przy grze atutowej: marka bezpośrednia (np. mała trefl - dobre trefle);" +
                "\n zrzutki przy grze w BA:   potwierdzenie wistu; -mała potwierdza;" +
                "\n zrzutka krakowska (pierwsza zrzutka bezwartościowego koloru (jak nie ma do koloru)) wskazuje swoją wysokością wyższa starszy, niska młodszy z pozostałych kolorów;" +
                "\n Wiele par stosuje także markę bezpośrednią przy obronie przeciwko kontraktom kolorowym, a zrzutkę krakowską przy bez atu.";

        TextArea text = new TextArea("Wisty i zrzutki");
        text.setStyleName("description");
        text.setValue(s);
        text.setWidth("100%");
        text.setHeight("400px");
        description.addComponent(text);

        return description;
    }

    private VerticalLayout createPointsTable() {
        VerticalLayout table = new VerticalLayout();


        table.addComponent(new Label("Wersja wstępna - tabelki do liczenia punktów dla wariantów podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));

        table.addStyleName("window");

        createAllInputElement();

        table.addComponent(checkboxAssumptionWe);
        table.addComponent(checkboxFitWe);
        table.addComponent(fitLabel);
        // table.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy dowolny kolor jest sfitowany, jeżeli mniej to tylko starszy."));
        //  checkboxAssumptionWe.addValueChangeListener(event ->
        //         checkboxFitWe.setValue(! checkboxAssumptionWe.getValue()));

        Button displayExpectedResults = new Button("Wyświetl tabelkę  oczekiwanych punktów dla  danych założeń! ", clickEvent -> {
            TextArea a = new TextArea();
            a.setWidth("100%");
            a.setValue(ExpectedResultsTable.getTableAsString(checkboxFitWe.getValue(), checkboxAssumptionWe.getValue()));
            table.addComponent(a);
        });

        Button displayImpTable = new Button("Wyświetl tabelkę impów! ", clickEvent -> {
            TextArea b = new TextArea();
            b.setWidth("100%");
            b.setValue(ImpTable.getTableAsString());
            table.addComponent(b);
        });
        table.addComponent(displayImpTable);
        table.addComponent(displayExpectedResults);


        return table;
    }

    private void createAllInputElement() {
        checkboxAssumptionWe.setCaption("Czy jesteście po partii (ustawiane w głównej aplikacji albo tutaj)? ");
        checkboxAssumptionThey.setCaption("Czy przeciwnicy są  po partii (ustawiane w głównej aplikacji albo tutaj)? ");

        checkboxFitWe = new CheckBox("Czy  macie fit (domyślnie tak - ustawiane tutaj)?", true);
        checkboxFitThey = new CheckBox("Czy  jest fit u przeciwników (domyślnie nie - ustawiane tutaj)?", false);
        fitLabel = new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze.");

        checkboxDouble = new CheckBox("Czy  była kontra (domyślnie nie - ustawiane tutaj)?", false);
        checkboxReDouble = new CheckBox("Czy  była rekontra (domyślnie nie - ustawiane tutaj)?", false);

        doubleRedoubleGroup = new RadioButtonGroup<>();
        doubleRedoubleGroup.setItems("Kontra", "Rekontra");
        doubleRedoubleGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        doubleRedoubleGroup.addValueChangeListener(event -> {
            if (event.getValue().equals("Kontra")) checkboxDouble.setValue(true);
            else if (event.getValue().equals("Rekontra")) checkboxReDouble.setValue(true);
        });

        pointsInBothHandsField = new TextField("Podaj liczbę punktów na obu swoich rękach (wraz z punktami układowymi):");
        contractLevelField = new TextField("Podaj wysokość granego kontraktu:");
        colorOfContractField = new TextField("Wybierz kolor granego kontraktu: (bez atu  [nt], piki [s], trefle [c], kiery [h], kara [d]");
        numberOfTricksField = new TextField("Podaj liczbę zebranych  lew:");
        pointsForContractField = new TextField("Podaj liczbę punktów uzyskanych przy rozgrywaniu kontraktu:");


        pointsInBothHandsField.addValueChangeListener(event -> resultsLabel.setValue(""));
        contractLevelField.addValueChangeListener(event -> resultsLabel.setValue(""));
        colorOfContractField.addValueChangeListener(event -> resultsLabel.setValue(""));
        numberOfTricksField.addValueChangeListener(event -> resultsLabel.setValue(""));
        pointsForContractField.addValueChangeListener(event -> resultsLabel.setValue(""));


        suitGroup = new RadioButtonGroup<>();
        suitGroup.setItems("Bez Atu", "Piki", "Kiery", "Trefle", "Kara");
        suitGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        //    JLabel message2 = new JLabel("Akuku");
        suitGroup.addValueChangeListener(event -> {
            switch (event.getValue()) {
                case "Bez Atu":
                    colorOfContractField.setValue("N");
                    break;
                case "Piki":
                    colorOfContractField.setValue("S");
                    break;
                case "Kiery":
                    colorOfContractField.setValue("H");
                    break;
                case "Trefle":
                    colorOfContractField.setValue("C");
                    break;
                case "Kara":
                    colorOfContractField.setValue("D");
                    break;
                default:
                    colorOfContractField.setValue(event.getValue());
                    break;
            }
            //      message2.setText(String.format("Radio button suitGroup value changed from '%s' to '%s'", event.getOldValue(), event.getValue()));}
        });


        resultsLabel = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);
    }

    private void fillExampleData() {
        pointsInBothHandsField.setValue("15");
        contractLevelField.setValue("3");
        colorOfContractField.setValue("NT");
        suitGroup.setValue("Bez Atu");
        numberOfTricksField.setValue("4");
        checkboxFitWe.setValue(false);
        checkboxFitThey.setValue(false);
        checkboxReDouble.setValue(false);
        checkboxDouble.setValue(false);

    }


    private FormLayout actionManualCalculetePoints() {
        FormLayout vL = new FormLayout();
        vL.addComponent(new Label("Wersja 1.1 - podaj ile macie punktów na ręku i ile zdobyliście punktów - liczone dla dowolnej pary! :)"));

        createAllInputElement();

        vL.addComponent(checkboxAssumptionWe);
        vL.addComponent(checkboxAssumptionThey);
        vL.addComponent(pointsInBothHandsField);

        vL.addComponent(checkboxFitWe);
        vL.addComponent(checkboxFitThey);
        vL.addComponent(fitLabel);
        vL.addComponent(pointsForContractField);


        Button calculateImp = new Button("Oblicz impy! ", clickEvent -> {
            try {
                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(
                       // checkboxWe.getValue(),
                        Float.parseFloat(pointsInBothHandsField.getValue()),
                        Integer.parseInt(pointsForContractField.getValue()),
                        checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(),
                        checkboxFitWe.getValue(), checkboxFitThey.getValue());

                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + a.getResultsWe(checkboxWe.getValue()) + " impów (punktów).  " +
                        "</B>  <BR> jeżeli liczba punktów jest ujemna to zapisuje się punkty po stronie przeciwników. ");

            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowy format  punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });

        vL.addComponent(calculateImp);
        vL.addComponent(resultsLabel);
        return vL;
    }

    private FormLayout actionCalculetePointsOneDeal() {
        FormLayout vL = new FormLayout();
        vL.addComponent(new Label("Wersja 1.2 - podaj jaki był kontrakt i co ugraliście - liczone dla pary rozgrywającej! :)"));

        createAllInputElement();
        vL.addComponent(checkboxAssumptionWe);
        vL.addComponent(checkboxAssumptionThey);
        vL.addComponent(pointsInBothHandsField);

        vL.addComponent(checkboxFitWe);
        vL.addComponent(checkboxFitThey);
        vL.addComponent(fitLabel);

        vL.addComponent(contractLevelField);
        //    vL.addComponent(colorOfContractField);
        vL.addComponent(suitGroup);
        vL.addComponent(doubleRedoubleGroup);
        vL.addComponent(numberOfTricksField);


        Button calculateImpPoints = new Button("Oblicz punkty i impy za jedno rodzanie (dla rozgrywającego)! ", clickEvent -> {
            try {

                DuplicateBridgeScoring duplicateBridgeScoring = new DuplicateBridgeScoring(Integer.parseInt(contractLevelField.getValue()), colorOfContractField.getValue(),
                        checkboxDouble.getValue(), checkboxReDouble.getValue(), checkboxAssumptionWe.getValue(), Integer.parseInt(numberOfTricksField.getValue()));

                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(
                        Float.parseFloat(pointsInBothHandsField.getValue()), duplicateBridgeScoring.getDeclarerContractScoringPoints(),
                        checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());

                resultsLabel.setValue("<B>W tym rozdaniu rozgrywający uzyskał " + duplicateBridgeScoring.getDeclarerContractScoringPoints() +
                        " punktów za kontrakt, (" + duplicateBridgeScoring.getDescription() + ") </B> " +
                        " <BR> czyli " + a.getDeclarerResluts() + impDeclination(a.getDeclarerResluts()) + ".  ");

            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowy format  punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });

        fillExampleData();
        vL.addComponent(calculateImpPoints);
        vL.addComponent(resultsLabel);
        return vL;
    }

    private FormLayout actionCalculetePointsRubberScorring() {
        FormLayout vL = new FormLayout();
        vL.addComponent(new Label("Wersja R1.1 - podaj jaki był kontrakt i co ugraliście - zapis robrowy :)"));

        createAllInputElement();
        vL.addComponent(contractLevelField);
        vL.addComponent(suitGroup);
        vL.addComponent(doubleRedoubleGroup);
        vL.addComponent(numberOfTricksField);

        checkboxWe.setValue(true);
        vL.addComponent(checkboxWe);

        final RubberScorring aa = new RubberScorring(1);
        Button calculateRubberPoints = new Button("Oblicz punkty  za jedno rodzanie (dla rozgrywającego)! ", clickEvent -> {
            try {
               int result =  aa.fillOneContract(checkboxWe.getValue(),Integer.parseInt(contractLevelField.getValue()),colorOfContractField.getValue(),
                        checkboxDouble.getValue(), checkboxReDouble.getValue(),Integer.parseInt(numberOfTricksField.getValue()));

                resultsLabel.setValue("<B>W tym rozdaniu numer " + aa.getContractNumber() + " rozgrywający uzyskał " + result +
                        " punktów za kontrakt,  </B> " +
                        " <BR> czyli do tej pory wynik jest dla nas:" + aa.getOurWiningScorring() + ", " +
                        "a suma punktów jest (my/oni)"+ aa.getUnderWeSumm()+aa.getOverWeSumm() +"/"+ aa.getOverTheySumm()+aa.getUnderTheySumm() );

            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowy format  punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });

        fillExampleData();
        vL.addComponent(calculateRubberPoints);
        vL.addComponent(resultsLabel);
        return vL;
    }


    private FormLayout actionPredictsOneDealResult() {
        FormLayout vL = new FormLayout();
        vL.addComponent(new Label("Wersja 1.1 - podaj założenia i twoje oczekiwania - uzyskasz wiadomość ile punków musisz zdobyć  :)"));

        createAllInputElement();
        vL.addComponent(checkboxAssumptionWe);
        vL.addComponent(checkboxAssumptionThey);
        vL.addComponent(pointsInBothHandsField);

        vL.addComponent(checkboxFitWe);
        vL.addComponent(checkboxFitThey);
        vL.addComponent(fitLabel);
        vL.addComponent(checkboxWe);

        //vL.addComponent(contractLevelField);
        //vL.addComponent(colorOfContractField);
        //  vL.addComponent(new Label("Zaznacz preferowany kolor fitu (domyślnie bez atu)"));
        //  vL.addComponent(suitGroup);
        //vL.addComponent(doubleRedoubleGroup);
        //vL.addComponent(numberOfTricksField);

        impsField = new TextField("Podaj ile impów chciałbyś/chciałabyś ugrać:");
        impsField.addValueChangeListener(event -> resultsLabel.setValue(""));
        vL.addComponent(impsField);
        impsField.setValue("0");

        Button calculateImpPoints = new Button("Wykonaj przewidywanie jednego rodzania ! ", clickEvent -> {
            try {

                Prediction a = new Prediction(Integer.parseInt(impsField.getValue()),
                        Float.parseFloat(pointsInBothHandsField.getValue()),
                        checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(),
                        checkboxFitWe.getValue(), checkboxFitThey.getValue(),
                        checkboxWe.getValue());

                resultsLabel.setValue(a.getDes());
            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowy format  punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });

        fillExampleData();
        vL.addComponent(calculateImpPoints);
        vL.addComponent(resultsLabel);
        return vL;
    }

    private FormLayout actionCalculetePoints() {
        FormLayout vL = new FormLayout();
        vL.addComponent(new Label("Wersja 1.3 - podaj jaki był kontrakt, ile mieliście puntów i ile zostało ugrane (i przez kogo):)"));

        createAllInputElement();
        vL.addComponent(pointsInBothHandsField);

        vL.addComponent(checkboxFitWe);
        vL.addComponent(checkboxFitThey);
        vL.addComponent(fitLabel);

        vL.addComponent(doubleRedoubleGroup);

        vL.addComponent(contractLevelField);
        vL.addComponent(suitGroup);
        vL.addComponent(numberOfTricksField);

        checkboxWe.setValue(true);
        vL.addComponent(checkboxWe);


        TextField numberOfContract = new TextField("Podaj które to rozdanie (numer rozdania określa założenia: 1-obie przed, 2-my po, 3 oni po, 4 obie po:");
        vL.addComponent(numberOfContract);
        numberOfContract.addValueChangeListener(event -> resultsLabel.setValue(""));


        Button calculateImpPoints = new Button("Oblicz punkty i impy za jedno rozdanie - niezależnie kto gra! ", clickEvent -> {
            try {
                int contractNumber = Integer.parseInt(numberOfContract.getValue()); //bo liczy od zera
                if (contractNumber > 4 || contractNumber <= 0) throw new InvalidNumberOfGamesInRubber(contractNumber);


                DuplicateBridgeScoring duplicateBridgeScoring =
                        new DuplicateBridgeScoring(Integer.parseInt(contractLevelField.getValue()), colorOfContractField.getValue(),
                                checkboxDouble.getValue(), checkboxReDouble.getValue(),
                                checkboxWe.getValue() ? fillAssumptionWe(contractNumber) : fillAssumptionThey(contractNumber),
                                checkboxWe.getValue() ? Integer.parseInt(numberOfTricksField.getValue()) : 13 - Integer.parseInt(numberOfTricksField.getValue()));
                String des = duplicateBridgeScoring.getDescription();

                int pointsContractWe = checkboxWe.getValue() ? duplicateBridgeScoring.getDeclarerContractScoringPoints() : -duplicateBridgeScoring.getDeclarerContractScoringPoints();

                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(checkboxWe.getValue(),
                        Float.parseFloat(pointsInBothHandsField.getValue()),
                        pointsContractWe,
                        fillAssumptionWe(contractNumber), fillAssumptionThey(contractNumber),
                        checkboxFitWe.getValue(), checkboxFitThey.getValue());

                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + pointsContractWe + " punktów za kontrakt, (" + des + ") </B> " +
                        " <BR> czyli " + a.getResultsWe(checkboxWe.getValue()) + impDeclination(abs(a.getResultsWe(checkboxWe.getValue()))) + ". ");// +

            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowy format  punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });


        String[] descriptionTable = {"pierwszy kontrakt:", "drugi kontrakt", "trzeci kontrakt", "czwarty kontrakt"};
        Label resultsLabelFor4Game = new Label("");
        resultsLabelFor4Game.setContentMode(ContentMode.HTML);
        setGameID(getGameID() + 1);
        final FourGameImpScorring aa = new FourGameImpScorring(getGameID());

        Button makeNew4GameScoring = new Button("Zacznij nowy zapis 4 rozdań", clickEvent -> {
            setGameID(getGameID() + 1);
        });

        Button makeScorringOfFourDeal = new Button("Prowadz zapis 4 rozdań! ", clickEvent -> {
            try {
                int contractNumber = Integer.parseInt(numberOfContract.getValue()); //bo liczy od zera
                if (contractNumber > 4 || contractNumber <= 0) throw new InvalidNumberOfGamesInRubber(contractNumber);


                DuplicateBridgeScoring duplicateBridgeScoring =
                        new DuplicateBridgeScoring(Integer.parseInt(contractLevelField.getValue()), colorOfContractField.getValue(),
                                checkboxDouble.getValue(), checkboxReDouble.getValue(),
                                checkboxWe.getValue() ? fillAssumptionWe(contractNumber) : fillAssumptionThey(contractNumber),
                                checkboxWe.getValue() ? Integer.parseInt(numberOfTricksField.getValue()) : 13 - Integer.parseInt(numberOfTricksField.getValue()));


                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(checkboxWe.getValue(),
                        Float.parseFloat(pointsInBothHandsField.getValue()),
                        //    checkboxWe.getValue() ? duplicateBridgeScoring.getDeclarerContractScoringPoints() : -duplicateBridgeScoring.getDeclarerContractScoringPoints(),
                        duplicateBridgeScoring.getContractScoringPointsWe(checkboxWe.getValue()),
                        fillAssumptionWe(contractNumber), fillAssumptionThey(contractNumber),
                        checkboxFitWe.getValue(), checkboxFitThey.getValue());

                descriptionTable[contractNumber - 1] =
                        "Kontrakt nr. " + (contractNumber) + ": (gra" + (checkboxWe.getValue() ? " my my" : "ją oni.") + ") "
                                + duplicateBridgeScoring.getShortDescription() + duplicateBridgeScoring.getDescription() + "\n " + a.getFitDescriprtion();


                //int imp =
                aa.fillOneContract(contractNumber, a);
               /* int imp = aa.fillOneContract(contractNumber, checkboxWe.getValue(),
                        Float.parseFloat(pointsInBothHandsField.getValue()), Integer.parseInt(contractLevelField.getValue()), colorOfContractField.getValue(),
                        Integer.parseInt(numberOfTricksField.getValue()), checkboxDouble.getValue(), checkboxReDouble.getValue(),
                        checkboxFitWe.getValue(), checkboxFitThey.getValue());
*/
                resultsLabel.setValue("<B>To " + numberOfContract.getValue() + "  rozdanie i uzyskaliście "
                        + (checkboxWe.getValue() ? duplicateBridgeScoring.getDeclarerContractScoringPoints() : -duplicateBridgeScoring.getDeclarerContractScoringPoints())
                        + " punktów za kontrakt, czyli " + a.getResultsWe(checkboxWe.getValue()) + impDeclination(a.getResultsWe(checkboxWe.getValue())) + ". "
                        + " </B>  <BR> W sumie uzyskaliście do tej pory w ostanich rozdaniach " + aa.getSumm() + impDeclination(aa.getSumm()) + ". ");
                //         + " </B>  <BR> W sumie uzyskaliście do tej pory w ostanich rozdaniach :" + imp + impDeclination(imp) + ". ");


                descriptionOf4play = makeDescription(aa, descriptionTable);

//w tym przypadku obydwa są ok, bo ciężko powiedzieć czy lepiej użyć instanceof czy duplikować blok
//pyt to może zrobię dwa z różnym kolorowaniem :) ???
            } catch (BridgeException e) {
                String mes1 = e.getMessage();
                resultsLabel.setValue("<font color=red>" + mes1 + "</font>");
            } catch (NumberFormatException e) {
                String mes1 = "Nieprawidłowy format liczby-  spróbuj jeszcze raz!";
                resultsLabel.setValue("<font color=pink>" + mes1 + "</font>");
            }

        });


        Button showResultsOfFourDeals = new Button("Pokaz wyniki ostatnich  4 rozdań! ", clickEvent ->
                actionOpenWindow(this.ui, "Okienko z wynikami ostatnich czterech rozdań.", actionDisplayResultsOf4GameWindow()));

        fillExampleData();

        vL.addComponent(calculateImpPoints);
        vL.addComponent(makeScorringOfFourDeal);
        vL.addComponent(resultsLabel);
        vL.addComponent(showResultsOfFourDeals);
        vL.addComponent(makeNew4GameScoring);
        return vL;
    }

    private String makeDescription(FourGameImpScorring aa, String[] descriptionTable) throws BridgeException {
        StringBuilder s = new StringBuilder("\n*** Zapis gier numer: " + aa.getGameID() + ".  ***  \n");
        for (int i = 0; i < 4; i++) s.append("\n").append(descriptionTable[i]);

        s.append("\n\n \t \t***\n");

        return s.toString() + "\n" + aa.getGameScoringAsString();
    }




  /*  private boolean[] fillAssumption(int contractNumber) {
        boolean[] auctionAssumption = {false, false};
        if (contractNumber == 2) {
            auctionAssumption[0] = true;
        } else if (contractNumber == 3) {
            auctionAssumption[1] = true;
        } else if (contractNumber == 4) {
            auctionAssumption[0] = true;
            auctionAssumption[1] = true;
        }
        return auctionAssumption;
    }*/

    private VerticalLayout actionDisplayResultsOf4GameWindow() {
        VerticalLayout vL = new VerticalLayout();
        vL.addComponent(new Label("Wersja wstępna - wyniki ostatnich zapisanych rozdań :)"));
        vL.addComponent(new Label("Uwaga na razie nie ma możliwości zapisu edytowanego tekstu"));
        TextArea a = new TextArea();
        a.setWidth("100%");
        // a.setHeight("800%");
        a.setValue(descriptionOf4play);
        //a.setEditable(false);
        vL.addComponent(a);
        vL.addStyleName("window");

        return vL;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
