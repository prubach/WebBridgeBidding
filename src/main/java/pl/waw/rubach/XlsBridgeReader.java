package pl.waw.rubach;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.waw.rubach.exceptions.XlsReaderException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class XlsBridgeReader extends XlsBridge {


    private static boolean checkHeader(Row row) throws XlsReaderException {
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

    private static int getInt(Cell currentCell) throws XlsReaderException {
        Double val = null;
        try {
            val = currentCell.getNumericCellValue();
            return Double.valueOf(val).intValue();
        } catch (IllegalStateException e) {
            try {
                val = Double.parseDouble(currentCell.getStringCellValue());
                return Double.valueOf(val).intValue();
            } catch (IllegalStateException | NumberFormatException ee) {
                throw new XlsReaderException("Problem reading Xls File at Cell: " + currentCell.getAddress()+ "\n" + ee.getMessage());
            }
        }
    }

    private static String getStr(Cell currentCell) throws XlsReaderException {
        try {
            return currentCell.getStringCellValue();
        } catch (IllegalStateException e) {
            try {
                return new Double(currentCell.getNumericCellValue()).toString();
            } catch (IllegalStateException | NumberFormatException ee) {
                throw new XlsReaderException("Problem reading Xls File at Cell: " + currentCell.getAddress() + "\n" + ee.getMessage());
            }
        }
    }


    private static boolean getBool(Cell currentCell) throws XlsReaderException {
        return getInt(currentCell)==1;
    }

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
                if (!checkHeader(currentRow)) {
                    System.out.println("Problem reading Header!!!!");
                    return newBids;
                }
                Map<Integer, Bid> bidMap = new HashMap<>();
                while (iterator.hasNext()) {
                    currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    Bid newBid = new Bid();
                    newBid.setBidSystem(bidSystem);
                    int m = 0;
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        switch (m) {
                            case 0:
                                newBid.setBidID(getInt(currentCell));
                                break;
                            case 1:
                                newBid.setParentBid(bidMap.get(getInt(currentCell)));
                                break;
                            case 2:
                                newBid.setBidLevel(getInt(currentCell));
                                break;
                            case 3:
                                newBid.setLevel(getInt(currentCell));
                                break;
                            case 4:
                                newBid.setSuit(getStr(currentCell));
                                break;
                            case 5:
                                newBid.setPointsMin(getInt(currentCell));
                                break;
                            case 6:
                                newBid.setPointsMax(getInt(currentCell));
                                break;
                            case 7:
                                newBid.setSuitLength(getStr(currentCell));
                                break;
                            case 8:
                                newBid.setAfterInterven(getBool(currentCell));
                                break;
                            case 9:
                                newBid.setShortDesc(getStr(currentCell));
                                break;
                            case 10:
                                newBid.setDescription(getStr(currentCell));
                                break;
                            case 11:
                                newBid.setBidType(getStr(currentCell));
                                break;
                            case 12:
                                newBid.setBidClass(getStr(currentCell));
                                break;
                        }
                        m++;
                    }
                    bidMap.put(newBid.getBidID(), newBid);
                    newBids.add(newBid);
                }
               // System.out.println(newBids);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Bid b : newBids) {
            b.setBidID(null);
        }
        //System.out.println(newBids);
        return newBids;
    }

    public static void main(String[] args) throws XlsReaderException {
        readBridgeBidsFromXls();
    }

}
