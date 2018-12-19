package pl.waw.rubach.web;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.waw.rubach.points.*;
import pl.waw.rubach.points.exceptions.*;

import static com.vaadin.icons.VaadinIcons.QUESTION_CIRCLE;

class OptionMenu extends MenuBar {


    private VaadinUI ui;

    private String descriprionOf4play;
    private CheckBox checkboxAssumptionWe = new CheckBox("Czy jesteście po partii (ustawiane w głównej aplikacji albo tutaj)? ");
    private CheckBox checkboxAssumptionThey = new CheckBox("Czy przeciwnicy są  po partii (ustawiane w głównej aplikacji albo tutaj)? ");

    private CheckBox checkboxFitWe = new CheckBox("Czy  macie fit (domyślnie tak - ustawiane tutaj)?");
    private CheckBox checkboxFitThey = new CheckBox("Czy  jest fit u przeciwników (domyślnie nie - ustawiane tutaj)?");

    private CheckBox checkboxDouble = new CheckBox("Czy  była kontra (domyślnie nie - ustawiane tutaj)?");
    private CheckBox checkboxReDouble = new CheckBox("Czy  była rekontra (domyślnie nie - ustawiane tutaj)?");


    private TextField contractLevelField ,numberOfTricksField ,pointsInBothHandsField, colorOfContractField;
    private TextField contractLevelField4 ,numberOfTricksField4 ,pointsInBothHandsField4, colorOfContractField4;


    private CheckBox checkboxWe = new CheckBox(" My rozgrywamy - domyślnie");
    private MenuBar.Command comandToSetAssumptionNo = (MenuBar.Command) selectedItem -> {
        checkboxAssumptionWe.setValue(false);
        ui.getAuctionAssumptionLabel().setValue("Założenia: " + (checkboxAssumptionWe.getValue() ? "Po parti" : "Przed partią"));
        ui.setAssumption(false);
        ui.refreshBidGrids();
        // if (checkboxAssumptionWe.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
        // else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command comandToSetAssumptionYes = (MenuBar.Command) selectedItem -> {
        checkboxAssumptionWe.setValue(true);
        ui.getAuctionAssumptionLabel().setValue("Założenia: " + (checkboxAssumptionWe.getValue() ? "Po parti" : "Przed partią"));
        ui.setAssumption(true);
        ui.refreshBidGrids();
        //  if (checkboxAssumptionWe.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
        //  else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command commandToOpenLegend = (MenuBar.Command) selectedItem -> actionOpenWindowWithLegend(ui);
    private MenuBar.Command commandToOpenDescription = (MenuBar.Command) selectedItem -> actionOpenWindowWithDescription(ui);
    private MenuBar.Command commandToCalculatePoints = (MenuBar.Command) selectedItem -> actionCalculetePoints(ui);
    private MenuBar.Command commandToCalculatePointsOneDeal = (MenuBar.Command) selectedItem -> actionCalculetePointsOneDeal(ui);
    private MenuBar.Command commandToOldCalculatePoints = (MenuBar.Command) selectedItem -> actionManualCalculetePoints(ui);
    private MenuBar.Command commandToDisplanyPointsTable = (MenuBar.Command) selectedItem -> actionDisplayPointsTable(ui);

    OptionMenu(VaadinUI ui) {
        this.ui = ui;
        checkboxAssumptionWe.setValue(false);

        // First left top-level item
        this.addItem("Oblicz punkty (nowe) ", null, commandToCalculatePoints);
        this.addItem("Oblicz punkty jednego rozdania", null, commandToCalculatePointsOneDeal);
        this.addItem("Oblicz punkty ręcznie (stare):", null, commandToOldCalculatePoints);


        // A top-level menu item that opens a submenu
        MenuBar.MenuItem optionMenuItemsAuctionAssumption = this.addItem("Założenia licytacyjne:", QUESTION_CIRCLE, null);
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

    private void actionDisplayPointsTable(VaadinUI ui) {
        final Window window = new Window("Okienko z tabelkami do liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna - tabelki do liczenia punktów dla wariantów podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));

        content.addStyleName("window");

        //   checkboxAssumptionWe.setValue(true); //move to create menu?
        checkboxFitWe.setValue(true);

        content.addComponent(checkboxAssumptionWe);
        content.addComponent(checkboxFitWe);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy dowolny kolor jest sfitowany, jeżeli mniej to tylko starszy."));
        //  checkboxAssumptionWe.addValueChangeListener(event ->
        //         checkboxFitWe.setValue(! checkboxAssumptionWe.getValue()));

        Button displayExpectedResults = new Button("Wyświetl tabelkę  oczekiwanych punktów dla  danych założeń! ", clickEvent -> {
            TextArea a = new TextArea();
            a.setWidth("100%");
            a.setValue(ExpectedResultsTable.getTableAsString(checkboxFitWe.getValue(), checkboxAssumptionWe.getValue()));
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



    private void actionCalculetePointsOneDeal(VaadinUI ui) {
        final Window window = new Window("Okienko do liczenia punktów za jeden kontrakt.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja 1.2 - podaj jaki był kontrakt i co ugraliście - liczone dla pary rozgrywającej! :)"));
        content.addStyleName("window");

        content.addComponent(checkboxAssumptionWe);
        content.addComponent(checkboxAssumptionThey);

        pointsInBothHandsField = new TextField("Podaj liczbę punktów na obu swoich rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHandsField);

        content.addComponent(checkboxFitWe);
        content.addComponent(checkboxFitThey);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze."));

        contractLevelField = new TextField("Podaj wysokość granego kontraktu:");
        content.addComponent(contractLevelField);

        colorOfContractField = new TextField("Wybierz kolor granego kontraktu: (bez atu  [nt], piki [s], trefle [c], kiery [h], kara [d]");
        content.addComponent(colorOfContractField);

         content.addComponent(checkboxDouble);
         content.addComponent(checkboxReDouble);


         numberOfTricksField = new TextField("Podaj liczbę zebranych  lew:");
         content.addComponent(numberOfTricksField);

        Label resultsLabel = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);

       // fillZeroData();
        contractLevelField.addValueChangeListener(event -> resultsLabel.setValue(""));
        pointsInBothHandsField.addValueChangeListener(event -> resultsLabel.setValue(""));
        numberOfTricksField.addValueChangeListener(event -> resultsLabel.setValue(""));
        colorOfContractField.addValueChangeListener(event -> resultsLabel.setValue(""));


       fillExampleData();
        Button calculateImpPoints = new Button("Oblicz punkty i impy! ", clickEvent -> {
            try {

                DuplicateBridgeScoring duplicateBridgeScoring = new DuplicateBridgeScoring(Integer.parseInt(contractLevelField.getValue()), colorOfContractField.getValue(),
                        checkboxDouble.getValue(), checkboxReDouble.getValue(), checkboxAssumptionWe.getValue(), Integer.parseInt(numberOfTricksField.getValue()));

                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(!checkboxWe.getValue(),
                        Float.parseFloat(pointsInBothHandsField.getValue()), duplicateBridgeScoring.getContractScoringPoints(),
                        checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());

                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + duplicateBridgeScoring.getContractScoringPoints() + " punktów za kontrakt, (" + duplicateBridgeScoring.getDescription() + ") </B>  <BR> czyli " + a.getResults() + " impów.  ");// +

            } catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowo podana liczba punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });


        content.addComponent(calculateImpPoints);
        content.addComponent(resultsLabel);
        window.setContent(content);
        ui.addWindow(window);
    }



    private void actionCalculetePoints(VaadinUI ui) {
        final Window window = new Window("Okienko do liczenia punktów za jeden kontrakt.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja 1.3 - podaj jaki był kontrakt i co ugraliście:)"));

        content.addStyleName("window");

        pointsInBothHandsField4 = new TextField("Podaj liczbę punktów na obu swoich rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHandsField4);

        //   checkboxAssumptionWe.setValue(true); //move to create menu?
        checkboxFitWe.setValue(true);
        content.addComponent(checkboxFitWe);

        checkboxFitThey.setValue(false);
        content.addComponent(checkboxFitThey);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze."));

        checkboxDouble.setValue(false);
        content.addComponent(checkboxDouble);

        checkboxReDouble.setValue(false);
        content.addComponent(checkboxReDouble);

        contractLevelField4 = new TextField("Podaj wysokość granego kontraktu:");
        content.addComponent(contractLevelField4);

        colorOfContractField4 = new TextField("Wybierz kolor granego kontraktu:");
        content.addComponent(colorOfContractField4);

        numberOfTricksField4 = new TextField("Podaj liczbę zebranych  lew:"); //przez rozgrywających
        content.addComponent(numberOfTricksField4);


        checkboxWe.setValue(true);
        content.addComponent(checkboxWe);


        Label resultsLabel = new Label("");
        Label resultsLabelFor4Game = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);
        resultsLabelFor4Game.setContentMode(ContentMode.HTML);
        pointsInBothHandsField4.addValueChangeListener(event -> resultsLabel.setValue(""));
        numberOfTricksField4.addValueChangeListener(event -> resultsLabel.setValue(""));
        contractLevelField4.addValueChangeListener(event -> resultsLabel.setValue(""));

        checkboxDouble.setValue(!checkboxReDouble.getValue());

        TextField numberOfContract = new TextField("Podaj które to rozdanie (tylko dla zapisu 4 rozdań):");
        content.addComponent(numberOfContract);
        numberOfContract.addValueChangeListener(event -> resultsLabel.setValue(""));


        Button calculateImpPoints = new Button("Oblicz punkty i impy! ", clickEvent -> {
            try {

                checkboxDouble.setValue(!checkboxReDouble.getValue());
                String suit = colorOfContractField4.getValue().toUpperCase();
                int tricksTakenWe = Integer.parseInt(numberOfTricksField.getValue());
                float pointsInBHWe = Float.parseFloat(pointsInBothHandsField.getValue());
                boolean assumptionWe = fillAssumption(Integer.parseInt(numberOfContract.getValue()) - 1)[0];
                boolean assumptionThey = fillAssumption(Integer.parseInt(numberOfContract.getValue()) - 1)[1];

                DuplicateBridgeScoring duplicateBridgeScoring = new DuplicateBridgeScoring(Integer.parseInt(contractLevelField4.getValue()), suit, checkboxDouble.getValue(), checkboxReDouble.getValue(),
                        checkboxWe.getValue() ? assumptionWe : assumptionThey, checkboxWe.getValue() ? tricksTakenWe : 13 - tricksTakenWe);
                String des = duplicateBridgeScoring.getDescription();

                int pointsContractWe = checkboxWe.getValue() ? duplicateBridgeScoring.getContractScoringPoints() : -duplicateBridgeScoring.getContractScoringPoints();

                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(checkboxWe.getValue(),
                        pointsInBHWe, pointsContractWe, assumptionWe, assumptionThey, checkboxFitWe.getValue(), checkboxFitThey.getValue());
                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + pointsContractWe + " punktów za kontrakt, (" + des + ") </B>  <BR> czyli " + a.getResults() + " impów.  ");// +

            }
            //pyt cz1: czy lepiej tak jak jest instance of ale w jednej linijce (i raz kolorowane)
            catch (NumberFormatException | BridgeException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowo podana liczba punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            }
        });


        boolean[] checkboxFitWeTable = {false, false, false, false};
        boolean[] checkboxFitTheyTable = {false, false, false, false};

        String[] descriptionTable = {"pierwszy kontrakt:", "drugi kontrakt", "trzeci kontrakt", "czwarty kontrakt"};
        float poinsInBHTable[] = {0, 0, 0, 0};
        int scoringTable[] = {0, 0, 0, 0};
        int tricskTakenTable[] = {0, 0, 0, 0};


        Button prowadzZapis = new Button("Prowadz zapis 4 rozdań! ", clickEvent -> {
            try {
                int contractNumber = Integer.parseInt(numberOfContract.getValue()) - 1; //bo liczy od zera
                if (contractNumber > 4 || contractNumber < 0) throw new InvalidNumberOfGamesInRuber(contractNumber);


                checkboxFitWeTable[contractNumber] = checkboxFitWe.getValue();
                checkboxFitTheyTable[contractNumber] = checkboxFitThey.getValue();

                String suit = colorOfContractField.getValue().toUpperCase();
                int trickTaken = Integer.parseInt(numberOfTricksField.getValue());
                tricskTakenTable[contractNumber]=trickTaken;

                float pointsInBH = Float.parseFloat(pointsInBothHandsField.getValue());
                poinsInBHTable[contractNumber] = pointsInBH;

                DuplicateBridgeScoring scoring = new DuplicateBridgeScoring(Integer.parseInt(contractLevelField4.getValue()), suit, checkboxDouble.getValue(), checkboxReDouble.getValue(), fillAssumption(contractNumber)[0], trickTaken);
                descriptionTable[contractNumber] = descriptionTable[contractNumber] + scoring.getShortDescription();
                scoringTable[contractNumber] = checkboxWe.getValue() ? scoring.getContractScoringPoints() : -scoringTable[contractNumber];


                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(checkboxWe.getValue(),poinsInBHTable[contractNumber], scoringTable[contractNumber], checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());

                RubberScoring aa = new RubberScoring();
                aa.fillOneContractFrom4GameSet(contractNumber,a);
                //RubberScoring aa = new RubberScoring(poinsInBHTable[0], poinsInBHTable[1], poinsInBHTable[2], poinsInBHTable[3], scoringTable[0], scoringTable[1], scoringTable[2], scoringTable[3], checkboxFitWeTable[0], checkboxFitWeTable[1], checkboxFitWeTable[2], checkboxFitWeTable[3], checkboxFitTheyTable[0], checkboxFitTheyTable[1], checkboxFitTheyTable[2], checkboxFitTheyTable[3]);

                resultsLabel.setValue("<B>To " + numberOfContract.getValue() + "  rozdanie i uzyskaliście " + scoringTable[contractNumber] + " punktów za kontrakt, czyli " + a.getResults() + " impów. " +
                        " </B>  <BR> W sumie uzyskaliście do tej pory w ostanich rozdaniach " + aa.getSumm() + " impy. ");

                StringBuilder s = new StringBuilder("\n*** Zapis gier numer: " + aa.getGameID() + ".  ***  \n");
                for (int i = 0; i < 4; i++)
                    s.append("\n").append(descriptionTable[i]);

                s.append("\n\n \t \t***\n");
                descriprionOf4play = s.toString() + "\n" + aa.getRubberScoringAsString();

//pyt: cz2 czy tak lepiej - nie ma instance of za to dwa razy catch?
            } catch (BridgeException e) {
                String mes1 = e.getMessage();
                //e.getContractLevel();
                resultsLabel.setValue("<font color=red>" + mes1 + "</font>");
            } catch (NumberFormatException e) {
                String mes1 = "Nieprawidłowy format liczby-  spróbuj jeszcze raz!";
                resultsLabel.setValue("<font color=red>" + mes1 + "</font>");
            }

        });

        Button pokazWyniki = new Button("Pokaz wyniki ostatnich  4 rozdań! ", clickEvent -> {

            actionDisplayResultsOf4GameWindow(this.ui);
            //System.out.println("Akuku"+ descriprionOf4play);

        });

        content.addComponent(calculateImpPoints);
        content.addComponent(prowadzZapis);
        content.addComponent(resultsLabel);
        content.addComponent(pokazWyniki);
        window.setContent(content);
        ui.addWindow(window);
    }

    private void fillExampleData() {
        contractLevelField.setValue("3");
        colorOfContractField.setValue("NT");
        numberOfTricksField.setValue("9");
        pointsInBothHandsField.setValue("25");

    }

    private boolean[] fillAssumption(int contractNumber) {
        boolean[] auctionAssumption = {false, false};
        if (contractNumber == 2) {
            auctionAssumption[0]  = true;
        } else if (contractNumber == 3) {
            auctionAssumption[1] = true;
        } else if (contractNumber == 4) {
            auctionAssumption[0] = true;
            auctionAssumption[1]  = true;
        }
        return auctionAssumption;
    }

    private void actionDisplayResultsOf4GameWindow(VaadinUI ui) {
        final Window window = new Window("Okienko z wynikami ostatnich czterech rozdań.");
        window.setWidth("50%");
        window.setHeight("60%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna - wyniki ostatnich zapisanych rozdań :)"));
        // content.addComponent(new Label("Uwaga na razie nie ma możliwości zapisu edytowanego tekstu"));
        TextArea a = new TextArea();
        a.setWidth("100%");
        a.setHeight("400%");
        a.setValue(descriprionOf4play);
        //a.setEditable(false);

        content.addComponent(a);


        //System.out.println("Akuku"+ descriprionOf4play);
        content.addStyleName("window");
        window.setContent(content);
        ui.addWindow(window);
    }



    private void actionManualCalculetePoints(VaadinUI ui) {
        final Window window = new Window("Stare okienko do ręcznego liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna 1.1- podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));
        content.addStyleName("window");

        content.addComponent(checkboxAssumptionWe);
        content.addComponent(checkboxAssumptionThey);

        TextField pointsInBothHandsField = new TextField("Podaj liczbę punktów na obu swoich rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHandsField);

        content.addComponent(checkboxFitWe);
        content.addComponent(checkboxFitThey);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy jest fit (8+ kart) w dowolnym kolorze, jeżeli mniej punktów to tylko czy jest fit w  starszym kolorze."));

        TextField pointsForContractField = new TextField("Podaj liczbę punktów uzyskanych przy rozgrywaniu kontraktu:");
        content.addComponent(pointsForContractField);

        Label resultsLabel = new Label("");
        resultsLabel.setContentMode(ContentMode.HTML);
        pointsInBothHandsField.addValueChangeListener(event -> resultsLabel.setValue(""));
        pointsForContractField.addValueChangeListener(event -> resultsLabel.setValue(""));



        Button calculateImp = new Button("Oblicz punkty! ", clickEvent -> {
            try {
                float foo = Float.parseFloat(pointsInBothHandsField.getValue());
                int foo2 = Integer.parseInt(pointsForContractField.getValue());
                CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(foo, foo2, checkboxAssumptionWe.getValue(), checkboxAssumptionThey.getValue(), checkboxFitWe.getValue(), checkboxFitThey.getValue());
                resultsLabel.setValue("<B>W tym rozdaniu uzyskaliście " + a.getResults() + " impów (punktów).  </B>  <BR> jeżeli liczba punktów jest ujemna to zapisuje się punkty po stronie przeciwników. ");
            } catch (NumberFormatException | InvalidNumberOfPointsException e) {
                String message = (e instanceof NumberFormatException) ?
                        "Nieprawidłowo podana liczba punktów spróbuj jeszcze raz!" : e.getMessage();
                resultsLabel.setValue("<font color=red>" + message + "</font>");
            } catch (InvalidParameterException e) {
                e.printStackTrace();
            }
        });

        content.addComponent(calculateImp);
        content.addComponent(resultsLabel);
        window.setContent(content);
        ui.addWindow(window);
    }


}
