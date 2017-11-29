package pl.waw.rubach;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import java.util.ArrayList;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private final BidRepository bidRepo;

//	private final CustomerEditor editor;

//	private final AccountEditor accountEditor;

	private final Grid bidGrid;

	private final Grid bidGrid2nd;

	private final TextField filter;

	private final Button backBtn;

	private Bid curBid = null;

	private Integer curLevel = 0;
	private Label curBidLabel = new Label("Choose bid\n\n");

//	private final Button addNewDebitAccountBtn;

//	private final Button addNewSavingsAccountBtn;


	@Autowired
	public VaadinUI(BidRepository bidRepository) {
		this.bidRepo = bidRepository;
		this.bidGrid = new Grid(Bid.class);
		this.bidGrid2nd = new Grid(Bid.class);
//		this.editor = editor;
//		this.accountEditor = accountEditor;
		this.filter = new TextField();
		this.backBtn = new Button("Back", FontAwesome.ARROW_CIRCLE_LEFT);
//		this.addNewDebitAccountBtn = new Button("New debit account", FontAwesome.PLUS);/
//		this.addNewSavingsAccountBtn = new Button("New savings account", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, backBtn, curBidLabel);

		CssLayout cssLayout = new CssLayout(bidGrid, bidGrid2nd);
		Responsive.makeResponsive(cssLayout);

		VerticalLayout mainLayout = new VerticalLayout(actions, cssLayout);

		setContent(mainLayout);
		//verticalLayout.setExpandRatio(grid, 1);
		//verticalLayout.setSizeFull();
		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		bidGrid.setColumns("shortDesc", "suitLength", "bidLevel");

		// Add generated full name column
		Grid.Column<String, Bid> levelSuit = bidGrid.addColumn(bid-> getBidLevelSuit((Bid)bid));
		levelSuit.setCaption("Name");
		levelSuit.setId("name");
		Grid.Column<String, Bid> points = bidGrid.addColumn(bid-> getPointsForBid((Bid)bid));
		points.setCaption("Points");
		points.setId("points");
		bidGrid.setColumnOrder("name", "points", "suitLength", "shortDesc"/*, "bidLevel" */);
		bidGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

		bidGrid2nd.setColumns("shortDesc", "suitLength", "bidLevel");
		bidGrid2nd.setSelectionMode(Grid.SelectionMode.SINGLE);
		// Add generated full name column
		Grid.Column<String, Bid> levelSuit2nd = bidGrid2nd.addColumn(bid-> getBidLevelSuit((Bid)bid));
		levelSuit2nd.setCaption("Name");
		levelSuit2nd.setId("name");
		Grid.Column<String, Bid> points2nd = bidGrid2nd.addColumn(bid-> getPointsForBid((Bid)bid));
		points2nd.setCaption("Points");
		points2nd.setId("points");
		bidGrid2nd.setColumnOrder("name", "points", "suitLength", "shortDesc"/*, "bidLevel"*/ );


		filter.setPlaceholder("Filter by description");
		filter.setVisible(false);

		// Connect selected Customer to editor or hide if none is selected
		bidGrid.addSelectionListener(e -> {
			if (!e.getAllSelectedItems().isEmpty()) {
				Bid selBid = new ArrayList<Bid>(bidGrid.getSelectedItems()).get(0);
				setCurrentBid(selBid);
				//editor.editCustomer(selCust);
				listBids2nd(selBid);
			}
		});

		bidGrid2nd.addSelectionListener(e -> {
			if (!e.getAllSelectedItems().isEmpty()) {
				Bid selBid = new ArrayList<Bid>(bidGrid2nd.getSelectedItems()).get(0);
				listBids(selBid);
				listBids2nd(selBid);
			}
		});

		backBtn.addClickListener(e -> {
			listBids(curBid!=null && curBid.getParentBid()!=null ? curBid.getParentBid() : null);
			listBids2nd(null);
		});

		// Initialize listing
		listBids(null);
	}

	private void listBids(Bid bid) {
		setCurrentBid(bid);
		if (bid==null) {
			bidGrid.setDataProvider(
					new ListDataProvider<>(bidRepo.findByBidLevel(0)));
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

	private void listBids2nd(Bid parentBid) {
		if (parentBid==null) {
			bidGrid2nd.setDataProvider(
					new ListDataProvider<>(bidRepo.findByBidLevel(999)));
		}
		else {
			bidGrid2nd.setDataProvider(new ListDataProvider<>(
					bidRepo.findByParentBid(parentBid)));
		}
	}

	private String getBidLevelSuit(Bid bid) {
		return bid.getSuit().equals("P") ? "PASS" :
				bid.getLevel() + " " + bid.getSuit();
	}
	private String getPointsForBid(Bid bid) {
		return bid.getPointsMin() + (bid.getPointsMax() >= 37 ? "+" : "-" + bid.getPointsMax());
	}
	private void setCurrentBid(Bid bid) {
		curBid = bid;
		if (bid==null) curBidLabel.setValue("Choose bid");
		else {
			String desc = "";
			Bid tempBid = curBid.getParentBid();
			while (tempBid!=null) {
				desc = getBidLevelSuit(tempBid) + " -> " + desc;
				tempBid = tempBid.getParentBid();
			}
			curBidLabel.setValue(desc + getBidLevelSuit(curBid) + "\t" + curBid.getDescription());
		}
	}

}
