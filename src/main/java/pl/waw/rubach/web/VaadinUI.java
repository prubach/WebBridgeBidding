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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.waw.rubach.BidHelper;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.icons.VaadinIcons.*;

@SpringUI
@Theme("WebBridgeBidding")
public class VaadinUI extends UI {

    private static Logger logger = LoggerFactory.getLogger(VaadinUI.class);
    private final BidRepository bidRepo;
    private final BidSystemRepository bidSystemRepo;
    private final Grid<Bid> bidGrid = new Grid(Bid.class);
    private final Grid<Bid> bidGrid2nd = new Grid(Bid.class);
    private final Button backBtn = new Button("Wróć", ARROW_CIRCLE_LEFT);
    private final Button homeBtn = new Button("Start", HOME);
    private final Button fwdBtn = new Button("Przód", ARROW_CIRCLE_RIGHT);
    private Bid curBid = null;
    private BidSystem curBidSystem = null;
    private Label bidSystemLabel = new Label("");
    private boolean assumption = false;
    private Label auctionAssumptionLabel = new Label("Założenia: Przed partią");
    private Link brydzApkLink = new Link("", new ExternalResource("/brydz.apk"));

    private MenuBar optionMenuBar = new MenuBar();
    private HorizontalLayout legendLabel = new HorizontalLayout();
    private Label navigatorLabel = new Label("");
    private Label curBidLabel = new Label("");

    private HeaderCell leftHeaderCell;
    private HeaderCell rightHeaderCell;
    private List<Bid> bidNaviList = new ArrayList<>();
    private boolean isNaviBack = false;


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
            case "AU":
                style = "rowAU";
                break;
            case "SO":
                style = "rowSO";
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
        Label legendLabelRed = new Label("AU");
        legendLabelRed.addStyleName("redD");
        Label legendLabelGrey = new Label("NF");
        legendLabelGrey.addStyleName("grey");

        //   legendLabel.addComponent(legendTitle);
        legendLabel.addComponent(legendLabelBlue);
        legendLabel.addComponent(legendLabelGreen);
        legendLabel.addComponent(legendLabelYellow);
        legendLabel.addComponent(legendLabelOrange);
        legendLabel.addComponent(legendLabelRose);
        legendLabel.addComponent(legendLabelRed);
        legendLabel.addComponent(legendLabelGrey);
    }



    @Override
    protected void init(VaadinRequest request) {

        // build layout
        navigatorLabel.setContentMode(ContentMode.HTML);
        curBidLabel.setContentMode(ContentMode.HTML);
        curBidLabel.setWidth("100%");
        createLegendLabel();
        optionMenuBar =new OptionMenu(this);
        //layout.addComponent(new Image(null,
         //       new ClassResource("smiley.jpg")));
        brydzApkLink.setIcon(new ThemeResource("androidapp.png"));
        brydzApkLink.setTargetName("_blank");
//		//HorizontalLayout topRightLayout = new HorizontalLayout(bidSystemLabel, optionMenuBar, bidSystemMenuBar);
        HorizontalLayout topRightLayout = new HorizontalLayout(brydzApkLink, legendLabel, auctionAssumptionLabel, bidSystemLabel, optionMenuBar);//, bidSystemMenuBar);

        auctionAssumptionLabel.setStyleName("window");



        topRightLayout.setComponentAlignment(legendLabel, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(bidSystemLabel, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(optionMenuBar, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(auctionAssumptionLabel, Alignment.MIDDLE_LEFT);
        topRightLayout.setComponentAlignment(brydzApkLink, Alignment.MIDDLE_RIGHT);

        //topRightLayout.setComponentAlignment(bidSystemMenuBar,Alignment.MIDDLE_RIGHT);
        //topRightLayout.setExpandRatio(bidSystemMenuBar,1);
      //  topRightLayout.setExpandRatio(optionMenuBar, 1);
        topRightLayout.setExpandRatio(bidSystemLabel, 1);
        topRightLayout.setExpandRatio(legendLabel, 1);
        topRightLayout.setExpandRatio(auctionAssumptionLabel, 1);
        topRightLayout.setWidth("100%");
        fwdBtn.setEnabled(false);
        HorizontalLayout topLayout = new HorizontalLayout(backBtn, homeBtn, fwdBtn, navigatorLabel);
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

        List<BidSystem> bidSystemList = bidSystemRepo.findAll();
        //MenuBar.MenuItem menuItem = new MenuIte
        curBidSystem = bidSystemList.get(0);
        MenuBar.Command menuCommand = selectedItem -> switchBidSystem(bidSystemRepo.findByName(selectedItem.getText()).get(0));
        MenuBar.MenuItem otherBidingSystemMenuItem = optionMenuBar.addItem("Inne systemy licytacyjne: ", null, null);
        for (BidSystem bidSystem : bidSystemList) {
            otherBidingSystemMenuItem.addItem(bidSystem.getName(), menuCommand);

        }

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
                addToBidNaviList(selBid);
                setCurrentBid(selBid);
                listBids2nd(selBid);
            }
        });

        // Define what happens when user clicks on a bid in the right Grid
        bidGrid2nd.addSelectionListener(e -> {
            if (!e.getAllSelectedItems().isEmpty()) {
                Bid selBid = new ArrayList<>(bidGrid2nd.getSelectedItems()).get(0);
                //if (!bidRepo.findByBidSystemAndParentBid(curBidSystem, selBid).isEmpty()) {
                addToBidNaviList(selBid);
                listBids(selBid);
                listBids2nd(selBid);
                /*} else {
                    setCurrentBid(selBid);
                }*/
            }
        });

        // Define behaviour of the Back Button
        backBtn.addClickListener(e -> {
            isNaviBack = true;
            listBids(curBid != null && curBid.getParentBid() != null ? curBid.getParentBid() : null);
            listBids2nd(null);
            isNaviBack = false;
            if (!bidNaviList.isEmpty()) fwdBtn.setEnabled(true);
        });

        fwdBtn.addClickListener(e -> {
            isNaviBack = true;
            logger.debug(bidNaviList.toString());
            int curPos = bidNaviList.indexOf(curBid);
            if (!bidNaviList.isEmpty() && (curBid==null || (curPos>=0 && curPos<bidNaviList.size()-1))) {
                if (curBid==null && !bidNaviList.isEmpty())
                    listBids(bidNaviList.get(0));
                else {
                    logger.debug("SETTING: " + bidNaviList.get(curPos+1));
                    listBids(bidNaviList.get(curPos + 1));
                }
            }
            isNaviBack = false;
        });

        homeBtn.addClickListener(e -> {
            fwdBtn.setEnabled(false);
            bidNaviList.clear();
            listBids(null);
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
        Grid.Column<Bid, String> suitLength = grid.addColumn(bid -> BidHelper.replaceSuitsInDesc(bid.getSuitLength()), new HtmlRenderer());
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
        Grid.Column<Bid, String> shortDesc = grid.addColumn(bid -> BidHelper.replaceSuitsInDesc(bid.getShortDesc()), new HtmlRenderer());
        shortDesc.setCaption("Opis");
        shortDesc.setId("shortDsc");
        grid.setColumns("name", "points", "suits", "shortDsc"/* "bidLevel"*/);
        grid.setColumnOrder("name", "points", "suits", "shortDsc" /*, "bidLevel" */);
        //grid.getColumn("suitLength").setCaption("Układ");
        grid.setStyleGenerator(rowReference -> getRowColorAndStyle(rowReference));
    }

    /**
     * Add extra header to grid and suitGroup columns
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
                    new ListDataProvider<>(assumption ?
                            bidRepo.findByBidSystemAndBidLevelAndAssumptionGreaterThanEqual(curBidSystem, 0, 0)
                            :
                            bidRepo.findByBidSystemAndBidLevelAndAssumptionLessThanEqual(curBidSystem, 0, 0)));
        } else {
            bidGrid.setDataProvider(new ListDataProvider<>(assumption ?
                    bidRepo.findByBidSystemAndParentBidAndAssumptionGreaterThanEqual(curBidSystem, bid.getParentBid(),0)
                    :
                    bidRepo.findByBidSystemAndParentBidAndAssumptionLessThanEqual(curBidSystem, bid.getParentBid(),0)));
            //logger.warn("Selecting bid in bidGrid: " + getBidLevelSuit(bid));
            //fixme Selection doesn't work!!!
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
                    new ListDataProvider<>(assumption ?
                            bidRepo.findByBidSystemAndBidLevelAndAssumptionGreaterThanEqual(curBidSystem, 999, 0)
                            :
                            bidRepo.findByBidSystemAndBidLevelAndAssumptionLessThanEqual(curBidSystem, 999, 0)
                    ));
        } else {
            bidGrid2nd.setDataProvider(
                    new ListDataProvider<>(assumption ?
                            bidRepo.findByBidSystemAndParentBidAndAssumptionGreaterThanEqual(curBidSystem, parentBid,0)
                            :
                            bidRepo.findByBidSystemAndParentBidAndAssumptionLessThanEqual(curBidSystem, parentBid,0)));
        }
    }

    public void refreshBidGrids() {
        listBids(curBid);
        listBids2nd(curBid);
    }


    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bid
     * @return
     */
    private String getBidSuit(Bid bid) {
        if (bid == null) return "";
        return BidHelper.getBidSuit(bid.getSuit());
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
            curBidLabel.setValue(BidHelper.replaceSuitsInDesc(curBid.getDescription()));


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

    private void addToBidNaviList(Bid bid) {
        if (isNaviBack==false && (bidNaviList.isEmpty() || !bid.equals(bidNaviList.get(bidNaviList.size()-1)))) {
            bidNaviList.add(bid);
            logger.debug("adding to naviList: " + bid + "\n" + bidNaviList.toString());
        }
    }

    public Label getAuctionAssumptionLabel() {
        return auctionAssumptionLabel;
    }

    public void setAuctionAssumptionLabel(Label auctionAssumptionLabel) {
        this.auctionAssumptionLabel = auctionAssumptionLabel;
    }

    public boolean isAssumption() {
        return assumption;
    }

    public void setAssumption(boolean assumption) {
        this.assumption = assumption;
    }

}
