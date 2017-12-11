package pl.waw.rubach.web;

import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.pointsCounting.ResultsOfOneGame;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.icons.VaadinIcons.ARROW_CIRCLE_LEFT;

@SpringUI
@Theme("WebBridgeBidding")
public class VaadinUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(VaadinUI.class);
    private final BidRepository bidRepo;
    private final BidSystemRepository bidSystemRepo;
    private final Grid<Bid> bidGrid = new Grid(Bid.class);
    private final Grid<Bid> bidGrid2nd = new Grid(Bid.class);
    private final Button backBtn = new Button("Wróć", ARROW_CIRCLE_LEFT);
    private Bid curBid = null;
    private BidSystem curBidSystem = null;
    private Label bidSystemLabel = new Label("");

    private Label auctionAssumptionLabel = new Label("");


    private CheckBox checkbox1 = new CheckBox("Czy jesteście po partii (ustawiane w głównej aplikacji albo tutaj)? ");
    private CheckBox checkbox2 = new CheckBox("Czy  kolor jest sfitowany (domyślnie tak - ustawiane tutaj)?");

    private MenuBar bidSystemMenuBar = new MenuBar();

    private MenuBar optionMenuBar = new MenuBar();
    private HorizontalLayout legendLabel = new HorizontalLayout();
    private Label navigatorLabel = new Label("");
    private Label curBidLabel = new Label("");

    private HeaderCell leftHeaderCell;
    private HeaderCell rightHeaderCell;
    private MenuBar.Command comandToSetAssumptionNo = (MenuBar.Command) selectedItem -> checkbox1.setValue(false);
    private MenuBar.Command comandToSetAssumptionYes = (MenuBar.Command) selectedItem -> checkbox1.setValue(true);
    private MenuBar.Command commandToOpenLegend = (MenuBar.Command) selectedItem -> actionOpenWindowWithLegend();
    private MenuBar.Command commandToCalculatePoints = (MenuBar.Command) selectedItem -> actionCalculetePoints();


    @Autowired
    public VaadinUI(BidRepository bidRepository, BidSystemRepository bidSystemRepo) {
        this.bidRepo = bidRepository;
        this.bidSystemRepo = bidSystemRepo;
    }

    /**
     * Return the style name for grid row depending on the bidType
     * Look in webbridgebidding.scss for the mapping of those style names to styles (background and font colors)
     *
     * @param bid bid to analyze for bidType
     * @return style name to add to row
     */
    private static String getRowColorAndStyle(Bid bid) {

        String style;
        switch (bid.getBidType()) {
            case "I":
                style = "rowI";
                break;
            case "FD":
                style = "rowFD";
                break;
            case "S":
                style = "rowS";
                break;
            case "F1":
                style = "rowF1";
                break;
            case "NF":
                style = "rowNF";
                break;
            case "BL":
                style = "rowBL";
                break;
            default:
                style = "";
        }

        if (bid.getBidClass().equals("Relay")) style = style + "R";

        return style;
    }

    private void createLegendLabel() {
        //  Label legendTitle = new Label("Legenda: ");
        Label legendLabelBlue = new Label("FD");
        legendLabelBlue.addStyleName("blue");
        Label legendLabelGreen = new Label("F1");
        legendLabelGreen.addStyleName("green");
        Label legendLabelYellow = new Label("SO");
        legendLabelYellow.addStyleName("yellow");
        Label legendLabelOrange = new Label("Inw.");
        legendLabelOrange.addStyleName("orange");
        Label legendLabelRose = new Label("BL");
        legendLabelRose.addStyleName("rose");
        Label legendLabelGrey = new Label("NF");
        legendLabelGrey.addStyleName("grey");

        //   legendLabel.addComponent(legendTitle);
        legendLabel.addComponent(legendLabelBlue);
        legendLabel.addComponent(legendLabelGreen);
        legendLabel.addComponent(legendLabelYellow);
        legendLabel.addComponent(legendLabelOrange);
        legendLabel.addComponent(legendLabelRose);
        legendLabel.addComponent(legendLabelGrey);

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
        Label sztuczne = new Label("<font color = red> Sztuczne - czerwona czcionka, mocniejszy kolor tła (patrz powyżej) </font >", ContentMode.HTML);

        //legendDescription.addComponent(silne);
        legendDescription.addComponent(sztuczne);


        return legendDescription;
    }

    private void actionOpenWindowWithLegend() {
        final Window window = new Window("Legenda - opis typów odzywek");
        window.setWidth("100%");
        //window.addStyleName("window");
        final FormLayout content = new FormLayout();
        content.setMargin(true);
        content.addComponent(createLegendDescription());
        content.addStyleName("window");
        window.setContent(content);

        addWindow(window);


        // sample.getUI().getUI().addWindow(window);
    }

    private void actionCalculetePoints() {
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
        addWindow(window);


        // sample.getUI().getUI().addWindow(window);
    }

    private void createMenuBar() {

        checkbox1.setValue(false);
        // A top-level menu item that opens a submenu
        MenuBar.MenuItem optionMenuItemsAuctionAssumption = optionMenuBar.addItem("Założenia licytacyjne", FontAwesome.QUESTION_CIRCLE, null);
        //TODO find if it is importatn what oponents are (if yes could be added but dont't think so - should be radioButton or sth to show if it is or not?
        optionMenuItemsAuctionAssumption.addItem("Obie przed partią", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("My przed, oni po", null, comandToSetAssumptionNo);
        optionMenuItemsAuctionAssumption.addItem("Oni przed, my po", null, comandToSetAssumptionYes);
        optionMenuItemsAuctionAssumption.addItem("Obie po partii", null, comandToSetAssumptionYes);

        // Another top-level item
        MenuBar.MenuItem legendMenuItemBidsTypes = optionMenuBar.addItem("Legenda: ", null);
        legendMenuItemBidsTypes.addItem("Opis typów odzywek w okienku - kliknij jak chcesz otworzyć. ", commandToOpenLegend);

        // Yet another top-level item
        MenuBar.MenuItem optionMenuItemOthers = optionMenuBar.addItem("Punkty: ", null, null);
        optionMenuItemOthers.addItem("Zasady obliczania punktów (nie wgrane)", null, null);
        optionMenuItemOthers.addItem("Obliczanie punków za grę w nowym okienku.", null, commandToCalculatePoints);

        // Yet another top-level item with biding system (moved from other menu)
        MenuBar.MenuItem otherBidingSystemMenuItem = optionMenuBar.addItem("Inne systemy licytacyjne: ", null, null);

        List<BidSystem> bidSystemList = bidSystemRepo.findAll();
        //MenuBar.MenuItem menuItem = new MenuIte
        curBidSystem = bidSystemList.get(0);
        MenuBar.Command menuCommand = selectedItem -> switchBidSystem(bidSystemRepo.findByName(selectedItem.getText()).get(0));
        for (BidSystem bidSystem : bidSystemList) {
            otherBidingSystemMenuItem.addItem(bidSystem.getName(), menuCommand);
        }

        //  optionMenuBar.setStyleGenerator( -> getRowColorAndStyle(ItemReference));

        optionMenuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        optionMenuBar.addStyleName("firstmenu");


    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout


        navigatorLabel.setContentMode(ContentMode.HTML);
        curBidLabel.setContentMode(ContentMode.HTML);
        curBidLabel.setWidth("100%");
        createLegendLabel();
        createMenuBar();
//		//HorizontalLayout topRightLayout = new HorizontalLayout(bidSystemLabel, optionMenuBar, bidSystemMenuBar);
        HorizontalLayout topRightLayout = new HorizontalLayout(legendLabel, auctionAssumptionLabel, bidSystemLabel, optionMenuBar);//, bidSystemMenuBar);


        topRightLayout.setComponentAlignment(legendLabel, Alignment.MIDDLE_LEFT);


        topRightLayout.setComponentAlignment(bidSystemLabel, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(optionMenuBar, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(auctionAssumptionLabel, Alignment.MIDDLE_LEFT);

        //topRightLayout.setComponentAlignment(bidSystemMenuBar,Alignment.MIDDLE_RIGHT);
        //topRightLayout.setExpandRatio(bidSystemMenuBar,1);
      //  topRightLayout.setExpandRatio(optionMenuBar, 1);
        topRightLayout.setExpandRatio(bidSystemLabel, 1);
        topRightLayout.setExpandRatio(legendLabel, 1);
        topRightLayout.setExpandRatio(auctionAssumptionLabel, 1);
        topRightLayout.setWidth("100%");
        HorizontalLayout topLayout = new HorizontalLayout(backBtn, navigatorLabel);
        topLayout.setComponentAlignment(navigatorLabel, Alignment.MIDDLE_LEFT);
        VerticalLayout topVertLayout = new VerticalLayout(topRightLayout, topLayout);
        //topVertLayout.setComponentAlignment(bidingPersonLabel,Alignment.BOTTOM_CENTER);
        topVertLayout.setSpacing(false);
        topVertLayout.setMargin(false);
        topVertLayout.setWidth("100%");
        CssLayout actions = new CssLayout(topVertLayout, curBidLabel);
        actions.setWidth("100%");
        Responsive.makeResponsive(actions);

        CssLayout cssLayout = new CssLayout(bidGrid, bidGrid2nd);
        cssLayout.setWidth("100%");
        cssLayout.setHeight("100%");
        bidGrid.setHeightMode(HeightMode.CSS);
        bidGrid.setHeight("100%");
        bidGrid.setWidth("50%");
        bidGrid2nd.setHeightMode(HeightMode.CSS);
        bidGrid2nd.setHeight("100%");
        bidGrid2nd.setWidth("50%");
        bidGrid.setResponsive(true);
        bidGrid2nd.setResponsive(true);

        cssLayout.setResponsive(true);

        VerticalLayout mainLayout = new VerticalLayout(actions, cssLayout);
        mainLayout.setSizeFull();
        setContent(mainLayout);
        mainLayout.setExpandRatio(cssLayout, 1);
        mainLayout.setSpacing(true);

        if (checkbox1.getValue()) auctionAssumptionLabel.setValue("Założenia: Po parti");
        else auctionAssumptionLabel.setValue("Założenia: Przed partią");
        auctionAssumptionLabel.setStyleName("window");

        bidSystemLabel.setValue(curBidSystem.getName());
        bidSystemLabel.addStyleName("title");
        navigatorLabel.setValue("Wybierz odzywkę:");

        // Define Left Grid Columns
        initializeGridLayout(bidGrid);
        leftHeaderCell = prependHeaderCell(bidGrid);
        bidGrid.setStyleName("bidGrid");
        // Define Right Grid Columns
        initializeGridLayout(bidGrid2nd);
        rightHeaderCell = prependHeaderCell(bidGrid2nd);
        bidGrid2nd.setStyleName("bidGrid");

        // Define what happens when user clicks on a bid in the left Grid
        bidGrid.addSelectionListener(e -> {
            if (!e.getAllSelectedItems().isEmpty()) {
                Bid selBid = new ArrayList<>(bidGrid.getSelectedItems()).get(0);
                setCurrentBid(selBid);
                listBids2nd(selBid);
            }
        });

        // Define what happens when user clicks on a bid in the right Grid
        bidGrid2nd.addSelectionListener(e -> {
            if (!e.getAllSelectedItems().isEmpty()) {
                Bid selBid = new ArrayList<>(bidGrid2nd.getSelectedItems()).get(0);
                if (!bidRepo.findByBidSystemAndParentBid(curBidSystem, selBid).isEmpty()) {
                    listBids(selBid);
                    listBids2nd(selBid);
                } else {
                    setCurrentBid(selBid);
                }
            }
        });

        // Define behaviour of the Back Button
        backBtn.addClickListener(e -> {
            listBids(curBid != null && curBid.getParentBid() != null ? curBid.getParentBid() : null);
            listBids2nd(null);
        });

        // Initialize upon startup
        listBids(null);
    }

    /**
     * Setup columns and colorize rows in grid
     *
     * @param grid grid to initialize
     */
    private void initializeGridLayout(Grid<Bid> grid) {
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        Grid.Column<Bid, String> suitLength = grid.addColumn(bid -> replaceSuitsInDesc(bid.getSuitLength()), new HtmlRenderer());
        suitLength.setCaption("Układ");
        suitLength.setId("suits");

        Grid.Column<Bid, String> levelSuit = grid.addColumn(bid -> getBidLevelSuit(bid), new HtmlRenderer());
        levelSuit.setCaption("Nazwa");
        levelSuit.setId("name");
        levelSuit.setComparator(new SerializableComparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                if (!o1.getLevel().equals(o2.getLevel()))
                    return o1.getLevel().compareTo(o2.getLevel());
                if (!o1.getSuit().equals("NT") && !o2.getSuit().equals("NT"))
                    return o1.getSuit().compareTo(o2.getSuit());
                else {
                    if (o1.getSuit().equals(o2.getSuit())) return 0;
                    if (o1.getSuit().equals("NT")) return 1;
                    else return -1;
                }
            }
        });
        Grid.Column<Bid, String> points = grid.addColumn(bid -> getPointsForBid(bid));
        points.setCaption("Punkty");
        points.setId("points");
        Grid.Column<Bid, String> shortDesc = grid.addColumn(bid -> replaceSuitsInDesc(bid.getShortDesc()), new HtmlRenderer());
        shortDesc.setCaption("Opis");
        shortDesc.setId("shortDsc");
        grid.setColumns("name", "points", "suits", "shortDsc"/* "bidLevel"*/);
        grid.setColumnOrder("name", "points", "suits", "shortDsc" /*, "bidLevel" */);
        //grid.getColumn("suitLength").setCaption("Układ");
        grid.setStyleGenerator(rowReference -> getRowColorAndStyle(rowReference));
    }

    /**
     * Add extra header to grid and group columns
     *
     * @param grid to which to add header
     * @return reference to the header Cell
     */
    private HeaderCell prependHeaderCell(Grid grid) {
        HeaderRow groupingHeader = grid.prependHeaderRow();
        HeaderCell groupCell = groupingHeader.join(groupingHeader.getCell("name"),
                groupingHeader.getCell("points"), groupingHeader.getCell("suits"), groupingHeader.getCell("shortDsc"));
        groupCell.setStyleName("header-grouped");
        return groupCell;
    }

    /**
     * Load bids into the left side Grid
     *
     * @param bid - bid to load as the currently displayed one
     */
    private void listBids(Bid bid) {
        setCurrentBid(bid);
        if (bid == null) {
            bidGrid.setDataProvider(
                    new ListDataProvider<>(bidRepo.findByBidSystemAndBidLevel(curBidSystem, 0)));
        } else {
            bidGrid.setDataProvider(new ListDataProvider<>(
                    bidRepo.findByBidSystemAndParentBid(curBidSystem, bid.getParentBid())));
            //logger.warn("Selecting bid in bidGrid: " + getBidLevelSuit(bid));
            //TODO Selection doesn't work!!!
            bidGrid.deselectAll();
            bidGrid.asSingleSelect().setValue(bid);
            bidGrid.select(bid);
            //bidGrid.select(bid.getParentBid());
            //bidGrid.markAsDirtyRecursive();
            bidGrid.sort("name");
        }
    }

    /**
     * Load bids into the right side Grid
     *
     * @param parentBid - bid that is a parent to the ones on the right
     */
    private void listBids2nd(Bid parentBid) {
        if (parentBid == null) {
            // Load an empty list of bids to the right Grid
            bidGrid2nd.setDataProvider(
                    new ListDataProvider<>(bidRepo.findByBidSystemAndBidLevel(curBidSystem, 999)));
        } else {
            bidGrid2nd.setDataProvider(new ListDataProvider<>(
                    bidRepo.findByBidSystemAndParentBid(curBidSystem, parentBid)));
        }
    }

    /**
     * Automatically replace the text that means Suits in the descriptions with Suit symbols and color them
     *
     * @param desc
     * @return
     */
    private String replaceSuitsInDesc(String desc) {
        if (desc == null) return null;
        desc = desc.replaceAll("kier", " <font color=\"red\">\u2665</font color> ");
        desc = desc.replaceAll("karo", " <font color=\"red\">\u2666</font color> ");
        desc = desc.replaceAll("trefl", " <font color=\"black\">\u2663</font color> ");
        desc = desc.replaceAll("pik", " <font color=\"black\">\u2660</font color> ");

        desc = desc.replaceAll("kiery", " <font color=\"red\">\u2665</font color> ");
        desc = desc.replaceAll("kara", " <font color=\"red\">\u2666</font color> ");
        desc = desc.replaceAll("trefle", " <font color=\"black\">\u2663</font color> ");
        desc = desc.replaceAll("piki", " <font color=\"black\">\u2660</font color> ");

        desc = desc.replaceAll("kierów", " <font color=\"red\">\u2665</font color> ");
        //  desc = desc.replaceAll("kar", " <font color=\"red\">\u2666</font color> ");
        desc = desc.replaceAll("trefli", " <font color=\"black\">\u2663</font color> ");
        desc = desc.replaceAll("pików", " <font color=\"black\">\u2660</font color> ");

        //desc = desc.replaceAll("SK", "<font color=\"black\">\u2660</font color>");

        return desc;
    }

    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bid
     * @return
     */
    private String getBidSuit(Bid bid) {
        if (bid == null) return "";
        switch (bid.getSuit()) {
            case "C":
                return "<font color=\"black\">\u2663</font color>";
            case "D":
                return "<font color=\"red\">\u2666</font color>";
            case "H":
                return "<font color=\"red\">\u2665</font color>";
            case "S":
                return "<font color=\"black\">\u2660</font color>";
            case "NT":
                return "BA";
        }
        return "";
    }

    /**
     * Present Level + Suit, in case of PASS just say PASS
     *
     * @param bid
     * @return
     */
    private String getBidLevelSuit(Bid bid) {
        return "P".equals(bid.getSuit()) ? "PAS" :
                bid.getLevel() + " " + getBidSuit(bid);
    }

    /**
     * Present points as 12-17 or 12+ depending on what's the pointsMax
     *
     * @param bid
     * @return
     */
    private String getPointsForBid(Bid bid) {
        return bid.getPointsMin() + (bid.getPointsMax() >= 37 ? "+" : "-" + bid.getPointsMax());
    }

    /**
     * Set current bid - load its description into the curBidLabel below the Back button
     *
     * @param bid
     */
    private void setCurrentBid(Bid bid) {
        curBid = bid;
        if (bid == null) {
            navigatorLabel.setValue("Wybierz odzywkę:");
            //bidGrid.setCaption("Otwarcie - gracz S:");
            leftHeaderCell.setText("Otwarcia - gracz S:");
            rightHeaderCell.setText("");
            curBidLabel.setValue("");
        } else {
            String desc = "";
            Bid tempBid = curBid.getParentBid();
            while (tempBid != null) {
                desc = getBidLevelSuit(tempBid) + " -> " + desc;
                tempBid = tempBid.getParentBid();

            }
            if (curBid.getBidLevel() % 2 == 0) {
                leftHeaderCell.setText("Gracz S (ten który otworzył licytację) :");
                rightHeaderCell.setText("Gracz N (odpowiadający): ");
            } else {
                leftHeaderCell.setText("Gracz N (odpowiadający): ");
                rightHeaderCell.setText("Gracz S (ten który otworzył licytację) :");
            }
            navigatorLabel.setValue(desc + getBidLevelSuit(curBid));
            curBidLabel.setValue(replaceSuitsInDesc(curBid.getDescription()));
        }
    }

    /**
     * Switch the Bidding System - load the new system only when the button pressed is for a different system than
     * the one currently active and reinitialize the view
     *
     * @param newbidSystem - the BidSystem to which to switch
     */
    private void switchBidSystem(BidSystem newbidSystem) {
        if (!curBidSystem.getName().equals(newbidSystem.getName())) {
            curBidSystem = newbidSystem;
            bidSystemLabel.setValue(curBidSystem.getName());
            // Initialize listing
            listBids(null);
            listBids2nd(null);
        }
    }

}
