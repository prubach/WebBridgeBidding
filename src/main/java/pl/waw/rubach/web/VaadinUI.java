package pl.waw.rubach.web;

import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.icons.VaadinIcons.ARROW_CIRCLE_LEFT;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private final int TABLE_SIZE = 10;

	private Bid curBid = null;

	private BidSystem curBidSystem = null;

	private final BidRepository bidRepo;

	private final BidSystemRepository bidSystemRepo;

	private final Grid<Bid> bidGrid = new Grid(Bid.class);

	private final Grid<Bid> bidGrid2nd = new Grid(Bid.class);

	private final Button backBtn = new Button("Back", ARROW_CIRCLE_LEFT);
	private Label bidSystemLabel = new Label("");
	private MenuBar bidSystemMenuBar = new MenuBar();
	private Label navigatorLabel = new Label("");
	private Label curBidLabel = new Label("");

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
		topVertLayout.setSpacing(false);
		topVertLayout.setMargin(false);
		topVertLayout.setWidth("100%");
		CssLayout actions = new CssLayout(topVertLayout, curBidLabel);
		actions.setWidth("100%");
		Responsive.makeResponsive(actions);

		CssLayout cssLayout = new CssLayout(bidGrid, bidGrid2nd);
		bidGrid.setHeightByRows(TABLE_SIZE);
		bidGrid2nd.setHeightByRows(TABLE_SIZE);
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
		navigatorLabel.setValue("Choose Bid");

		// Define Left Grid Columns
		bidGrid.setColumns("suitLength"/*, "bidLevel"*/);
		Grid.Column<Bid, String> levelSuit = bidGrid.addColumn(bid -> getBidLevelSuit(bid), new HtmlRenderer());
		levelSuit.setCaption("Name");
		levelSuit.setId("name");
		Grid.Column<Bid, String> points = bidGrid.addColumn(bid -> getPointsForBid(bid));
		points.setCaption("Points");
		points.setId("points");
		Grid.Column<Bid, String> shortDesc = bidGrid.addColumn(bid -> replaceSuitsInDesc(bid.getShortDesc()), new HtmlRenderer());
		shortDesc.setCaption("Short Desc");
		shortDesc.setId("shortDesc");
		bidGrid.setColumnOrder("name", "points", "suitLength", "shortDesc" /*, "bidLevel" */);
		bidGrid.getColumn("suitLength").setCaption("# in Suit");
		bidGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

		// Define Right Grid Columns
		bidGrid2nd.setColumns("suitLength" /*, "bidLevel"*/);
		bidGrid2nd.getColumn("suitLength").setCaption("# in Suit");
		bidGrid2nd.setSelectionMode(Grid.SelectionMode.SINGLE);
		Grid.Column<Bid, String> levelSuit2nd = bidGrid2nd.addColumn(bid -> getBidLevelSuit(bid), new HtmlRenderer());
		levelSuit2nd.setCaption("Name");
		levelSuit2nd.setId("name");
		Grid.Column<Bid, String> points2nd = bidGrid2nd.addColumn(bid -> getPointsForBid(bid));
		points2nd.setCaption("Points");
		points2nd.setId("points");
		Grid.Column<Bid, String> shortDesc2nd = bidGrid2nd.addColumn(bid -> replaceSuitsInDesc(bid.getShortDesc()), new HtmlRenderer());
		shortDesc2nd.setCaption("Short Desc");
		shortDesc2nd.setId("shortDesc");
		bidGrid2nd.setColumnOrder("name", "points", "suitLength", "shortDesc"/*, "bidLevel"*/ );

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
				if (!bidRepo.findByParentBid(selBid).isEmpty()) {
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
					bidRepo.findByParentBid(bid.getParentBid())));
			//TODO Selection doesn't work!!!
			bidGrid.asSingleSelect().setValue(bid);
			bidGrid.select(bid);
			bidGrid.markAsDirtyRecursive();
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
					bidRepo.findByParentBid(parentBid)));
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
		desc = desc.replaceAll("trefl ", "\u2663 ");
		desc = desc.replaceAll("pik ", "\u2660 ");
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
			case "C" : return "\u2663";
			case "D" : return "<font color=\"red\">\u2666</font color>";
			case "H" : return "<font color=\"red\">\u2665</font color>";
			case "S" : return "\u2660";
			case "NT" : return "NT";
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
			navigatorLabel.setValue("Choose bid");
			curBidLabel.setValue("");
		}
		else {
			String desc = "";
			Bid tempBid = curBid.getParentBid();
			while (tempBid!=null) {
				desc = getBidLevelSuit(tempBid) + " -> " + desc;
				tempBid = tempBid.getParentBid();
			}
			navigatorLabel.setValue(desc + getBidLevelSuit(curBid));
			curBidLabel.setValue(replaceSuitsInDesc(curBid.getDescription()));
		}
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
