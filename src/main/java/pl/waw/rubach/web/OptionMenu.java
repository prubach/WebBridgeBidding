package pl.waw.rubach.web;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.waw.rubach.pointsCounting.ResultsOfOneGame;

class OptionMenu extends MenuBar {


    private VaadinUI ui;

    private CheckBox checkbox1 = new CheckBox("Czy jesteście po partii (ustawiane w głównej aplikacji albo tutaj)? ");
    private CheckBox checkbox2 = new CheckBox("Czy  kolor jest sfitowany (domyślnie tak - ustawiane tutaj)?");


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

        HorizontalLayout horlBlue = new HorizontalLayout(legendLabelBlue, legendLabelBlueDark);
        HorizontalLayout horlGreen = new HorizontalLayout(legendLabelGreen, legendLabelGreenDark);
        HorizontalLayout horlYellow = new HorizontalLayout(legendLabelYellow, legendLabelYellowDark);
        HorizontalLayout horlOrange = new HorizontalLayout(legendLabelOrange, legendLabelOrangeDark);
        HorizontalLayout horlRose = new HorizontalLayout(legendLabelRose, legendLabelRoseDark);
        HorizontalLayout horlGrey = new HorizontalLayout(legendLabelGrey, legendLabelGreyDark);

        legendDescription.addComponent(legendTitle);
        legendDescription.addComponent(horlBlue);
        legendDescription.addComponent(horlGreen);
        legendDescription.addComponent(horlYellow);
        legendDescription.addComponent(horlOrange);
        legendDescription.addComponent(horlRose);
        legendDescription.addComponent(horlGrey);


        //	Label silne = new Label("<b> Silne - wytłuszczone (nie działa)</b>", ContentMode.HTML);
        Label sztuczne = new Label("<font color = #0000A0 > Sztuczne - granatowa czcionka, mocniejszy kolor tła (patrz powyżej) </font >", ContentMode.HTML);

        //legendDescription.addComponent(silne);
        legendDescription.addComponent(sztuczne);


        return legendDescription;
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

    private void actionCalculetePoints(VaadinUI ui) {
        final Window window = new Window("Okienko do liczenia punktów.");
        window.setWidth("100%");
        window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(new Label("Wersja wstępna - podaj wszystkie parametry (potem może będzie je brało z innego miejsca) :)"));

        content.addStyleName("window");

        TextField pointsInBothHands = new TextField("Podaj liczbę punktów na obu rękach (wraz z punktami układowymi):");
        content.addComponent(pointsInBothHands);

        TextField pointsForContract = new TextField("Podaj liczbę punktów uzyskanych przy rozgrywaniu kontraktu:");
        content.addComponent(pointsForContract);


        //   checkbox1.setValue(true); //move to create menu?
        checkbox2.setValue(true);

        content.addComponent(checkbox1);
        content.addComponent(checkbox2);
        content.addComponent(new Label("Uwaga: Jeżeli macie PC >30 to czy dowolny kolor jest sfitowany, jeżeli mniej to tylko starszy."));
        //  checkbox1.addValueChangeListener(event ->
        //         checkbox2.setValue(! checkbox1.getValue()));

        Button sayHelloButton = new Button("Oblicz punkty! ", clickEvent -> {

            int foo = Integer.parseInt(pointsInBothHands.getValue());
            int foo2 = Integer.parseInt(pointsForContract.getValue());

            ResultsOfOneGame example = new ResultsOfOneGame(20, 50, true, true);

            ResultsOfOneGame a = new ResultsOfOneGame(foo, foo2, checkbox1.getValue(), checkbox2.getValue());
            int result = a.getResults();

            // Notification.show("W tym rozdaniu w którym mieliście na obu rękach "+ a.getPointsInBothHands()+" i byliście po partii:"+ checkbox1.getValue()+" uzyskaliście " + result + " impów (punktów). ");
            // Notification.show("W tym rozdaniu uzyskaliście " + result + " impów (punktów). ", Notification.Type.ERROR_MESSAGE);

            Notification notif = new Notification("W tym rozdaniu uzyskaliście " + result + " impów (punktów). ");
            notif.show(Page.getCurrent());
            notif.setDelayMsec(-1);
        });

        content.addComponent(sayHelloButton);
        window.setContent(content);
        ui.addWindow(window);

        // sample.getUI().getUI().addWindow(window);
    }

    private MenuBar.Command comandToSetAssumptionNo = (MenuBar.Command) selectedItem -> {checkbox1.setValue(false);
        if (checkbox1.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
        else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command comandToSetAssumptionYes = (MenuBar.Command) selectedItem -> {checkbox1.setValue(true);
        if (checkbox1.getValue()) ui.getAuctionAssumptionLabel().setValue("Założenia: Po parti");
        else ui.getAuctionAssumptionLabel().setValue("Założenia: Przed partią");
    };
    private MenuBar.Command commandToOpenLegend = (MenuBar.Command) selectedItem -> actionOpenWindowWithLegend(ui);
    private MenuBar.Command commandToCalculatePoints = (MenuBar.Command) selectedItem -> actionCalculetePoints(ui);


    OptionMenu(VaadinUI ui) {
        this.ui = ui;
        checkbox1.setValue(false);
        // A top-level menu item that opens a submenu
        MenuBar.MenuItem optionMenuItemsAuctionAssumption = this.addItem("Założenia licytacyjne", FontAwesome.QUESTION_CIRCLE, null);
        //TODO find if it is importatn what oponents are (if yes could be added but dont't think so - should be radioButton or sth to show if it is or not?
        optionMenuItemsAuctionAssumption.addItem("Obie przed partią", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("My przed, oni po", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("Oni przed, my po", null, comandToSetAssumptionYes);
        optionMenuItemsAuctionAssumption.addItem("Obie po partii", null, comandToSetAssumptionYes);

        // Another top-level item
        MenuBar.MenuItem legendMenuItemBidsTypes = this.addItem("Legenda: ", null);
        legendMenuItemBidsTypes.addItem("Opis typów odzywek w okienku - kliknij jak chcesz otworzyć. ", commandToOpenLegend);

        // Yet another top-level item
        MenuBar.MenuItem optionMenuItemOthers = this.addItem("Punkty: ", null, null);
        optionMenuItemOthers.addItem("Zasady obliczania punktów (nie wgrane)", null, null);
        optionMenuItemOthers.addItem("Obliczanie punków za grę w nowym okienku.", null, commandToCalculatePoints);

        // Yet another top-level item with biding system (moved from other menu moved to Vaadin because is to difficult ?)
        //MenuBar.MenuItem otherBidingSystemMenuItem = this.addItem("Inne systemy licytacyjne: ", null, null);
        //  optionMenuBar.setStyleGenerator( -> getRowColorAndStyle(ItemReference));

        this.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        this.addStyleName("firstmenu");


    }
}
