package pl.waw.rubach;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import pl.waw.rubach.exceptions.XlsReaderException;

import java.util.Iterator;
import java.util.Map;

public abstract class XlsBridge {

    protected static final String FILE_NAME_IN = "bridgeIn.xlsx";

    protected static final String FILE_NAME_OUT = "bridgeOut.xlsx";

    protected static final String[] header = new String[] { "bidId", "parentBid", "bidLevel","level", "suit","pointsMin", "pointsMax", "suitLength",
            "afterInterven", "shortDesc", "description", "bidType", "bidClass" };

    /**
     * Read a given cell into a field of the new Bid
     *
     * @param currentCell - cell
     * @param columnNum - which column of the Xlsx file is it
     * @param newBid - the Bid into which to read it
     * @param bidMap - the bidMap with existing bids - necessary to add relations between bids
     * @return updated bid
     * @throws XlsReaderException - thrown when a parsing error occurs
     */
    public static Bid cellReader(Cell currentCell, int columnNum, Bid newBid, Map<Integer,Bid> bidMap) throws XlsReaderException {
        switch (columnNum) {
            case 0:
                newBid.setBidID(getInt(currentCell)); break;
            case 1:
                newBid.setParentBid(bidMap.get(getInt(currentCell))); break;
            case 2:
                newBid.setBidLevel(getInt(currentCell)); break;
            case 3:
                newBid.setLevel(getInt(currentCell)); break;
            case 4:
                newBid.setSuit(getStr(currentCell)); break;
            case 5:
                newBid.setPointsMin(getInt(currentCell)); break;
            case 6:
                newBid.setPointsMax(getInt(currentCell)); break;
            case 7:
                newBid.setSuitLength(getStr(currentCell)); break;
            case 8:
                newBid.setAfterInterven(getBool(currentCell)); break;
            case 9:
                newBid.setShortDesc(getStr(currentCell)); break;
            case 10:
                newBid.setDescription(getStr(currentCell)); break;
            case 11:
                newBid.setBidType(getStr(currentCell)); break;
            case 12:
                newBid.setBidClass(getStr(currentCell)); break;
        }
        return newBid;
    }

    /**
     *
     * @param bid to write
     * @return array of objects containing the fields (properties) of the provided bid
     */
    protected static Object[] cellWriter(Bid bid) {
        return new Object[]{
                bid.getBidID(),
                (bid.getParentBid() != null ? bid.getParentBid().getBidID() : null),
                bid.getBidLevel(),
                bid.getLevel(),
                bid.getSuit(),
                bid.getPointsMin(),
                bid.getPointsMax(),
                bid.getSuitLength(),
                bid.isAfterInterven() ? new Integer(1) : new Integer(0),
                bid.getShortDesc(),
                bid.getDescription(),
                bid.getBidType(),
                bid.getBidClass()
        };
    }


    /**
     * Check the header. See if the column names are the same as in the definition (XlsBridge.header)
     *
     * @param row - reference to the row containing the header
     * @return true when header is ok
     * @throws XlsReaderException - thrown when header is not ok
     */
    protected static boolean checkHeader(Row row) throws XlsReaderException {
        Iterator<Cell> cellIterator = row.iterator();

        int i = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (!header[i].equalsIgnoreCase(cell.getStringCellValue())) {
                throw new XlsReaderException("Problem reading Xls File Header at Cell: " + cell.getAddress());
            }
            i++;
        }
        return true;
    }

    /**
     * Read a single numeric cell and convert it to an int, if the cell is a text try to read and parse it
     *
     * @param currentCell
     * @return
     * @throws XlsReaderException
     */
    protected static int getInt(Cell currentCell) throws XlsReaderException {
        Double val;
        try {
            val = currentCell.getNumericCellValue();
            return val.intValue();
        } catch (IllegalStateException e) {
            try {
                val = Double.parseDouble(currentCell.getStringCellValue());
                return val.intValue();
            } catch (IllegalStateException | NumberFormatException ee) {
                throw new XlsReaderException("Problem reading Xls File at Cell: " + currentCell.getAddress()+ "\n" + ee.getMessage());
            }
        }
    }

    /**
     * Read a single text cell and return its content. If the cell is set to numeric try to read it and convert it to text
     *
     * @param currentCell
     * @return
     * @throws XlsReaderException
     */
    protected static String getStr(Cell currentCell) throws XlsReaderException {
        try {
            return currentCell.getStringCellValue();
        } catch (IllegalStateException e) {
            try {
                return Double.toString(currentCell.getNumericCellValue());
            } catch (IllegalStateException | NumberFormatException ee) {
                throw new XlsReaderException("Problem reading Xls File at Cell: " + currentCell.getAddress() + "\n" + ee.getMessage());
            }
        }
    }

    /**
     * Convert a numeric cell (1 or 0) into a boolean
     *
     * @param currentCell
     * @return
     * @throws XlsReaderException
     */
    protected static boolean getBool(Cell currentCell) throws XlsReaderException {
        return getInt(currentCell)==1;
    }

}
