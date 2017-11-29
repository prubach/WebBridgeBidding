package pl.waw.rubach;

import com.vaadin.data.provider.ListDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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

	private Integer curLevel = 0;

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
		HorizontalLayout actions = new HorizontalLayout(filter, backBtn);

		VerticalLayout customerLayout = new VerticalLayout(bidGrid);

		VerticalLayout accountLayout = new VerticalLayout(bidGrid2nd);

		HorizontalLayout grids = new HorizontalLayout(customerLayout, accountLayout);

		VerticalLayout mainLayout = new VerticalLayout(actions, grids);

		setContent(mainLayout);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		bidGrid.setColumns("description", "suitLength", "bidLevel");

		// Add generated full name column
		Grid.Column<String, Bid> levelSuit = bidGrid.addColumn(bid-> ((Bid)bid).getLevel() + " " + ((Bid)bid).getSuit());
		levelSuit.setCaption("Name");
		levelSuit.setId("name");
		Grid.Column<String, Bid> points = bidGrid.addColumn(bid-> ((Bid)bid).getPointsMin() + "-" + ((Bid)bid).getPointsMax());
		points.setCaption("Points");
		points.setId("points");
		bidGrid.setColumnOrder("name", "points", "suitLength", "description", "bidLevel" );


		bidGrid2nd.setColumns("description", "suitLength", "bidLevel");

		// Add generated full name column
		Grid.Column<String, Bid> levelSuit2nd = bidGrid2nd.addColumn(bid-> ((Bid)bid).getLevel() + " " + ((Bid)bid).getSuit());
		levelSuit2nd.setCaption("Name");
		levelSuit2nd.setId("name");
		Grid.Column<String, Bid> points2nd = bidGrid2nd.addColumn(bid-> ((Bid)bid).getPointsMin() + "-" + ((Bid)bid).getPointsMax());
		points2nd.setCaption("Points");
		points2nd.setId("points");
		bidGrid2nd.setColumnOrder("name", "points", "suitLength", "description", "bidLevel" );


		filter.setPlaceholder("Filter by description");
		filter.setVisible(false);

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		//filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		bidGrid.addSelectionListener(e -> {
			if (e.getAllSelectedItems().isEmpty()) {
				//editor.setVisible(false);
				listBids(null);
			}
			else {
				Bid selBid = new ArrayList<Bid>(bidGrid.getSelectedItems()).get(0);
				//editor.editCustomer(selCust);
				listBids2nd(selBid);
			}
		});

		bidGrid2nd.addSelectionListener(e -> {
			if (!e.getAllSelectedItems().isEmpty()) {
				Bid selBid = new ArrayList<Bid>(bidGrid2nd.getSelectedItems()).get(0);

				System.out.println("Bid level: " + selBid.getLevel());
				listBids(selBid.getLevel());
				listBids2nd(selBid);
			}
		});


		// Instantiate and edit new Customer the new button is clicked
		backBtn.addClickListener(e -> {
			listBids(curLevel -1);
			listBids2nd(null);
		});

		// Initialize listing
		listBids(null);
	}

	private void listBids(Integer level) {
		if (level==null) {
			bidGrid.setDataProvider(
					new ListDataProvider<Bid>(bidRepo.findByBidLevel(0)));
		}
		else {
			curLevel = level;
			bidGrid.setDataProvider(new ListDataProvider<Bid>(
					bidRepo.findByBidLevel(level)));
	//		bidGrid.setDataProvider(new ListDataProvider<Bid>(
//					bidRepo.findByParentBid(parentBid)));
		}
	}

	// tag::listCustomers[]
	private void listBids2nd(Bid parentBid) {
		if (parentBid==null) {
			bidGrid2nd.setDataProvider(
					new ListDataProvider<Bid>(bidRepo.findByBidLevel(999)));
		}
		else {
			bidGrid2nd.setDataProvider(new ListDataProvider<Bid>(
					bidRepo.findByParentBid(parentBid)));
		}
	}
}
