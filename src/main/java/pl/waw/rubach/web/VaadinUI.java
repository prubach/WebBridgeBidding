package pl.waw.rubach.web;

import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Responsive;
import com.vaadin.server.SerializableComparator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.icons.VaadinIcons.ARROW_CIRCLE_LEFT;

@SpringUI
@Theme("WebBridgeBidding")
public class VaadinUI extends UI {

	private static Logger logger = LoggerFactory.getLogger(VaadinUI.class);

	private final int TABLE_SIZE = 16;

	private Bid curBid = null;

	private BidSystem curBidSystem = null;

	private final BidRepository bidRepo;

	private final BidSystemRepository bidSystemRepo;

	private final Grid<Bid> bidGrid = new Grid(Bid.class);

	private final Grid<Bid> bidGrid2nd = new Grid(Bid.class);

	private final Button backBtn = new Button("Wróć", ARROW_CIRCLE_LEFT);
	private Label bidSystemLabel = new Label("");

	private MenuBar bidSystemMenuBar = new MenuBar();
	private Label navigatorLabel = new Label("");
	private Label curBidLabel = new Label("");

	private HeaderCell leftHeaderCell;
	private HeaderCell rightHeaderCell;

	@Autowired
	public VaadinUI(BidRepository bidRepository, BidSystemRepository bidSystemRepo) {
		this.bidRepo = bidRepository;
		this.bidSystemRepo = bidSystemRepo;
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		navigatorLabel.setContentMode(ContentMode.HTML);
		curBidLabel.setContentMode(ContentMode.HTML);
		curBidLabel.setWidth("100%");
		HorizontalLayout topRightLayout = new HorizontalLayout(bidSystemLabel, bidSystemMenuBar);
		topRightLayout.setComponentAlignment(bidSystemLabel,Alignment.MIDDLE_RIGHT);
		topRightLayout.setComponentAlignment(bidSystemMenuBar,Alignment.MIDDLE_RIGHT);
		topRightLayout.setWidth("100%");
		HorizontalLayout topLayout = new HorizontalLayout(backBtn, navigatorLabel);
		topLayout.setComponentAlignment(navigatorLabel,Alignment.MIDDLE_LEFT);
		VerticalLayout topVertLayout = new VerticalLayout(topRightLayout, topLayout);
		//topVertLayout.setComponentAlignment(bidingPersonLabel,Alignment.BOTTOM_CENTER);
		topVertLayout.setSpacing(false);
		topVertLayout.setMargin(false);
		topVertLayout.setWidth("100%");
		CssLayout actions = new CssLayout(topVertLayout, curBidLabel);
		actions.setWidth("100%");
		Responsive.makeResponsive(actions);

		CssLayout cssLayout = new CssLayout(bidGrid, bidGrid2nd);
		bidGrid.setHeightByRows(TABLE_SIZE);
		//bidGrid.setCaption("Otwarcie");
		bidGrid.setWidth("750px");
		bidGrid2nd.setHeightByRows(TABLE_SIZE);
		bidGrid2nd.setWidth("750px");
		Responsive.makeResponsive(cssLayout);

		VerticalLayout mainLayout = new VerticalLayout(actions, cssLayout);
		//mainLayout.setSizeFull();
		setContent(mainLayout);
		//verticalLayout.setExpandRatio(grid, 1);
		//verticalLayout.setSizeFull();
		// Configure layouts and components
		//actions.setSpacing(true);
		//mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		List<BidSystem> bidSystemList = bidSystemRepo.findAll();
		//MenuBar.MenuItem menuItem = new MenuIte
		curBidSystem = bidSystemList.get(0);
		MenuBar.Command menuCommand = selectedItem -> switchBidSystem(bidSystemRepo.findByName(selectedItem.getText()).get(0));
		for (BidSystem bidSystem : bidSystemList) {
			bidSystemMenuBar.addItem(bidSystem.getName(), menuCommand);
		}
		bidSystemLabel.setValue(curBidSystem.getName());
		navigatorLabel.setValue("Wybierz odzywkę:");
	//	bidingPersonLabel.setValue("Opening");

		// Define Left Grid Columns
		initializeGridLayout(bidGrid);
		leftHeaderCell = prependHeaderCell(bidGrid);
		// Define Right Grid Columns
		initializeGridLayout(bidGrid2nd);
		rightHeaderCell = prependHeaderCell(bidGrid2nd);

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
			listBids(curBid!=null && curBid.getParentBid()!=null ? curBid.getParentBid() : null);
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
		grid.setColumns(/*"suitLength",*/ "bidLevel");
		Grid.Column<Bid, String> suitLength = grid.addColumn(bid -> replaceSuitsInDesc(bid.getSuitLength()), new HtmlRenderer());
		suitLength.setCaption("Układ");
		suitLength.setId("suitLength");

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
		shortDesc.setId("shortDesc");
		grid.setColumnOrder("name", "points", "suitLength", "shortDesc" /*, "bidLevel" */);
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
				groupingHeader.getCell("points"), groupingHeader.getCell("suitLength"), groupingHeader.getCell("shortDesc"));
		groupCell.setStyleName("header-grouped");
		return groupCell;
	}
	
	
	/**
	 * Load bids into the left side Grid
	 * @param bid - bid to load as the currently displayed one
	 */
	private void listBids(Bid bid) {
		setCurrentBid(bid);
		if (bid==null) {
			bidGrid.setDataProvider(
					new ListDataProvider<>(bidRepo.findByBidSystemAndBidLevel(curBidSystem,0)));
		}
		else {
			bidGrid.setDataProvider(new ListDataProvider<>(
					bidRepo.findByBidSystemAndParentBid(curBidSystem, bid.getParentBid())));
			logger.warn("Selecting bid in bidGrid: " + getBidLevelSuit(bid));
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
	 * @param parentBid - bid that is a parent to the ones on the right
	 */
	private void listBids2nd(Bid parentBid) {
		if (parentBid==null) {
			// Load an empty list of bids to the right Grid
			bidGrid2nd.setDataProvider(
					new ListDataProvider<>(bidRepo.findByBidSystemAndBidLevel(curBidSystem,999)));
		}
		else {
			bidGrid2nd.setDataProvider(new ListDataProvider<>(
					bidRepo.findByBidSystemAndParentBid(curBidSystem, parentBid)));
		}
	}

	/**
	 * Automatically replace the text that means Suits in the descriptions with Suit symbols and color them
	 * @param desc
	 * @return
	 */
	private String replaceSuitsInDesc(String desc) {
		if (desc == null) return null;
		desc = desc.replaceAll("kier ", "<font color=\"red\">\u2665</font color> ");
		desc = desc.replaceAll("karo ", "<font color=\"red\">\u2666</font color> ");
		desc = desc.replaceAll("trefl ", "<font color=\"black\">\u2663</font color>");
		desc = desc.replaceAll("pik ", "<font color=\"black\">\u2660</font color>");

		desc = desc.replaceAll("kiery ", "<font color=\"red\">\u2665</font color> ");
		desc = desc.replaceAll("kara ", "<font color=\"red\">\u2666</font color> ");
		desc = desc.replaceAll("trefle ", "<font color=\"black\">\u2663</font color>");
		desc = desc.replaceAll("piki ", "<font color=\"black\">\u2660</font color>");

		desc = desc.replaceAll("SK", "<font color=\"black\">\u2660</font color>");

		return desc;
	}

	/**
	 * Use the Suit symbols instead of letters, add red color to hearts and diamonds
	 * @param bid
	 * @return
	 */
	private String getBidSuit(Bid bid) {
		if (bid==null) return "";
		switch (bid.getSuit()) {
			case "C" : return "<font color=\"black\">\u2663</font color>";
			case "D" : return "<font color=\"red\">\u2666</font color>";
			case "H" : return "<font color=\"red\">\u2665</font color>";
			case "S" : return "<font color=\"black\">\u2660</font color>";
			case "NT" : return "BA";
		}
		return "";
	}

	/**
	 * Present Level + Suit, in case of PASS just say PASS
	 * @param bid
	 * @return
	 */
	private String getBidLevelSuit(Bid bid) {
		return "P".equals(bid.getSuit()) ? "PASS" :
				bid.getLevel() + " " + getBidSuit(bid);
	}

	/**
	 * Present points as 12-17 or 12+ depending on what's the pointsMax
	 * @param bid
	 * @return
	 */
	private String getPointsForBid(Bid bid) {
		return bid.getPointsMin() + (bid.getPointsMax() >= 37 ? "+" : "-" + bid.getPointsMax());
	}

	/**
	 * Set current bid - load its description into the curBidLabel below the Back button
	 * @param bid
	 */
	private void setCurrentBid(Bid bid) {
		curBid = bid;
		if (bid==null) {
			navigatorLabel.setValue("Wybierz odzywkę:");
			//bidGrid.setCaption("Otwarcie - gracz S:");
			leftHeaderCell.setText("Otwarcia - gracz S:");
			rightHeaderCell.setText("");
			curBidLabel.setValue("");
		}
		else {
			String desc = "";
			Bid tempBid = curBid.getParentBid();
			while (tempBid!=null) {
				desc = getBidLevelSuit(tempBid) + " -> " + desc;
				tempBid = tempBid.getParentBid();

			}
			if(curBid.getBidLevel() % 2 == 0) {
				leftHeaderCell.setText("Gracz S (ten który otworzył licytację) :");
				rightHeaderCell.setText("Gracz N (odpowiadający): ");
			}
			else {
				leftHeaderCell.setText("Gracz N (odpowiadający): ");
				rightHeaderCell.setText("Gracz S (ten który otworzył licytację) :");
			}
			navigatorLabel.setValue(desc + getBidLevelSuit(curBid));
			curBidLabel.setValue(replaceSuitsInDesc(curBid.getDescription()));
		}
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
			case "I" : style= "rowI"; break;
			case "FD" : style= "rowFD"; break;
			case "S": style= "rowS"; break;
			case "F1": style= "rowF1"; break;
			case "NF": style= "rowNF"; break;
			case "BL": style= "rowBL"; break;
			default: style="";
		}

		if(bid.getBidClass().equals("Relay")) style = style +"R";

		return style;
	}



	/**
	 * Switch the Bidding System - load the new system only when the button pressed is for a different system than
	 * the one currently active and reinitialize the view
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
