package pl.waw.rubach;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XlsBridgeWriter extends XlsBridge {

    private static Logger logger = LoggerFactory.getLogger(XlsBridgeWriter.class);

    /**
     *
     *
     * @param bidSystems
     * @param bidRepo
     */
    public static void writeBridgeBidsToXlsx(List<BidSystem> bidSystems, BidRepository bidRepo) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (BidSystem bidSystem : bidSystems) {
            XSSFSheet sheet = workbook.createSheet(bidSystem.getName());
            int rowNum = 0;
            System.out.println("Creating excel");

            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            // Create a header
            for (String s : header) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(s);
            }
            // For all bids in the Bid System do
            for (Bid bid : bidRepo.findByBidSystem(bidSystem)) {
                row = sheet.createRow(rowNum++);
                colNum = 0;
                for (Object field : cellWriter(bid)) {
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
        }
        logger.info("File created!");
    }

    // Test the procedure
    public static void main(String[] args) {
        Bid bid1C = new Bid(0, 1, "C", 12, 37, "5+", "F", "", false, "Wieloznaczny trefl", "3 znaczenia", null);
        Bid bid1D = new Bid(0, 1, "D", 12, 18, "5", "S", "", false, "Słabe 1 karo", "Słabe 1 karo",null);
        Bid bid1C1D = new Bid(1, 1, "D", 0, 37, "0+", "S", "", false, "Negat na 1 trefl","Negat na 1 trefl", bid1C);
        Bid bid1C1D1H = new Bid(2, 1, "H", 12, 18, "4+", "S", "", false, "Zgłoszenie 4ki kier", "Zgłoszenie 4ki kier", bid1C1D);
        List<Bid> list = Arrays.asList(bid1C,bid1D, bid1C1D, bid1C1D1H);
        //writeBridgeBidsToXlsx(new BidSystem("W-J"),list);
    }

}
