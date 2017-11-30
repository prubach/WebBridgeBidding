package pl.waw.rubach;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XlsBridgeWriter extends XlsBridge {

    // Test the procedure
    public static void main(String[] args) {
        Bid bid1C = new Bid(0, 1, "C", 12, 37, "5+", "F", "", false, "Wieloznaczny trefl", "3 znaczenia", null);
        Bid bid1D = new Bid(0, 1, "D", 12, 18, "5", "S", "", false, "Słabe 1 karo", "Słabe 1 karo",null);
        Bid bid1C1D = new Bid(1, 1, "D", 0, 37, "0+", "S", "", false, "Negat na 1 trefl","Negat na 1 trefl", bid1C);
        Bid bid1C1D1H = new Bid(2, 1, "H", 12, 18, "4+", "S", "", false, "Zgłoszenie 4ki kier", "Zgłoszenie 4ki kier", bid1C1D);
        List<Bid> list = Arrays.asList(bid1C,bid1D, bid1C1D, bid1C1D1H);
        writeBridgeBidsToXlsx(new BidSystem("W-J"),list);
    }

    public static void writeBridgeBidsToXlsx(BidSystem bidSystem, List<Bid> bids) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(bidSystem.getName());
        int rowNum = 0;
        System.out.println("Creating excel");

        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (String s : header) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(s);
        }
        
        for (Bid bid: bids) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            Object[] fields = new Object[] { bid.getBidID(), (bid.getParentBid()!=null ? bid.getParentBid().getBidID() : null), bid.getBidLevel(), bid.getLevel(), bid.getSuit(), bid.getPointsMin(),
                    bid.getPointsMax(), bid.getSuitLength(), bid.isAfterInterven() ? new Integer(1) : new Integer(0),
                    bid.getShortDesc(), bid.getDescription(), bid.getBidType(), bid.getBidClass() };
            for (Object field : fields) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Long) {
                    cell.setCellValue((Long) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_OUT);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}
