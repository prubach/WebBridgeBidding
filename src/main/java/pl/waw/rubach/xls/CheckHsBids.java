package pl.waw.rubach.xls;

import org.apache.commons.beanutils.BeanUtils;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CheckHsBids {

    private final BidRepository bidRepo;
    private final BidSystemRepository bidSystemRepo;

    public CheckHsBids(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
        this.bidRepo = bidRepo;
        this.bidSystemRepo = bidSystemRepo;
    }

    private void runOnChildren(Bid parent,Bid parentSibling) throws InvocationTargetException, IllegalAccessException {
        for (Bid bChild : parent.getChildrenBids()) {
            Bid newChild = new Bid();
            bChild = replaceSuit(bChild,"kier");
            BeanUtils.copyProperties(newChild, bChild);
            newChild.setParentBid(parentSibling);
            newChild.setBidID(null);
            newChild = replaceSuit(newChild,"pik");
            bidRepo.save(bChild);
            bidRepo.save(newChild);
            if (!bChild.getChildrenBids().isEmpty())
                runOnChildren(bChild, newChild);
        }
    }

    public void checkHS() throws InvocationTargetException, IllegalAccessException {
        List<Bid> bidsToAdd = new ArrayList<>();
        for (Bid b : bidRepo.findBySuit("HS")) {
            Bid bidS = new Bid();
            BeanUtils.copyProperties(bidS, b);
            bidS.setBidID(null);
            b.setSuit("H");
            b = replaceSuit(b,"kier");
            bidRepo.save(b);
            bidS.setSuit("S");
            bidS = replaceSuit(bidS,"pik");
            bidRepo.save(bidS);

            runOnChildren(b,bidS);
/*
            for (Bid bChild : b.getChildrenBids()) {
                Bid newChild = new Bid();
                bChild = replaceSuit(bChild,"kier");
                BeanUtils.copyProperties(bChild, newChild);
                newChild.setParentBid(bidS);
                newChild = replaceSuit(newChild,"pik");
                bidRepo.save(bChild);
                bidRepo.save(newChild);
            }
*/
        }
    }

    private Bid replaceSuit(Bid b, String suit) {
        if (b.getDescription()!=null)
            b.getDescription().replaceAll("KOLOR", suit);
        b.getShortDesc().replaceAll("KOLOR", suit);
        return b;
    }
}
