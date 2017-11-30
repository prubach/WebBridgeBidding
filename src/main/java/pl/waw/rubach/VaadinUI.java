package pl.waw.rubach;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import javax.swing.text.html.CSS;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private final BidRepository bidRepo;

	private final BidSystemRepository bidSystemRepo;

	private final Grid bidGrid;

	private final Grid bidGrid2nd;

	private final TextField filter;

	private final Button backBtn;

	private Bid curBid = null;

	private BidSystem curBidSystem = null;

	private Label bidSystemLabel = new Label("");
	private MenuBar bidSystemMenuBar = new MenuBar();
	private Label navigatorLabel = new Label("");
	private Label curBidLabel = new Label("");

	@Autowired
	public VaadinUI(BidRepository bidRepository, BidSystemRepository bidSystemRepo) {
		this.bidRepo = bidRepository;
		this.bidSystemRepo = bidSystemRepo;
		this.bidGrid = new Grid(Bid.class);
		this.bidGrid2nd = new Grid(Bid.class);
		this.filter = new TextField();
		this.backBtn = new Button("Back", FontAwesome.ARROW_CIRCLE_LEFT);
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
		bidGrid.setHeightByRows(8);
		bidGrid2nd.setHeightByRows(8);
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

		bidGrid.setColumns("suitLength"/*, "bidLevel"*/);
		// Add generated full name column
		Grid.Column<String, Bid> levelSuit = bidGrid.addColumn(bid-> getBidLevelSuit((Bid)bid), new HtmlRenderer());
		levelSuit.setCaption("Name");
		levelSuit.setId("name");
		Grid.Column<String, Bid> points = bidGrid.addColumn(bid-> getPointsForBid((Bid)bid));
		points.setCaption("Points");
		points.setId("points");
		Grid.Column<String, Bid> shortDesc = bidGrid.addColumn(bid-> replaceSuitsInDesc(((Bid)bid).getShortDesc()), new HtmlRenderer());
		shortDesc.setCaption("Short Desc");
		shortDesc.setId("shortDesc");
		bidGrid.setColumnOrder("name", "points", "suitLength", "shortDesc" /*, "bidLevel" */);

		bidGrid.getColumn("suitLength").setCaption("# in Suit");
		bidGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

		bidGrid2nd.setColumns("suitLength" /*, "bidLevel"*/);
		bidGrid2nd.getColumn("suitLength").setCaption("# in Suit");
		bidGrid2nd.setSelectionMode(Grid.SelectionMode.SINGLE);
		// Add generated full name column
		Grid.Column<String, Bid> levelSuit2nd = bidGrid2nd.addColumn(bid-> getBidLevelSuit((Bid)bid), new HtmlRenderer());
		levelSuit2nd.setCaption("Name");
		levelSuit2nd.setId("name");
		//levelSuit.set
		Grid.Column<String, Bid> points2nd = bidGrid2nd.addColumn(bid-> getPointsForBid((Bid)bid));
		points2nd.setCaption("Points");
		points2nd.setId("points");
		Grid.Column<String, Bid> shortDesc2nd = bidGrid2nd.addColumn(bid-> replaceSuitsInDesc(((Bid)bid).getShortDesc()), new HtmlRenderer());
		shortDesc2nd.setCaption("Short Desc");
		shortDesc2nd.setId("shortDesc");
		bidGrid2nd.setColumnOrder("name", "points", "suitLength", "shortDesc"/*, "bidLevel"*/ );


		filter.setPlaceholder("Filter by description");
		filter.setVisible(false);

		// Connect selected Customer to editor or hide if none is selected
		bidGrid.addSelectionListener(e -> {
			if (!e.getAllSelectedItems().isEmpty()) {
				Bid selBid = new ArrayList<Bid>(bidGrid.getSelectedItems()).get(0);
				setCurrentBid(selBid);
				listBids2nd(selBid);
			}
		});

		bidGrid2nd.addSelectionListener(e -> {
			if (!e.getAllSelectedItems().isEmpty()) {
				Bid selBid = new ArrayList<Bid>(bidGrid2nd.getSelectedItems()).get(0);
				if (!bidRepo.findByParentBid(selBid).isEmpty()) {
					listBids(selBid);
					listBids2nd(selBid);
				} else {
					setCurrentBid(selBid);
				}
			}
		});

		backBtn.addClickListener(e -> {
			System.out.println("back and curBid: " + curBid);
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

	private void listBids2nd(Bid parentBid) {
		if (parentBid==null) {
			bidGrid2nd.setDataProvider(
					new ListDataProvider<>(bidRepo.findByBidSystemAndBidLevel(curBidSystem,999)));
		}
		else {
			bidGrid2nd.setDataProvider(new ListDataProvider<>(
					bidRepo.findByParentBid(parentBid)));
		}
	}

	private String replaceSuitsInDesc(String desc) {
		if (desc==null) return desc;
		desc = desc.replaceAll("kier ", "<font color=\"red\">\u2665</font color> ");
		desc = desc.replaceAll("karo ", "<font color=\"red\">\u2666</font color> ");
		desc = desc.replaceAll("trefl ", "\u2663 ");
		desc = desc.replaceAll("pik ", "\u2660 ");
		return desc;
	}

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

	private String getBidLevelSuit(Bid bid) {
		if (bid==null || bid.getLevel()==null) return "null";
		return "P".equals(bid.getSuit()) ? "PASS" :
				bid.getLevel() + " " + getBidSuit(bid);
	}

	private String getPointsForBid(Bid bid) {
		if (bid==null) return "";
		return bid.getPointsMin() + (bid.getPointsMax() >= 37 ? "+" : "-" + bid.getPointsMax());
	}

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
