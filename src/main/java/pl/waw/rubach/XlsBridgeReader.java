package pl.waw.rubach;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.exceptions.XlsReaderException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XlsBridgeReader extends XlsBridge {

    private static Logger logger = LoggerFactory.getLogger(XlsBridgeReader.class);

    /**
     * Read the XLSX file containing Bidding Systems into the database.
     * Treats each sheet as a separate bidding system and adds the bids on that sheet to it
     *
     * @return list of all bids (for all bidding systems found in the file
     * @throws XlsReaderException - occurs when there is a problem interpreting the XLSX file:
     *      * the header is not the same as expected
     *      * the cell type (Text/Numeric) is not as expected
     *
     */
    public static List<Bid> readBridgeBidsFromXls() throws XlsReaderException {
        List<Bid> newBids = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_IN));
            Workbook workbook = new XSSFWorkbook(excelFile);

            for (int sheetNum=0;sheetNum<workbook.getNumberOfSheets();sheetNum++) {
                Sheet datatypeSheet = workbook.getSheetAt(sheetNum);
                BidSystem bidSystem = new BidSystem(datatypeSheet.getSheetName());
                Iterator<Row> iterator = datatypeSheet.iterator();
                // Check Head
                Row currentRow = iterator.next();
                checkHeader(currentRow);

                Map<Integer, Bid> bidMap = new HashMap<>();
                while (iterator.hasNext()) {
                    currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    Bid newBid = new Bid();
                    newBid.setBidSystem(bidSystem);
                    int m = 0;
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        cellReader(currentCell, m, newBid, bidMap);
                        m++;
                    }
                    bidMap.put(newBid.getBidID(), newBid);
                    newBids.add(newBid);
                }
            }
        } catch (IOException e) {
            logger.error("Can't load Bridge XLSX file, file not found or not readable: " + new File(FILE_NAME_IN).getAbsolutePath());
        }
        // Remove IDs from the loaded bids so that the database can create their own.
        // This way it is possible to use the same IDs on different Sheets (Bidding Systems) since they will be replaced
        // by the database later
        for (Bid b : newBids) {
            b.setBidID(null);
        }
        return newBids;
    }

    /**
     * Test the XlsBridgeReader
     *
     * @param args
     * @throws XlsReaderException
     */
    public static void main(String[] args) throws XlsReaderException {
        readBridgeBidsFromXls();
    }

}
