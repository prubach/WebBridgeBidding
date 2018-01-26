package pl.waw.rubach.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.xls.exceptions.XlsWriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class XlsBridgeWriter extends XlsBridge {

    private static Logger logger = LoggerFactory.getLogger(XlsBridgeWriter.class);

    /**
     * Write the content of the database into an Xlsx file
     *
     * @param bidSystems - list of bid Systems to export
     * @param bidRepo - bid repository reference to find the bids
     */
    public static void writeBridgeBidsToXlsx(List<BidSystem> bidSystems, BidRepository bidRepo)
            throws XlsWriterException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        logger.info("Exporting data from database to XLSX file");

        for (BidSystem bidSystem : bidSystems) {
            // Create a new sheet for every BidSystem
            XSSFSheet sheet = workbook.createSheet(bidSystem.getName());
            int rowNum = 0;
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
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_OUT);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new XlsWriterException("Can't write to file: " + new File(FILE_NAME_OUT).getAbsolutePath() + " " + e.getMessage());
        }
        logger.info("File created: " + new File(FILE_NAME_OUT).getAbsolutePath());
    }

    // Test the procedure
    public static void main(String[] args) {
        Bid bid1C = new Bid(0, 1, "C", 12, 37, "5+", "F",
                "", 0, "Wieloznaczny trefl", "3 znaczenia", 0, null, null);
        Bid bid1D = new Bid(0, 1, "D", 12, 18, "5", "S", "", 0, "Słabe 1 karo", "Słabe 1 karo", 0, null, null);
        Bid bid1C1D = new Bid(1, 1, "D", 0, 37, "0+", "S", "", 0, "Negat na 1 trefl", "Negat na 1 trefl", 0, bid1C, null);
        Bid bid1C1D1H = new Bid(2, 1, "H", 12, 18, "4+", "S", "", 0, "Zgłoszenie 4ki kier", "Zgłoszenie 4ki kier", 0, bid1C1D, null);
        List<Bid> list = Arrays.asList(bid1C,bid1D, bid1C1D, bid1C1D1H);
        //writeBridgeBidsToXlsx(new BidSystem("W-J"),list);
    }

}
